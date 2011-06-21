package firelord;

import java.util.logging.Logger;
import org.bukkit.Achievement;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Pig;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityListener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitScheduler;

/**
* FireLord 0.4
* Copyright (C) 2011 W4rGo , Francisco Ruiz Valdes <franrv@gmail.com>
*
* This program is free software: you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation, either version 2 of the License, or
* (at your option) any later version.
*
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this program. If not, see <http://www.gnu.org/licenses/>.
*/


public class DamageListener extends EntityListener {
    public static final Logger  log = Logger.getLogger("Minecraft");
    public static FireLord plugin = null;

    DamageListener(FireLord firelord) {
       plugin = firelord;
    }



    @Override
    public void onEntityDeath(EntityDeathEvent event) {
        super.onEntityDeath(event);
        //check if is the pig.
        if(event.getEntity() instanceof Pig) {
            if(PlayerChecks.pigBurnt((Pig) event.getEntity())) {
                
                int numDrops = event.getDrops().size();
                event.getDrops().clear();
                for(int i = 0;i<numDrops;i++) {
                    event.getEntity().getWorld().dropItemNaturally(event.getEntity().getLocation(), new ItemStack(Material.GRILLED_PORK,1));
                }

            }
            //if the pig die due to the firelord sword, or by fire, drop cooked steak.
        }
    }

    //Fire Sword and Fire Armor
    @Override
    public void onEntityDamage(EntityDamageEvent event) { //
        super.onEntityDamage(event);



        if(event instanceof EntityDamageByEntityEvent) {
            
            EntityDamageByEntityEvent eventDmgByEntity = (EntityDamageByEntityEvent) event;
            Entity damager = eventDmgByEntity.getDamager();
            //FIRELORD SWORD FIRE DAMAGE AND TOOLS
            if(damager instanceof Player) { //If the damager is a player, check if has the firelord sword
                Player player = (Player) damager;

                if ( PlayerChecks.playerSetFireOnHit(player) ) {

                    if(PlayerChecks.hasFirelordSword(player) && PlayerChecks.checkLuck()) {
                     
                        if(event.getEntity() instanceof HumanEntity) {
                            if(Config.isAllowedPvp() && event.getEntity().getWorld().getPVP()) {
                                event.getEntity().setFireTicks(Config.getFireDuration());
                            }
                        } else {
                            event.getEntity().setFireTicks(Config.getFireDuration());
                        }
                         //Set on fire when hit with gold sword
                        if(event.getEntity() instanceof Pig) PlayerChecks.burnPig((Pig) event.getEntity()); //Add pig to the burned pigs list
                    }
                   
                }
            }
            //FIRELORD ARMOR FIRE REFLECT
            if(event.getEntity() instanceof Player) {//If the damaged is a player, check if has the firelord armor
               Player player = (Player) event.getEntity();
                if (PlayerChecks.allowedArmor(player)&&Config.isFireReflect() && PlayerChecks.checkLuck()) {
                   boolean damagerHasBow = false;
                   if(damager instanceof Player) {
                       if( ((Player)damager).getItemInHand().getTypeId()==261 ) damagerHasBow = true;
                   }
                   else if(damager instanceof Skeleton) damagerHasBow = true;
                   if(PlayerChecks.hasFirelordArmor(player) && !damagerHasBow){//If the player has the firelord armor and the damager is not ranged
                        damager.setFireTicks(Config.getFireReflectDuration());//Set the damager on fire
                   }
                }
            }
            //FIRELORD ARMOR FIRE RESISTANCE
        } else if ((event.getCause() == DamageCause.FIRE) || 
                   (event.getCause() == DamageCause.FIRE_TICK) || 
                   (event.getCause() == DamageCause.LAVA)) {
                            //Permissions
                        if(event.getEntity() instanceof Pig) PlayerChecks.burnPig((Pig) event.getEntity());//if it is a pig, then add to burntPigs list

                        if(event.getEntity() instanceof Player) {//if the damaged is a player, and damaged by fire/lava
                          Player player = (Player) event.getEntity();
                            if (PlayerChecks.allowedArmor(player)&&Config.isFireResist() && PlayerChecks.checkLuck()) {
                                if (PlayerChecks.hasFirelordArmor(player)) {
                                    event.setCancelled(true);//If he has the firelord armor cancel the damage
                                    player.setFireTicks(0);//To avoid the anoying fire animation
                                }
                            }
                        }
                }
        
                //FIRE LORD HELMET UNDERWATER AIR
        if(event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            if(PlayerChecks.hasFirelordHelmet(player)) {
                if( PlayerChecks.allowedHelmet(player)&&Config.isUnderWater() ) {
                    if(event.getCause().equals(event.getCause().DROWNING)) {

                        player.setRemainingAir(10);
                        player.setMaximumAir(10);
                        event.setCancelled(true);
                    }

                }
            }
        }
        
        
    }

    

}


