/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package firelord.perks;

import firelord.tools.FirePlayer;
import firelord.BlocksChecks;
import firelord.Config;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.Plugin;

import firelord.Config;
import firelord.tools.FirePlayer;
import org.bukkit.Location;
import org.bukkit.Material;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.BlockBreakEvent;

import org.bukkit.inventory.ItemStack;


/**
 *
 * @author Fran
 */
public class FireTools implements Perk {

    private Plugin father;

    public FireTools(Plugin firelord) {
        //if(active()) {
        father = firelord;
        father.getServer().getPluginManager().registerEvents(this, father);
        //}
    }

    public void activate() {
        father.getServer().getPluginManager().registerEvents(this, father);
    }

    public boolean active() {
        return Config.isFireTools();
    }

    public boolean allowed(Player player) {

        FirePlayer fp = new FirePlayer(player);
        fp.getPerm().printAllPerms();



        //Have Permissions for tools
        boolean allowedPerk = fp.getPerm().allowedTools();

        //System.out.println("\nAllowedFP: "+allowedPerk + "\nnotExclBl: " + "\n Activefs:"+ active() +"Luck:" +fp.checkLuck());
        return allowedPerk && active() && fp.checkLuck();
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onBlockBreak(final BlockBreakEvent event) {
        Player player = event.getPlayer();

        //If residence active and is from residence, chao.

//        if (Config.isIsResidence()) {
//            ResidenceManager rm = new ResidenceManager();
//            ClaimedResidence cres = rm.getByLoc(event.getBlock().getLocation());
//            if (cres != null) {
//                if (cres.containsLoc(event.getBlock().getLocation())) {
//                    return;
//                }
//            }
//
//        }
        if (allowed(player)) {

            int iih = player.getItemInHand().getTypeId();

            switch (event.getBlock().getType()) {
                case STONE:
                    if (iih == Config.getPickId()) {
                        //smeltBlock(event, Material.COBBLESTONE, Material.STONE);
                        smeltBlock(event, Material.STONE);
                    }

                    break;
                case COBBLESTONE:
                    if (iih == Config.getPickId()) {
                        //smeltBlock(event, Material.COBBLESTONE, Material.STONE);
                         smeltBlock(event, Material.STONE);
                    }

                    break;
                case SAND:
                    if (iih == Config.getShovelId()) {
                         smeltBlock(event, Material.GLASS);
                    }
                    break;
                case LOG:
                    if (iih == Config.getAxeId()) {
                        smeltBlock(event,  Material.COAL);
                    }
                    break;
                case IRON_ORE:
                    if (iih == Config.getPickId()) {
                        smeltBlock(event, Material.IRON_INGOT);
                    }
                    break;
                case GOLD_ORE:
                    if (iih == Config.getPickId()) {
                       smeltBlock(event, Material.GOLD_INGOT);
                    }
                    break;
                case CLAY:
                    if (iih == Config.getShovelId()) {
                       smeltBlock(event, Material.CLAY_BRICK);
                    }

                    break;
                default:
                    return;
            }

        }
    }

    public void smeltBlock(BlockBreakEvent event, /*Material oldMaterial,*/ Material newMaterial) {
       
//        ArrayList<ItemStack> oldItems = new ArrayList<ItemStack>();

        Location loc = event.getBlock().getLocation();
        ItemStack matStack = new ItemStack(newMaterial);
        event.setCancelled(true);
        event.getBlock().getDrops().clear();
        event.getBlock().setType(Material.AIR);
        loc.getWorld().dropItemNaturally(loc,matStack);
        
//        for (ItemStack item : event.getDrops()) {
//            if (item.getType() == oldMaterial) {
//                oldItems.add(item);
//                int newAmount = item.getAmount();
//                newItems.add(new ItemStack(newMaterial, newAmount));
//            }
//        }
//        event.getDrops().removeAll(oldItems);
//        event.getDrops().addAll(newItems);
        
    }
}
