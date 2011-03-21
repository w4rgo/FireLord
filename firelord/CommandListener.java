package firelord;

import java.util.logging.Logger;
import org.bukkit.ChatColor;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerListener;

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
public class CommandListener extends PlayerListener {

    public static final Logger log = Logger.getLogger("Minecraft");
    public static FireLord plugin = null;

    CommandListener(FireLord firelord) {
        plugin = firelord;
    }

    @Override
    public void onPlayerCommandPreprocess(PlayerChatEvent event) {
        super.onPlayerCommandPreprocess(event);
        String[] split = event.getMessage().split(" ");
        if (!PlayerChecks.isAdmin(event.getPlayer())) {
            return;
        }

        if (split[0].equalsIgnoreCase("/fl") || split[0].equalsIgnoreCase("/firelord")) {
            if (split.length > 1) {
                if (split[1].equalsIgnoreCase("setfiredur")) {
                    if (split.length > 2) {
                        String fdur = split[2];
                        try {
                            Config.setFireDuration(Integer.valueOf(fdur));
                            event.getPlayer().sendMessage( ChatColor.RED +  "FireLord: Duration of fire set as " + fdur + " s");
                        } catch (NumberFormatException e) {
                            event.getPlayer().sendMessage( ChatColor.RED +  "You have to select a numeric value.");
                        }
                    } else {
                        event.getPlayer().sendMessage( ChatColor.RED +  "USAGE: /fl|firelord setfiredur [num]");
                    }
                } else if (split[1].equalsIgnoreCase("setfirerefdur")) {
                    if (split.length > 2) {
                        String fdur = split[2];
                        try {
                            Config.setFireReflectDuration(Integer.valueOf(fdur));
                            event.getPlayer().sendMessage( ChatColor.RED +  "FireLord: Duration of reflect fire set as " + fdur + " s");
                        } catch (NumberFormatException e) {
                            event.getPlayer().sendMessage( ChatColor.RED +  "You have to select a numeric value.");
                        }
                    } else {
                        event.getPlayer().sendMessage( ChatColor.RED +  "USAGE: /fl|firelord setfiredur [num]");
                    }
                } else if (split[1].equalsIgnoreCase("firesword")) {
                    if (split.length > 2) {
                        String value = split[2];
                        if (value.equalsIgnoreCase("on")) {
                            Config.setFireSword(true);
                            event.getPlayer().sendMessage( ChatColor.RED +  "FireLord Sword Activated");
                        } else if (value.equalsIgnoreCase("off")) {
                            Config.setFireSword(false);
                            event.getPlayer().sendMessage( ChatColor.RED +  "FireLord Sword Deactivated");
                        } else {
                            event.getPlayer().sendMessage( ChatColor.RED +  "Write on / off");
                        }
                    } else {
                        event.getPlayer().sendMessage( ChatColor.RED +  "USAGE: /fl|firelord firesword on/off");
                    }
                } else if (split[1].equalsIgnoreCase("fireresist")) {
                    if (split.length > 2) {
                        String value = split[2];
                        if (value.equalsIgnoreCase("on")) {
                            Config.setFireResist(true);
                            event.getPlayer().sendMessage( ChatColor.RED +  "FireLord fire resist Activated");
                        } else if (value.equalsIgnoreCase("off")) {
                            Config.setFireResist(false);
                            event.getPlayer().sendMessage( ChatColor.RED +  "FireLord fire resist Deactivated");
                        } else {
                            event.getPlayer().sendMessage( ChatColor.RED +  "Write on / off");
                        }
                    } else {
                        event.getPlayer().sendMessage( ChatColor.RED +  "USAGE: /fl|firelord fireresist on/off");
                    }
                } else if (split[1].equalsIgnoreCase("firereflect")) {
                    if (split.length > 2) {
                        String value = split[2];
                        if (value.equalsIgnoreCase("on")) {
                            Config.setFireReflect(true);
                            event.getPlayer().sendMessage( ChatColor.RED +  "FireLord fire reflect Activated");
                        } else if (value.equalsIgnoreCase("off")) {
                            Config.setFireReflect(false);
                            event.getPlayer().sendMessage( ChatColor.RED +  "FireLord fire reflect Deactivated");
                        } else {
                            event.getPlayer().sendMessage( ChatColor.RED +  "Write on / off");
                        }
                    } else {
                        event.getPlayer().sendMessage( ChatColor.RED +  "USAGE: /fl|firelord firereflect on/off");
                    }
                } else if (split[1].equalsIgnoreCase("firestep")) {
                    if (split.length > 2) {
                        String value = split[2];
                        if (value.equalsIgnoreCase("on")) {
                            Config.setFireStep(true);
                            event.getPlayer().sendMessage( ChatColor.RED +  "FireLord fire step Activated");
                        } else if (value.equalsIgnoreCase("off")) {
                            Config.setFireStep(false);
                            event.getPlayer().sendMessage( ChatColor.RED +  "FireLord fire step Deactivated");
                        } else {
                            event.getPlayer().sendMessage( ChatColor.RED +  "Write on / off");
                        }
                    } else {
                        event.getPlayer().sendMessage( ChatColor.RED +  "USAGE: /fl|firelord firestep on/off");
                    }
                } else if (split[1].equalsIgnoreCase("overwater")) {
                    if (split.length > 2) {
                        String value = split[2];
                        if (value.equalsIgnoreCase("on")) {
                            Config.setOverWater(true);
                            event.getPlayer().sendMessage( ChatColor.RED +  "FireLord over water Activated");
                        } else if (value.equalsIgnoreCase("off")) {
                            Config.setOverWater(false);
                            event.getPlayer().sendMessage( ChatColor.RED +  "FireLord over water Deactivated");
                        } else {
                            event.getPlayer().sendMessage( ChatColor.RED +  "Write on / off");
                        }
                    } else {
                        event.getPlayer().sendMessage( ChatColor.RED +  "USAGE: /fl|firelord overwater on/off");
                    }
                } else if (split[1].equalsIgnoreCase("overlava")) {
                    if (split.length > 2) {
                        String value = split[2];
                        if (value.equalsIgnoreCase("on")) {
                            Config.setOverLava(true);
                            event.getPlayer().sendMessage( ChatColor.RED +  "FireLord over lava Activated");
                        } else if (value.equalsIgnoreCase("off")) {
                            Config.setOverLava(false);
                            event.getPlayer().sendMessage( ChatColor.RED +  "FireLord over lava Deactivated");
                        } else {
                            event.getPlayer().sendMessage( ChatColor.RED +  "Write on / off");
                        }
                    } else {
                        event.getPlayer().sendMessage( ChatColor.RED +  "USAGE: /fl|firelord overlava on/off");
                    }
                } else if (split[1].equalsIgnoreCase("underwater")) {
                    if (split.length > 2) {
                        String value = split[2];
                        if (value.equalsIgnoreCase("on")) {
                            Config.setUnderWater(true);
                            event.getPlayer().sendMessage( ChatColor.RED +  "FireLord under water Activated");
                        } else if (value.equalsIgnoreCase("off")) {
                            Config.setUnderWater(false);
                            event.getPlayer().sendMessage( ChatColor.RED +  "FireLord under water Deactivated");
                        } else {
                            event.getPlayer().sendMessage( ChatColor.RED +  "Write on / off");
                        }
                    } else {
                        event.getPlayer().sendMessage( ChatColor.RED +  "USAGE: /fl|firelord underwater on/off");
                    }
                } else if (split[1].equalsIgnoreCase("firetools")) {
                    if (split.length > 2) {
                        String value = split[2];
                        if (value.equalsIgnoreCase("on")) {
                            Config.setFirePick(true);
                            Config.setFireAxe(true);
                            Config.setFireShovel(true);
                            event.getPlayer().sendMessage( ChatColor.RED +  "FireLord tools activated (pick/shovel/axe) Thanks Samkio!!");
                        } else if (value.equalsIgnoreCase("off")) {
                            Config.setFirePick(false);
                            Config.setFireAxe(false);
                            Config.setFireShovel(false);
                            event.getPlayer().sendMessage( ChatColor.RED +  "FireLord tools dectivated");
                        } else {
                            event.getPlayer().sendMessage( ChatColor.RED +  "Write on / off");
                        }
                    } else {
                        event.getPlayer().sendMessage( ChatColor.RED +  "USAGE: /fl|firelord underwater on/off");
                    }
                } else if (split[1].equalsIgnoreCase("status")) {
                    event.getPlayer().sendMessage( ChatColor.RED +  "############################");
                    event.getPlayer().sendMessage( ChatColor.RED +  "# FIRELORD FEATURES STATUS #");
                    event.getPlayer().sendMessage( ChatColor.RED +  "############################");
                    event.getPlayer().sendMessage( ChatColor.RED +  "");
                    event.getPlayer().sendMessage( ChatColor.RED +  "  Fire Sword = " + Config.isFireSword());
                    event.getPlayer().sendMessage( ChatColor.RED +  "  Fire Reflect Duration = " + Config.getFireReflectDuration() / 20);
                    event.getPlayer().sendMessage( ChatColor.RED +  "  Fire Duration = " + Config.getFireDuration() / 20);
                    event.getPlayer().sendMessage( ChatColor.RED +  "  Fire Resist = " + Config.isFireResist());
                    event.getPlayer().sendMessage( ChatColor.RED +  "  Fire Reflect = " + Config.isFireReflect());
                    event.getPlayer().sendMessage( ChatColor.RED +  "  Fire Step = " + Config.isFireStep());
                    event.getPlayer().sendMessage( ChatColor.RED +  "  Over Water = " + Config.isOverWater());
                    event.getPlayer().sendMessage( ChatColor.RED +  "  Over Lava = " + Config.isOverLava());
                    event.getPlayer().sendMessage( ChatColor.RED +  "  Under water = " + Config.isUnderWater());
                    event.getPlayer().sendMessage( ChatColor.RED +  "  Fire axe = " + Config.isFireAxe());
                    event.getPlayer().sendMessage( ChatColor.RED +  "  Fire pick = " + Config.isFirePick());
                    event.getPlayer().sendMessage( ChatColor.RED +  "  Fire shovel = " + Config.isFireShovel());
                    event.getPlayer().sendMessage( ChatColor.RED +  "");
                    event.getPlayer().sendMessage( ChatColor.RED +  "############################");
                    //event.getPlayer().sendMessage( ChatColor.RED + "Write on / off");
                }
            } else {
                //Commands explanation
                event.getPlayer().sendMessage( ChatColor.RED +  "############################");
                event.getPlayer().sendMessage( ChatColor.RED +  "# FIRELORD COMMANDS BRIEF  #");
                event.getPlayer().sendMessage( ChatColor.RED +  "############################");
                event.getPlayer().sendMessage( ChatColor.RED +  "");
                event.getPlayer().sendMessage( ChatColor.RED +  "  /fl|firelord");
                event.getPlayer().sendMessage( ChatColor.RED +  "  /fl|firelord status");
                event.getPlayer().sendMessage( ChatColor.RED +  "  /fl|firelord setfirerefdur ");
                event.getPlayer().sendMessage( ChatColor.RED +  "  /fl|firelord setfiredur");
                event.getPlayer().sendMessage( ChatColor.RED +  "  /fl|firelord fireresist on/off");
                event.getPlayer().sendMessage( ChatColor.RED +  "  /fl|firelord firereflect on/off");
                event.getPlayer().sendMessage( ChatColor.RED +  "  /fl|firelord firestep on/off");
                event.getPlayer().sendMessage( ChatColor.RED +  "  /fl|firelord overwater on/off");
                event.getPlayer().sendMessage( ChatColor.RED +  "  /fl|firelord overlava on/off");
                event.getPlayer().sendMessage( ChatColor.RED +  "  /fl|firelord underwater on/off");
                event.getPlayer().sendMessage( ChatColor.RED +  "  /fl|firelord firetools on/off");
                event.getPlayer().sendMessage( ChatColor.RED +  "");
                event.getPlayer().sendMessage( ChatColor.RED +  "############################");
            }
        }
    }
}
