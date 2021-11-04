package net.cyberflame.lumberharvester.listeners;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerJoinListener implements Listener {
    @SuppressWarnings("deprecation")
    @EventHandler
    public void onBlockBreak(final BlockBreakEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlock();
        Material material = block.getType();
        byte blockData = block.getData();
        if(material == Material.LOG || material == Material.LOG_2) {
            player.getInventory().addItem(new ItemStack(material, 1, blockData));
            block.setType(Material.STONE);

        }
    }
}