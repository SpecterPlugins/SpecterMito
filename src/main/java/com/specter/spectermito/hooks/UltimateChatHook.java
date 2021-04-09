package com.specter.spectermito.hooks;

import br.net.fabiozumbi12.UltimateChat.Bukkit.API.SendChannelMessageEvent;
import com.specter.spectermito.SpecterMito;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class UltimateChatHook implements Listener {

    @EventHandler
    public void onChat(SendChannelMessageEvent e) {
        Player p = (Player) e.getSender();
        if (SpecterMito.getInstance().controller.mito.equalsIgnoreCase(p.getName())) {
            e.addTag("spectermito", SpecterMito.getInstance().controller.tag);
        }
    }
}
