package fi.ds.tbd.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import fi.ds.tbd.Sprite;

public class Wall {
    
    public static final int WIDTH = 128;
    public static final int HEIGHT = 128;
    private final Sprite sprite;
    public final Rectangle hitbox;
    
    public Wall(float x, float y) {
        sprite = new Sprite();
        sprite.x = x;
        sprite.y = y;
        sprite.width = WIDTH;
        sprite.height = HEIGHT;
        sprite.texture = new Texture("wall.png");
        hitbox = new Rectangle(x, y, WIDTH, HEIGHT);
    }
    
    public void render(SpriteBatch batch) {
        sprite.render(batch);
    }
}
