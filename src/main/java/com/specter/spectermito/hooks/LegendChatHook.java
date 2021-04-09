package com.specter.spectermito.hooks;

import br.com.devpaulo.legendchat.api.events.ChatMessageEvent;
import com.specter.spectermito.SpecterMito;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class LegendChatHook implements Listener {

    @EventHandler
    public void onChat(ChatMessageEvent e) {
        Player p = e.getSender();

        if (e.getTags().contains("spectermito") && SpecterMito.getInstance().controller.mito.equalsIgnoreCase(p.getName())) {
            e.setTagValue("spectermito", SpecterMito.getInstance().controller.tag);

        }
    }
}
