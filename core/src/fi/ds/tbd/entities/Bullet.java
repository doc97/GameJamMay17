package fi.ds.tbd.entities;

import com.badlogic.gdx.graphics.Texture;

/**
 *
 * @author Daniel
 */
public class Bullet extends Entity {
    public static final int SPEED = 800;
    public static final int WIDTH = 10;
    public static final int HEIGHT = 10;
    private final float dx, dy;
    
    public Bullet(float x, float y, float dx, float dy) {
        super(x, y, -WIDTH / 2, -HEIGHT / 2, WIDTH, HEIGHT,
                    -WIDTH / 2, -HEIGHT / 2, WIDTH, HEIGHT,
                    new Texture("bullet.png"));
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
