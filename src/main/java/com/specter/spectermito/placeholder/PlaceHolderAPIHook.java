package com.specter.spectermito.placeholder;

import com.specter.spectermito.SpecterMito;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;

public class PlaceHolderAPIHook extends PlaceholderExpansion {

    public boolean canRegister() {
        return true;
    }

    public String getAuthor() {
        return "MisterSevent_";

    }

    public String getIdentifier() {
        return "SpecterMito-Lite";

    }

    public String getVersion() {
        return SpecterMito.getInstance().getDescription().getVersion();
    }

    public String onRequest(OfflinePlayer player, String identifier) {
        if (identifier.equals("spectermito")) {
            return SpecterMito.getInstance().controller.mito;

        } else if (!identifier.equals("spectermito_tag")) {
            return null;
        }else {
            return SpecterMito.getInstance().controller.mito != null && SpecterMito.getInstance().controller.mito.equals(player.getName()) ? SpecterMito.getInstance().controller.tag : "";
        }
    }
}
