/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package firelord.threads;

import firelord.tools.BlockLog;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;

/**
 *
 * @author Fran
 */
class RestoreBlockInSecondsThread extends Thread {
    
    //private HashMap <Location,Integer> blocks= new HashMap<Location,Integer>();
    private ArrayList <BlockLog> blocks = new ArrayList<BlockLog>();
   // private ArrayList <BlockList> blockMatrix = new ArrayList<BlockList>();
    private int sec ;
    private World world;
    
   public RestoreBlockInSecondsThread (World world,ArrayList <BlockLog> blocks, int sec) { // Constructor no siempre necesario
     this.world = world;
     this.blocks = blocks;
     this.sec = sec;
   }
   @Override
   public void run() { 
       
       
       try {
            // Sobrecargando al metodo run

            java.lang.Thread.sleep(sec*1000);   
        
        } catch (InterruptedException ex) {
            Logger.getLogger(RestoreBlockInSecondsThread.class.getName()).log(Level.SEVERE, null, ex);
        }
        //ahora,antes
   
        for(BlockLog b : blocks) {
            
            
            world.getBlockAt(b.getLoc()).setTypeId(b.getTypeId());
            //world.getBlockAt(b).setTypeId(blocks.get(b));
        }

   }
}
    