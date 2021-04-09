package com.specter.spectermito.listeners;

import com.specter.spectermito.SpecterMito;
import com.specter.spectermito.config.ConfigValue;
import com.specter.spectermito.utils.ActionBar;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player p = e.getPlayer();

        if (SpecterMito.getInstance().controller.mito.equalsIgnoreCase(p.getName())) {

            if (ConfigValue.get(ConfigValue::eventsBroadCast)) {

                ConfigValue.get(ConfigValue::eventsBroadCastMessageExited).forEach(message -> {
                    Bukkit.broadcastMessage(message
                            .replace("{player}", SpecterMito.getInstance().controller.mito));
                });
            }

            if (ConfigValue.get(ConfigValue::eventsActionBar)) {

                Bukkit.getOnlinePlayers().forEach(onlineplayers -> {
                    ActionBar.EnviarActionbar(onlineplayers, ConfigValue.get(ConfigValue::eventsActionBarMessageExited)
                            .replace("{player}", SpecterMito.getInstance().controller.mito));
                });
            }
        }
    }
}
