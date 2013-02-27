package firelord.tools;

import java.util.ArrayList;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Pig;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Ajax
 */
public class EntityCache {

    private static EntityCache instance = null;
    private ArrayList<Entity> burnt = new ArrayList<Entity>();

    public EntityCache() {
        instance = null;
    }

    public static EntityCache getInstance() {
        if (instance == null) {
            instance = new EntityCache();

        }
        return instance;
    }

    public boolean entityBurnt(Entity entity) {
        if (burnt.contains(entity)) {
            burnt.remove(entity);
            return true;
        } else {
            return false;
        }
    }

    public void burnEntity(Entity entity) {
        if (!burnt.contains(entity)) {
            burnt.add(entity);
        }
    }
}