package net.cyberflame.lumberharvester.tasks;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.scheduler.BukkitRunnable;

// I could use a regular Runnable instead (and even have this scheduled in the listener, but I feel like this is more
// clean and readable.
public class ReplaceTask extends BukkitRunnable
{

    private final Block block;
    private final World world;
    private final Location location;
    private final Material material;
    private final byte blockData;
    private final BlockState blockState;

    public ReplaceTask(final Block block, final World world, final Location location, final Material material,
                       final byte blockData,
                       final BlockState blockState)
    {
        this.block = block;
        this.world = world;
        this.location = location;
        this.material = material;
        this.blockData = blockData;
        this.blockState = blockState;
    }
    @SuppressWarnings("deprecation")
    @Override
    public void run()
    {
        if(world.getBlockAt(location).getType() != Material.BEDROCK) return;
        block.setType(material);
        //noinspection deprecation
        block.setData(blockData);
        // if(!blockState.isPlaced()) return;
        blockState.update(true);
    }
}
