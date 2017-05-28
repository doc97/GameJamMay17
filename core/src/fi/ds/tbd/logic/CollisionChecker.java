package fi.ds.tbd.logic;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author Sofia
 * @author Daniel
 */
public class CollisionChecker {
    
    private final Map<Class<?>,List<Class<?>>> ignoreMap;
    private final List<Hitbox> hitboxes;
    private final List<CollisionListener> listeners;
    private final Rectangle intersect;
    
    public CollisionChecker() {
        ignoreMap = new HashMap<>();
        hitboxes = new ArrayList<>();
        listeners = new ArrayList<>();
        intersect = new Rectangle();
    }
    
    public void register(Hitbox hitbox) {
        hitboxes.add(hitbox);
    }
    
    public void unregister(Hitbox hitbox) {
        hitboxes.remove(hitbox);
    }
    
    public void addListener(CollisionListener listener) {
        listeners.add(listener);
    }
    
    public void removeListener(CollisionListener listener) {
        listeners.remove(listener);
    }
    
    public void ignore(Class<?> clazz1, Class<?> clazz2) {
        if (!ignoreMap.containsKey(clazz1))
            ignoreMap.put(clazz1, new ArrayList<>());
        if (!ignoreMap.containsKey(clazz2))
            ignoreMap.put(clazz2, new ArrayList<>());

        ignoreMap.get(clazz1).add(clazz2);
        ignoreMap.get(clazz2).add(clazz1);
    }

    public void notice(Class<?> clazz1, Class<?> clazz2) {
        ignoreMap.remove(clazz1);
        ignoreMap.remove(clazz2);
        for (Iterator<Map.Entry<Class<?>, List<Class<?>>>> it = ignoreMap.entrySet().iterator(); it.hasNext();) {
            Map.Entry<Class<?>, List<Class<?>>> entry = it.next();
            if (entry.getValue().contains(clazz1)) {
                it.remove();
            }
        }
        
        for (Iterator<Map.Entry<Class<?>, List<Class<?>>>> it = ignoreMap.entrySet().iterator(); it.hasNext();) {
            Map.Entry<Class<?>, List<Class<?>>> entry = it.next();
            if (entry.getValue().contains(clazz2)) {
                it.remove();
            }
        }
    }
    
    public void update() {
        boolean conflict;
        int counter = 0;
        do {
            counter++;
            conflict = false;
            List<Collision> collisions = new ArrayList<>();
            for (int i = 0; i < hitboxes.size() - 1; i++) {
                for (int j = i + 1; j < hitboxes.size(); j++) {
                    Hitbox h1 = hitboxes.get(i);
                    Hitbox h2 = hitboxes.get(j);
                    
                    List<Class<?>> clazzes = ignoreMap.get(h1.owner.getClass());
                    boolean ignored = clazzes != null && clazzes.contains(h2.owner.getClass());
                    boolean intersects = Intersector.intersectRectangles(h1, h2, intersect);
                    
                    if (intersects && !ignored) {
                        Collision collision = new Collision(h1.owner, h2.owner, intersect);
                        collisions.add(collision);
                        conflict = true;
                    }
                }
            }

            for (Collision collision : collisions)
                for (CollisionListener listener : listeners)
                    listener.notify(collision);
            
            if (counter > 5)
                break;
        } while (conflict);
    }
}
