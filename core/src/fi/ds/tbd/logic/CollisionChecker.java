package fi.ds.tbd.logic;

import fi.ds.tbd.entities.Wall;
import fi.ds.tbd.entities.Player;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Sofia
 * @author Daniel
 */
public class CollisionChecker {
    
    private final List<Hitbox> hitboxes;
    private final List<CollisionListener> listeners;
    private final Rectangle intersect;
    
    public CollisionChecker() {
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
    
    public void update() {
        boolean conflict;
        do {
            conflict = false;
            List<Collision> collisions = new ArrayList<>();
            for (int i = 0; i < hitboxes.size() - 1; i++) {
                for (int j = i + 1; j < hitboxes.size(); j++) {
                    Hitbox h1 = hitboxes.get(i);
                    Hitbox h2 = hitboxes.get(j);
                    if (Intersector.intersectRectangles(h1, h2, intersect)) {
                        Collision collision = new Collision(h1.owner, h2.owner, intersect);
                        collisions.add(collision);
                        conflict = true;
                    }
                }
            }

            for (Collision collision : collisions)
                for (CollisionListener listener : listeners)
                    listener.notify(collision);
        } while (conflict);
    }
}
