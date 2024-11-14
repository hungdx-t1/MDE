package com.minodx.MDExpansion;

import com.minodx.MDExpansion.expansions.ChatFormatter;
import com.minodx.MDExpansion.expansions.JoinLeaveFormatter;
import com.minodx.MDExpansion.expansions.ScoreboardManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class MDExpansion extends JavaPlugin {

    private ScoreboardManager scoreboardManager;

    @Override
    public void onEnable() {
        // Plugin startup logic
        saveDefaultConfig();
        scoreboardManager = new ScoreboardManager(this);

        getServer().getPluginManager().registerEvents(new ChatFormatter(this), this);
        getServer().getPluginManager().registerEvents(new JoinLeaveFormatter(this), this);
        startUpdatingScoreboard();

        getLogger().info("Plugin was loaded successfully!");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        scoreboardManager.removeScoreboards();
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

    private void startUpdatingScoreboard() {
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    scoreboardManager.updateScoreboard(player);
                }
            }
        }.runTaskTimer(this, 0L, 15L);
    }

}
