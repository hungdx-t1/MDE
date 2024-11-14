package com.minodx.MDExpansion.expansions;

import com.minodx.MDExpansion.utils.TranslateHexColorCodes;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

import java.util.List;

public class ScoreboardManager {

    private final JavaPlugin plugin;

    public ScoreboardManager(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public void updateScoreboard(Player player) {
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective("test","dummy",format(plugin.getConfig().getString("scoreboard.title")));

        objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        List<String> lines = plugin.getConfig().getStringList("scoreboard.lines");
        int score = lines.size();
        for(String line: lines) {
            line = format(line);
            if(Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
                line = PlaceholderAPI.setPlaceholders(player, line);
            }
            Score scoreLine = objective.getScore(line);
            scoreLine.setScore(score--);
        }
        player.setScoreboard(scoreboard);
    }

    public void removeScoreboards() {
        for (Player player: Bukkit.getOnlinePlayers()) {
            player.setScoreboard(Bukkit.getScoreboardManager().getMainScoreboard());
        }
    }

    private String format(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }

}
