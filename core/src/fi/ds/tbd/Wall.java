package fi.ds.tbd;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Wall extends Rectangle {
    
    public static final int WIDTH = 128;
    public static final int HEIGHT = 128;
    private final Sprite sprite;
    
    public Wall(float x, float y) {
        super(x, y, WIDTH, HEIGHT);
        sprite = new Sprite();
        sprite.width = WIDTH;
        sprite.height = HEIGHT;
        sprite.texture = new Texture("wall.png");
    }
    
    public void render(SpriteBatch batch) {
        sprite.render(batch, x, y);
    }

}
