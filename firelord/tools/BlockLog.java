/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package firelord.tools;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import sun.util.calendar.BaseCalendar.Date;

/**
 *
 * @author Ajax
 */
public class BlockLog {

    private World world;
    private Location loc;
    private int typeId;
    private long timePlaced;
    
    
    
    public BlockLog(Block block) {
        loc =block.getLocation();
        typeId = block.getTypeId();
        world = block.getWorld();
        timePlaced = System.currentTimeMillis();
        
    }
    
    public BlockLog(Location loc, int typeId) {
        this.loc = loc;
        this.typeId = typeId;
        this.world = loc.getWorld();
        timePlaced = System.currentTimeMillis();
    }

    public long getTimePlaced() {
        return timePlaced;
    }



    public Location getLoc() {
        return loc;
    }

    public void setLoc(Location loc) {
        this.loc = loc;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }
    
    public synchronized void restore() {
        
        
        
        //System.out.println("ANTES: " +world.getBlockAt(loc).getTypeId());
        //if(world.getBlockAt(loc).getType() == Material.SNOW) {
            //System.out.println(world.getBlockAt(loc).getBiome());
          //  world.getBlockAt(loc).setType(Material.AIR);
            
        //}
        world.getBlockAt(loc).setTypeId(typeId);
        //System.out.println("DP:" + typeId);
        
    }
    
    public void setType(Material material) {
        world.getBlockAt(loc).setType(material);
    }
//    public synchronized void setType(Material material) {
//         SpoutBlock sb = (SpoutBlock) world.getBlockAt(loc);
//        sb.setTypeAsync(material);
//    }
    
}
