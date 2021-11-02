package net.cyberflame.lumberharvester;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import net.cyberflame.lumberharvester.commands.RootCommand;
import net.cyberflame.lumberharvester.listeners.PlayerJoinListener;
import net.cyberflame.lumberharvester.tasks.Task;

public class Main extends JavaPlugin
{
    
    @Override
    public void onEnable()
    {
        this.saveDefaultConfig();

        Main.instance = this;
	    
        this.getCommand("command").setExecutor(new RootCommand());
        
        Bukkit.getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);

        final long taskRepeatEvery = this.getConfig().getInt("task-repeat-every") * 20L;
        this.getServer().getScheduler().runTaskTimer(this, new Task(), taskRepeatEvery, taskRepeatEvery);
    }

    private static Main instance;

    public static Main getInstance() {
        return Main.instance;
    }
}
