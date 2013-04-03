package firelord.tools;

import firelord.Config;
import java.util.ArrayList;

import java.util.HashMap;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Ajax
 */
public class FirePlayer {

    
    
    private static HashMap<String,FirePlayer> loggedPlayers = new HashMap<String,FirePlayer> ()    ;
    
    private FirePerm perms;
    private Player player;
    private Block lastBlock;
    private static HashMap<Integer, Block> lastBlocks = new HashMap<Integer, Block>();
    private long lastTimeRodUsed=0;

    public static HashMap<String, FirePlayer> getLoggedPlayers() {
        return loggedPlayers;
    }

    public static void setLoggedPlayers(HashMap<String, FirePlayer> loggedPlayers) {
        FirePlayer.loggedPlayers = loggedPlayers;
    }
    
    public FirePlayer(Player player) {
        this.player = player;
        perms = new FirePerm(this);

    }

    public Player getPlayer() {
        return player;
    }

    public FirePerm getPerm() {
        return perms;
    }

    public boolean hasFirelordArmor() {
        return hasFirelordHelmet() && hasFirelordChest() && hasFirelordLegs();
    }

    public boolean hasFirelordSword() {
        if (this.player.getInventory().getItemInHand() != null) {
            return ((this.player.getItemInHand().getTypeId() == Config.getAxeId()) || (this.player.getItemInHand().getTypeId() == Config.getPickId()) || (this.player.getItemInHand().getTypeId() == Config.getShovelId()) || (this.player.getItemInHand().getTypeId() == Config.getSwordId()));
        } else {
            return false;
        }
    }

    public boolean hasFirelordHelmet() {
        if (this.player.getInventory().getHelmet() != null) {
            return this.player.getInventory().getHelmet().getTypeId() == Config.getHelmId();
        } else {
            return false;
        }
    }

    public boolean hasFirelordChest() {
        if (this.player.getInventory().getChestplate() != null) {
            return this.player.getInventory().getChestplate().getTypeId() == Config.getChestId();
        } else {
            return false;
        }
    }

    public boolean hasFirelordLegs() {
        if (this.player.getInventory().getLeggings() != null) {
            return this.player.getInventory().getLeggings().getTypeId() == Config.getLegsId();
        } else {
            return false;
        }
    }

    public boolean hasFirelordBoots() {
        if (this.player.getInventory().getBoots() != null) {
            return this.player.getInventory().getBoots().getTypeId() == Config.getBootsId();
        } else {
            return false;
        }
    }

    public boolean checkLuck() {
        double luck = Math.random() * 100;
        if (Config.getPercentage() >= luck) {
            return true;
        } else {
            return false;
        }
    }

    public Block getLastBlock() {
        return this.lastBlock;
    }

    public void setLastBlock(Block block) {
        this.lastBlock = block;
    }

    public boolean hasFirelordRod() {
        if (this.player.getInventory().getItemInHand() != null) {
            return ((this.player.getItemInHand().getTypeId() == Config.getRodId()));
        } else {
            return false;
        }
    }
    
    public boolean readyRod() {
        
        if(System.currentTimeMillis()> this.lastTimeRodUsed + Config.getRodReuseTime()*1000) {
            return true;
        } else {
            long reuseTime = (this.lastTimeRodUsed + Config.getRodReuseTime()*1000 - System.currentTimeMillis())/1000;
            this.player.sendMessage( reuseTime + " seconds to use rod.");
            return false;
        }
    }

 }
