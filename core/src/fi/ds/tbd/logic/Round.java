package fi.ds.tbd.logic;

import fi.ds.tbd.entities.Player;
import fi.ds.tbd.Map;
import fi.ds.tbd.SpriteRenderer;
import fi.ds.tbd.TBD;
import fi.ds.tbd.entities.Projectile;
import fi.ds.tbd.entities.Collectible;
import fi.ds.tbd.entities.Entity;
import fi.ds.tbd.entities.Wall;
import fi.ds.tbd.gui.Property;
import fi.ds.tbd.gui.RoundGUI;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Daniel
 */
public class Round implements CollisionListener {

    private static final int ROUND_TIME_SEC = 120;
    public CollisionChecker collisions;
    public Map map;
    public Player player1, player2;
    public Collectible collectible;
    public Wall wall;

    private final List<Entity> entities;
    private final CollisionFilter bvwFilter;
    private final CollisionFilter bvpFilter;
    private final CollisionFilter bvbFilter;
    private float time;
    private Property<Integer> timeSec;
    private RoundGUI gui;
    private TBD game;
    
    public Round(TBD game) {
        this.game = game;
        this.player1 = game.player1;
        this.player2 = game.player2;
        entities = new ArrayList<>();
        bvwFilter = (c) -> (c.entityA instanceof Projectile ^ c.entityB instanceof Projectile)
                && (c.entityA instanceof Wall ^ c.entityB instanceof Wall);
        bvpFilter = (c) -> (c.entityA instanceof Projectile ^ c.entityB instanceof Projectile)
                && (c.entityA instanceof Player ^ c.entityB instanceof Player);
        bvbFilter = (c) -> (c.entityA instanceof Projectile && c.entityB instanceof Projectile);
        
        timeSec = new Property<>(ROUND_TIME_SEC);
        gui = new RoundGUI(game.ui, player1.points, player2.points,
                player1.health, player2.health, timeSec);
    }
    
    public void start() {
        time = ROUND_TIME_SEC;
        timeSec.set(ROUND_TIME_SEC);
        player1.setPosition(50, 50);
        player1.round = this;
        player1.speed = 200;
        player1.health.set(Player.MAX_HEALTH);
        
        player2.setPosition(100, 100);
        player2.round = this;
        player2.speed = 200;
        player2.health.set(Player.MAX_HEALTH);
        
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
        collisions.ignore(Projectile.class, Collectible.class);
        
        gui.create();
    }
    
    public void update(float delta) {
        for (Entity e : entities)
            e.update(delta);
        
        collisions.update();
        time -= delta;
        timeSec.set((int) time);
    }
    
    public void prepareRender(SpriteRenderer renderer) {
        //renderer.add(map.sprite);
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
            if (collision.entityA instanceof Projectile)
                despawn(collision.entityA);
            else
                despawn(collision.entityB);
        } else if (bvpFilter.match(collision)) {
            if (collision.entityA instanceof Projectile) {
                despawn(collision.entityA);
                Property<Integer> hp = ((Player) collision.entityB).health;
                hp.set(hp.value() - 1);
            } else {
                despawn(collision.entityB);
                Property<Integer> hp = ((Player) collision.entityA).health;
                hp.set(hp.value() - 1);
            }
        } else if (bvbFilter.match(collision)) {
            despawn(collision.entityA);
            despawn(collision.entityB);
        }
    }
    
    public boolean hasTimeLeft() {
        return timeSec.value() > 0;
    }
    
    public void finish() {
        if (player1.health.value() > 0) {
            player1.points.set(player1.points.value() + 1);
            if (player1.hasCollectible)
                player1.points.set(player1.points.value() + 1);
        }
        if (player2.health.value() > 0) {
            player2.points.set(player2.points.value() + 1);
            if (player2.hasCollectible)
                player2.points.set(player2.points.value() + 1);
        }
    }
}
