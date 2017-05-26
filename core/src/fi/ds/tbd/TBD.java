package fi.ds.tbd;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TBD extends Game {
    private SpriteBatch batch;
    private InputMultiplexer inputs;
    private Player player1;
    private Player player2;
    private Map map;

    @Override
    public void create () {
        batch = new SpriteBatch();
        
        player1 = new Player(100, 100);
        player1.x = 50;
        player1.y = 50;
        player1.speed = 200;
        
        player2 = new Player(400, 400);
        player2.x = 200;
        player2.y = 200;
        player2.speed = 200;
        
        map = new Map();
        
        inputs = new InputMultiplexer();
        PlayerInputProcessor p1Input = new PlayerInputProcessor(player1, Keys.W, Keys.S, Keys.A, Keys.D, Keys.SPACE);
        PlayerInputProcessor p2Input = new PlayerInputProcessor(player2, Keys.I, Keys.K, Keys.J, Keys.L, Keys.SHIFT_RIGHT);
        inputs.addProcessor(p1Input);
        inputs.addProcessor(p2Input);
        Gdx.input.setInputProcessor(inputs);
        
        CollisionChecker.player1 = player1;
        CollisionChecker.player2 = player2;
    }

    @Override
    public void render () {
        player1.update(Gdx.graphics.getDeltaTime());
        player2.update(Gdx.graphics.getDeltaTime());
        
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        map.render(batch);
        player1.render(batch);
        player2.render(batch);
        batch.end();
    }

    @Override
    public void dispose () {
        batch.dispose();
        player1.dispose();
    }
}
