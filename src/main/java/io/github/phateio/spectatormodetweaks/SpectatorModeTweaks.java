package io.github.phateio.spectatormodetweaks;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class SpectatorModeTweaks extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
    }

}
