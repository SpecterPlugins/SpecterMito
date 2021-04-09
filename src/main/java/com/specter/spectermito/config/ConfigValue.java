package com.specter.spectermito.config;

import com.specter.spectermito.SpecterMito;
import com.specter.spectermito.utils.ColorUtil;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.ConfigurationSection;

import java.util.List;
import java.util.function.Function;


@Getter
@Accessors(fluent = true)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ConfigValue {

    private static final ConfigValue instance = new ConfigValue();

    private final Configuration configuration = SpecterMito.getInstance().getConfig();

    //Configuration

    private final String mitoActually = configuration.getString("Mito_Atual");
    private final String mitoTag = ColorUtil.colored(configuration.getString("Tag"));

    private final String mitoInexistent = ColorUtil.colored(configuration.getString("messages.MitoInexistent"));
    private final String mitoExistent = ColorUtil.colored(configuration.getString("messages.MitoActually"));
    private final String argsSetIncorret = ColorUtil.colored(configuration.getString("messages.argsSetIncorret"));
    private final String noPermission = ColorUtil.colored(configuration.getString("messages.no-permission"));
    private final String minimumPlayersInvalid = ColorUtil.colored(configuration.getString("messages.minimumPlayersInvalid"));
    private final String titleMessage = ColorUtil.colored(configuration.getString("messages.Title.Message"));
    private final String actionBarMitoSet = ColorUtil.colored(configuration.getString("messages.Action-Bar.Message-NewMito"));
    private final String actionBarMitoKilled = ColorUtil.colored(configuration.getString("messages.Action-Bar.Message-Killed"));
    private final String playerInvalid = ColorUtil.colored(configuration.getString("messages.player-invalid"));
    private final String playerAlreadyMito = ColorUtil.colored(configuration.getString("messages.already-mito"));
    private final String reloaded = ColorUtil.colored(configuration.getString("messages.reloaded"));
    private final String mitoLocalNpc = configuration.getString("Mito_Local");

    private final ConfigurationSection events = configuration.getConfigurationSection("Events");
    private final Boolean eventsBroadCast = events.getBoolean("Broadcast.Active");
    private final Boolean eventsActionBar = events.getBoolean("Action-Bar.Active");
    private final List<String> eventsBroadCastMessageJoin = ColorUtil.colored(events.getStringList("Broadcast.Message_Join"));
    private final List<String> eventsBroadCastMessageExited = ColorUtil.colored(events.getStringList("Broadcast.Message_Exited"));
    private final String eventsActionBarMessageJoin = ColorUtil.colored(events.getString("Action-Bar.Message_Join"));
    private final String eventsActionBarMessageExited = ColorUtil.colored(events.getString("Action-Bar.Message_Exited"));

    private final ConfigurationSection npc = configuration.getConfigurationSection("NPC");
    private final String npcSpawned = ColorUtil.colored(npc.getString("Spawned"));
    private final String npcRemoved = ColorUtil.colored(npc.getString("Removed"));
    private final String notSeted = "§cNPC Ainda não foi setado";
    private final Double HologramHeight = npc.getDouble("Hologram.Height");
    private final List<String> HologramText = ColorUtil.colored(npc.getStringList("Hologram.Holo"));

    private final int minimumPlayers = configuration.getInt("Config.minimumPlayersRandom");
    private final int spawnBatsQuantity = configuration.getInt("Config.spawnBats.Quantity");
    private final int rayQuantity = configuration.getInt("Config.Ray.Quantity");
    private final int minimumDeathsNecessaryMitoRandom = configuration.getInt("Config.QuantityNecessaryMitoDeath");
    private final Boolean broadcastActive = configuration.getBoolean("messages.Broadcast.Active");
    private final Boolean titleActive = configuration.getBoolean("messages.Title.Active");
    private final Boolean actiobarActive = configuration.getBoolean("messages.Action-Bar.Active");
    private final Boolean Ray = configuration.getBoolean("Config.Ray.Active");
    private final Boolean spawnBats = configuration.getBoolean("Config.spawnBats.Active");

    private final List<String> broadcastMessageKilled = ColorUtil.colored(configuration.getStringList("messages.Broadcast.Message-Killed"));
    private final List<String> broadcastMessageSet = ColorUtil.colored(configuration.getStringList("messages.Broadcast.Message-Set"));
    public static <T> T get(Function<ConfigValue, T> supplier) {
        return supplier.apply(ConfigValue.instance);

    }


}
