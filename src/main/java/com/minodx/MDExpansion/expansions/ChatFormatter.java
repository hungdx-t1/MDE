package com.minodx.MDExpansion.expansions;

import com.minodx.MDExpansion.MDExpansion;
import com.minodx.MDExpansion.utils.TranslateHexColorCodes;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.ChatColor;

public class ChatFormatter implements Listener {
    private final MDExpansion plugin;

    public ChatFormatter(MDExpansion plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        String format = plugin.getConfig().getString("chat-format", "&8[&a{player}&8]: &#FFFFFF{message}");
        String message = format.replace("{player}", event.getPlayer().getName()).replace("{message}",event.getMessage());

        // PlaceholderAPI Support
        message = PlaceholderAPI.setPlaceholders(event.getPlayer(), message);

        // Hex color converter
        // TranslateHexColorCodes translator = new TranslateHexColorCodes();
        // message = translator.translateHexColorCodes(message);

        event.setFormat(ChatColor.translateAlternateColorCodes('&', message));
    }
}
