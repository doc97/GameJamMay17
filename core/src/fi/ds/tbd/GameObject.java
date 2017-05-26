package fi.ds.tbd;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Disposable;

/**
 *
 * @author Daniel
 */
public abstract class GameObject implements Disposable {

    public Texture tint;
    public float x, y;
    public float width, height;

    @Override
    public void dispose() {
        if (tint != null)
            tint.dispose();
    }
}