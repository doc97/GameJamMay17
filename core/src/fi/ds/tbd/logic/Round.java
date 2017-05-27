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
public class Round {

    private static final int ROUND_TIME_SEC = 10;
    public Player player1, player2;
    public CollisionChecker collisions;
    public Map map;
    public Wall wall;
    private final List<Bullet> bullets;
    private float timeSec;
    
    public Round(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        bullets = new ArrayList<>();
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
    }
    
    public boolean hasTimeLeft() {
        return timeSec > 0;
    }
    
    public void finish() {
        // TODO: Implement points
    }
}
