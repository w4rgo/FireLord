package firelord;

import java.util.logging.Logger;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityListener;

public class DamageListener extends EntityListener {
    public static final Logger  log = Logger.getLogger("Minecraft");
    public static FireLord plugin = null;

    DamageListener(FireLord firelord) {
       plugin = firelord;
    }
    
    //Fire Sword and Fire Armor
    @Override
    public void onEntityDamage(EntityDamageEvent event) { //
        super.onEntityDamage(event);

        if(event instanceof EntityDamageByEntityEvent) {
            
            EntityDamageByEntityEvent eventDmgByEntity = (EntityDamageByEntityEvent) event;
            Entity damager = eventDmgByEntity.getDamager();
            //FIRELORD SWORD FIRE DAMAGE
            if(damager instanceof Player) { //If the damager is a player, check if has the firelord sword
                Player player = (Player) damager;
                if (PlayerChecks.allowedSword(player)&&Config.isFireSword()) {
                    if(PlayerChecks.hasFirelordSword(player)) {
                        event.getEntity().setFireTicks(Config.getFireDuration()); //Set on fire when hit with gold sword
                        event.getEntity();                 
                    }
                }
            }
            //FIRELORD ARMOR FIRE REFLECT
            if(event.getEntity() instanceof Player) {//If the damaged is a player, check if has the firelord armor
                Player player = (Player) event.getEntity();
                if (PlayerChecks.allowedArmor(player)&&Config.isFireReflect()) {
                   boolean damagerHasBow = false;
                   if(damager instanceof Player) {
                       if( ((Player)damager).getItemInHand().getTypeId()==261 ) damagerHasBow = true;
                   }
                   else if(damager instanceof Skeleton) damagerHasBow = true;
                   if(PlayerChecks.hasFirelordArmor(player) && !damagerHasBow){//If the player has the firelord armor and the damager is not ranged
                        damager.setFireTicks(Config.getFireDuration());//Set the damager on fire
                   }
                }
            }
            //FIRELORD ARMOR FIRE RESISTANCE
        } else if ((event.getCause() == DamageCause.FIRE) || 
                   (event.getCause() == DamageCause.FIRE_TICK) || 
                   (event.getCause() == DamageCause.LAVA)) {
                            //Permissions
                        if(event.getEntity() instanceof Player) {//if the damaged is a player, and damaged by fire/lava
                            Player player = (Player) event.getEntity();
                            if (PlayerChecks.allowedArmor(player)&&Config.isFireResist()) {
                                if (PlayerChecks.hasFirelordArmor(player)) {
                                    event.setCancelled(true);//If he has the firelord armor cancel the damage
                                    player.setFireTicks(0);//To avoid the anoying fire animation
                                }
                            }
                        }
                }
    }
}


