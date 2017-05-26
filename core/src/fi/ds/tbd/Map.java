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

    private final Sprite sprite;
    
    public static final int WIDTH = Gdx.graphics.getWidth();
    public static final int HEIGHT = Gdx.graphics.getHeight();
    
    public Map() {
        sprite = new Sprite();
        sprite.width = WIDTH;
        sprite.height = HEIGHT;
        sprite.texture = new Texture("background.png");
    }
    
    public void render(SpriteBatch batch) {
        sprite.render(batch, 0, 0);
    }
    
    @Override
    public void dispose() {
        sprite.dispose();
    }
}
