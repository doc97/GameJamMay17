package fi.ds.tbd.entities;

import com.badlogic.gdx.graphics.Texture;
import fi.ds.tbd.Sprite;
import fi.ds.tbd.logic.Hitbox;

/**
 *
 * @author Daniel
 */
public abstract class Entity {
    public final Sprite sprite;
    public final Hitbox hitbox;
    public float x, y;
    
    public Entity(float x, float y, float sWidth, float sHeight,
            float hWidth, float hHeight, Texture texture) {
        this.x = x;
        this.y = y;
        sprite = new Sprite();
        sprite.x = x;
        sprite.y = y;
        sprite.width = sWidth;
        sprite.height = sHeight;
        sprite.texture = texture;
        hitbox = new Hitbox(this, x, y, hWidth, hHeight);
    }
    
    protected void syncComponentPos() {
        sprite.x = x;
        sprite.y = y;
        hitbox.x = x;
        hitbox.y = y;
    }
}
