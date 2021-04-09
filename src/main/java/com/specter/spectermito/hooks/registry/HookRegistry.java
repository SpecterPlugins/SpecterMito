package com.specter.spectermito.hooks.registry;

import com.specter.spectermito.SpecterMito;
import com.specter.spectermito.hooks.LegendChatHook;
import com.specter.spectermito.hooks.OpenChatHook;
import com.specter.spectermito.hooks.UltimateChatHook;
import lombok.Data;
import org.bukkit.Bukkit;

@Data(staticConstructor = "of")
public class HookRegistry {

    private final SpecterMito plugin;

    public void register() {
        if (Bukkit.getPluginManager().getPlugin("OpeNChat") != null) {
            new OpenChatHook();
            plugin.getLogger().info("[SpecterMito] OpenChat encontrado, metodos inicializados!");
        }else if (Bukkit.getPluginManager().getPlugin("UltimateChat") != null) {
            new UltimateChatHook();
            plugin.getLogger().info("[SpecterMito] UltimateChat encontrado, metodos inicializados!");
        }else if (Bukkit.getPluginManager().getPlugin("Legendchat") != null) {
            new LegendChatHook();
            plugin.getLogger().info("[SpecterMito] LegendChat encontrado, metodos inicializados!");
        }else
            plugin.getLogger().info("[SpecterMito] Nenhum plugin de chat, desligando metodos de TAG!");
    }
}
