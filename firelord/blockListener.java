package firelord;


import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockListener;
import org.bukkit.inventory.ItemStack;



public class blockListener extends BlockListener
{
	public static FireLord plugin;
	  public blockListener(FireLord instance) {
	        plugin = instance;
	    }

  @Override
  public void onBlockBreak(BlockBreakEvent event)
  {
	  Player player = event.getPlayer();
	  int iih = player.getItemInHand().getTypeId();
	  
    if ((!event.isCancelled()))
    {
      if (event.getBlock().getType() == Material.STONE && iih == Config.getPickId() && PlayerChecks.allowedPick(player))
      {
        Location locy = new Location(event.getBlock().getWorld(), event.getBlock().getX(), event.getBlock().getY(), event.getBlock().getZ(), 0.0F, 0.0F);
        event.getBlock().getWorld().dropItem(locy, new ItemStack(1, 1));
        event.getBlock().setType(Material.AIR);
        event.setCancelled(true);

      }
      if (event.getBlock().getType() == Material.COBBLESTONE && iih == Config.getPickId() && PlayerChecks.allowedPick(player))
      {
        Location locy = new Location(event.getBlock().getWorld(), event.getBlock().getX(), event.getBlock().getY(), event.getBlock().getZ(), 0.0F, 0.0F);

        event.getBlock().getWorld().dropItem(locy, new ItemStack(1, 1));
        event.getBlock().setType(Material.AIR);
        event.setCancelled(true);

      }
      if (event.getBlock().getType() == Material.LOG && iih == Config.getAxeId() && PlayerChecks.allowedAxe(player))
      {
        Location locy = new Location(event.getBlock().getWorld(), event.getBlock().getX(), event.getBlock().getY(), event.getBlock().getZ(), 0.0F, 0.0F);

        event.getBlock().getWorld().dropItem(locy, new ItemStack(263, 1, (short) 1));
        event.getBlock().setType(Material.AIR);
        event.setCancelled(true);

      }
      if (event.getBlock().getType() == Material.GOLD_ORE && iih == Config.getPickId() && PlayerChecks.allowedPick(player))
      {
        Location locy = new Location(event.getBlock().getWorld(), event.getBlock().getX(), event.getBlock().getY(), event.getBlock().getZ(), 0.0F, 0.0F);

        event.getBlock().getWorld().dropItem(locy, new ItemStack(266, 1));
        event.getBlock().setType(Material.AIR);
        event.setCancelled(true);

      }
      if (event.getBlock().getType() == Material.IRON_ORE && iih == Config.getPickId()  && PlayerChecks.allowedPick(player))
      {
        Location locy = new Location(event.getBlock().getWorld(), event.getBlock().getX(), event.getBlock().getY(), event.getBlock().getZ(), 0.0F, 0.0F);

        event.getBlock().getWorld().dropItem(locy, new ItemStack(265, 1));
        event.getBlock().setType(Material.AIR);
        event.setCancelled(true);

      }
      if (event.getBlock().getType() == Material.SAND && iih == Config.getShovelId() && PlayerChecks.allowedShovel(player))
      {
        Location locy = new Location(event.getBlock().getWorld(), event.getBlock().getX(), event.getBlock().getY(), event.getBlock().getZ(), 0.0F, 0.0F);

        event.getBlock().getWorld().dropItem(locy, new ItemStack(20, 1));
        event.getBlock().setType(Material.AIR);
        event.setCancelled(true);

      }
      if (event.getBlock().getType() == Material.CLAY && iih == Config.getShovelId() && PlayerChecks.allowedShovel(player))
      {
        Location locy = new Location(event.getBlock().getWorld(), event.getBlock().getX(), event.getBlock().getY(), event.getBlock().getZ(), 0.0F, 0.0F);

        event.getBlock().getWorld().dropItem(locy, new ItemStack(336, 4));
        event.getBlock().setType(Material.AIR);
        event.setCancelled(true);

      }
    }
  }
}