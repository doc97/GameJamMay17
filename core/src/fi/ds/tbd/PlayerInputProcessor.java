package fi.ds.tbd;

import fi.ds.tbd.entities.Player;
import com.badlogic.gdx.InputAdapter;

public class PlayerInputProcessor extends InputAdapter {
    
    private final Player player;
    
    public int keycodeUp;
    public int keycodeDown;
    public int keycodeLeft;
    public int keycodeRight;
    public int keycodeShoot;
    
    public PlayerInputProcessor(Player player, int keycodeUp, int keycodeDown,
            int keycodeLeft, int keycodeRight, int keycodeShoot) {
        this.player = player;
        this.keycodeUp = keycodeUp;
        this.keycodeDown = keycodeDown;
        this.keycodeLeft = keycodeLeft;
        this.keycodeRight = keycodeRight;
        this.keycodeShoot = keycodeShoot;
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == keycodeUp) {
            player.dy++;
        } else if (keycode == keycodeDown) {
            player.dy--;
        } else if (keycode == keycodeLeft) {
            player.dx--;
        } else if (keycode == keycodeRight) {
            player.dx++;
        } else if (keycode == keycodeShoot) {
            player.shoot();
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == keycodeUp) {
            player.dy--;
        } else if (keycode == keycodeDown) {
            player.dy++;
        } else if (keycode == keycodeLeft) {
            player.dx++;
        } else if (keycode == keycodeRight) {
            player.dx--;
        }
        return false;
    }
}
