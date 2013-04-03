/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package firelord.perks;

import firelord.tools.FirePlayer;
import firelord.Config;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.plugin.Plugin;

/**
 *
 * @author Fran
 */
public class FireReflect implements Perk {
  
    private Plugin father;
  
    public FireReflect(Plugin firelord) {
        //if(active()) {
            father = firelord;
            father.getServer().getPluginManager().registerEvents(this, father);
        //}
    }
    
    public void activate() {
        father.getServer().getPluginManager().registerEvents(this, father);
    }

    public boolean active() {
        return Config.isFireReflect();
    }

    public boolean allowed(Player player) {
        
        FirePlayer fp = new FirePlayer(player);

        //Have Firelord Sword
        boolean hasArmor=fp.hasFirelordArmor();
        //Have Permissions for sword
        boolean allowedPerk=fp.getPerm().allowedArmor();

        //System.out.println("HasBoots: " + hasBoots + "\nAllowedFS: "+allowedPerk + "\nnotExclBl: " + notExcludedBlock + "\n Activefs:"+ active());
        return hasArmor && allowedPerk && active() &&  fp.checkLuck();
    }

    
    //FIRE ARMOR RESISTANCE
    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerDamaged(EntityDamageByEntityEvent event) {
        //If the damaged is Human.
        if(event.getEntity() instanceof Player) {//If the damaged is a player, check if has the firelord armor
            Player player = (Player) event.getEntity();
            //If its allowed to reflect damage
            if(allowed(player)) {
                Entity damager = event.getDamager();
                boolean damagerHasBow = false;
               if(damager instanceof Player) {
                   if( ((Player)damager).getItemInHand().getTypeId()==261 ) damagerHasBow = true;
               } else if(damager instanceof Skeleton) damagerHasBow = true;
               
               if(!damagerHasBow){//If the player has the firelord armor and the damager is not ranged
                    damager.setFireTicks(Config.getFireReflectDuration());//Set the damager on fire
               }
            }
        }
    }
}
