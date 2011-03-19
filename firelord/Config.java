/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package firelord;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.nijiko.permissions.PermissionHandler;
import java.io.File;

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


public class Config{
    public static PermissionHandler Permissions;
    private static String configPath = "plugins/FireLord/config.properties";
    private static String configFile = "config.properties";
    private static int fireDuration = 50;

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

    private static boolean fireSword=true;
    private static boolean firePick=true;
    private static boolean fireAxe=true;
    private static boolean fireShovel=true;
    private static boolean fireResist=true;
    private static boolean fireReflect=true;
    private static boolean fireStep=false;
    private static boolean overWater=false;
    private static boolean overLava=false;
    private static boolean underWater=false;


    private static boolean boolValue(String value) {
        if(value.equalsIgnoreCase("true")) return true;
        else if(value.equalsIgnoreCase("false")) return false;
        else return false;
    }

    public static void loadConfig(File filePath) throws FileNotFoundException, IOException {
        configPath = filePath.toString() + File.separator + configFile;
        config = new Properties();

        fi = new FileInputStream(Config.configPath);

        config.load(fi);
        
        Config.fireDuration = Integer.valueOf(config.getProperty("fireduration")) * 20;

        Config.fireSword= boolValue(config.getProperty("fireSword"));
        Config.firePick= boolValue(config.getProperty("firePick"));
        Config.fireAxe= boolValue(config.getProperty("fireAxe"));
        Config.fireShovel= boolValue(config.getProperty("fireShovel"));
        Config.fireResist =boolValue(config.getProperty("fireResist"));
        Config.fireReflect = boolValue(config.getProperty("fireReflect"));
        Config.fireStep = boolValue(config.getProperty("fireStep"));
        Config.overWater = boolValue(config.getProperty("overWater"));
        Config.overLava = boolValue(config.getProperty("overLava"));
        Config.underWater = boolValue(config.getProperty("underWater"));

//        Config.fireSword= Boolean.getBoolean(config.getProperty("fireSword").toString());
//        Config.fireResist = Boolean.getBoolean(config.getProperty("fireResist").toString());
//        Config.fireReflect = Boolean.getBoolean(config.getProperty("fireReflect").toString());
//        Config.fireStep = Boolean.getBoolean(config.getProperty("fireStep").toString());
//        Config.overWater = Boolean.getBoolean(config.getProperty("overWater").toString());
//        Config.overLava = Boolean.getBoolean(config.getProperty("overLava").toString());
//        Config.underWater = Boolean.getBoolean(config.getProperty("underWater").toString());
        
        //Item Ids
        Config.bootsId = Integer.valueOf(config.getProperty("boots"));
        Config.chestId = Integer.valueOf(config.getProperty("chestplate"));
        Config.helmId = Integer.valueOf(config.getProperty("helmet"));
        Config.legsId = Integer.valueOf(config.getProperty("leggings"));
        Config.swordId = Integer.valueOf(config.getProperty("sword"));
        Config.pickId = Integer.valueOf(config.getProperty("pick"));
        Config.axeId = Integer.valueOf(config.getProperty("axe"));
        Config.shovelId = Integer.valueOf(config.getProperty("shovel"));

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
    public static void setFireStep(boolean value) {
        FileOutputStream fo = null;
        try {
            fireStep=value;
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
    public static void setFirePick(boolean value) {
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
    public static boolean isFirePick() {
        return firePick;
    }
    public static boolean isFireAxe() {
        return fireAxe;
    }
    public static boolean isFireShovel() {
        return fireShovel;
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
}
