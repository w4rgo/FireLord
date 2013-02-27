/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package firelord.perks;

import firelord.tools.FirePlayer;
import firelord.Config;
import org.bukkit.Material;
import org.bukkit.entity.Chicken;
import org.bukkit.entity.Cow;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Pig;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import firelord.tools.EntityCache;

/**
 *
 * @author Fran
 */
public class FireDamage implements Perk {

    private Plugin father;

    public FireDamage(Plugin firelord) {
        //if(active()) {
        father = firelord;
        father.getServer().getPluginManager().registerEvents(this, father);
        //}
    }

    public void activate() {
        father.getServer().getPluginManager().registerEvents(this, father);
    }

    public boolean active() {
        return Config.isFireSword();
    }

    public boolean allowed(Player player) {

        FirePlayer fp = new FirePlayer(player);

        //Have Firelord Sword
        boolean hasSword = fp.hasFirelordSword();
        //Have Permissions for sword
        boolean allowedPerk = fp.getPerm().allowedSword();

        //System.out.println("HasBoots: " + hasBoots + "\nAllowedFS: "+allowedPerk + "\nnotExclBl: " + notExcludedBlock + "\n Activefs:"+ active());
        return hasSword && allowedPerk && active() && fp.checkLuck();
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerDamages(EntityDamageByEntityEvent event) {
        //If the damager is a player
        if (event.getDamager() instanceof Player) {
            Player player = (Player) event.getDamager();
            //If the damager is allowed to use firesword
            if (allowed(player)) {
                //If victim is a player check if pvp is allowed.
                //If it is not a player just fire it.
                if (event.getEntity() instanceof HumanEntity) {
                    if (Config.isAllowedPvp() && event.getEntity().getWorld().getPVP()) {
                        event.getEntity().setFireTicks(Config.getFireDuration());
                    }
                } else {
                    event.getEntity().setFireTicks(Config.getFireDuration());
                    //If it is a pig or cow or chiken we storage that he waas burnt.
                    if ((event.getEntity() instanceof Pig)
                            || (event.getEntity() instanceof Cow)
                            || (event.getEntity() instanceof Chicken)) {
                        EntityCache ac = EntityCache.getInstance();
                        ac.burnEntity(event.getEntity());
                    }
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onEntityDeath(final EntityDeathEvent event) {
        //check if is the pig.
        EntityCache ec = EntityCache.getInstance();
        if (event.getEntity() instanceof Pig) {
            if (ec.entityBurnt(event.getEntity())) {
                int numDrops = event.getDrops().size();
                event.getDrops().clear();
                for (int i = 0; i < numDrops; i++) {
                    event.getEntity().getWorld().dropItemNaturally(event.getEntity().getLocation(), new ItemStack(Material.GRILLED_PORK, 1));
                }
            }
            //if the pig die due to the firelord sword, or by fire, drop cooked steak.
        }else if (event.getEntity() instanceof Cow) {
            if (ec.entityBurnt(event.getEntity())) {
                int numDrops = event.getDrops().size();
                event.getDrops().clear();
                for (int i = 0; i < numDrops; i++) {
                    event.getEntity().getWorld().dropItemNaturally(event.getEntity().getLocation(), new ItemStack(Material.COOKED_BEEF, 1));
                }
            }
            //if the cow die due to the firelord sword, or by fire, drop cooked steak.
        } else if (event.getEntity() instanceof Chicken) {
            if (ec.entityBurnt(event.getEntity())) {
                int numDrops = event.getDrops().size();
                event.getDrops().clear();
                for (int i = 0; i < numDrops; i++) {
                    event.getEntity().getWorld().dropItemNaturally(event.getEntity().getLocation(), new ItemStack(Material.COOKED_CHICKEN, 1));
                }
            }
            //if the chiken die due to the firelord sword, or by fire, drop cooked steak.
        }
    }
}
