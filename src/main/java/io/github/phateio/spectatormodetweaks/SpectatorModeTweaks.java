package io.github.phateio.spectatormodetweaks;

import org.bukkit.GameMode;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class SpectatorModeTweaks extends JavaPlugin implements Listener {

    static final String path_gameModeSurvivalWhenJoin = "gameModeSurvivalWhenJoin";
    private boolean gameModeSurvivalWhenJoin = true;

    @Override
    public void onEnable() {
        loadConfig();
        getServer().getPluginManager().registerEvents(this, this);
    }

    private void loadConfig() {
        FileConfiguration config = getConfig();
        config.addDefault(path_gameModeSurvivalWhenJoin, gameModeSurvivalWhenJoin);
        gameModeSurvivalWhenJoin = config.getBoolean(path_gameModeSurvivalWhenJoin);
        config.options().copyDefaults(true);
        saveConfig();
    }

    @EventHandler
    public void onSpectatorTeleport(PlayerTeleportEvent event) {
        Player player = event.getPlayer();
        if (player.getGameMode() != GameMode.SPECTATOR
                || event.getCause() != PlayerTeleportEvent.TeleportCause.SPECTATE
                || player.hasPermission("spectatormodetweaks.tpbypass")
        ) return;

        event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (!gameModeSurvivalWhenJoin) return;
        final GameMode gameMode = event.getPlayer().getGameMode();
        if (gameMode == GameMode.SURVIVAL) return;
        event.getPlayer().setGameMode(GameMode.SURVIVAL);
    }

}
