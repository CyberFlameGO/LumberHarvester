package net.cyberflame.lumberharvester.utils;

import java.util.ArrayList;
import java.util.List;

import net.cyberflame.lumberharvester.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FormatUtils
{
    // TODO: Change these messages to enums possibly
    public static final String NO_PERMISSION = "&cYou do not have permission to perform this command.";
    public static final String BYPASS_OTHER_TOGGLE_OFF = "&eYou &cdisabled &6%player%'s &ebypassability.";
    public static final String BYPASS_OTHER_TOGGLE_ON = "&eYou &aenabled &6%player%'s &ebypassability.";
    public static final String BYPASS_TOGGLE_OFF = "&eYou &cdisabled &eyour &ebypassability.";
    public static final String BYPASS_TOGGLE_ON = "&eYou &aenabled &eyour &ebypassability.";
    public static final String UNKNOWN_PLAYER = "&cError: No player matching &e%player% &cis connected to this server.";

    @SuppressWarnings("unused")
    public static String g(String msg)
    {
        return msg;
    }

    @SuppressWarnings("unused")
    public static String formatSeconds(long seconds)
    {
        long days = seconds / (24 * 60 * 60);
        seconds %= 24 * 60 * 60;
        long hh = seconds / (60 * 60);
        seconds %= 60 * 60;
        long mm = seconds / 60;
        seconds %= 60;
        long ss = seconds;

        if (days > 0) return days + "d " + hh + "h " + mm + "m " + ss + "s";
        if (hh > 0) return hh + "h " + mm + "m " + ss + "s";
        if (mm > 0) return mm + "m " + ss + "s";
        return ss + "s";
    }

    @SuppressWarnings("unused")
    public static String main(String string)
    {
        return String.join("\n", string.split("\n"));
    }

    @SuppressWarnings("unused")
    public static List<String> startsWith(String prefix, String... input)
    {
        prefix = prefix.toLowerCase();
        List<String> matches = new ArrayList<>();
        for (String s : input) if (s.toLowerCase().startsWith(prefix)) matches.add(s);
        return matches;
    }

    public static String replace(String text)
    {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    public static void sendMessage(CommandSender sender, String message)
    {
        sender.sendMessage(replace(message));
    }

    @SuppressWarnings("unused")
    public static void bMsg(String message)
    {
        for (Player p : Bukkit.getOnlinePlayers()) sendMessage(p, message);
    }

    @SuppressWarnings("unused")
    public static void sendConsoleMessage(String message)
    {
        Main.getInstance().getServer().getConsoleSender().sendMessage(replace(message));
    }

    @SuppressWarnings("unused")
    public static String format(String message, String str0, String str1)
    {
        return message.replace(str0, str1);
    }

}