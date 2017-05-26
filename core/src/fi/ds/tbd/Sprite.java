package fi.ds.tbd;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;

/**
 *
 * @author Daniel
 */
public class Sprite implements Disposable {

    public Texture texture;
    public Texture tint;
    public float width, height;
    public float x, y;

    public void render(SpriteBatch batch) {
        if (texture != null)
            batch.draw(texture, x, y, width, height);
        if (tint != null)
            batch.draw(tint, x, y, width, height);
    }
    
    @Override
    public void dispose() {
        if (tint != null)
            tint.dispose();
    }
}