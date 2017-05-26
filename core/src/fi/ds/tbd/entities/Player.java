package fi.ds.tbd.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Disposable;
import fi.ds.tbd.logic.CollisionChecker;
import fi.ds.tbd.Sprite;

/**
 *
 * @author Daniel
 */
public class Player implements Disposable {

    public static final int WIDTH = 50;
    public static final int HEIGHT = 50;
    
    public final Sprite sprite;
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
    
    @Override
    public void dispose() {
        sprite.dispose();
    }
}
