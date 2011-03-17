package firelord;

import java.util.logging.Logger;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerItemEvent;
import org.bukkit.event.player.PlayerListener;

public class CommandListener extends PlayerListener {
    public static final Logger  log = Logger.getLogger("Minecraft");
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

        if(split[0].equalsIgnoreCase("/fl") || split[0].equalsIgnoreCase("/firelord") ) {
            if(split.length>1) {
                if(split[1].equalsIgnoreCase("setfiredur")) {
                    if(split.length>2) {
                            String fdur = split[2];
                            try {
                                Config.setFireDuration(Integer.valueOf(fdur));
                                Messaging.send(event.getPlayer(),"FireLord: Duration of fire set as " + fdur + " s");
                            } catch (NumberFormatException e) {
                                Messaging.send(event.getPlayer(),"You have to select a numeric value.");
                            }
                    } else Messaging.send(event.getPlayer(),"USAGE: /fl|firelord setfiredur [num]");
                } else if(split[1].equalsIgnoreCase("firesword")) {
                    if(split.length>2) {
                            String value = split[2];
                                if(value.equalsIgnoreCase("on")) {
                                    Config.setFireSword(true);
                                    Messaging.send(event.getPlayer(),"FireLord Sword Activated");
                                }
                                else if (value.equalsIgnoreCase("off")) {
                                    Config.setFireSword(false);
                                    Messaging.send(event.getPlayer(),"FireLord Sword Deactivated");
                                }
                                else  Messaging.send(event.getPlayer(),"Write on / off");
                   }else Messaging.send(event.getPlayer(),"USAGE: /fl|firelord firesword on/off");
                } else if(split[1].equalsIgnoreCase("fireresist")) {
                    if(split.length>2) {
                            String value = split[2];
                                if(value.equalsIgnoreCase("on")) {
                                    Config.setFireResist(true);
                                    Messaging.send(event.getPlayer(),"FireLord fire resist Activated");
                                }
                                else if (value.equalsIgnoreCase("off")) {
                                    Config.setFireResist(false);
                                    Messaging.send(event.getPlayer(),"FireLord fire resist Deactivated");
                                }
                                else  Messaging.send(event.getPlayer(),"Write on / off");
                   }else Messaging.send(event.getPlayer(),"USAGE: /fl|firelord fireresist on/off");
                }else if(split[1].equalsIgnoreCase("firereflect")) {
                    if(split.length>2) {
                            String value = split[2];
                                if(value.equalsIgnoreCase("on")) {
                                    Config.setFireReflect(true);
                                    Messaging.send(event.getPlayer(),"FireLord fire reflect Activated");
                                }
                                else if (value.equalsIgnoreCase("off")) {
                                    Config.setFireReflect(false);
                                    Messaging.send(event.getPlayer(),"FireLord fire reflect Deactivated");
                                }
                                else  Messaging.send(event.getPlayer(),"Write on / off");
                   }else Messaging.send(event.getPlayer(),"USAGE: /fl|firelord firereflect on/off");
                }else if(split[1].equalsIgnoreCase("firestep")) {
                    if(split.length>2) {
                            String value = split[2];
                                if(value.equalsIgnoreCase("on")) {
                                    Config.setFireStep(true);
                                    Messaging.send(event.getPlayer(),"FireLord fire step Activated");
                                }
                                else if (value.equalsIgnoreCase("off")) {
                                    Config.setFireStep(false);
                                    Messaging.send(event.getPlayer(),"FireLord fire step Deactivated");
                                }
                                else  Messaging.send(event.getPlayer(),"Write on / off");
                   }else Messaging.send(event.getPlayer(),"USAGE: /fl|firelord firestep on/off");
                }else if(split[1].equalsIgnoreCase("overwater")) {
                    if(split.length>2) {
                            String value = split[2];
                                if(value.equalsIgnoreCase("on")) {
                                    Config.setOverWater(true);
                                    Messaging.send(event.getPlayer(),"FireLord over water Activated");
                                }
                                else if (value.equalsIgnoreCase("off")) {
                                    Config.setOverWater(false);
                                    Messaging.send(event.getPlayer(),"FireLord over water Deactivated");
                                }
                                else  Messaging.send(event.getPlayer(),"Write on / off");
                   }else Messaging.send(event.getPlayer(),"USAGE: /fl|firelord overwater on/off");
                }else if(split[1].equalsIgnoreCase("overlava")) {
                    if(split.length>2) {
                            String value = split[2];
                                if(value.equalsIgnoreCase("on")) {
                                    Config.setOverLava(true);
                                    Messaging.send(event.getPlayer(),"FireLord over lava Activated");
                                }
                                else if (value.equalsIgnoreCase("off")) {
                                    Config.setOverLava(false);
                                    Messaging.send(event.getPlayer(),"FireLord over lava Deactivated");
                                }
                                else  Messaging.send(event.getPlayer(),"Write on / off");
                   }else Messaging.send(event.getPlayer(),"USAGE: /fl|firelord overlava on/off");
                }else if(split[1].equalsIgnoreCase("underwater")) {
                    if(split.length>2) {
                            String value = split[2];
                                if(value.equalsIgnoreCase("on")) {
                                    Config.setUnderWater(true);
                                    Messaging.send(event.getPlayer(),"FireLord under water Activated");
                                }
                                else if (value.equalsIgnoreCase("off")) {
                                    Config.setUnderWater(false);
                                    Messaging.send(event.getPlayer(),"FireLord under water Deactivated");
                                }
                                else  Messaging.send(event.getPlayer(),"Write on / off");
                   }else Messaging.send(event.getPlayer(),"USAGE: /fl|firelord underwater on/off");
                } else if(split[1].equalsIgnoreCase("status")) {
                    Messaging.send(event.getPlayer(),"############################");
                    Messaging.send(event.getPlayer(),"# FIRELORD FEATURES STATUS #");
                    Messaging.send(event.getPlayer(),"############################");
                    Messaging.send(event.getPlayer(),"");
                    Messaging.send(event.getPlayer(),"  Fire Sword = " + Config.isFireSword());
                    Messaging.send(event.getPlayer(),"  Fire Duration = " + Config.getFireDuration()/20);
                    Messaging.send(event.getPlayer(),"  Fire Resist = " + Config.isFireResist());
                    Messaging.send(event.getPlayer(),"  Fire Reflect = " + Config.isFireReflect());
                    Messaging.send(event.getPlayer(),"  Fire Step = " + Config.isFireStep());
                    Messaging.send(event.getPlayer(),"  Over Water = " + Config.isOverWater());
                    Messaging.send(event.getPlayer(),"  Over Lava = " + Config.isOverLava());
                    Messaging.send(event.getPlayer(),"  Under water = " + Config.isUnderWater());
                    Messaging.send(event.getPlayer(),"");
                    Messaging.send(event.getPlayer(),"############################");
                    //Messaging.send(event.getPlayer(),"Write on / off");
                }
            } else {
                //Commands explanation
                Messaging.send(event.getPlayer(),"############################");
                Messaging.send(event.getPlayer(),"# FIRELORD COMMANDS BRIEF  #");
                Messaging.send(event.getPlayer(),"############################");
                Messaging.send(event.getPlayer(),"");
                Messaging.send(event.getPlayer(),"  /fl|firelord : Commands briefing ");
                Messaging.send(event.getPlayer(),"  /fl|firelord setfiredur : Changes the duration of the sword fire damage ");
                Messaging.send(event.getPlayer(),"  /fl|firelord fireresist on/off : On/off fire/lava res of the armor ");
                Messaging.send(event.getPlayer(),"  /fl|firelord firereflect on/off : Activates /Deactivates reflect damage of armor");
                Messaging.send(event.getPlayer(),"  /fl|firelord firestep on/off : Activates /Deactivates setting the ground on fire with boots");
                Messaging.send(event.getPlayer(),"  /fl|firelord overwater on/off : Activates /Deactivates walking over water with boots ");
                Messaging.send(event.getPlayer(),"  /fl|firelord overlava on/off : Activates /Deactivates walking over lava with boots ");
                Messaging.send(event.getPlayer(),"  /fl|firelord underwater on/off : Activates /Deactivates breathing underwater with helmet ");
                Messaging.send(event.getPlayer(),"");
                Messaging.send(event.getPlayer(),"############################");
            }
        }
    }
  }


