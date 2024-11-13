package com.minodx.MDExpansion;

import com.minodx.MDExpansion.Expansion.ChatFormatter;
import com.minodx.MDExpansion.Expansion.JoinLeaveFormatter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class MDExpansion extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic

        if(!Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
            getLogger().severe("PlaceholderAPI is not installed, disabling...");
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }

        saveDefaultConfig();

        getServer().getPluginManager().registerEvents(new ChatFormatter(this), this);
        getServer().getPluginManager().registerEvents(new JoinLeaveFormatter(this), this);

        getLogger().info("Plugin was loaded successfully!");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("Plugin was disabled successfully!");
    }
}
