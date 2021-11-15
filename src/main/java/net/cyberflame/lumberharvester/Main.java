package net.cyberflame.lumberharvester;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

// import net.cyberflame.lumberharvester.commands.RootCommand;
import net.cyberflame.lumberharvester.listeners.BlockBreakListener;

public class Main extends JavaPlugin
{
    
    @Override
    public void onEnable()
    {
        this.saveDefaultConfig();
        Main.instance = this;
        // this.getCommand("command").setExecutor(new RootCommand());
        Bukkit.getServer().getPluginManager().registerEvents(new BlockBreakListener(), this);
    }

    private static Main instance;
    public static Main getInstance()
    {
        return Main.instance;
    }

    private static final long taskDelay = getInstance().getConfig().getInt("replace-delay") * 20L;

    @SuppressWarnings("FinalStaticMethod")
    public static final long getTaskInterval()
    {
        return taskDelay;
    }
}

