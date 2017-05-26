package fi.ds.tbd;

import com.badlogic.gdx.InputProcessor;

public class PlayerInputProcessor implements InputProcessor {
    
    private Player player;
    
    private int keycodeUp;
    private int keycodeDown;
    private int keycodeLeft;
    private int keycodeRight;
    private int keycodeShoot;
    
    public PlayerInputProcessor(Player player, int keycodeUp, int keycodeDown, int keycodeLeft, int keycodeRight, int keycodeShoot) {
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
            
        } else if (keycode == keycodeDown) {
             
        } else if (keycode == keycodeLeft) {
            
        } else if (keycode == keycodeRight) {
            
        } else if (keycode == keycodeShoot) {
            
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == keycodeUp) {
            
        } else if (keycode == keycodeDown) {
             
        } else if (keycode == keycodeLeft) {
            
        } else if (keycode == keycodeRight) {
            
        } else if (keycode == keycodeShoot) {
            
        }
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

}
