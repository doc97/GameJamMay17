package fi.ds.tbd.gui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

/**
 *
 * @author Daniel
 */
public class HealthIndicator extends HorizontalGroup {
    
    private Texture health;
    public PropertyListener<Integer> listener;
    public Property<Integer> property;
    
    public HealthIndicator(Property<Integer> property) {
        super();
        this.property = property;
        health = new Texture("Heart2.png");
        
        for (int i = 0; i < property.value(); i++)
            addActor(new Image(health));
        
        listener = (i) -> {
            clearChildren();
            for (int j = 0; j < i; j++)
                addActor(new Image(health));
        };
        property.setListener(listener);
    }
}
