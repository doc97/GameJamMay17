package fi.ds.tbd.logic;

import fi.ds.tbd.entities.Player;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import fi.ds.tbd.Map;

/**
 *
 * @author Daniel
 */
public class Round {

    private static final int ROUND_TIME_SEC = 10;
    public Player player1, player2;
    public Map map;
    private float timeSec;
    
    public Round(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }
    
    public void start() {
        timeSec = ROUND_TIME_SEC;
        player1.x = 50;
        player1.y = 50;
        player1.speed = 200;
        
        player2.x = 200;
        player2.y = 200;
        player2.speed = 200;
        
        map = new Map();
    }
    
    public void update(float delta) {
        player1.update(delta);
        player2.update(delta);
        timeSec -= delta;
    }
    
    public void render(SpriteBatch batch) {
        map.render(batch);
        player1.render(batch);
        player2.render(batch);
    }
    
    public boolean hasTimeLeft() {
        return timeSec > 0;
    }
    
    public void finish() {
        // TODO: Implement points
    }
}
