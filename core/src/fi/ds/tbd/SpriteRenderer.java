package fi.ds.tbd;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Daniel
 */
public class SpriteRenderer implements Disposable {

    public SpriteBatch batch;
    private final List<Sprite> sprites = new ArrayList<>();
    
    public SpriteRenderer() {
        batch = new SpriteBatch();
    }
    
    public void add(Sprite sprite) {
        sprites.add(sprite);
    }
    
    public void render() {
        batch.begin();
        for (Sprite s : sprites)
            s.render(batch);
        batch.end();
        sprites.clear();
    }
    
    @Override
    public void dispose() {
        batch.dispose();
    }
}
