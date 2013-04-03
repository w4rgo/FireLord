/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package firelord.perks;

import org.bukkit.event.player.PlayerAnimationEvent;
import org.bukkit.event.player.PlayerAnimationType;

import firelord.tools.FirePlayer;
import firelord.Config;
import firelord.tools.BlocksOper;
import firelord.tools.Dbg;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.plugin.Plugin;

/**
 *
 * @author Fran
 */
public class FireRod implements Perk {

    private Plugin father;

    public FireRod(Plugin firelord) {
        //if(active()) {
        father = firelord;
        father.getServer().getPluginManager().registerEvents(this, father);
        //}
    }

    public void activate() {
        father.getServer().getPluginManager().registerEvents(this, father);
    }

    public boolean active() {
        return Config.isFireRod();
    }

    public boolean allowed(Player player) {

        FirePlayer fp = new FirePlayer(player);

        //Have Firelord Rod
        boolean hasRod = fp.hasFirelordRod();
        //Have Permissions for sword
        boolean allowedPerk = fp.getPerm().allowedRod();
        
        Dbg.p("HasRod: " + hasRod + "\nAllowedRod: "+allowedPerk +  " ActiveRod"+ active());
        return hasRod && allowedPerk && active();
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerUseRod(PlayerAnimationEvent event) {
        
        FirePlayer fp;
        
        if(FirePlayer.getLoggedPlayers().containsKey(event.getPlayer().getName())) {
            fp = FirePlayer.getLoggedPlayers().get(event.getPlayer().getName());
            
        } else {
            fp = new FirePlayer(event.getPlayer());
            FirePlayer.getLoggedPlayers().put(event.getPlayer().getName(),fp);
        }
        
        if(allowed(event.getPlayer()) && fp.readyRod()) {
            int size = 3;
            int explosion = 2;
            
            if (event.getAnimationType().equals(PlayerAnimationType.ARM_SWING)) {
                //throw fireball
                Block block = event.getPlayer().getTargetBlock(null, 100);
                BlocksOper.createExpansiveCircle(block.getLocation(), event.getPlayer().getWorld(), Material.FIRE, 5, size, Config.getExpansiveQuantum(), true);
                event.getPlayer().getWorld().createExplosion(block.getLocation(), explosion);
            }



        }
    }
}
