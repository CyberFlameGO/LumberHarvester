package net.cyberflame.lumberharvester.tasks;

import net.cyberflame.lumberharvester.Main;
import org.bukkit.Bukkit;

// I could use regular Runnables instead, but I feel like this is more readable.
public class Task extends BukkitRunnable {
    @Override
    public void run() {
        final String message = Main.getInstance().getConfig().getString("messages.from-task");
        Bukkit.getServer().broadcastMessage(message);
    }
}
