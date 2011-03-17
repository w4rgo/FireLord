/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package firelord;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 *
 * @author Fran
 */
public class PlayerChecks {

    private static final String armorPerm = "firelord.armor";
    private static final String bootsPerm = "firelord.boots";
    private static final String fireStepPerm = "firelord.boots.firestep";
    private static final String overFluidsPerm = "firelord.boots.overfluids";
    private static final String swordPerm = "firelord.sword";
    private static final String helmetPerm = "firelord.helmet";
    private static final String adminPerm = "firelord.admins";
    private static boolean permissionsOn = false;

    public static boolean hasFirelordArmor(Player player) {

            ItemStack helm = player.getInventory().getHelmet();
            ItemStack breast = player.getInventory().getChestplate();
            ItemStack legs = player.getInventory().getLeggings();

            if((helm.getTypeId() == Config.getHelmId())&&(breast.getTypeId() == Config.getChestId())&&(legs.getTypeId() == Config.getLegsId())) {
                return true;
            } else return false;
    }

    public static boolean allowedArmor(Player player) {
            if(permissionsOn) {
                if (FireLord.Permissions.has(player, armorPerm)) {
                    return true;
                } else return false;
            } else return true;
    }
    public static boolean allowedSword(Player player) {
        if(permissionsOn) {
            if (FireLord.Permissions.has( player, swordPerm)) {
                return true;
            } else return false;
        } else return true;
    }
    public static boolean allowedBoots(Player player) {
        if(permissionsOn) {
            if (FireLord.Permissions.has( player, bootsPerm)) {
                return true;
            } else return false;
        } else return true;
    }
    public static boolean allowedFireStep(Player player) {
        if(permissionsOn) {
            if (FireLord.Permissions.has( player, fireStepPerm)) {
                return true;
            } else return false;
        } else return true;
    }
    public static boolean allowedOverFluids(Player player) {
        if(permissionsOn) {
            if (FireLord.Permissions.has( player, overFluidsPerm)) {
                return true;
            } else return false;
        } else return true;
    }
    public static boolean allowedHelmet(Player player) {
        if(permissionsOn) {
            if (FireLord.Permissions.has( player, helmetPerm)) {
                return true;
            } else return false;
        } else return true;
    }

    public static boolean isAdmin(Player player) {
        if(permissionsOn) {
            if (FireLord.Permissions.has( player, adminPerm)) {
                return true;
            } else return false;
        } else if(player.isOp()) {
            return true;
        } else return false;
    }
    public static boolean hasFirelordSword(Player player) {
        return player.getItemInHand().getTypeId() == Config.getSwordId();
    }
    public static boolean hasFirelordHelmet(Player player) {
        return player.getInventory().getHelmet().getTypeId() == Config.getHelmId();
    }
    public static boolean hasFirelordBoots(Player player) {
        return player.getInventory().getBoots().getTypeId() == Config.getBootsId();
    }
    public static void setPermissions(boolean value) {
        permissionsOn=value;
    }



}
