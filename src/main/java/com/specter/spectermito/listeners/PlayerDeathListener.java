package com.specter.spectermito.listeners;

import com.specter.spectermito.SpecterMito;
import com.specter.spectermito.config.ConfigValue;
import com.specter.spectermito.npc.runnable.NpcRunnable;
import com.specter.spectermito.utils.ActionBar;
import com.specter.spectermito.utils.LocationSerealizer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import java.util.HashMap;

public class PlayerDeathListener implements Listener {

    public static HashMap<String, Integer> deaths = new HashMap<>();

    @EventHandler(
            priority = EventPriority.HIGHEST
    )

    public void onDeathNoPlayer(EntityDeathEvent e) {
        if (e.getEntity() instanceof Player) {
            Player death = (Player) e.getEntity();

            if (SpecterMito.getInstance().controller.mito.equalsIgnoreCase(death.getName())) {
                if (deaths.containsKey(death.getName())) {
                    deaths.replace(death.getName(), (Integer) deaths.get(death.getName()) + 1);
                    if ((Integer) deaths.get(death.getName()) >= ConfigValue.get(ConfigValue::minimumDeathsNecessaryMitoRandom)) {

                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "mito setar random");
                        deaths.clear();
                    }
                }else {
                    deaths.put(death.getName(), 1);
                }
            }
        }
    }

    @EventHandler
    public void onDeathPlayer(EntityDeathEvent e) {
        if (e.getEntity() instanceof Player) {
            if (e.getEntity().getKiller() instanceof Player) {
                Player morreu = (Player) e.getEntity();
                Player matou = e.getEntity().getKiller();
                if (SpecterMito.getInstance().controller.mito.equalsIgnoreCase(morreu.getName())) {
                    SpecterMito.getInstance().controller.mito = matou.getName();
                    sendMessage(SpecterMito.getInstance(), matou.getName(), morreu.getName());

                    if (ConfigValue.get(ConfigValue::Ray)) {

                        for (int i = 0; i < ConfigValue.get(ConfigValue::rayQuantity); ++i) {

                            matou.getWorld().strikeLightning(matou.getLocation());

                        }

                    }

                    if (ConfigValue.get(ConfigValue::spawnBats)) {

                        for (int i = 0; i < ConfigValue.get(ConfigValue::spawnBatsQuantity); ++i) {

                            matou.getWorld().spawnEntity(matou.getLocation(), EntityType.BAT);


                        }
                    }
                    deaths.clear();
                }
            }
        }
    }

    protected void sendMessage(SpecterMito main, String name, String killed) {

        if (ConfigValue.get(ConfigValue::broadcastActive)) {

            ConfigValue.get(ConfigValue::broadcastMessageKilled).forEach(broadcastMessage -> {

                Bukkit.broadcastMessage(broadcastMessage
                        .replace("{player}", name)
                        .replace("{killed}", killed));

            });

        }

        if (ConfigValue.get(ConfigValue::titleActive)) {

            String title = ConfigValue.get(ConfigValue::titleMessage).split("<nl>")[0];
            String subtitle = ConfigValue.get(ConfigValue::titleMessage).split("<nl>")[1];

            Bukkit.getOnlinePlayers().forEach(onlineplayers -> {

                onlineplayers.sendTitle(

                        title
                                .replace("{player}", name),

                        subtitle
                                .replace("{player}", name)

                );

            });
        }

        if (ConfigValue.get(ConfigValue::actiobarActive)) {

            Bukkit.getOnlinePlayers().forEach(onlineplayers -> {

                ActionBar.EnviarActionbar(onlineplayers, ConfigValue.get(ConfigValue::actionBarMitoKilled)
                        .replace("{player}", name)
                        .replace("{killed}", killed));

            });
        }

        (new NpcRunnable()).update((SpecterMito) SpecterMito.getPlugin(SpecterMito.class), new LocationSerealizer());
    }
}
