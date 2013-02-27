/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package firelord.threads;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import firelord.tools.*;

/**
 *
 * @author Fran
 */
public class ExpansiveCircleThread extends Thread{

    
    
    private Location loc;
    private World world;
    private Material material;
    private int duration;
    private int lenght;
    private int quantum;
    private boolean replace;
    private ArrayList <BlockList> matrix= new ArrayList<BlockList>();
    public ExpansiveCircleThread(Location loc, World world, Material material, int duration, int lenght,int quantum,boolean replace) {
        this.loc = loc;
        this.world = world;
        this.material = material;
        this.duration = duration;
        this.lenght = lenght;
        this.quantum = quantum;
        this.replace = replace;
    }
    

    
    @Override
    public void run() {
        
        
       //ArrayList <BlockList> matrix = new ArrayList<BlockList>();
        
        long ms=quantum;
        for(int i = 0 ; i<= lenght ; i++) {
            
            matrix.add(new BlockList( BlocksOper.rasterCircle(loc,i, duration, world, material, false,replace)));
            
            
            try {
                java.lang.Thread.sleep(ms);
            } catch (InterruptedException ex) {
                Logger.getLogger(ExpansiveCircleThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
        
    }
    
    public ArrayList <BlockList> getLogMatrix() {
        return this.matrix;
    }
    
    
}
