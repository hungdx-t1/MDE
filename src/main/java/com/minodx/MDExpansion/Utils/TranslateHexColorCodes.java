package com.minodx.MDExpansion.Utils;

import org.bukkit.ChatColor;
import org.bukkit.Translatable;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TranslateHexColorCodes {

    private static final Pattern HEX_PATTERN = Pattern.compile("&#([A-Fa-f0-9]{6})");

    // Hex color translator
    public String translateHexColorCodes(String message) {
        char colorChar = ChatColor.COLOR_CHAR;
        Matcher matcher = HEX_PATTERN.matcher(message);
        StringBuffer buffer = new StringBuffer(message.length() + 4 * 8);

        while(matcher.find()) {
            String hex = matcher.group(1);
            matcher.appendReplacement(buffer, colorChar + "x"
                    + colorChar + hex.charAt(0) + colorChar + hex.charAt(1)
                    + colorChar + hex.charAt(2) + colorChar + hex.charAt(3)
                    + colorChar + hex.charAt(4) + colorChar + hex.charAt(5)
            );
        }
        matcher.appendTail(buffer);
        return buffer.toString();
    }
}
