package com.specter.spectermito.controller;

import com.specter.spectermito.SpecterMito;
import com.specter.spectermito.config.ConfigValue;

public class MitoController {
    public String mito;
    public String tag;

    public void setup(SpecterMito plugin) {

        MitoController controller = plugin.controller;

        controller.mito = ConfigValue.get(ConfigValue::mitoActually);
        controller.tag = ConfigValue.get(ConfigValue::mitoTag);

    }

    public void save(SpecterMito plugin) {

        MitoController controller = plugin.controller;
        plugin.reloadConfig();
        plugin.getConfig().set("Mito_Atual", controller.mito);
        plugin.saveConfig();

    }
}
