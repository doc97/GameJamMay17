package fi.ds.tbd;

import fi.ds.tbd.logic.CollisionChecker;
import fi.ds.tbd.logic.RoundGame;
import fi.ds.tbd.entities.Player;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TBD extends Game {
    private SpriteBatch batch;
    private InputMultiplexer inputs;
    private CollisionChecker collisions;
    private Player player1;
    private Player player2;
    private RoundGame game;
    private Map map;

    @Override
    public void create () {
        batch = new SpriteBatch();
        
        player1 = new Player(100, 100);
        player2 = new Player(400, 400);
        
        inputs = new InputMultiplexer();
        PlayerInputProcessor p1Input = new PlayerInputProcessor(player1, Keys.W, Keys.S, Keys.A, Keys.D, Keys.SPACE);
        PlayerInputProcessor p2Input = new PlayerInputProcessor(player2, Keys.I, Keys.K, Keys.J, Keys.L, Keys.SHIFT_RIGHT);
        inputs.addProcessor(p1Input);
        inputs.addProcessor(p2Input);
        Gdx.input.setInputProcessor(inputs);
        
        collisions = new CollisionChecker();
        collisions.player1 = player1;
        collisions.player2 = player2;

        game = new RoundGame(player1, player2);
    }

    @Override
    public void render () {
        game.update(Gdx.graphics.getDeltaTime());
        
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        game.render(batch);
        batch.end();
    }

    @Override
    public void dispose () {
        batch.dispose();
        player1.dispose();
    }
}
