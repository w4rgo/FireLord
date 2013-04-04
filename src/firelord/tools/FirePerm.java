package firelord.tools;


import firelord.tools.FirePlayer;


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ajax
 */
public class FirePerm {
    
    
    private static final String _armorPerm = "firelord.armor";
    private static final String _fireStepPerm = "firelord.firestep";
    private static final String _overFluidsPerm = "firelord.overfluids";
    private static final String _swordPerm = "firelord.sword";
    private static final String _fallPerm = "firelord.fall";
    private static final String _shovelPerm = "firelord.shovel";
    private static final String _helmetPerm = "firelord.helmet";
    private static final String _adminPerm = "firelord.admins";
    private static final String _toolPerm = "firelord.tools";
    private static boolean _permissionsOn = false;

    private FirePlayer player;
    private static final String  _splashPerm="firelord.splash";
    private static final String _rodPerm="firelord.rod";
    private static boolean residence= false;

    public static boolean isResidence() {
        return residence;
    }

    public static void setResidence(boolean residence) {
        FirePerm.residence = residence;
    }
    
    
    
    public FirePerm(FirePlayer player) {
        this.player = player;
    }


    public static void setPermissions(boolean value) {
        _permissionsOn=value;
        
    }
    public boolean hasPermission(String perm) {
//        if(!perm.equals("firelord.boots.firestep")) {
//        Dbg.p("Permission try: " + perm);
//        Dbg.p("-vault:"+(_permVault!=null));
//        Dbg.p("bukkitperm: " + _permissionsOn);}
            if(_permissionsOn) {
                //USA BUKKITPERM
                 //System.out.println("Pregunta si tiene permiso de permissions");
                 
                 return player.getPlayer().hasPermission(perm);
            } else {
                //USA OP
                return player.getPlayer().isOp();
            }
    }
    
    public boolean allowedArmor() {
        return this.hasPermission(_armorPerm);
    }
    public boolean allowedSword() {
        return hasPermission(_swordPerm);
    }
    public boolean allowedShovel() {
        return hasPermission(_shovelPerm);
    }
    public boolean allowedHelmet() {
        return hasPermission(_helmetPerm);
    }
    public boolean allowedFireStep() {
        return hasPermission(_fireStepPerm);
    }
    public boolean allowedFall() {
        return hasPermission(_fallPerm);
    }
    public boolean allowedOverFluids() {
        return hasPermission(_overFluidsPerm);
    }
    public boolean isAdmin() {
        return hasPermission(_adminPerm);
    }
    public boolean allowedTools() {
        return hasPermission(_toolPerm);
    }

    
    
    public void printAllPerms() {
        
        Dbg.p(player.getPlayer().getName() +": - PERMISSIONS");
        Dbg.p("armor: "+this.allowedArmor());
        Dbg.p("tools: "+this.allowedTools());
        Dbg.p("helmet: "+this.allowedHelmet());
        Dbg.p("fall " + this.allowedFall());
        Dbg.p("admin: "+this.isAdmin());
        Dbg.p("firestep: "+this.allowedFireStep());
        Dbg.p("splash: "+this.allowedSplash());
        
    }

    public boolean allowedSplash() {
        return hasPermission(_splashPerm);
    }

    public boolean allowedRod() {
        return hasPermission(_rodPerm);
    }
    
    
}
