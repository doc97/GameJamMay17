package fi.ds.tbd;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;

/**
 *
 * @author Daniel
 */
public class Map implements Disposable {

    public static final int WIDTH = Gdx.graphics.getWidth();
    public static final int HEIGHT = Gdx.graphics.getHeight();
    public final Sprite sprite;
    
    public Map() {
        sprite = new Sprite();
        sprite.x = 0;
        sprite.y = 0;
        sprite.width = WIDTH;
        sprite.height = HEIGHT;
        sprite.texture = new Texture("background.png");
    }
    
    public void render(SpriteBatch batch) {
        sprite.render(batch);
    }
    
    @Override
    public void dispose() {
        sprite.dispose();
    }
}
