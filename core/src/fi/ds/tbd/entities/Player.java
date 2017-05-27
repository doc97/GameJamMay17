package fi.ds.tbd.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Disposable;
import fi.ds.tbd.logic.Collision;
import fi.ds.tbd.logic.CollisionFilter;
import fi.ds.tbd.logic.CollisionListener;

/**
 *
 * @author Daniel
 */
public class Player extends Entity implements Disposable, CollisionListener {

    public static final int WIDTH = 50;
    public static final int HEIGHT = 50;
    
    public float dx, dy;
    public float speed;

    private final CollisionFilter pvpFilter;
    private final CollisionFilter pvwFilter;
    
    public Player(float x, float y) {
        super(x, y, -WIDTH / 2, -HEIGHT / 2, WIDTH, HEIGHT,
                    -WIDTH / 2, -HEIGHT / 2, WIDTH, HEIGHT,
                    new Texture("player.png"));
        pvpFilter = (c) -> c.entityA instanceof Player && c.entityB instanceof Player;
        pvwFilter = (c) -> (c.entityA.equals(this) | c.entityB.equals(this))
                && (c.entityA instanceof Wall | c.entityB instanceof Wall);
    }
    
    public void update(float delta) {
        x += dx * speed * delta;
        y += dy * speed * delta;
        syncComponentPos();
    }
    
    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
        syncComponentPos();
    }
    
    @Override
    public void dispose() {
        sprite.dispose();
    }

    @Override
    public void notify(Collision col) {
        if (pvpFilter.match(col)) {
            if (col.intersect.width > col.intersect.height) {
                int mod = col.intersect.y == hitbox.y ? 1 : -1;
                y += mod * col.intersect.height / 2;
            } else {
                int mod = col.intersect.x == hitbox.x ? 1 : -1;
                x += mod * col.intersect.width / 2;
            }
            syncComponentPos();
        } else if (pvwFilter.match(col)) {
            if (col.intersect.width > col.intersect.height) {
                int mod = col.intersect.y == hitbox.y ? 1 : -1;
                y += mod * col.intersect.height;
            } else {
                int mod = col.intersect.x == hitbox.x ? 1 : -1;
                x += mod * col.intersect.width;
            }
            syncComponentPos();
        }
    }
}
