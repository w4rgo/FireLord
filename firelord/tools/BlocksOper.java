package firelord.tools;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import firelord.threads.ExpansiveCircleThread;
import java.util.ArrayList;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 *
 * @author Fran
 */
public class BlocksOper {
    
//    public static void setBlock(World world,Material material,Location loc,ArrayList<BlockLog> blocks,boolean replace) {
//        
//        boolean isAir = world.getBlockAt(loc).getType()== Material.AIR;
//        boolean blockDownIsAir =  world.getBlockAt(loc).getRelative(BlockFace.DOWN).getType() == Material.AIR;
//        boolean blockDownIsSnow =  world.getBlockAt(loc).getRelative(BlockFace.DOWN).getType() == Material.SNOW;
//        boolean isNotSame = world.getBlockAt(loc).getType() != material;
//        Block block; 
//        if(replace) {
//            if(isNotSame) {
//                BlockLog blog = new BlockLog(loc.getBlock());
//                blocks.add(blog);
//                world.getBlockAt(loc).setType(material);
//            }
//        } else {
//            if(isAir&&!blockDownIsAir&&!blockDownIsSnow) {
//                BlockLog blog = new BlockLog(loc.getBlock());
//                blocks.add(blog);
//                world.getBlockAt(loc).setType(material);
//            }
//        }
//        
//       
//       
//    }
    
        public static void setBlock(World world,Material material,Location loc,ArrayList<BlockLog> blocks,boolean onTop) {
        
        boolean isAir = world.getBlockAt(loc).getType()== Material.AIR;
        boolean blockDownIsAir =  world.getBlockAt(loc).getRelative(BlockFace.DOWN).getType() == Material.AIR;
        boolean blockDownIsSnow =  world.getBlockAt(loc).getRelative(BlockFace.DOWN).getType() == Material.SNOW;
        boolean isNotSame = world.getBlockAt(loc).getType() != material;

        
        if(onTop) {
            if(isNotSame) {
                Block blockFloor = calculateFloorBlock(loc.getBlock());
                BlockLog blog = new BlockLog(blockFloor);
                blocks.add(blog);
                //world.dropItemNaturally(blockFloor.getLocation(), new ItemStack(blockFloor.getTypeId(),1));
                blockFloor.setType(material);
            }
        } else {
            if(isAir&&!blockDownIsAir) {
                BlockLog blog = new BlockLog(loc.getBlock());
                blocks.add(blog);
                world.getBlockAt(loc).setType(material);
            }
        }
        
       
       
    }
    
    
    public static ArrayList <BlockLog> rasterCircle (Location loc,int radius,int duration,World world,Material material,boolean full,boolean replace) {
        
        ArrayList <BlockLog> blocks = new ArrayList <BlockLog> ();
        int x0=loc.getBlockX();
        int y0=loc.getBlockZ();
        int z0=loc.getBlockY();
        int f = 1 - radius;
        int ddF_x = 1;
        int ddF_y = -2 * radius;
        int x = 0;
        int y = radius;
        
        
        setBlock(world,material,world.getBlockAt(x0, z0, y0+radius).getLocation(),blocks,replace);
        setBlock(world,material,world.getBlockAt(x0,z0, y0-radius).getLocation(),blocks,replace);
        
        if(!full) {
            setBlock(world,material,world.getBlockAt(x0+radius, z0, y0).getLocation(),blocks,replace);
            setBlock(world,material,world.getBlockAt(x0-radius, z0, y0).getLocation(),blocks,replace);
        } else
        createLine(world.getBlockAt(x0-radius, z0, y0).getLocation(), world.getBlockAt(x0+radius, z0, y0).getLocation(),world,material, duration,replace);
       
        while (x<y) {
            if(f>=0) {
                y--;
                ddF_x += 2;
                f+= ddF_y;
            }
            x++;
            ddF_x += 2;
            f +=ddF_x;
            
            if(!full) {
                setBlock(world,material,world.getBlockAt(x0+x, z0,y0+y).getLocation(),blocks,replace);
                setBlock(world,material,world.getBlockAt(x0-x,z0, y0+y).getLocation(),blocks,replace);
            }else createLine(world.getBlockAt(x0-x, z0,y0+y).getLocation(), world.getBlockAt(x0+x,z0, y0+y).getLocation(),world,material, duration,replace);
            
            
            if(!full) {
                setBlock(world,material,world.getBlockAt(x0+x,z0, y0-y).getLocation(),blocks,replace);
                setBlock(world,material,world.getBlockAt(x0-x,z0, y0-y).getLocation(),blocks,replace);

            } else createLine(world.getBlockAt(x0-x,z0, y0-y).getLocation(), world.getBlockAt(x0+x,z0, y0-y).getLocation(),world,material, duration,replace);
            
            if(!full) {
                setBlock(world,material,world.getBlockAt(x0+y,z0, y0+x).getLocation(),blocks,replace);
                setBlock(world,material,world.getBlockAt(x0-y,z0, y0+x).getLocation(),blocks,replace);
            } else createLine(world.getBlockAt(x0-y,z0, y0+x).getLocation(), world.getBlockAt(x0+y,z0, y0+x).getLocation(),world,material, duration,replace);
            
            if(!full) {
                setBlock(world,material,world.getBlockAt(x0+y,z0, y0-x).getLocation(),blocks,replace);
                setBlock(world,material,world.getBlockAt(x0-y,z0, y0-x).getLocation(),blocks,replace);                
            } else createLine(world.getBlockAt(x0-y,z0, y0-x).getLocation(), world.getBlockAt(x0+y,z0, y0-x).getLocation(),world,material, duration,replace);
        }
//for(BlockLog b : blocks) {
//            System.out.println(b.getTypeId());
//        }
        
        
        if (duration > 0) {
            return blocks;
        } else return new ArrayList();
        
    }

    public static BlockList createPillar(Location loc,int height,int radius,World world,Material material,int duration,boolean replace) {
        int x0 = loc.getBlockX();
        int y0 = loc.getBlockZ();
        int z0 = loc.getBlockY();
        ArrayList <BlockLog> blocks= new ArrayList <BlockLog>();
        //System.out.println("Crea pilar");
        int z = z0;
        
        
        for(int i = 0; i<height;i++ ) {
            
            Block block = world.getBlockAt(x0, z+i, y0);
            
            //blocks.add(new BlockLog(block.getLocation(),block.getTypeId()));
            blocks.addAll(rasterCircle(block.getLocation(),radius,duration,world,material,false,replace));
            
            //block.setType(material);
            
         
        }
//          
//            RestoreBlockInSecondsThread rbst = new RestoreBlockInSecondsThread(world,blocks, duration);
//            rbst.start();
        if (duration > 0) return new BlockList(blocks);
        else return new BlockList(new ArrayList());
        
    }

    public static BlockList createLine(Location loc1, Location loc2,World world,Material material, int duration,boolean replace) {
        
        int x1 = loc1.getBlockX();
        int y1 = loc1.getBlockY();
        int z1 = loc1.getBlockZ();
        ArrayList <BlockLog> blocks= new ArrayList <BlockLog>();
        int x2 = loc2.getBlockX();
        int y2 = loc2.getBlockY();
        int z2 = loc2.getBlockZ();
        int lenght = (int) loc2.distance(loc1);
        
        for(int i =0;i <= lenght;i++) {
            
            
            setBlock(world,material,world.getBlockAt(x1+i, y1, z1).getLocation(),blocks,replace);
            //Block block = world.getBlockAt(x1+i, y1, z1);
            //blocks.add(new BlockLog(block.getLocation(),block.getTypeId()));
            //block.setType(material);
            
         
        }
        if(duration >0 ) {
            return new BlockList(blocks);
        } else return new BlockList(new ArrayList());
    }

    
    public static void createExpansiveCircle(Location loc,World world,Material material, int duration, int lenght,int quantum,boolean replace) {
        
        
        
        
        ExpansiveCircleThread ect = new ExpansiveCircleThread(loc,world,material,duration,lenght,quantum,replace);
        ect.start();
        
        //while(ect.isAlive()) {
            
        //}
        //System.out.println(ect.getLogMatrix().toString());
        RestoreBlockMatrixInSecondsThread rbsmt = new RestoreBlockMatrixInSecondsThread(world,ect.getLogMatrix(),ect,duration,quantum);
        rbsmt.start();
        
        
        
    }

    public static Block calculateFloorBlock(Block block) {
        int max=5;//Max
        Block blockSelected=null;
        int count=0;
        //if the block is air, then get the next block with less height
        if(block.getType() == Material.AIR) {

            while( (blockSelected==null)&&(count < max)) { 
            
                if(block.getType()!= Material.AIR) {
                    blockSelected=block;
                    break;
                }
                block = block.getRelative(BlockFace.DOWN);

                count++;
            }
        //If the block have other not air block on top , get the block on top
        } else if(block.getRelative(BlockFace.UP).getType()!= Material.AIR){
            
            
            
            while( (blockSelected==null)&&(count < max)) { 
            
                if(block.getType()== Material.AIR) {
                    blockSelected=block;
                    break;
                }
                block = block.getRelative(BlockFace.UP);

                count++;
            }
        }
        if(blockSelected==null) return block;
        return blockSelected.getRelative(BlockFace.UP);
    }
    
public static Block calculateNextBlock(Player player) {
        Location loc= player.getLocation();
        
        double umbral=0.4;
        
        double xa = loc.getX() - Math.floor(loc.getX());
        double za = loc.getZ() - Math.floor(loc.getZ());
        //System.out.println(" ( " + xa + " - " + za + " ) ");
        //2,2
        if ((xa > 1 - umbral) && (za > 1 - umbral)     ) {
            //11
            Location loca = new Location(player.getWorld(),Math.ceil(loc.getX()) ,loc.getBlockY() ,Math.ceil(loc.getZ())    );
            return loca.getBlock();
            
        } else if ((xa <umbral) && (za > 1 - umbral)) {
        //02
            Location loca = new Location(player.getWorld(),Math.floor(loc.getX())-1 ,loc.getBlockY() ,Math.ceil(loc.getZ())    );
            return loca.getBlock();
        } else if((xa >1-umbral) && (za < umbral)) {
        //20
         Location loca = new Location(player.getWorld(),Math.ceil(loc.getX()) ,loc.getBlockY() ,Math.floor(loc.getZ())-1    );
            return loca.getBlock();
        } else if((xa <umbral) && (za < umbral)) {
        //00
         Location loca = new Location(player.getWorld(),Math.floor(loc.getX())-1 ,loc.getBlockY() ,Math.floor(loc.getZ())-1    );
            return loca.getBlock();
            
        } else if(za > 1 - umbral) {
            Location loca = new Location(player.getWorld(),loc.getX() ,loc.getBlockY() ,Math.ceil(loc.getBlockZ())    );
            return loca.getBlock();
        } else if(za < umbral) {
            Location loca = new Location(player.getWorld(),loc.getX() ,loc.getBlockY() ,Math.floor(loc.getBlockZ())-1    );
            return loca.getBlock();
        }else if(xa > 1 - umbral) {
            Location loca = new Location(player.getWorld(),Math.ceil(loc.getX()) ,loc.getBlockY() ,loc.getBlockZ()    );
            return loca.getBlock();
        } else if(xa < umbral) {
            Location loca = new Location(player.getWorld(),Math.floor(loc.getX()) -1,loc.getBlockY() ,loc.getBlockZ()    );
            return loca.getBlock();
        } else return loc.getBlock();
        
        
        
    }

public static BlockList calculateNextBlocks(Player player) {
        Location loc= player.getLocation();
        BlockList blocks = new BlockList(new ArrayList<BlockLog>());
        double umbral=0.4999;
        
        double xa = loc.getX() - Math.floor(loc.getX());
        double za = loc.getZ() - Math.floor(loc.getZ());
        //System.out.println(" ( " + xa + " - " + za + " ) ");
        //2,2
        if ((xa > 1 - umbral) && (za > 1 - umbral)     ) {
            //11
            Location loca = new Location(player.getWorld(),Math.ceil(loc.getX()) ,loc.getBlockY() ,Math.ceil(loc.getZ())    );
            blocks.getBlocks().add(new BlockLog(loca.getBlock()));

            
        } 
        
        if ((xa <umbral) && (za > 1 - umbral)) {
        //02
            Location loca = new Location(player.getWorld(),Math.floor(loc.getX())-1 ,loc.getBlockY() ,Math.ceil(loc.getZ())    );
            blocks.getBlocks().add(new BlockLog(loca.getBlock()));
        }
        if((xa >1-umbral) && (za < umbral)) {
        //20
         Location loca = new Location(player.getWorld(),Math.ceil(loc.getX()) ,loc.getBlockY() ,Math.floor(loc.getZ())-1    );
            blocks.getBlocks().add(new BlockLog(loca.getBlock()));
        }
        if((xa <umbral) && (za < umbral)) {
        //00
         Location loca = new Location(player.getWorld(),Math.floor(loc.getX())-1 ,loc.getBlockY() ,Math.floor(loc.getZ())-1    );
            blocks.getBlocks().add(new BlockLog(loca.getBlock()));
            
        }
        if(za > 1 - umbral) {
            Location loca = new Location(player.getWorld(),loc.getX() ,loc.getBlockY() ,Math.ceil(loc.getBlockZ())    );
            blocks.getBlocks().add(new BlockLog(loca.getBlock()));
        }
        if(za < umbral) {
            Location loca = new Location(player.getWorld(),loc.getX() ,loc.getBlockY() ,Math.floor(loc.getBlockZ())-1    );
            blocks.getBlocks().add(new BlockLog(loca.getBlock()));
        }
        if(xa > 1 - umbral) {
            Location loca = new Location(player.getWorld(),Math.ceil(loc.getX()) ,loc.getBlockY() ,loc.getBlockZ()    );
            blocks.getBlocks().add(new BlockLog(loca.getBlock()));
        }
        if(xa < umbral) {
            Location loca = new Location(player.getWorld(),Math.floor(loc.getX()) -1,loc.getBlockY() ,loc.getBlockZ()    );
            blocks.getBlocks().add(new BlockLog(loca.getBlock()));
        }
        
        blocks.getBlocks().add(new BlockLog(loc.getBlock()));
        return blocks;
        
        
    }

public static BlockFace numerizeFace(int num) {
    
    switch( num) {
        case 0 : return BlockFace.SOUTH_WEST;
        case 1 : return BlockFace.SOUTH;
        case 2 : return BlockFace.SOUTH_EAST;
        case 3 : return BlockFace.WEST;
        case 4: return BlockFace.SELF;
        case 5: return BlockFace.EAST;
        case 6: return BlockFace.NORTH_WEST;
        case 7: return BlockFace.NORTH;
        case 8: return BlockFace.NORTH_EAST;
    }
    return BlockFace.SELF;
}  


}




//public static ArrayList<BlockLog> rasterCircle (Location loc,int radius,int duration,World world,Material material,boolean full) {
//        
//        ArrayList <BlockLog> blocks = new ArrayList <BlockLog> ();
//        int x0=loc.getBlockX();
//        int y0=loc.getBlockZ();
//        int z0=loc.getBlockY();
//        int f = 1 - radius;
//        int ddF_x = 1;
//        int ddF_y = -2 * radius;
//        int x = 0;
//        int y = radius;
//  
//        
//        
//        if(world.getBlockAt(x0, z0, y0+radius).getType() == Material.AIR) {
//            blocks.add( new BlockLog(world.getBlockAt(x0, z0, y0+radius).getLocation(),world.getBlockAt(x0, z0, y0+radius).getTypeId()) );
//            world.getBlockAt(x0, z0, y0+radius).setType(material);
//        }
//        if(world.getBlockAt(x0,z0, y0-radius).getType() == Material.AIR) {
//                blocks.add( new BlockLog( world.getBlockAt(x0,z0, y0-radius).getLocation(),world.getBlockAt(x0,z0, y0-radius).getTypeId() ));
//                world.getBlockAt(x0,z0, y0-radius).setType(material);
//        }
//        
//
//
//        if(!full) {
//            if(world.getBlockAt(x0+radius, z0, y0).getType() == Material.AIR) {
//                blocks.add( new BlockLog(world.getBlockAt(x0+radius, z0, y0).getLocation(),world.getBlockAt(x0+radius, z0, y0).getTypeId()));
//                world.getBlockAt(x0+radius, z0, y0).setType(material);
//            }
//            if(world.getBlockAt(x0-radius, z0, y0).getType() == Material.AIR) {
//                blocks.add( new BlockLog(world.getBlockAt(x0-radius, z0, y0).getLocation(),world.getBlockAt(x0-radius, z0, y0).getTypeId()));
//                world.getBlockAt(x0-radius, z0, y0).setType(material);
//            }
//
//        } else
//        createLine(world.getBlockAt(x0-radius, z0, y0).getLocation(), world.getBlockAt(x0+radius, z0, y0).getLocation(),world,material, duration);
//       
//        while (x<y) {
//            if(f>=0) {
//                y--;
//                ddF_x += 2;
//                f+= ddF_y;
//            }
//            x++;
//            ddF_x += 2;
//            f +=ddF_x;
//            
//            if(!full) {
//                if(world.getBlockAt(x0+x, z0,y0+y).getType() == Material.AIR) {
//                    blocks.add( new BlockLog(world.getBlockAt(x0+x, z0,y0+y).getLocation(),world.getBlockAt(x0+x, z0,y0+y).getTypeId()));
//                    world.getBlockAt(x0+x, z0,y0+y).setType(material);
//                }
//                if(world.getBlockAt(x0-x,z0, y0+y).getType() == Material.AIR) {
//                    blocks.add( new BlockLog(world.getBlockAt(x0-x,z0, y0+y).getLocation(),world.getBlockAt(x0-x,z0, y0+y).getTypeId()));
//                    world.getBlockAt(x0-x,z0, y0+y).setType(material);
//                }
//
//            }else createLine(world.getBlockAt(x0-x, z0,y0+y).getLocation(), world.getBlockAt(x0+x,z0, y0+y).getLocation(),world,material, duration);
//            
//            
//            if(!full) {
//                if(world.getBlockAt(x0+x,z0, y0-y).getType() == Material.AIR) {
//                    blocks.add( new BlockLog(world.getBlockAt(x0+x,z0, y0-y).getLocation(),world.getBlockAt(x0+x,z0, y0-y).getTypeId()));
//                    world.getBlockAt(x0+x,z0, y0-y).setType(material);
//                }
//                if(world.getBlockAt(x0-x,z0, y0-y).getType() == Material.AIR) {
//                    blocks.add( new BlockLog(world.getBlockAt(x0-x,z0, y0-y).getLocation(),world.getBlockAt(x0-x,z0, y0-y).getTypeId()));
//                    world.getBlockAt(x0-x,z0, y0-y).setType(material);
//                }
//            } else createLine(world.getBlockAt(x0-x,z0, y0-y).getLocation(), world.getBlockAt(x0+x,z0, y0-y).getLocation(),world,material, duration);
//            if(!full) {
//                if(world.getBlockAt(x0+y,z0, y0+x).getType() == Material.AIR) {
//                    blocks.add( new BlockLog(world.getBlockAt(x0+y,z0, y0+x).getLocation(),world.getBlockAt(x0+y,z0, y0+x).getTypeId()));
//                    world.getBlockAt(x0+y,z0, y0+x).setType(material); 
//                } 
//                if(world.getBlockAt(x0-y,z0, y0+x).getType() == Material.AIR) {
//                    blocks.add( new BlockLog(world.getBlockAt(x0-y,z0, y0+x).getLocation(),world.getBlockAt(x0-y,z0, y0+x).getTypeId()));
//                    world.getBlockAt(x0-y,z0, y0+x).setType(material);
//                }
//                } else createLine(world.getBlockAt(x0-y,z0, y0+x).getLocation(), world.getBlockAt(x0+y,z0, y0+x).getLocation(),world,material, duration);
//            if(!full) {
//                if(world.getBlockAt(x0+y,z0, y0-x).getType() == Material.AIR) {
//                    blocks.add( new BlockLog(world.getBlockAt(x0+y,z0, y0-x).getLocation(),world.getBlockAt(x0+y,z0, y0-x).getTypeId()));
//                    world.getBlockAt(x0+y,z0, y0-x).setType(material);
//                }
//                if(world.getBlockAt(x0-y,z0, y0-x).getType() == Material.AIR) {
//                    blocks.add( new BlockLog(world.getBlockAt(x0-y,z0, y0-x).getLocation(),world.getBlockAt(x0-y,z0, y0-x).getTypeId()));
//                    world.getBlockAt(x0-y,z0, y0-x).setType(material);
//                }
//                
//            } else createLine(world.getBlockAt(x0-y,z0, y0-x).getLocation(), world.getBlockAt(x0+y,z0, y0-x).getLocation(),world,material, duration);
//        }
//        
//        
//        
//        for(BlockLog b : blocks) {
//            
//            System.out.println(" - " + b.getTypeId());
//        }
//        return blocks;
//        
//        RestoreBlockInSecondsThread rbst = new RestoreBlockInSecondsThread(world,blocks, duration);
//        
//        rbst.start();
//        
//        
//        if(full&&(radius>=0)) rasterCircle (x0,z0,y0,radius-1, world, material,full);
//    }