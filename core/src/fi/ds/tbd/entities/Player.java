package fi.ds.tbd.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Disposable;
import fi.ds.tbd.gui.Property;
import fi.ds.tbd.logic.Collision;
import fi.ds.tbd.logic.CollisionFilter;
import fi.ds.tbd.logic.CollisionListener;
import fi.ds.tbd.logic.Round;

/**
 *
 * @author Daniel
 */
public class Player extends Entity implements Disposable, CollisionListener {

    public static final int WIDTH = 50;
    public static final int HEIGHT = 50;
    public static final int MAX_HEALTH = 3;

    public Property<Integer> points;
    public Property<Integer> health;
    public boolean hasCollectible;

    public float dx, dy;
    public float speed;
    public Round round;

    private float dirX, dirY;

    private final Texture projectileTex;
    private final CollisionFilter pvpFilter;
    private final CollisionFilter pvwFilter;
    private final CollisionFilter pvcFilter;

    public Player(float x, float y, Texture texture, Texture projectileTex) {
        super(x, y, -WIDTH / 2, -HEIGHT / 2, WIDTH, HEIGHT,
                -WIDTH / 2, -HEIGHT / 2, WIDTH, HEIGHT, texture);
        this.projectileTex = projectileTex;
        if (x > Gdx.graphics.getWidth() / 2) {
            dirX = -1;
        } else {
            dirX = 1;
        }
        points = new Property<>(0);
        health = new Property<>(MAX_HEALTH);
        pvpFilter = (c) -> c.entityA instanceof Player && c.entityB instanceof Player;
        pvwFilter = (c) -> (c.entityA.equals(this) ^ c.entityB.equals(this))
                && (c.entityA instanceof Wall ^ c.entityB instanceof Wall);
        pvcFilter = (c) -> (c.entityA.equals(this) ^ c.entityB.equals(this))
                && (c.entityA instanceof Collectible ^ c.entityB instanceof Collectible);
    }

    @Override
    public void update(float delta) {
        x += dx * speed * delta;
        y += dy * speed * delta;

        if (dx != 0 || dy != 0) {
            dirX = dx;
            dirY = dy;
        }
        
        if (dirX < 0) {
            sprite.flipX = true; // Facing left
        } else if (dirX > 0) {
            sprite.flipX = false; // Facing right
        } // since we don't do anything if dirX == 0, it doesn't change if we only move up/down

        syncComponentPos();
    }

    public void shoot() {
        Projectile projectile = new Projectile(x + dirX * (Projectile.WIDTH + WIDTH) / 2,
                y + dirY * (Projectile.HEIGHT + HEIGHT) / 2,
                dirX * Projectile.SPEED, dirY * Projectile.SPEED,
                projectileTex);
        round.spawn(projectile);
    }

    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
        if (x > Gdx.graphics.getWidth() / 2) { dirX = -1; }
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
        } else if (pvcFilter.match(col)) {
            if (col.entityA instanceof Collectible) {
                round.despawn(col.entityA);
            } else {
                round.despawn(col.entityB);
            }

            hasCollectible = true;
        }
    }
}
