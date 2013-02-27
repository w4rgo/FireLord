/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
package firelord;

import firelord.perks.FireStep;
import firelord.perks.FireDamage;
import firelord.perks.FireReflect;
import firelord.perks.FireResist;
import firelord.perks.FireResistFall;
import firelord.perks.FireRod;
import firelord.perks.FireSplash;
import firelord.perks.FireTools;
import firelord.perks.FireUnderWaterHelmet;
import firelord.tools.Dbg;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import firelord.tools.FirePerm;

/**
 *
 * @author franrv
 *

 */
public class FireLord extends JavaPlugin {

    private String name = "[FireLord]";
    private String version = "0.9";
    public static final Logger log = Logger.getLogger("Minecraft");
    private Permission perm;
    private Plugin residence=null;
    public void onDisable() {
        log.info(name + " version " + version + " disabled");
    }

    public Plugin getResidence() {
        return residence;
    }

    public void setResidence(Plugin residence) {
        this.residence = residence;
    }

    private boolean setupVault() {

        Plugin test = this.getServer().getPluginManager().getPlugin("Vault");

        RegisteredServiceProvider<Permission> permissionProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.permission.Permission.class);
        if (permissionProvider != null) {
            perm = permissionProvider.getProvider();
        }
        return (perm != null);


    }

    private boolean setupRes() {
        Plugin test = this.getServer().getPluginManager().getPlugin("Residence");
        this.residence=test;
        return (perm != null);
    }

    private void setupBukkitPerm() {
        FirePerm.setPermissions(true);
    }

    //Java is like a beautifull woman. Beautifull but you have to understand it.
    @Override
    public void onEnable() {
        this.getServer().getIp();
        try {

            Config.loadConfig(this.getDataFolder());
            if (setupVault()) {
                System.out.println("[Firelord] Valut is enabled, Using vault!");

                FirePerm.setPermVault(perm);
            } else {
                setupBukkitPerm();
                System.out.println("[Firelord] You should use vault...");
            }
        } catch (IOException ex) {
            Logger.getLogger(FireLord.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(setupRes()) Config.setIsResidence(true);
        else Config.setIsResidence(false);

        if (Config.isFireStep()) {
            new FireStep(this);
        }
        
        new FireDamage(this);
        new FireResist(this);
        new FireReflect(this);
        new FireUnderWaterHelmet(this);
        new FireResistFall(this);
        new FireTools(this);
        new FireSplash(this);
        new FireRod(this);
    }
}
