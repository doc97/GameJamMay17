package fi.ds.tbd;

import com.badlogic.gdx.math.Intersector;
import java.util.ArrayList;

public class CollisionChecker {
    
    public static ArrayList<Wall> walls;
    public static Player player1;
    public static Player player2;
    
    public static boolean didPlayersIntersect() {
        return Intersector.intersectRectangles(player1, player2, null);
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
