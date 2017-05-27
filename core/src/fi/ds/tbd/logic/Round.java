package fi.ds.tbd.logic;

import fi.ds.tbd.entities.Player;
import fi.ds.tbd.Map;
import fi.ds.tbd.SpriteRenderer;
import fi.ds.tbd.entities.Bullet;
import fi.ds.tbd.entities.Wall;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Daniel
 */
public class Round implements CollisionListener {

    private static final int ROUND_TIME_SEC = 10;
    public Player player1, player2;
    public CollisionChecker collisions;
    public Map map;
    public Wall wall;

    private final List<Bullet> bullets;
    private final CollisionFilter bvwFilter;
    private final CollisionFilter bvpFilter;
    private final CollisionFilter bvbFilter;
    private float timeSec;
    
    public Round(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        bullets = new ArrayList<>();
        bvwFilter = (c) -> (c.entityA instanceof Bullet | c.entityB instanceof Bullet)
                && (c.entityA instanceof Wall | c.entityB instanceof Wall);
        bvpFilter = (c) -> (c.entityA instanceof Bullet | c.entityB instanceof Bullet)
                && (c.entityA instanceof Player | c.entityB instanceof Player);
        bvbFilter = (c) -> (c.entityA instanceof Bullet && c.entityB instanceof Bullet);
    }
    
    public void start() {
        timeSec = ROUND_TIME_SEC;
        player1.setPosition(50, 50);
        player1.round = this;
        player1.speed = 200;
        
        player2.setPosition(100, 100);
        player2.round = this;
        player2.speed = 200;
        
        map = new Map();
        wall = new Wall(Wall.WIDTH * 5, Wall.HEIGHT * 2);
        
        collisions = new CollisionChecker();
        collisions.addListener(this);
        collisions.addListener(player1);
        collisions.addListener(player2);
        collisions.register(player1.hitbox);
        collisions.register(player2.hitbox);
        collisions.register(wall.hitbox);
    }
    
    public void update(float delta) {
        player1.update(delta);
        player2.update(delta);
        for (Bullet b : bullets)
            b.update(delta);
        
        collisions.update();
        timeSec -= delta;
    }
    
    public void prepareRender(SpriteRenderer renderer) {
        renderer.add(map.sprite);
        renderer.add(player1.sprite);
        renderer.add(player2.sprite);
        for (Bullet b : bullets)
            renderer.add(b.sprite);
        renderer.add(wall.sprite);
    }
    
    public void spawn(Bullet bullet) {
        bullets.add(bullet);
        collisions.register(bullet.hitbox);
    }
    
    public void despawn(Bullet bullet) {
        collisions.unregister(bullet.hitbox);
        bullets.remove(bullet);
    }
    
    @Override
    public void notify(Collision collision) {
        if (bvwFilter.match(collision)) {
            if (collision.entityA instanceof Bullet)
                despawn((Bullet) collision.entityA);
            else
                despawn((Bullet) collision.entityB);
        } else if (bvpFilter.match(collision)) {
            if (collision.entityA instanceof Bullet)
                despawn((Bullet) collision.entityA);
            else
                despawn((Bullet) collision.entityB);
        } else if (bvbFilter.match(collision)) {
            despawn((Bullet) collision.entityA);
            despawn((Bullet) collision.entityB);
        }
    }
    
    public boolean hasTimeLeft() {
        return timeSec > 0;
    }
    
    public void finish() {
        // TODO: Implement points
    }
}
