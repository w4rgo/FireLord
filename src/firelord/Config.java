/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package firelord;

import firelord.tools.Dbg;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.File;
import java.util.HashMap;

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
public class Config {

    // public static PermissionHandler Permissions;
    private static boolean isResidence;


    

    /**
     * Get the value of isResidence
     *
     * @return the value of isResidence
     */
    public static boolean isIsResidence() {
        return isResidence;
    }

    /**
     * Set the value of isResidence
     *
     * @param isResidence new value of isResidence
     */
    public static void setIsResidence(boolean isResidence) {
        isResidence = isResidence;
    }

    private static String configPath = "plugins/FireLord/config.properties";
    private static String configFile = "config.properties";
    private static int fireDuration = 50;
    private static int fireReflectDuration = 40;
    private static Properties config;
    private static FileInputStream fi;
    private static int swordId = 283;
    private static int helmId = 314;
    private static int chestId = 315;
    private static int bootsId = 317;
    private static int legsId = 316;
    private static int pickId = 285;
    private static int axeId = 286;
    private static int shovelId = 284;
    private static boolean fireSword = true;
    private static boolean fireTools = true;
    //private static boolean firePick = true;
    //private static boolean fireAxe = true;
    //private static boolean fireShovel = true;        
    private static boolean fireResist = true;
    private static boolean fireReflect = true;
    private static boolean fireStep = false;
    private static boolean overWater = false;
    private static boolean overLava = false;
    private static boolean underWater = false;
    private static boolean allowedPvp = true;
    private static int percentage = 100;
    private static boolean isAllowedMolotov = true;
    private static boolean fireNoFallDamage= true;
    
    private static int stepRadius = 0;//-
    private static int pillarRadius = 1;//-
    private static int pillarHeight = 2;//-
    private static int explosionForce = 3;//-
    private static boolean expansive = true;//
    private static int expansiveQuantum = 300;//-
    private static int expansiveMaxRadius =5;//-
    private static boolean fallDmg = true;//-
    private static boolean fireSplash = true;
    private static int rodId=369;
    private static boolean fireRod=false;
    private static Config instance;
    private static int rodReuseTime=60;
    public static int getRodId() {
        return rodId;
    }

    public static boolean isFireRod() {
        return fireRod;
    }

    public static int getRodReuseTime() {
        return rodReuseTime;
    }
    
    private HashMap<String,Object> props = new HashMap<String,Object>();

    public void setProp(String name,int prop) {
            config.setProperty(name, String.valueOf(prop));
    }
    public void setProp(String name,String prop) {
            config.setProperty(name, prop);
    }
    public int getIntProp(String name) {
        return Integer.valueOf(config.getProperty(name));
    }
    public String getStringProp(String name) {
        return config.getProperty(name);
    }
    
    public static Config getInstance () {
        if(instance == null) instance = new Config();
        return instance;
    }
    
    public static int getExplosionForce() {
        return explosionForce;
    }

    public static void setExplosionForce(int explosionForce) {
        Config.explosionForce = explosionForce;
    }
   
    private static boolean boolValue(String value) {
        if (value.equalsIgnoreCase("true")) {
            return true;
        } else if (value.equalsIgnoreCase("false")) {
            return false;
        } else {
            return false;
        }
    }

    public static void loadConfig(File filePath) throws IOException {
        boolean changed = false;
        configPath = filePath.toString() + File.separator + configFile;
        config = new Properties();
        

        try {
            fi = new FileInputStream(Config.configPath);

            config.load(fi);
        } catch (IOException e) {

            File folder = new File("FireLord");
            File f = new File(configPath);          
            f.createNewFile();
            fi = new FileInputStream(Config.configPath);
        
        }


        if (config.getProperty("fireReflectDuration") != null) {
            fireReflectDuration = Integer.valueOf(config.getProperty("fireReflectDuration")) * 20;
        } else {
            config.setProperty("fireReflectDuration", String.valueOf(fireReflectDuration));
            changed = true;
        }
        if (config.getProperty("rodReuseTime") != null) {
            fireDuration = Integer.valueOf(config.getProperty("rodReuseTime")) ;
        } else {
            config.setProperty("rodReuseTime", String.valueOf(rodReuseTime));
            changed = true;
        }
        
        
        if (config.getProperty("fireduration") != null) {
            fireDuration = Integer.valueOf(config.getProperty("fireduration")) * 20;
        } else {
            config.setProperty("fireduration", String.valueOf(fireDuration));
            changed = true;
        }
        if (config.getProperty("fireSword") != null) {
            fireSword = boolValue(config.getProperty("fireSword"));
        } else {
            config.setProperty("fireSword", String.valueOf(fireSword));
            changed = true;
        }

        if (config.getProperty("fireTools") != null) {
            fireTools = boolValue(config.getProperty("fireTools"));
        } else {
            config.setProperty("fireTools", String.valueOf(fireTools));
            changed = true;
        }
        if (config.getProperty("fireResist") != null) {
            fireResist = boolValue(config.getProperty("fireResist"));
        } else {
            config.setProperty("fireResist", String.valueOf(fireResist));
            changed = true;
        }
        if (config.getProperty("fireReflect") != null) {
            fireReflect = boolValue(config.getProperty("fireReflect"));
        } else {
            config.setProperty("fireReflect", String.valueOf(fireReflect));
            changed = true;
        }
        if (config.getProperty("fireStep") != null) {
            fireStep = boolValue(config.getProperty("fireStep"));
        } else {
            config.setProperty("fireStep", String.valueOf(fireStep));
            changed = true;
        }
        if (config.getProperty("fireNoFallDamage") != null) {
            fireNoFallDamage = boolValue(config.getProperty("fireNoFallDamage"));
        } else {
            config.setProperty("fireNoFallDamage", String.valueOf(fireNoFallDamage));
            changed = true;
        }
        if (config.getProperty("overWater") != null) {
            overWater = boolValue(config.getProperty("overWater"));
        } else {
            config.setProperty("overWater", String.valueOf(overWater));
            changed = true;
        }
        if (config.getProperty("overLava") != null) {
            overLava = boolValue(config.getProperty("overLava"));
        } else {
            config.setProperty("overLava", String.valueOf(overLava));
            changed = true;
        }
        if (config.getProperty("underWater") != null) {
            underWater = boolValue(config.getProperty("underWater"));
        } else {
            config.setProperty("underWater", String.valueOf(underWater));
            changed = true;
        }
        
        //Item Ids

        if (config.getProperty("boots") != null) {
            bootsId = Integer.valueOf(config.getProperty("boots"));
        } else {
            config.setProperty("boots", String.valueOf(bootsId));
            changed = true;
        }
        if (config.getProperty("chestplate") != null) {
            chestId = Integer.valueOf(config.getProperty("chestplate"));
        } else {
            config.setProperty("chestplate", String.valueOf(chestId));
            changed = true;
        }
        if (config.getProperty("helmet") != null) {
            helmId = Integer.valueOf(config.getProperty("helmet"));
        } else {
            config.setProperty("helmet", String.valueOf(helmId));
            changed = true;
        }
        if (config.getProperty("leggings") != null) {
            legsId = Integer.valueOf(config.getProperty("leggings"));
        } else {
            config.setProperty("leggings", String.valueOf(legsId));
            changed = true;
        }
        if (config.getProperty("sword") != null) {
            swordId = Integer.valueOf(config.getProperty("sword"));
        } else {
            config.setProperty("sword", String.valueOf(swordId));
            changed = true;
        }
        if (config.getProperty("pick") != null) {
            pickId = Integer.valueOf(config.getProperty("pick"));
        } else {
            config.setProperty("pick", String.valueOf(pickId));
            changed = true;
        }
        if (config.getProperty("axe") != null) {
            axeId = Integer.valueOf(config.getProperty("axe"));
        } else {
            config.setProperty("axe", String.valueOf(axeId));
            changed = true;
        }
        if (config.getProperty("shovel") != null) {
            shovelId = Integer.valueOf(config.getProperty("shovel"));
        } else {
            config.setProperty("shovel", String.valueOf(shovelId));
            changed = true;
        }
        
        if (config.getProperty("allowedPvp") != null) {
            allowedPvp = boolValue(config.getProperty("allowedPvp"));
        } else {
            config.setProperty("allowedPvp", String.valueOf(allowedPvp));
            changed = true;
        }
        if(config.getProperty("luck") != null) {
            percentage = Integer.valueOf(config.getProperty("luck"));
        } else {
            config.setProperty("luck", String.valueOf(percentage));
            changed = true;
        }
        if (config.getProperty("fireSplash") != null) {
            fireSplash = boolValue(config.getProperty("fireSplash"));
        } else {
            config.setProperty("fireSplash", String.valueOf(fireSplash));
            changed = true;
        }
        if (config.getProperty("fireRod") != null) {
            fireRod = boolValue(config.getProperty("fireRod"));
        } else {
            config.setProperty("fireRod", String.valueOf(fireRod));
            changed = true;
        }
        if (changed) {
            FileOutputStream fo = new FileOutputStream(configPath);
            config.store(fo, configPath);
            fo.close();
        }

    }

    public static void setFireDuration(int value) {
        FileOutputStream fo = null;
        try {
            fireDuration = value * 20; //Translation into bukkit ticks 1 s -> 1/20 ticks
            config.setProperty("fireduration", String.valueOf(value));
            fo = new FileOutputStream(configPath);
            config.store(fo, configPath);
            fo.close();
        } catch (IOException ex) {
            Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void setFireReflectDuration(int value) {
        FileOutputStream fo = null;
        try {
            fireReflectDuration = value * 20; //Translation into bukkit ticks 1 s -> 1/20 ticks
            config.setProperty("fireReflectDuration", String.valueOf(value));
            fo = new FileOutputStream(configPath);
            config.store(fo, configPath);
            fo.close();
        } catch (IOException ex) {
            Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void setFireNoFallDamage(boolean value) {
        FileOutputStream fo = null;
        try {
            fireNoFallDamage = value;
            config.setProperty("fireNoFallDamage", String.valueOf(value));
            fo = new FileOutputStream(configPath);
            config.store(fo, configPath);
            fo.close();
        } catch (IOException ex) {
            Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void setFireStep(boolean value) {
        FileOutputStream fo = null;
        try {
            fireStep = value;
            config.setProperty("fireStep", String.valueOf(value));
            fo = new FileOutputStream(configPath);
            config.store(fo, configPath);
            fo.close();
        } catch (IOException ex) {
            Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void setFireSword(boolean value) {
        FileOutputStream fo = null;
        try {
            fireSword = value;
            config.setProperty("fireSword", String.valueOf(value));
            fo = new FileOutputStream(configPath);
            config.store(fo, configPath);
            fo.close();
        } catch (IOException ex) {
            Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /*public static void setFirePick(boolean value) {
        FileOutputStream fo = null;
        try {
            firePick = value;
            config.setProperty("firePick", String.valueOf(value));
            fo = new FileOutputStream(configPath);
            config.store(fo, configPath);
            fo.close();
        } catch (IOException ex) {
            Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void setFireAxe(boolean value) {
        FileOutputStream fo = null;
        try {
            fireAxe = value;
            config.setProperty("fireAxe", String.valueOf(value));
            fo = new FileOutputStream(configPath);
            config.store(fo, configPath);
            fo.close();
        } catch (IOException ex) {
            Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void setFireShovel(boolean value) {
        FileOutputStream fo = null;
        try {
            fireShovel = value;
            config.setProperty("fireShovel", String.valueOf(value));
            fo = new FileOutputStream(configPath);
            config.store(fo, configPath);
            fo.close();
        } catch (IOException ex) {
            Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/
    public static void setFireTools(boolean value) {
        FileOutputStream fo = null;
        try {
            fireTools = value;
            config.setProperty("fireTools", String.valueOf(value));
            fo = new FileOutputStream(configPath);
            config.store(fo, configPath);
            fo.close();
        } catch (IOException ex) {
            Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        public static void setFireSplash(boolean value) {
        FileOutputStream fo = null;
        try {
            fireSplash = value;
            config.setProperty("fireSplash", String.valueOf(value));
            fo = new FileOutputStream(configPath);
            config.store(fo, configPath);
            fo.close();
        } catch (IOException ex) {
            Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void setFireReflect(boolean value) {
        FileOutputStream fo = null;
        try {
            fireReflect = value;
            config.setProperty("fireReflect", String.valueOf(value));
            fo = new FileOutputStream(configPath);
            config.store(fo, configPath);
            fo.close();
        } catch (IOException ex) {
            Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void setFireResist(boolean value) {
        FileOutputStream fo = null;
        try {
            fireResist = value;
            config.setProperty("fireResist", String.valueOf(value));
            fo = new FileOutputStream(configPath);
            config.store(fo, configPath);
            fo.close();
        } catch (IOException ex) {
            Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void setOverWater(boolean value) {
        FileOutputStream fo = null;
        try {
            overWater = value;
            config.setProperty("overWater", String.valueOf(value));
            fo = new FileOutputStream(configPath);
            config.store(fo, configPath);
            fo.close();
        } catch (IOException ex) {
            Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void setOverLava(boolean value) {
        FileOutputStream fo = null;
        try {
            overLava = value;
            config.setProperty("overLava", String.valueOf(value));
            fo = new FileOutputStream(configPath);
            config.store(fo, configPath);
            fo.close();
        } catch (IOException ex) {
            Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void setUnderWater(boolean value) {
        FileOutputStream fo = null;
        try {
            underWater = value;
            config.setProperty("underWater", String.valueOf(value));
            fo = new FileOutputStream(configPath);
            config.store(fo, configPath);
            fo.close();
        } catch (IOException ex) {
            Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void setAllowedPvp(boolean value) {
        FileOutputStream fo = null;
        try {
            allowedPvp = value;
            config.setProperty("allowedPvp", String.valueOf(value));
            fo = new FileOutputStream(configPath);
            config.store(fo, configPath);
            fo.close();
        } catch (IOException ex) {
            Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    public static int getLegsId() {
        return legsId;
    }

    public static int getBootsId() {
        return bootsId;
    }

    public static int getChestId() {
        return chestId;
    }

    public static int getHelmId() {
        return helmId;
    }

    public static int getSwordId() {
        return swordId;
    }

    public static int getPickId() {
        return pickId;
    }

    public static int getAxeId() {
        return axeId;
    }

    public static int getShovelId() {
        return shovelId;
    }

    public static int getFireDuration() {
        return fireDuration;
    }

    public static int getFireReflectDuration() {
        return fireReflectDuration;
    }

    public static boolean isFireReflect() {
        return fireReflect;
    }

    public static boolean isFireResist() {
        return fireResist;
    }

    public static boolean isFireStep() {
        return fireStep;
    }

    public static boolean isFireSword() {
        return fireSword;
    }
    
    public static boolean isFireSplash() {
        return fireSplash;
    }
    /*
    public static boolean isFirePick() {
        return firePick;
    }

    public static boolean isFireAxe() {
        return fireAxe;
    }

    public static boolean isFireShovel() {
        return fireShovel;
    }*/
    public static boolean isFireTools() {
        return fireTools;
    }
    public static boolean isOverLava() {
        return overLava;
    }

    public static boolean isOverWater() {
        return overWater;
    }

    public static boolean isUnderWater() {
        return underWater;
    }

    public static boolean isAllowedPvp() {
        
        return allowedPvp;
    }

    public static int getPercentage() {
        return percentage;
    }

    public static void setPercentage(int percentage) {
        Config.percentage = percentage;
    }

    public static boolean isFireNoFallDamage() {
        return fireNoFallDamage;
    }
    
    public static int getExpansiveQuantum() {
        return expansiveQuantum;
    }
}
