/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package firelord.perks;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

/**
 *
 * @author Fran
 */
public interface Perk extends Listener{
    
    public void activate();
    public boolean active();
    public boolean allowed(Player player);
}
