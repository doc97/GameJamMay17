package fi.ds.tbd.logic;

import com.badlogic.gdx.Gdx;
import fi.ds.tbd.SpriteRenderer;
import fi.ds.tbd.TBD;

/**
 *
 * @author Daniel
 */
public class RoundGame {

    public Round currRound;
    public TBD game;
    
    public RoundGame(TBD game) {
        this.game = game;
        currRound = new Round(game);
        currRound.start();
    }
    
    public void update(float delta) {
        currRound.update(Gdx.graphics.getDeltaTime());
        if (currentRoundHasEnded())
            nextRound();
    }
    
    public void prepareRender(SpriteRenderer renderer) {
        currRound.prepareRender(renderer);
    }
    
    private void nextRound() {
        currRound.finish();
        currRound = new Round(game);
        currRound.start();
    }
    
    private boolean currentRoundHasEnded() {
        return !currRound.hasTimeLeft() || game.player1.health.value() <= 0
                || game.player2.health.value() <= 0;
    }
}
