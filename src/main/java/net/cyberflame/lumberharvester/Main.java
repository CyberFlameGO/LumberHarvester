package net.cyberflame.lumberharvester;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import net.cyberflame.lumberharvester.commands.BypassCommand;
import net.cyberflame.lumberharvester.listeners.BlockBreakListener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Main extends JavaPlugin
{
    /*
    TODO:
        * Add a bypass toggle (just a case of copying from my KPM logic)
        * Make the logs being processed get saved to a file (and get processed on server relaunch)
        * Make sure all is working
     */

    private static Map<UUID, Boolean> bypassing;
    private static List<String> disabledWorlds;
    private static long taskDelay;
    
    @Override
    public void onEnable()
    {
        this.saveDefaultConfig();
        getConfig().options().copyDefaults(true);
        saveConfig();
        Main.instance = this;
        bypassing = new HashMap<>();
        taskDelay = (long) getInstance().getConfig().getInt("replace-delay") * 20L;
        disabledWorlds = this.getConfig().getStringList("disabled-worlds");
        this.getCommand("lhbypass").setExecutor(new BypassCommand());
        Bukkit.getPluginManager().registerEvents(new BlockBreakListener(), this);
    }

    // this shouldn't cause memory leaks since it's not static in the listener

    private static Main instance;

    @SuppressWarnings("FinalStaticMethod")
    public static final Main getInstance()
    {
        return Main.instance;
    }

    @SuppressWarnings("FinalStaticMethod")
    public static final long getTaskInterval()
    {
        return taskDelay;
    }


    public static List<String> getDisabledWorlds()
    {
        return disabledWorlds;
    }

    public static Map<UUID, Boolean> getAllBypassing()
    {
        return bypassing;
    }

    public void setBypassing(UUID uuid)
    {
        if (bypassing.get(uuid) != null)
            {
                bypassing.remove(uuid);
                return;
            }
        bypassing.put(uuid, true);
    }

    public boolean getBypassing(UUID uuid)
    {
        return bypassing.get(uuid) != null;
    }
}

