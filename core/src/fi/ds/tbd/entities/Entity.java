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
    public float sOffsetX, sOffsetY;
    public float hOffsetX, hOffsetY;
    protected float x, y;
    
    public Entity(float x, float y,
            float sOffsetX, float sOffsetY, float sWidth, float sHeight,
            float hOffsetX, float hOffsetY, float hWidth, float hHeight,
            Texture texture) {
        this.x = x;
        this.y = y;
        this.sOffsetX = sOffsetX;
        this.sOffsetY = sOffsetY;
        this.hOffsetX = hOffsetX;
        this.hOffsetY = hOffsetY;
        sprite = new Sprite();
        sprite.x = x + sOffsetX;
        sprite.y = y + sOffsetY;
        sprite.width = sWidth;
        sprite.height = sHeight;
        sprite.texture = texture;
        hitbox = new Hitbox(this, x + hOffsetX, y + hOffsetY, hWidth, hHeight);
    }
    
    protected void syncComponentPos() {
        sprite.x = x + sOffsetX;
        sprite.y = y + sOffsetY;
        hitbox.x = x + hOffsetX;
        hitbox.y = y + hOffsetY;
    }
}
