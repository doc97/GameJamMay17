package fi.ds.tbd.entities;

import com.badlogic.gdx.graphics.Texture;

/**
 *
 * @author Daniel
 */
public class Projectile extends Entity {
    public static final int SPEED = 800;
    public static final int WIDTH = 19;
    public static final int HEIGHT = 19;
    private final float dx, dy;
    
    public Projectile(float x, float y, float dx, float dy, Texture texture) {
        super(x, y, -WIDTH / 2, -HEIGHT / 2, WIDTH, HEIGHT,
                    -WIDTH / 2, -HEIGHT / 2, WIDTH, HEIGHT, texture);
        this.dx = dx;
        this.dy = dy;
    }
    
    public void update(float delta) {
        float normDx = dx / (float) Math.sqrt(dx * dx + dy * dy) * SPEED * delta;
        float normDy = dy / (float) Math.sqrt(dx * dx + dy * dy) * SPEED * delta;
        
        hitbox.x += normDx;
        hitbox.y += normDy;
        sprite.x += normDx;
        sprite.y += normDy;
    }
}
