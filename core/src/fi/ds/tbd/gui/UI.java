package fi.ds.tbd.gui;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;

/**
 *
 * @author Daniel
 */
public class UI implements Disposable {
    
    private Stage stage;
    public Table root;
    
    public void create() {
        stage = new Stage();
        
        root = new Table();
        root.setFillParent(true);
        
        stage.addActor(root);
    }
    
    public void update(float delta) {
        stage.act(delta);
    }
    
    public void render() {
        stage.draw();
    }
    
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
