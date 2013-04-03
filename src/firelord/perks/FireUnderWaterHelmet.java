/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package firelord.perks;

import firelord.tools.FirePlayer;
import firelord.Config;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.plugin.Plugin;

/**
 *
 * @author Fran
 */
public class FireUnderWaterHelmet implements Perk {

    private Plugin father;

    public FireUnderWaterHelmet(Plugin firelord) {
        //if(active()) {
        father = firelord;
        father.getServer().getPluginManager().registerEvents(this, father);
        //}
    }

    public void activate() {
        father.getServer().getPluginManager().registerEvents(this, father);
    }

    public boolean active() {
        return Config.isUnderWater();
    }

    public boolean allowed(Player player) {

        FirePlayer fp = new FirePlayer(player);

        //Have Firelord Sword
        boolean hasHelm = fp.hasFirelordHelmet();
        //Have Permissions for sword
        boolean allowedPerk = fp.getPerm().allowedHelmet();

        //System.out.println("HasBoots: " + hasBoots + "\nAllowedFS: "+allowedPerk + "\nnotExclBl: " + notExcludedBlock + "\n Activefs:"+ active());
        return hasHelm && allowedPerk && active();
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerDamaged(EntityDamageEvent event) {
        //If the damaged is Human.
        if (event.getEntity().getType() == org.bukkit.entity.EntityType.PLAYER) {
            Player player = (Player) event.getEntity();
            //if the player is allowed
            if (allowed(player)) {
                //if the damage was because of Drowning.
                if ((event.getCause() == DamageCause.DROWNING)) {
                    player.setRemainingAir(10);
                    player.setMaximumAir(10);
                    event.setCancelled(true);
                }
            }

        }
    }
}
