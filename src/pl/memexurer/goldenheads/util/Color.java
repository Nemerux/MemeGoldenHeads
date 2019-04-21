package pl.memexurer.goldenheads.util;

import org.bukkit.ChatColor;

public class Color {
    public static String color(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }
}
