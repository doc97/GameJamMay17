package fi.ds.tbd.entities;

import com.badlogic.gdx.graphics.Texture;

/**
 *
 * @author Daniel
 */
public class Collectible extends Entity {

    public static final int WIDTH = 32;
    public static final int HEIGHT = 32;
    
    public Collectible(float x, float y) {
        super(x, y, -WIDTH / 2, -HEIGHT / 2, WIDTH, HEIGHT,
                    -WIDTH / 2, -HEIGHT / 2, WIDTH, HEIGHT,
                    new Texture("collectible.png"));
    }
}
