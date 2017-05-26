package fi.ds.tbd;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TBD extends Game {
    private SpriteBatch batch;
    private Player player;
    private Map map;

    @Override
    public void create () {
        batch = new SpriteBatch();
        player = new Player();
        player.x = 50;
        player.y = 50;
        
        map = new Map();
    }

    @Override
    public void render () {
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
