package com.specter.spectermito.hooks;

import com.nickuc.chat.api.events.PublicMessageEvent;
import com.specter.spectermito.SpecterMito;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;


public class OpenChatHook implements Listener {

    @EventHandler
    public void onChat(PublicMessageEvent e) {
        Player p = e.getSender();
        if (SpecterMito.getInstance().controller.mito.equalsIgnoreCase(p.getName())) {
            TextComponent tag = new TextComponent(SpecterMito.getInstance().controller.tag);
            e.setTag("spectermito", tag);
        }
    }
}
