package fi.ds.tbd.entities;

import com.badlogic.gdx.graphics.Texture;

public class Wall extends Entity {
    
    public static final int WIDTH = 96;
    public static final int HEIGHT = 96;
    
    public Wall(float x, float y) {
        super(x, y, -WIDTH / 2, -HEIGHT / 2, WIDTH, HEIGHT,
                    -WIDTH / 2, -HEIGHT / 2, WIDTH, HEIGHT,
                    new Texture("wall.png"));
    }
}
