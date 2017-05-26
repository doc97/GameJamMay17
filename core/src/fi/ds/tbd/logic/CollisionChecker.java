package fi.ds.tbd.logic;

import fi.ds.tbd.entities.Wall;
import fi.ds.tbd.entities.Player;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import java.util.ArrayList;

public class CollisionChecker {
    
    public static ArrayList<Wall> walls;
    public static Player player1;
    public static Player player2;
    
    public static boolean didPlayersIntersect(Rectangle intersect) {
        if (player1 != null && player2 != null)
            return Intersector.intersectRectangles(player1.hitbox, player2.hitbox,
                    intersect == null ? new Rectangle() : intersect);
        return false;
    }
    
    public static boolean didIHitWall(Player player) {
        return false;
    }
    
    /*
    public static boolean didIHitWall(Bullet bullet) {
        return false;
    }
    
    public static boolean didIHitPlayer(Bullet bullet) {
        return false;
    }
    */

}
