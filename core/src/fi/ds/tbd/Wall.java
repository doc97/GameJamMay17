package fi.ds.tbd;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Wall {
    
    public static final int WIDTH = 128;
    public static final int HEIGHT = 128;
    private final Sprite sprite;
    public float x, y;
    
    public Wall() {
        sprite = new Sprite();
        sprite.width = WIDTH;
        sprite.height = HEIGHT;
        sprite.texture = new Texture("wall.png");
    }
    
    public void Render(SpriteBatch batch) {
        sprite.render(batch, x, y);
    }

}
