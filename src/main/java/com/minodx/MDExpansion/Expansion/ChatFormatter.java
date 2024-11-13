package com.minodx.MDExpansion.Expansion;

import com.minodx.MDExpansion.MDExpansion;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.ChatColor;

public class ChatFormatter implements Listener {
    private final MDExpansion plugin;

    public ChatFormatter(MDExpansion plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        String format = plugin.getConfig().getString("chat-format");
        String message = format.replace("%player_name", event.getPlayer().getName()).replace("%message%",event.getMessage());

        message = PlaceholderAPI.setPlaceholders(event.getPlayer(), message);
        event.setFormat(ChatColor.translateAlternateColorCodes('&', message));
    }
}
