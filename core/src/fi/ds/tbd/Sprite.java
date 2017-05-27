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
    public boolean flipX, flipY;

    public void render(SpriteBatch batch) {
        if (texture != null)
            batch.draw(texture, x, y, x + width / 2, y + height / 2, width, height,
                    1, 1, 0, 0, 0, texture.getWidth(), texture.getHeight(),
                    flipX, flipY);
        if (tint != null)
            batch.draw(tint, x, y, width, height);
    }
    
    @Override
    public void dispose() {
        if (tint != null)
            tint.dispose();
    }
}