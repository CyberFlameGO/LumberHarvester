package net.cyberflame.lumberharvester.listeners;

import net.cyberflame.lumberharvester.Main;
import net.cyberflame.lumberharvester.tasks.ReplaceTask;
import org.bukkit.GameMode;
import org.bukkit.Location;
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
import org.jetbrains.annotations.NotNull;

public class BlockBreakListener implements Listener {

    final Main instance = Main.getInstance();

    @SuppressWarnings("deprecation")
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onBlockBreak(final @NotNull BlockBreakEvent event) {
        Player player = event.getPlayer();
        if (instance.getBypassing(player.getUniqueId())) return;
        if (player.getGameMode() == GameMode.CREATIVE) return;
        final Block block = event.getBlock();
        final World world = block.getWorld();
        // return on same name as the world is in disabled-worlds.
        if(Main.getDisabledWorlds().stream().anyMatch(worldName -> world.getName().equalsIgnoreCase(worldName))) return;
        Material material = block.getType();
        if(material != Material.LOG && material != Material.LOG_2) return;
        player.getInventory().addItem(block.getDrops().toArray(new ItemStack[0]));
        final byte blockData = block.getData();
        final BlockState blockState = block.getState();
        final Location location = block.getLocation();
        event.setCancelled(true);
        block.setType(Material.BEDROCK);
        final long taskInterval = Main.getTaskInterval();
        new ReplaceTask(block, world, location, material, blockData, blockState).runTaskLater(instance, taskInterval);
    }
}