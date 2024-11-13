package com.minodx.MDExpansion.Expansion;

import com.minodx.MDExpansion.MDExpansion;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.ChatColor;

public class JoinLeaveFormatter implements Listener {
    private final MDExpansion plugin;

    public JoinLeaveFormatter(MDExpansion plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        String joinMessage = plugin.getConfig().getString("join-message");
        joinMessage = joinMessage.replace("%player_name%", event.getPlayer().getName());
        joinMessage = PlaceholderAPI.setPlaceholders(event.getPlayer(), joinMessage);

        event.setJoinMessage(ChatColor.translateAlternateColorCodes('&', joinMessage));
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        String leaveMessage = plugin.getConfig().getString("leave-message");
        leaveMessage = leaveMessage.replace("%player_name%", event.getPlayer().getName());
        leaveMessage = PlaceholderAPI.setPlaceholders(event.getPlayer(), leaveMessage);

        event.setQuitMessage(ChatColor.translateAlternateColorCodes('&', leaveMessage));
    }


}
