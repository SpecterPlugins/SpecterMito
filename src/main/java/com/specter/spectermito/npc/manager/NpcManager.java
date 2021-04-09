package com.specter.spectermito.npc.manager;


import com.specter.spectermito.SpecterMito;
import com.specter.spectermito.npc.runnable.NpcRunnable;
import com.specter.spectermito.utils.LocationSerealizer;
import lombok.Data;
import org.bukkit.Bukkit;

@Data(staticConstructor = "of")
public class NpcManager {

    protected final SpecterMito plugin = SpecterMito.getInstance();


    public void init() {

        Bukkit.getScheduler().runTaskLater(plugin, () -> {
            (new NpcRunnable()).update((SpecterMito) plugin, new LocationSerealizer());
        }, 20L);
    }
}
