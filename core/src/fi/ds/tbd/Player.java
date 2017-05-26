package fi.ds.tbd;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Disposable;

/**
 *
 * @author Daniel
 */
public class Player implements Disposable {

    public static final int WIDTH = 50;
    public static final int HEIGHT = 50;
    
    private final Sprite sprite;
    public final Rectangle hitbox;
    public float x, y;
    public float dx, dy;
    public float speed;
    
    public Player(float x, float y) {
        sprite = new Sprite();
        sprite.x = x;
        sprite.y = y;
        sprite.width = WIDTH;
        sprite.height = HEIGHT;
        sprite.texture = new Texture("player.png");
        hitbox = new Rectangle(x, y, WIDTH, HEIGHT);
    }
    
    public void update(float delta) {
        hitbox.x += dx * speed * delta;
        hitbox.y += dy * speed * delta;
        
        Rectangle intersect = new Rectangle();
        if (CollisionChecker.didPlayersIntersect(intersect)) {
            hitbox.x = x;
            hitbox.y = y;
        }

        x = hitbox.x;
        y = hitbox.y;
        sprite.x = x;
        sprite.y = y;
    }
    
    public void render(SpriteBatch batch) {
        sprite.render(batch);
    }
    
    @Override
    public void dispose() {
        sprite.dispose();
    }
}
