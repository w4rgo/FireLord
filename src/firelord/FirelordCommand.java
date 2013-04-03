package firelord;

import java.util.logging.Logger;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import firelord.tools.FirePerm;
import firelord.tools.FirePlayer;

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
public class FirelordCommand implements CommandExecutor {

    public static final Logger log = Logger.getLogger("Minecraft");
    public static FireLord plugin = null;

    FirelordCommand(FireLord firelord) {
        plugin = firelord;
    }

    public boolean onCommand(CommandSender cs, Command cmnd, String string, String[] strings) {
                String[] split =strings;
        Player player = (Player) cs;
        FirePlayer fp = new FirePlayer(player);
        
        if (fp.getPerm().isAdmin()) {
            return false;
        }

            if (split.length > 0) {
                if (split[0].equalsIgnoreCase("setfiredur")) {
                    if (split.length > 1) {
                        String fdur = split[1];
                        try {
                            Config.setFireDuration(Integer.valueOf(fdur));
                            player.sendMessage( ChatColor.RED +  "FireLord: Duration of fire set as " + fdur + " s");
                        } catch (NumberFormatException e) {
                            player.sendMessage( ChatColor.RED +  "You have to select a numeric value.");
                        }
                    } else {
                        player.sendMessage( ChatColor.RED +  "USAGE: /fl|firelord setfiredur [num]");
                    }
                } else if (split[0].equalsIgnoreCase("setfirerefdur")) {
                    if (split.length > 1) {
                        String fdur = split[1];
                        try {
                            Config.setFireReflectDuration(Integer.valueOf(fdur));
                            player.sendMessage( ChatColor.RED +  "FireLord: Duration of reflect fire set as " + fdur + " s");
                        } catch (NumberFormatException e) {
                            player.sendMessage( ChatColor.RED +  "You have to select a numeric value.");
                        }
                    } else {
                        player.sendMessage( ChatColor.RED +  "USAGE: /fl|firelord setfiredur [num]");
                    }
                } else if (split[0].equalsIgnoreCase("firesword")) {
                    if (split.length > 1) {
                        String value = split[1];
                        if (value.equalsIgnoreCase("on")) {
                            Config.setFireSword(true);
                            player.sendMessage( ChatColor.RED +  "FireLord Sword Activated");
                        } else if (value.equalsIgnoreCase("off")) {
                            Config.setFireSword(false);
                            player.sendMessage( ChatColor.RED +  "FireLord Sword Deactivated");
                        } else {
                            player.sendMessage( ChatColor.RED +  "Write on / off");
                        }
                    } else {
                        player.sendMessage( ChatColor.RED +  "USAGE: /fl|firelord firesword on/off");
                    }
                } else if (split[0].equalsIgnoreCase("fireresist")) {
                    if (split.length > 1) {
                        String value = split[1];
                        if (value.equalsIgnoreCase("on")) {
                            Config.setFireResist(true);
                            player.sendMessage( ChatColor.RED +  "FireLord fire resist Activated");
                        } else if (value.equalsIgnoreCase("off")) {
                            Config.setFireResist(false);
                            player.sendMessage( ChatColor.RED +  "FireLord fire resist Deactivated");
                        } else {
                            player.sendMessage( ChatColor.RED +  "Write on / off");
                        }
                    } else {
                        player.sendMessage( ChatColor.RED +  "USAGE: /fl|firelord fireresist on/off");
                    }
                } else if (split[0].equalsIgnoreCase("firereflect")) {
                    if (split.length > 1) {
                        String value = split[1];
                        if (value.equalsIgnoreCase("on")) {
                            Config.setFireReflect(true);
                            player.sendMessage( ChatColor.RED +  "FireLord fire reflect Activated");
                        } else if (value.equalsIgnoreCase("off")) {
                            Config.setFireReflect(false);
                            player.sendMessage( ChatColor.RED +  "FireLord fire reflect Deactivated");
                        } else {
                            player.sendMessage( ChatColor.RED +  "Write on / off");
                        }
                    } else {
                        player.sendMessage( ChatColor.RED +  "USAGE: /fl|firelord firereflect on/off");
                    }
                } else if (split[0].equalsIgnoreCase("firestep")) {
                    if (split.length > 1) {
                        String value = split[1];
                        if (value.equalsIgnoreCase("on")) {
                            Config.setFireStep(true);
                            player.sendMessage( ChatColor.RED +  "FireLord fire step Activated");
                        } else if (value.equalsIgnoreCase("off")) {
                            Config.setFireStep(false);
                            player.sendMessage( ChatColor.RED +  "FireLord fire step Deactivated");
                        } else {
                            player.sendMessage( ChatColor.RED +  "Write on / off");
                        }
                    } else {
                        player.sendMessage( ChatColor.RED +  "USAGE: /fl|firelord firestep on/off");
                    }
                } else if (split[0].equalsIgnoreCase("overwater")) {
                    if (split.length > 1) {
                        String value = split[1];
                        if (value.equalsIgnoreCase("on")) {
                            Config.setOverWater(true);
                            player.sendMessage( ChatColor.RED +  "FireLord over water Activated");
                        } else if (value.equalsIgnoreCase("off")) {
                            Config.setOverWater(false);
                            player.sendMessage( ChatColor.RED +  "FireLord over water Deactivated");
                        } else {
                            player.sendMessage( ChatColor.RED +  "Write on / off");
                        }
                    } else {
                        player.sendMessage( ChatColor.RED +  "USAGE: /fl|firelord overwater on/off");
                    }
                } else if (split[0].equalsIgnoreCase("overlava")) {
                    if (split.length > 1) {
                        String value = split[1];
                        if (value.equalsIgnoreCase("on")) {
                            Config.setOverLava(true);
                            player.sendMessage( ChatColor.RED +  "FireLord over lava Activated");
                        } else if (value.equalsIgnoreCase("off")) {
                            Config.setOverLava(false);
                            player.sendMessage( ChatColor.RED +  "FireLord over lava Deactivated");
                        } else {
                            player.sendMessage( ChatColor.RED +  "Write on / off");
                        }
                    } else {
                        player.sendMessage( ChatColor.RED +  "USAGE: /fl|firelord overlava on/off");
                    }
                } else if (split[0].equalsIgnoreCase("underwater")) {
                    if (split.length > 1) {
                        String value = split[1];
                        if (value.equalsIgnoreCase("on")) {
                            Config.setUnderWater(true);
                            player.sendMessage( ChatColor.RED +  "FireLord under water Activated");
                        } else if (value.equalsIgnoreCase("off")) {
                            Config.setUnderWater(false);
                            player.sendMessage( ChatColor.RED +  "FireLord under water Deactivated");
                        } else {
                            player.sendMessage( ChatColor.RED +  "Write on / off");
                        }
                    } else {
                        player.sendMessage( ChatColor.RED +  "USAGE: /fl|firelord underwater on/off");
                    }
                } else if (split[0].equalsIgnoreCase("firetools")) {
                    if (split.length > 1) {
                        String value = split[1];
                        if (value.equalsIgnoreCase("on")) {
//                            Config.setFirePick(true);
//                            Config.setFireAxe(true);
//                            Config.setFireShovel(true);
                            Config.setFireTools(true);
                            player.sendMessage( ChatColor.RED +  "FireLord tools activated (pick/shovel/axe) Thanks Samkio!!");
                        } else if (value.equalsIgnoreCase("off")) {
//                            Config.setFirePick(false);
//                            Config.setFireAxe(false);
//                            Config.setFireShovel(false);
                            Config.setFireTools(false);
                            player.sendMessage( ChatColor.RED +  "FireLord tools dectivated");
                        } else {
                            player.sendMessage( ChatColor.RED +  "Write on / off");
                        }
                    } else {
                        player.sendMessage( ChatColor.RED +  "USAGE: /fl|firelord firetools on/off");
                    }
                } else if (split[0].equalsIgnoreCase("luck")) {
                    if (split.length > 1) {
                        
                        int value = Integer.getInteger(split[1]);
                        
                            Config.setPercentage(value);
                            player.sendMessage( ChatColor.RED +  "Luck set to " + value);
                      
                    } else {
                        player.sendMessage( ChatColor.RED +  "USAGE: /fl|firelord luck [value]");
                    }
                   
                } else if (split[0].equalsIgnoreCase("kit")) {
                    if (split.length == 1) {
                            
                            player.getInventory().setBoots(new ItemStack(Config.getBootsId()));
                            player.getInventory().setChestplate(new ItemStack(Config.getChestId()));
                            player.getInventory().setLeggings(new ItemStack(Config.getLegsId()));
                            player.getInventory().setHelmet(new ItemStack(Config.getHelmId()));
                            player.getInventory().addItem(new ItemStack(Config.getAxeId()));
                            player.getInventory().addItem(new ItemStack(Config.getPickId()));
                            player.getInventory().addItem(new ItemStack(Config.getShovelId()));
                            player.getInventory().addItem(new ItemStack(Config.getSwordId()));
                            player.sendMessage( ChatColor.RED +  "You have received the firelord kit!");
                            
                    } else {
                        player.sendMessage( ChatColor.RED +  "USAGE: /fl|firelord luck [value]");
                    }
                    
                } else if (split[0].equalsIgnoreCase("status")) {
                    player.sendMessage( ChatColor.RED +  "############################");
                    player.sendMessage( ChatColor.RED +  "# FIRELORD FEATURES STATUS #");
                    player.sendMessage( ChatColor.RED +  "############################");
                    player.sendMessage( ChatColor.RED +  "");
                    player.sendMessage( ChatColor.RED +  "  Fire Sword = " + Config.isFireSword());
                    player.sendMessage( ChatColor.RED +  "  Fire Reflect Duration = " + Config.getFireReflectDuration() / 20);
                    player.sendMessage( ChatColor.RED +  "  Fire Duration = " + Config.getFireDuration() / 20);
                    player.sendMessage( ChatColor.RED +  "  Fire Resist = " + Config.isFireResist());
                    player.sendMessage( ChatColor.RED +  "  Fire Reflect = " + Config.isFireReflect());
                    player.sendMessage( ChatColor.RED +  "  Fire Step = " + Config.isFireStep());
                    player.sendMessage( ChatColor.RED +  "  Over Water = " + Config.isOverWater());
                    player.sendMessage( ChatColor.RED +  "  Over Lava = " + Config.isOverLava());
                    player.sendMessage( ChatColor.RED +  "  Under water = " + Config.isUnderWater());
                    player.sendMessage( ChatColor.RED +  "  Fire TOOLS = " + Config.isFireTools());
//                    player.sendMessage( ChatColor.RED +  "  Fire pick = " + Config.isFirePick());
//                    player.sendMessage( ChatColor.RED +  "  Fire shovel = " + Config.isFireShovel());
                    player.sendMessage( ChatColor.RED +  "");
                    player.sendMessage( ChatColor.RED +  "############################");
                    //player.sendMessage( ChatColor.RED + "Write on / off");
                }
            } else {
                //Commands explanation
                player.sendMessage( ChatColor.RED +  "############################");
                player.sendMessage( ChatColor.RED +  "# FIRELORD COMMANDS BRIEF  #");
                player.sendMessage( ChatColor.RED +  "############################");
                player.sendMessage( ChatColor.RED +  "");
                player.sendMessage( ChatColor.RED +  "  /fl|firelord");
                player.sendMessage( ChatColor.RED +  "  /fl|firelord status");
                player.sendMessage( ChatColor.RED +  "  /fl|firelord setfirerefdur ");
                player.sendMessage( ChatColor.RED +  "  /fl|firelord setfiredur");
                player.sendMessage( ChatColor.RED +  "  /fl|firelord fireresist on/off");
                player.sendMessage( ChatColor.RED +  "  /fl|firelord firereflect on/off");
                player.sendMessage( ChatColor.RED +  "  /fl|firelord firestep on/off");
                player.sendMessage( ChatColor.RED +  "  /fl|firelord overwater on/off");
                player.sendMessage( ChatColor.RED +  "  /fl|firelord overlava on/off");
                player.sendMessage( ChatColor.RED +  "  /fl|firelord underwater on/off");
                player.sendMessage( ChatColor.RED +  "  /fl|firelord firetools on/off");
                player.sendMessage( ChatColor.RED +  "  /fl|firelord luck [value]");
                
                player.sendMessage( ChatColor.RED +  "");
                player.sendMessage( ChatColor.RED +  "############################");
            }
            
            return true;
        }

    
}
