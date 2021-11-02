package net.cyberflame.lumberharvester.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerJoinListener implements Listener {
    @EventHandler
    public void onBlockBreak(final BlockBreakEvent event) {
        Player player = event.getPlayer();
        Material material = event.getBlock().getType();
        byte blockData = event.getBlock().getData();
        if(material == Material.LOG || material == Material.LOG_2) {
            player.getInventory().addItem(new ItemStack(material, 1, blockData));

        }
    }
}