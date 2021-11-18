package net.cyberflame.lumberharvester.listeners;

import net.cyberflame.lumberharvester.Main;
import net.cyberflame.lumberharvester.tasks.ReplaceTask;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

import java.util.Collection;
import java.util.HashMap;

public class BlockBreakListener implements Listener {

    final Main instance = Main.getInstance();
    private HashMap<String, Collection<PotionEffect>> playerMap = new HashMap<>();

    @SuppressWarnings("deprecation")
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onBlockBreak(final BlockBreakEvent event) {
        Player player = event.getPlayer();
        if (instance.getBypassing(player.getUniqueId())) return;
        if (player.getGameMode() == GameMode.CREATIVE) return;
        Block block = event.getBlock();
        World world = block.getWorld();
        for (int i = 0; i < Main.getDisabledWorlds().size(); i++)
            {
                String worldName = Main.getDisabledWorlds().get(i);

                // return on same name as the world is in disabled-worlds.
                if (world.getName().equalsIgnoreCase(worldName)) return;
            }
        Material material = block.getType();
        if (material != Material.LOG && material != Material.LOG_2) return;
        //noinspection deprecation
        byte blockData = block.getData();
        player.getInventory().addItem(new ItemStack(material, 1, blockData));
        block.setType(Material.STONE);
        final long taskInterval = Main.getTaskInterval();
        final BlockState blockState = block.getState();
        new ReplaceTask(block, material, blockData, blockState).runTaskLater(instance, taskInterval);
    }
}