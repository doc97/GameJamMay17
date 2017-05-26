package fi.ds.tbd;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;

/**
 *
 * @author Daniel
 */
public class Player implements Disposable {

    public static final int WIDTH = 50;
    public static final int HEIGHT = 50;
    private final Sprite sprite;
    
    public Player() {
        sprite = new Sprite();
        sprite.width = WIDTH;
        sprite.height = HEIGHT;
        sprite.texture = new Texture("player.png");
    }
    
    public void render(SpriteBatch batch) {
        sprite.render(batch);
    }
    
    public void setPosition(float x, float y) {
        sprite.x = x;
        sprite.y = y;
    }
    
    @Override
    public void dispose() {
        sprite.dispose();
    }
}
