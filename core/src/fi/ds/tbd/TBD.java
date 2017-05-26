package fi.ds.tbd;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TBD extends Game {
    private SpriteBatch batch;
    private PlayerInputProcessor playerInput;
    private Player player;
    private Map map;

    @Override
    public void create () {
        batch = new SpriteBatch();
        
        player = new Player();
        player.x = 50;
        player.y = 50;
        player.speed = 200;
        
        map = new Map();
        
        playerInput = new PlayerInputProcessor(player, Keys.W, Keys.S, Keys.A, Keys.D, Keys.SPACE);
        Gdx.input.setInputProcessor(playerInput);
    }

    @Override
    public void render () {
        player.update(Gdx.graphics.getDeltaTime());
        
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        map.render(batch);
        player.render(batch);
        batch.end();
    }

    @Override
    public void dispose () {
        batch.dispose();
        player.dispose();
    }
}
