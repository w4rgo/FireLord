/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
* FireLord 0.4
* Copyright (C) 2011 W4rGo <franrv@gmail.com>
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


package firelord;
import com.nijiko.permissions.PermissionHandler;
import com.nijikokun.bukkit.Permissions.Permissions;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.event.Event;
import org.bukkit.event.Event.Priority;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author franrv
 *
 *
 * //Y si no tienes permissions ?
 *
 * Y si no quieres las funcionalidades ?
 */
public class FireLord extends JavaPlugin{
    public static PermissionHandler Permissions;
    public CommandListener listener = new CommandListener(this);
    public DamageListener dmgListener = new DamageListener(this);
    public PlayerMoveListener moveListener = new PlayerMoveListener(this);
    private String name = "FireLord (By W4rGo)";
    private String version = "0.4";
    public static final Logger log = Logger.getLogger("Minecraft");

    public void onDisable() {
            log.info(name + " version " + version + " disabled");
    }
    private void setupPermissions() {
      Plugin test = this.getServer().getPluginManager().getPlugin("Permissions");

      if (Permissions == null) {
          if (test != null) {
              Permissions = ((Permissions)test).getHandler();
              PlayerChecks.setPermissions(true);
              log.info(name + " " +version + " using Permissions system");
          } else {
              PlayerChecks.setPermissions(false);
              log.info("Permission system not detected, default to everybody! and admin commands for OP");
          }
      }
  }
    public void onEnable() {
        try {
            Config.loadConfig(this.getDataFolder());
            setupPermissions();
            log.info(name + " version " + version + " enabled!");
            getServer().getPluginManager().registerEvent(Event.Type.PLAYER_COMMAND_PREPROCESS, listener, Priority.Normal, this);
            getServer().getPluginManager().registerEvent(Event.Type.PLAYER_JOIN, listener, Priority.Normal, this);
            getServer().getPluginManager().registerEvent(Event.Type.PLAYER_ITEM, listener, Priority.Normal, this);
            getServer().getPluginManager().registerEvent(Event.Type.ENTITY_DAMAGED, dmgListener, Priority.Normal, this);
            getServer().getPluginManager().registerEvent(Event.Type.PLAYER_MOVE, moveListener, Priority.Normal, this);
            
            
        } catch (FileNotFoundException ex) {
            log.info("FireLord: The configuration file is missing!");
            //Create template
        } catch (IOException ex) {
            Logger.getLogger(FireLord.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
