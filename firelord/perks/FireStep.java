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

/**
 *
 * @author Fran
 */
public class FireStep implements Perk {

    private Plugin father;

    public FireStep(Plugin firelord) {
        //if(active()) {
        father = firelord;
        father.getServer().getPluginManager().registerEvents(this, father);
        //father.getServer().getPluginManager().registerEvent(this, this, EventPriority.LOW, null, father, true);
        //}
    }

    public void activate() {
        father.getServer().getPluginManager().registerEvents(this, father);
    }

    public boolean active() {
        return Config.isFireStep();
    }

    public boolean allowed(Player player) {

        FirePlayer fp = new FirePlayer(player);
        Block blockOn = player.getLocation().getBlock();




        //Have Firelord Bootes.
        boolean hasBoots = fp.hasFirelordBoots();
        //Have Permissions
        boolean allowedPerk = fp.getPerm().allowedFireStep();
        //Block is not excluded from Step
        boolean notExcludedBlock = !BlocksChecks.isExcludedFromStep(blockOn.getTypeId()) && !BlocksChecks.isHalfBlock(blockOn.getTypeId());
        //FireStepIsactive

        //System.out.println("HasBoots: " + hasBoots + "\nAllowedFS: "+allowedPerk + "\nnotExclBl: " + notExcludedBlock + "\n Activefs:"+ active());
        return hasBoots && allowedPerk && notExcludedBlock && active() && fp.checkLuck();
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onPlayerMove(PlayerMoveEvent event) {

        Block blockOn = event.getPlayer().getLocation().getBlock();
        //Check if player block changes.
        if (allowed(event.getPlayer())) {
            //Check if block is not already on fire.
            if (event.getPlayer().getLocation().getBlock().getType() != Material.FIRE) //Set block on fire
            {
                event.getPlayer().getLocation().getBlock().setType(Material.FIRE);
            }
        }

    }
}
