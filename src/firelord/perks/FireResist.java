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
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityCombustEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.Plugin;

/**
 *
 * @author Fran
 */
public class FireResist implements Perk {

    private Plugin father;

    public FireResist(Plugin firelord) {
        //if(active()) {
        father = firelord;
        father.getServer().getPluginManager().registerEvents(this, father);
        //}
    }

    public void activate() {
        father.getServer().getPluginManager().registerEvents(this, father);
    }

    public boolean active() {
        return Config.isFireResist();
    }

    public boolean allowed(Player player) {

        FirePlayer fp = new FirePlayer(player);

        //Have Firelord Sword
        boolean hasArmor = fp.hasFirelordArmor();
        //Have Permissions for sword
        boolean allowedPerk = fp.getPerm().allowedArmor();

        //System.out.println("HasBoots: " + hasBoots + "\nAllowedFS: "+allowedPerk + "\nnotExclBl: " + notExcludedBlock + "\n Activefs:"+ active());
        return hasArmor && allowedPerk && active();
    }

    //FIRE ARMOR RESISTANCE
    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerDamaged(EntityDamageEvent event) {
        //If the damaged is Human.

        if (event.getEntity().getType() == org.bukkit.entity.EntityType.PLAYER) {
            Player player = (Player) event.getEntity();
            //if the player is allowed
            if (allowed(player)) {
                //if the damage was because of Fire, lava or continous fire.
                if ((event.getCause() == DamageCause.FIRE)
                        || (event.getCause() == DamageCause.FIRE_TICK)
                        || (event.getCause() == DamageCause.LAVA)|| (event.getCause() == DamageCause.BLOCK_EXPLOSION)) {
                    event.setCancelled(true);
                    player.setFireTicks(0);
                }
            }
        }
    }
}
