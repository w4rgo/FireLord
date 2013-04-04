/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package firelord.perks;

import com.bekvon.bukkit.residence.Residence;
import com.bekvon.bukkit.residence.protection.ClaimedResidence;
import com.bekvon.bukkit.residence.protection.ResidencePermissions;
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
import firelord.tools.FirePerm;
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

    public boolean isResidenceInBlock(Player player , Block block) {
        
        if(FirePerm.isResidence()) {
            Location loc = block.getLocation();
            ClaimedResidence res = Residence.getResidenceManager().getByLoc(loc);
            if (res == null) {
                return false;
            } else {
                ResidencePermissions perms = res.getPermissions();
                boolean defaultValue = true;
                return perms.playerHas(player.getName(), "build", defaultValue);
               
            }
        } else {
            return false;
        }
    }
    
    @EventHandler(priority = EventPriority.NORMAL)
    public void onBlockBreak(final BlockBreakEvent event) {
        Player player = event.getPlayer();


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
        if(!this.isResidenceInBlock(event.getPlayer(),event.getBlock())){
            ItemStack matStack = new ItemStack(newMaterial);
            event.setCancelled(true);
            event.getBlock().getDrops().clear();
            event.getBlock().setType(Material.AIR);
            loc.getWorld().dropItemNaturally(loc,matStack);
        }
        
        
    }
}
