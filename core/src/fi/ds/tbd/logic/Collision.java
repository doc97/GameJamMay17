package fi.ds.tbd.logic;

import com.badlogic.gdx.math.Rectangle;
import fi.ds.tbd.entities.Entity;

/**
 *
 * @author Daniel
 */
public class Collision {
    public final Entity entityA;
    public final Entity entityB;
    public final Rectangle intersect;
    
    public Collision(Entity entityA, Entity entityB, Rectangle intersect) {
        this.entityA = entityA;
        this.entityB = entityB;
        this.intersect = intersect;
    }
}
