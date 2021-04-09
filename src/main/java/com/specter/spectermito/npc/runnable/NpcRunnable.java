package com.specter.spectermito.npc.runnable;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import com.specter.spectermito.SpecterMito;
import com.specter.spectermito.config.ConfigValue;
import com.specter.spectermito.utils.LocationSerealizer;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.npc.NPCRegistry;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;

import java.util.Iterator;
import java.util.UUID;

public class NpcRunnable {

    public void update(SpecterMito specterMito, LocationSerealizer serealizer) {
        if (ConfigValue.get(ConfigValue::mitoLocalNpc) != null && !ConfigValue.get(ConfigValue::mitoLocalNpc).isEmpty()) {
            Location location = serealizer.getDeserializedLocation(ConfigValue.get(ConfigValue::mitoLocalNpc));

            Iterator var5 = CitizensAPI.getNPCRegistry().iterator();

            while(var5.hasNext()) {
                NPC nPC = (NPC)var5.next();
                if (nPC.getId() == 40000) {
                    nPC.despawn();
                }
            }

            HologramsAPI.getHolograms(SpecterMito.getInstance()).forEach(hologram -> {
                hologram.delete();

            });

            if (ConfigValue.get(ConfigValue::mitoLocalNpc) != null) {
                NPCRegistry npcRegistry = CitizensAPI.getNPCRegistry();
                NPC npc = npcRegistry.createNPC(EntityType.PLAYER, UUID.randomUUID(), 40000, "§7");
                String mito = SpecterMito.getInstance().controller.mito;
                if (mito.equals("") || mito.isEmpty()) {
                    mito = "Ninguém";
                }

                Location location1 = location.clone().add(0.0D, ConfigValue.get(ConfigValue::HologramHeight), 0.0D);
                Hologram hologram = HologramsAPI.createHologram(SpecterMito.getInstance(), location1);
                npc.data().set("player-skin-name", SpecterMito.getInstance().controller.mito);
                npc.spawn(location);
                npc.setProtected(true);

                ConfigValue.get(ConfigValue::HologramText).forEach(h -> {
                    hologram.appendTextLine(h.replace("{player}", SpecterMito.getInstance().controller.mito));
                });
            }


        }
    }
}
