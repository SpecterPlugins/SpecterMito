package com.specter.spectermito.commands.registry;

import com.specter.spectermito.SpecterMito;
import com.specter.spectermito.commands.MitoCommand;
import lombok.Data;
import org.bukkit.command.PluginCommand;

import java.util.logging.Logger;

@Data(staticConstructor = "of")
public class CommandRegistry {

    private final SpecterMito plugin;

    public void register() {

        Logger logger = plugin.getLogger();
        try {

            PluginCommand mito = plugin.getCommand("mito");
            if (mito != null) mito.setExecutor(new MitoCommand());

        }catch (Throwable t) {
            t.printStackTrace();
            logger.info("[SpecterMito] Não foi possível carregar os comandos!");
        }
    }
}
