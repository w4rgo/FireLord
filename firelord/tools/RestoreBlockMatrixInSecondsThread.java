/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package firelord.tools;

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
class RestoreBlockMatrixInSecondsThread extends Thread {
    
    //private HashMap <Location,Integer> blocks= new HashMap<Location,Integer>();
    //private ArrayList <BlockLog> blocks = new ArrayList<BlockLog>();
    private ArrayList <BlockList> blockMatrix = new ArrayList<BlockList>();
    private int sec ;
    private World world;
    private int quantum;
    private Thread mainThread;
    
   public RestoreBlockMatrixInSecondsThread (World world,ArrayList<BlockList> blocks,Thread mainThread, int sec,int quantum) { // Constructor no siempre necesario
     this.world = world;
     this.blockMatrix = blocks;
     this.sec = sec;
     this.quantum = quantum;
     this.mainThread = mainThread;
   }
   @Override
   public void run() { 
            // Sobrecargando al metodo run
       while(mainThread.isAlive()) {
           
           //Wait until the main thread finish
       }
       try {
                    java.lang.Thread.sleep(sec*1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(RestoreBlockMatrixInSecondsThread.class.getName()).log(Level.SEVERE, null, ex);
        }

       for(BlockList list : blockMatrix) {
                
           
           for(BlockLog block : list.getBlocks()) {
               block.restore();
               //System.out.println(block.getTypeId());
           }
           try {
                    java.lang.Thread.sleep(quantum);
                } catch (InterruptedException ex) {
                    Logger.getLogger(RestoreBlockMatrixInSecondsThread.class.getName()).log(Level.SEVERE, null, ex);
                }
           
           
           
           
       }
            
   }
}
    