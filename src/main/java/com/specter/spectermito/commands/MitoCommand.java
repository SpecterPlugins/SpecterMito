package com.specter.spectermito.commands;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import com.specter.spectermito.SpecterMito;
import com.specter.spectermito.config.ConfigValue;
import com.specter.spectermito.npc.runnable.NpcRunnable;
import com.specter.spectermito.utils.ActionBar;
import com.specter.spectermito.utils.LocationSerealizer;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.npc.NPCRegistry;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

public class MitoCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String lb, String[] args) {

        if (args.length == 0) {

            if (SpecterMito.getInstance().controller.mito.isEmpty() && SpecterMito.getInstance().controller.mito.equalsIgnoreCase("")) {

                sender.sendMessage(ConfigValue.get(ConfigValue::mitoInexistent));
                return false;

            }
//e
            sender.sendMessage(ConfigValue.get(ConfigValue::mitoExistent)
                    .replace("{player}", SpecterMito.getInstance().controller.mito));

            return true;

        }

                if (args[0].equalsIgnoreCase("set") || args[0].equalsIgnoreCase("setar")) {

                    if (!sender.hasPermission("spectermito.admin")) {

                        sender.sendMessage(ConfigValue.get(ConfigValue::noPermission));
                        return false;

                    }

                    if (args.length != 2) {

                        sender.sendMessage(ConfigValue.get(ConfigValue::argsSetIncorret));
                        return false;

                    }

                    String type = args[1];
                    Player original;

                    if (type.equalsIgnoreCase("random")) {

                        int onlineplayers = Bukkit.getOnlinePlayers().size();

                        if (ConfigValue.get(ConfigValue::minimumPlayers) > onlineplayers) {

                            sender.sendMessage(ConfigValue.get(ConfigValue::minimumPlayersInvalid));
                            return false;

                        }

                        onlineplayers = (new Random()).nextInt(Bukkit.getOnlinePlayers().size());
                        ArrayList<Player> players = new ArrayList<>();
                        players.addAll(Bukkit.getOnlinePlayers());
                        original = (Player) players.get(onlineplayers);
                        String originalWinner = original.getName();

                        SpecterMito.getInstance().controller.mito = originalWinner;

                        sendMessage(SpecterMito.getInstance(), originalWinner);

                        if (ConfigValue.get(ConfigValue::Ray)) {

                            for (int i = 0; i < ConfigValue.get(ConfigValue::rayQuantity); ++i) {

                                original.getWorld().strikeLightning(original.getLocation());

                            }

                        }

                        if (ConfigValue.get(ConfigValue::spawnBats)) {

                            for (int i = 0; i < ConfigValue.get(ConfigValue::spawnBatsQuantity); ++i) {

                                original.getWorld().spawnEntity(original.getLocation(), EntityType.BAT);

                            }

                        }


                    } else {

                        original = Bukkit.getPlayerExact(args[1]);

                        if (original == null) {

                            sender.sendMessage(ConfigValue.get(ConfigValue::playerInvalid));
                            return false;

                        }

                        String originalWinner = original.getName();

                        SpecterMito.getInstance().controller.mito = originalWinner;

                        sendMessage(SpecterMito.getInstance(), originalWinner);

                        if (ConfigValue.get(ConfigValue::Ray)) {

                            for (int i = 0; i < ConfigValue.get(ConfigValue::rayQuantity); ++i) {

                                original.getWorld().strikeLightning(original.getLocation());

                            }

                        }

                        if (ConfigValue.get(ConfigValue::spawnBats)) {

                            for (int i = 0; i < ConfigValue.get(ConfigValue::spawnBatsQuantity); ++i) {

                                original.getWorld().spawnEntity(original.getLocation(), EntityType.BAT);


                        }

                    }

                }
        }

        if (args[0].equalsIgnoreCase("setnpc")) {

            if (!sender.hasPermission("spectermito.admin")) {

                sender.sendMessage(ConfigValue.get(ConfigValue::noPermission));
                return false;

            }

            if (sender instanceof Player) {
                Player p = (Player) sender;
                LocationSerealizer serealizer = new LocationSerealizer();
                if (ConfigValue.get(ConfigValue::mitoLocalNpc) != null && CitizensAPI.getNPCRegistry().getById(40000) != null && CitizensAPI.getNPCRegistry().getById(40000).isSpawned()) {
                    CitizensAPI.getNPCRegistry().getById(40000).destroy();
                    HologramsAPI.getHolograms(SpecterMito.getInstance()).forEach(h -> {
                        h.delete();
                    });
                }

                Location loc = p.getLocation();
                String mito = SpecterMito.getInstance().controller.mito;
                if (mito.equals("") || mito.isEmpty()) {
                    mito = "Ninguém";
                }

                NPCRegistry npcRegistry = CitizensAPI.getNPCRegistry();
                NPC npc = npcRegistry.createNPC(EntityType.PLAYER, UUID.randomUUID(), 40000, "§7");
                Location location2 = loc.clone().add(0.0D, ConfigValue.get(ConfigValue::HologramHeight), 0.0D);
                Hologram hologram = HologramsAPI.createHologram(SpecterMito.getInstance(), location2);
                npc.data().set("player-skin-name", SpecterMito.getInstance().controller.mito);
                npc.spawn(loc);
                npc.setProtected(true);

                String finalMito = mito;
                ConfigValue.get(ConfigValue::HologramText).forEach(h -> {
                    hologram.appendTextLine(h.replace("{player}", finalMito));
                });

                sender.sendMessage(ConfigValue.get(ConfigValue::npcSpawned));
                SpecterMito.getInstance().getConfig().set("Mito_Local", serealizer.getSerializedLocation(p.getLocation()));
                SpecterMito.getInstance().saveConfig();
                SpecterMito.getInstance().reloadConfig();
            }
        }

        if (args[0].equalsIgnoreCase("delnpc")) {

            if (!sender.hasPermission("spectermito.admin")) {

                sender.sendMessage(ConfigValue.get(ConfigValue::noPermission));
                return false;

            }

            if (ConfigValue.get(ConfigValue::mitoLocalNpc) != null && CitizensAPI.getNPCRegistry().getById(40000) != null && CitizensAPI.getNPCRegistry().getById(40000).isSpawned()) {
                CitizensAPI.getNPCRegistry().getById(40000).destroy();
                HologramsAPI.getHolograms(SpecterMito.getInstance()).forEach(h -> {
                    h.delete();
                });

                SpecterMito.getInstance().getConfig().set(ConfigValue.get(ConfigValue::mitoLocalNpc), null);
                SpecterMito.getInstance().saveConfig();
                sender.sendMessage(ConfigValue.get(ConfigValue::npcRemoved));
            }else {
                sender.sendMessage(ConfigValue.get(ConfigValue::notSeted));
            }
        }

        return false;
    }

    protected void sendMessage(SpecterMito main, String name) {

        if (ConfigValue.get(ConfigValue::broadcastActive)) {

            ConfigValue.get(ConfigValue::broadcastMessageSet).forEach(broadcastMessage -> {

                Bukkit.broadcastMessage(broadcastMessage
                        .replace("{player}", name));

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

                ActionBar.EnviarActionbar(onlineplayers, ConfigValue.get(ConfigValue::actionBarMitoSet)
                        .replace("{player}", name));

            });
        }

        (new NpcRunnable()).update((SpecterMito) SpecterMito.getPlugin(SpecterMito.class), new LocationSerealizer());
    }
}
