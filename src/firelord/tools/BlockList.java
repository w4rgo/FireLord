/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package firelord.tools;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.bukkit.block.Block;

/**
 *
 * @author Ajax
 */
public class BlockList {

    
    
    private ArrayList<BlockLog> blocks = new ArrayList<BlockLog>();
//    private List<BlockLog> blocks = Collections.synchronizedList(new ArrayList<BlockLog>()); 
    
    public BlockList() {
        
        
    }
    
    
    
    
    
    public List<BlockLog> getBlocks() {
        return blocks;
    }

    public void setBlocks(ArrayList<BlockLog> blocks) {
        this.blocks = blocks;
    }

    public BlockList(ArrayList<BlockLog> blocks) {
        this.blocks= blocks;
    }
    
//    public boolean contains (BlockLog block) {
//        if(block.getLoc())
//    }
//    
}
