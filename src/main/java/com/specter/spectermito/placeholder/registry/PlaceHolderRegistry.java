package com.specter.spectermito.placeholder.registry;

import com.specter.spectermito.SpecterMito;
import com.specter.spectermito.placeholder.PlaceHolderAPIHook;
import lombok.Data;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;

@Data(staticConstructor = "of")
public class PlaceHolderRegistry {

    private final SpecterMito plugin;

    private static final PluginManager MANAGER = Bukkit.getPluginManager();
    private static final String PLACEHOLDERS_API = "PlaceholderAPI";

    public void register() {
        if (!MANAGER.isPluginEnabled(PLACEHOLDERS_API)) {
            plugin.getLogger().warning(
                    String.format("Depedência não encontrada (%s). A placeholder não pode ser registrada",
                            PLACEHOLDERS_API
                    )
            );
            return;
        }

        new PlaceHolderAPIHook().register();
        plugin.getLogger().info("PlaceHolder registrada com sucesso");
    }
}
