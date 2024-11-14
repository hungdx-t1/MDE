package com.minodx.MDExpansion;

import com.minodx.MDExpansion.expansions.ChatFormatter;
import com.minodx.MDExpansion.expansions.JoinLeaveFormatter;
import com.minodx.MDExpansion.expansions.ScoreboardManager;
import com.minodx.MDExpansion.expansions.TabHeaderFooterManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class MDExpansion extends JavaPlugin implements Listener {

    private ScoreboardManager scoreboardManager;
    private TabHeaderFooterManager tabHeaderFooterManager;

    private boolean serverPaperSupport(String javaVersion) {
        return javaVersion.startsWith("17") || javaVersion.startsWith("1.8") && Integer.parseInt(javaVersion.split("\\.")[0]) >= 17;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        String javaVersion = System.getProperty("java.version");
        if (!serverPaperSupport(javaVersion)) {
            getLogger().severe("This plugin requires Java 17 or higher! Disabling plugin...");
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }
        saveDefaultConfig();
        scoreboardManager = new ScoreboardManager(this);
        tabHeaderFooterManager = new TabHeaderFooterManager(this);

        getServer().getPluginManager().registerEvents(this, this);
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
        if (args.length == 1 && "reload".equals(args[0])) {
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
                    tabHeaderFooterManager.sendTabHeaderFooter(player);
                }
            }
        }.runTaskTimer(this, 0L, 15L);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        tabHeaderFooterManager.sendTabHeaderFooter(player);
    }
}
