/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package firelord.perks;

import firelord.tools.FirePlayer;
import firelord.BlocksChecks;
import firelord.Config;
import firelord.tools.BlocksOper;
import firelord.tools.Dbg;
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
public class FireSplash implements Perk {
  
    private static final int MAX_SIZE=5;
    private static final int EXPLOSION=2;
    private Plugin father;
  
    public FireSplash(Plugin firelord) {
        //if(active()) {
            father = firelord;
            father.getServer().getPluginManager().registerEvents(this, father);
        //}
    }
    
    public void activate() {
        father.getServer().getPluginManager().registerEvents(this, father);
    }

    public boolean active() {
        return Config.isFireSplash();
    }

    public boolean allowed(Player player) {
        
        FirePlayer fp = new FirePlayer(player);

        //Have Firelord Sword
        boolean hasArmor=fp.hasFirelordBoots();
        //Have Permissions for sword
        boolean allowedPerk=fp.getPerm().allowedSplash();
        //System.out.println("HasBoots: " + hasBoots + "\nAllowedFS: "+allowedPerk + "\nnotExclBl: " + notExcludedBlock + "\n Activefs:"+ active());
        return hasArmor && allowedPerk && active();
    }

    
    //FIRE ARMOR RESISTANCE
    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerFall(EntityDamageEvent event) {
        //If the damaged is Human.
        
        if(event.getEntity().getType() == org.bukkit.entity.EntityType.PLAYER) {
            Player player = (Player) event.getEntity();
            //if the player is allowed
            if(allowed(player)) {
            //if the damage was because of Fall
                if ((event.getCause() == DamageCause.FALL) ) {
                    int size ;
                    int expsize;
                    if((event.getDamage()>MAX_SIZE)) {
                        size=MAX_SIZE;
                        expsize = EXPLOSION;
                    } else {
                        size = event.getDamage();
                        expsize = 0;
                    }
                    Dbg.p("Damage:"+event.getDamage());
                    Dbg.p(size);
                    BlocksOper.createExpansiveCircle(player.getLocation(), event.getEntity().getWorld(), Material.FIRE, 5, size, Config.getExpansiveQuantum(), true);
                    if(expsize > 0) player.getWorld().createExplosion(player.getLocation(), EXPLOSION);
                    event.setCancelled(true);
                    
                }
            }
        }
    }
    
    
    

    
    
}

