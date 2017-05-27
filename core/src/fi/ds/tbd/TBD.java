package fi.ds.tbd;

import fi.ds.tbd.logic.RoundGame;
import fi.ds.tbd.entities.Player;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import fi.ds.tbd.gui.UI;

public class TBD extends Game {
    private SpriteRenderer renderer;
    private InputMultiplexer inputs;
    private RoundGame game;
    public Player player1;
    public Player player2;
    public UI ui;

    @Override
    public void create () {
        renderer = new SpriteRenderer();
        ui = new UI();
        ui.create();
        
        player1 = new Player(100, 100, new Texture("player1.png"), new Texture("Orb1.png"));
        player2 = new Player(400, 400, new Texture("player2.png"), new Texture("Orb2.png"));
        
        inputs = new InputMultiplexer();
        PlayerInputProcessor p1Input = new PlayerInputProcessor(player1, Keys.W, Keys.S, Keys.A, Keys.D, Keys.SPACE);
        PlayerInputProcessor p2Input = new PlayerInputProcessor(player2, Keys.I, Keys.K, Keys.J, Keys.L, Keys.SHIFT_RIGHT);
        inputs.addProcessor(p1Input);
        inputs.addProcessor(p2Input);
        Gdx.input.setInputProcessor(inputs);
        
        game = new RoundGame(this);
    }

    @Override
    public void render () {
        game.update(Gdx.graphics.getDeltaTime());
        ui.update(Gdx.graphics.getDeltaTime());
        
        Gdx.gl.glClearColor(0.4f, 0.4f, 0.4f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.prepareRender(renderer);
        renderer.render();
        ui.render();
    }

    @Override
    public void dispose () {
        renderer.dispose();
        player1.dispose();
    }
}
