package com.minodx.MDExpansion;

import org.bukkit.plugin.java.JavaPlugin;

public final class MDExpansion extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("Plugin was loaded successfully!");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("Plugin was disabled successfully!");
    }
}
