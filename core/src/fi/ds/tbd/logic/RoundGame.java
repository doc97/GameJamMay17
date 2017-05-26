package fi.ds.tbd.logic;

import fi.ds.tbd.entities.Player;
import com.badlogic.gdx.Gdx;
import fi.ds.tbd.SpriteRenderer;

/**
 *
 * @author Daniel
 */
public class RoundGame {

    public Round currRound;
    public Player player1, player2;
    
    public RoundGame(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        currRound = new Round(player1, player2);
        currRound.start();
    }
    
    public void update(float delta) {
        currRound.update(Gdx.graphics.getDeltaTime());
        if (!currRound.hasTimeLeft())
            nextRound();
    }
    
    public void prepareRender(SpriteRenderer renderer) {
        currRound.prepareRender(renderer);
    }
    
    private void nextRound() {
        currRound.finish();
        currRound = new Round(player1, player2);
        currRound.start();
    }
}
