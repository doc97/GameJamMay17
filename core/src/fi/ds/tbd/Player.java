package fi.ds.tbd;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Disposable;

/**
 *
 * @author Daniel
 */
public class Player extends Rectangle implements Disposable {

    public static final int WIDTH = 50;
    public static final int HEIGHT = 50;
    
    private final Sprite sprite;
    public float x, y;
    public float dx, dy;
    public float speed;
    
    public Player(float x, float y) {
        super (x, y, WIDTH, HEIGHT);
        sprite = new Sprite();
        sprite.width = WIDTH;
        sprite.height = HEIGHT;
        sprite.texture = new Texture("player.png");
    }
    
    public void update(float delta) {
        x += dx * speed * delta;
        y += dy * speed * delta;
        if (CollisionChecker.didPlayersIntersect()) {
            // Return? But we don't know which side we're hitting them from...
        }
    }
    
    public void render(SpriteBatch batch) {
        sprite.render(batch, x, y);
    }
    
    @Override
    public void dispose() {
        sprite.dispose();
    }
}
