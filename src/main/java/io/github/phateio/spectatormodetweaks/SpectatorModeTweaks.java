package io.github.phateio.spectatormodetweaks;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class SpectatorModeTweaks extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
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

}
