package fi.ds.tbd.logic;

import com.badlogic.gdx.math.Rectangle;
import fi.ds.tbd.entities.Entity;

/**
 *
 * @author Daniel
 */
public class Hitbox extends Rectangle {
    public Entity owner;
    
    public Hitbox(Entity owner, float x, float y, float width, float height) {
        super(x, y, width, height);
        this.owner = owner;
    }
}
