package net.cyberflame.lumberharvester.tasks;

import net.cyberflame.lumberharvester.Main;
import org.bukkit.Bukkit;

public class Task implements Runnable {
    @Override
    public void run() {
        final String message = Main.getInstance().getConfig().getString("messages.from-task");
        Bukkit.getServer().broadcastMessage(message);
    }
}
