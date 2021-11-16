package net.cyberflame.lumberharvester.listeners;

import net.cyberflame.lumberharvester.Main;
import net.cyberflame.lumberharvester.tasks.ReplaceTask;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class BlockBreakListener implements Listener {

    @SuppressWarnings("deprecation")
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onBlockBreak(final BlockBreakEvent event) {
        Player player = event.getPlayer();
        if (player.getGameMode() == GameMode.CREATIVE) return;
        Block block = event.getBlock();
        Material material = block.getType();
        if (material != Material.LOG && material != Material.LOG_2) return;
        //noinspection deprecation
        byte blockData = block.getData();
        player.getInventory().addItem(new ItemStack(material, 1, blockData));
        block.setType(Material.STONE);
        final long taskInterval = Main.getTaskInterval();
        final Main instance = Main.getInstance();
        final BlockState blockState = block.getState();
        new ReplaceTask(block, material, blockData, blockState).runTaskLater(instance, taskInterval);
    }
}