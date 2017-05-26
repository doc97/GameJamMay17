package fi.ds.tbd;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;

/**
 *
 * @author Daniel
 */
public class Player extends GameObject {

    private final Texture texture;
    
    public Player() {
        this.texture = new Texture("player.png");
        this.width = 50;
        this.height = 50;
    }
    
    public void render(SpriteBatch batch) {
        batch.draw(texture, x, y, width, height);
        if (tint != null)
            batch.draw(tint, x, y, width, height);
    }
    
    @Override
    public void dispose() {
        super.dispose();
        texture.dispose();
    }
}
