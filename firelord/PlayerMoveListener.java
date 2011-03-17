package firelord;

import java.util.logging.Logger;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerMoveEvent;

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


