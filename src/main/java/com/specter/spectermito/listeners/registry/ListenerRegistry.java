package com.specter.spectermito.listeners.registry;

import com.specter.spectermito.SpecterMito;
import com.specter.spectermito.hooks.LegendChatHook;
import com.specter.spectermito.hooks.OpenChatHook;
import com.specter.spectermito.hooks.UltimateChatHook;
import com.specter.spectermito.listeners.PlayerDeathListener;
import com.specter.spectermito.listeners.PlayerJoinListener;
import com.specter.spectermito.listeners.PlayerQuitListener;
import com.sun.javafx.charts.Legend;
import lombok.Data;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;

import java.util.logging.Logger;

@Data(staticConstructor = "of")
public class ListenerRegistry {

    private final SpecterMito plugin;

    public void register() {

        Logger logger = plugin.getLogger();

        try {

            PluginManager pluginManager = Bukkit.getPluginManager();

            pluginManager.registerEvents(new LegendChatHook(),
                    plugin);

            pluginManager.registerEvents(new OpenChatHook(),
                    plugin);

            pluginManager.registerEvents(new UltimateChatHook(),
                    plugin);

            pluginManager.registerEvents(new PlayerJoinListener(),
                    plugin);

            pluginManager.registerEvents(new PlayerQuitListener(),
                    plugin);

            pluginManager.registerEvents(new PlayerDeathListener(),
                    plugin);

        }catch (Throwable t) {
            t.printStackTrace();
            logger.info("[SpecterMito] Não foi possível carregar os eventos!");
        }
    }
}
