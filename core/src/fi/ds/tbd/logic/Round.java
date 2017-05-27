package fi.ds.tbd.logic;

import fi.ds.tbd.entities.Player;
import fi.ds.tbd.Map;
import fi.ds.tbd.SpriteRenderer;
import fi.ds.tbd.entities.Bullet;
import fi.ds.tbd.entities.Collectible;
import fi.ds.tbd.entities.Entity;
import fi.ds.tbd.entities.Wall;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Daniel
 */
public class Round implements CollisionListener {

    private static final int ROUND_TIME_SEC = 10;
    public CollisionChecker collisions;
    public Map map;
    public Player player1, player2;
    public Collectible collectible;
    public Wall wall;

    private final List<Entity> entities;
    private final CollisionFilter bvwFilter;
    private final CollisionFilter bvpFilter;
    private final CollisionFilter bvbFilter;
    private float timeSec;
    
    public Round(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        entities = new ArrayList<>();
        bvwFilter = (c) -> (c.entityA instanceof Bullet ^ c.entityB instanceof Bullet)
                && (c.entityA instanceof Wall ^ c.entityB instanceof Wall);
        bvpFilter = (c) -> (c.entityA instanceof Bullet ^ c.entityB instanceof Bullet)
                && (c.entityA instanceof Player ^ c.entityB instanceof Player);
        bvbFilter = (c) -> (c.entityA instanceof Bullet && c.entityB instanceof Bullet);
    }
    
    public void start() {
        timeSec = ROUND_TIME_SEC;
        player1.setPosition(50, 50);
        player1.round = this;
        player1.speed = 200;
        player1.health = Player.MAX_HEALTH;
        
        player2.setPosition(100, 100);
        player2.round = this;
        player2.speed = 200;
        player2.health = Player.MAX_HEALTH;
        
        collectible = new Collectible(350, 500);
        
        map = new Map();
        wall = new Wall(Wall.WIDTH * 5, Wall.HEIGHT * 2);
        
        entities.add(player1);
        entities.add(player2);
        entities.add(collectible);
        entities.add(wall);
        
        collisions = new CollisionChecker();
        collisions.addListener(this);
        collisions.addListener(player1);
        collisions.addListener(player2);
        collisions.register(player1.hitbox);
        collisions.register(player2.hitbox);
        collisions.register(collectible.hitbox);
        collisions.register(wall.hitbox);
        collisions.ignore(Bullet.class, Collectible.class);
    }
    
    public void update(float delta) {
        for (Entity e : entities)
            e.update(delta);
        
        collisions.update();
        timeSec -= delta;
    }
    
    public void prepareRender(SpriteRenderer renderer) {
        renderer.add(map.sprite);
        for (Entity e : entities)
            renderer.add(e.sprite);
    }
    
    public void spawn(Entity entity) {
        entities.add(entity);
        collisions.register(entity.hitbox);
    }
    
    public void despawn(Entity entity) {
        collisions.unregister(entity.hitbox);
        entities.remove(entity);
    }
    
    @Override
    public void notify(Collision collision) {
        if (bvwFilter.match(collision)) {
            if (collision.entityA instanceof Bullet)
                despawn(collision.entityA);
            else
                despawn(collision.entityB);
        } else if (bvpFilter.match(collision)) {
            if (collision.entityA instanceof Bullet) {
                despawn(collision.entityA);
                ((Player) collision.entityB).health--;
            } else {
                despawn(collision.entityB);
                ((Player) collision.entityA).health--;
            }
        } else if (bvbFilter.match(collision)) {
            despawn(collision.entityA);
            despawn(collision.entityB);
        }
    }
    
    public boolean hasTimeLeft() {
        return timeSec > 0;
    }
    
    public void finish() {
        if (player1.health > 0) {
            player1.points++;
            if (player1.hasCollectible)
                player1.points++;
        }
        if (player2.health > 0) {
            player2.points++;
            if (player2.hasCollectible)
                player2.points++;
        }
    }
}
