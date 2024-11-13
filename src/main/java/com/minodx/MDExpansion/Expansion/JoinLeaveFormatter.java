package com.minodx.MDExpansion.Expansion;

import com.minodx.MDExpansion.MDExpansion;
import com.minodx.MDExpansion.Utils.TranslateHexColorCodes;
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
        String joinMessage = plugin.getConfig().getString("join-message", "&#00FF00Welcome {player} to the server!");
        joinMessage = joinMessage.replace("%player_name%", event.getPlayer().getName());

        // PlaceholderAPI Support
        joinMessage = PlaceholderAPI.setPlaceholders(event.getPlayer(), joinMessage);

        // Hex color changes
        TranslateHexColorCodes translator = new TranslateHexColorCodes();
        joinMessage = translator.translateHexColorCodes(joinMessage);

        event.setJoinMessage(ChatColor.translateAlternateColorCodes('&', joinMessage));
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        String leaveMessage = plugin.getConfig().getString("leave-message", "&#FF0000Goodbye {player}!");
        leaveMessage = leaveMessage.replace("%player_name%", event.getPlayer().getName());

        // PlaceholderAPI Support
        leaveMessage = PlaceholderAPI.setPlaceholders(event.getPlayer(), leaveMessage);

        // Hex color changes
        TranslateHexColorCodes translator = new TranslateHexColorCodes();
        leaveMessage = translator.translateHexColorCodes(leaveMessage);

        event.setQuitMessage(ChatColor.translateAlternateColorCodes('&', leaveMessage));
    }


}
