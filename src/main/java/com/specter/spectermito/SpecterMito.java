package com.specter.spectermito;

import com.specter.spectermito.commands.registry.CommandRegistry;
import com.specter.spectermito.hooks.registry.HookRegistry;
import com.specter.spectermito.listeners.registry.ListenerRegistry;
import com.specter.spectermito.controller.MitoController;
import com.specter.spectermito.metric.MetricProvider;
import com.specter.spectermito.npc.manager.NpcManager;
import com.specter.spectermito.npc.runnable.NpcRunnable;
import com.specter.spectermito.placeholder.registry.PlaceHolderRegistry;
import com.specter.spectermito.utils.LocationSerealizer;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class SpecterMito extends JavaPlugin {

    public MitoController controller = new MitoController();

    @Override
    public void onEnable() {

        try {

            saveDefaultConfig();
            this.register();


            Bukkit.getConsoleSender().sendMessage("§a[SpecterMito-Lite] Inicialização ocorrida com sucesso!");
        } catch (Throwable t) {
            t.printStackTrace();
            Bukkit.getConsoleSender().sendMessage("§c[SpecterMito-Lite] Não foi possível inicializar o plugin!");
            Bukkit.getPluginManager().disablePlugin(this);

        }

    }

    @Override
    public void onDisable() {

        saveDefaultConfig();
        this.controller.save(this);
    }

    public void register() {

        this.controller.setup(this);
        CommandRegistry.of(this).register();
        HookRegistry.of(this).register();
        ListenerRegistry.of(this).register();
        PlaceHolderRegistry.of(this).register();
        MetricProvider.of(this).setup();
        NpcManager.of().init();

    }


    public static SpecterMito getInstance() {
        return getPlugin(SpecterMito.class);

    }
}
