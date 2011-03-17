package firelord;

import java.util.logging.Logger;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerMoveEvent;
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

public class PlayerMoveListener extends PlayerListener {
    public static final Logger  log = Logger.getLogger("Minecraft");
    public static FireLord plugin = null;

    PlayerMoveListener(FireLord firelord) {
       plugin = firelord;
    }

    @Override
    public void onPlayerMove(PlayerMoveEvent event) {
        super.onPlayerMove(event);
        Player player = event.getPlayer();
        Block blockOn = player.getLocation().getBlock();

        if(PlayerChecks.hasFirelordBoots(player)) {
                //FIRELORD BOOTS WALK OVER FLUIDS
            if(PlayerChecks.allowedOverFluids(player)) {
                if(((blockOn.getType() == Material.WATER)&&Config.isOverWater() )||
                   ((blockOn.getType() == Material.LAVA)&&Config.isOverLava()) ) {
                    //walk Over
                }
            }
            //FIRELORD BOOTS FIRE STEP
            if(PlayerChecks.allowedFireStep(player)&&Config.isFireStep()) {
                if( !BlocksChecks.isExcludedFromStep(blockOn.getTypeId()) )
                    if(BlocksChecks.isHalfBlock(blockOn.getTypeId())) {
                        blockOn.getRelative(BlockFace.UP).setType(Material.FIRE);
                    } else {
                        blockOn.setType(Material.FIRE);
                    }
            }
        }
        //FIRE LORD HELMET UNDERWATER AIR
        if(PlayerChecks.hasFirelordHelmet(player)) {
            if( PlayerChecks.allowedHelmet(player)&&Config.isUnderWater() ) {
                if(blockOn.getType()== Material.WATER ) {
                    player.setRemainingAir(10);
                    player.setMaximumAir(10);
                    System.out.println("Vas amorir");
                }
            }
        }
    }
}


