package com.specter.spectermito.metric;

import com.specter.spectermito.SpecterMito;
import lombok.Data;
import org.bstats.bukkit.Metrics;

@Data(staticConstructor = "of")
public final class MetricProvider {

    private final SpecterMito plugin;

    private final int PLUGIN_ID = 10991;

    public void setup() {
        new Metrics(plugin, PLUGIN_ID);
    }
}
