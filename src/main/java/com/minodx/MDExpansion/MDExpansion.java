package com.minodx.MDExpansion;

import com.minodx.MDExpansion.Expansion.ChatFormatter;
import com.minodx.MDExpansion.Expansion.JoinLeaveFormatter;
import net.luckperms.api.LuckPerms;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public final class MDExpansion extends JavaPlugin {

    private LuckPerms luckPerms;

    @Override
    public void onEnable() {
        // Plugin startup logic

        if(!Bukkit.getPluginManager().isPluginEnabled("LuckPerms")) {
            getLogger().severe("LuckPerms is not installed, disabling...");
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }

        if(!Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
            getLogger().severe("PlaceholderAPI is not installed, disabling...");
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }

        this.luckPerms = getServer().getServicesManager().load(LuckPerms.class);

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

    @Override
    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
        if(args.length == 1 && "reload".equals(args[0])) {
            reloadConfig();
            sender.sendMessage(ChatColor.GREEN + "MDE has been reloaded!");
            return true;
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(final CommandSender sender, final Command command, final String alias, final String[] args) {
        if (args.length == 1)
            return Collections.singletonList("reload");

        return new ArrayList<>();
    }

}
