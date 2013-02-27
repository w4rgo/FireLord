

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */





package firelord;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;

/**
 *
 * @author Fran
 */
public class BlocksChecks {
   
    
    private static ArrayList<Integer> halfBlocks = new ArrayList<Integer>() {{
             add(26);add(63);add(44);add(67);add(53);add(70);add(55);
             add(72);

        }};
    private static ArrayList<Integer> noFireStep = new ArrayList<Integer>() {{
             add(63);add(64);add(65);add(66);add(68);add(69);
             add(71);add(75);add(76);add(77);add(93);add(94);
        }};

    public static boolean isExcludedFromStep(int value) {
        return noFireStep.contains(value);
    }

    public static boolean isHalfBlock(int value) {
        return halfBlocks.contains(value);
    }

    public static boolean nearLava(Block block) {
        if( (block.getType() == Material.LAVA) ||
           (block.getRelative(BlockFace.UP).getType() == Material.LAVA )||
           (block.getRelative(BlockFace.DOWN).getType() == Material.LAVA)||
           (block.getRelative(BlockFace.NORTH).getType() == Material.LAVA)||
           (block.getRelative(BlockFace.SOUTH).getType() == Material.LAVA)||
           (block.getRelative(BlockFace.NORTH_EAST).getType() == Material.LAVA) || 
           (block.getRelative(BlockFace.NORTH_WEST).getType() == Material.LAVA) ||
           (block.getRelative(BlockFace.SOUTH_EAST).getType() == Material.LAVA) ||
           (block.getRelative(BlockFace.SOUTH_WEST).getType()== Material.LAVA) ||
           (block.getRelative(BlockFace.EAST).getType()== Material.LAVA) ||
            (block.getRelative(BlockFace.WEST).getType() == Material.LAVA) ) {
            
                return true;
        } else return false;
    }

}
