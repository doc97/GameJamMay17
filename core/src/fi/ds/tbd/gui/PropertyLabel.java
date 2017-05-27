package fi.ds.tbd.gui;

import com.badlogic.gdx.scenes.scene2d.ui.Label;

/**
 *
 * @author Daniel
 * @param <T>
 */
public class PropertyLabel<T> extends Label {

    public PropertyListener<T> listener;
    public Property<T> property;
    
    public PropertyLabel(Property<T> prop, LabelStyle style) {
        super(prop.value().toString(), style);
        
        listener = (t) -> setText(t.toString());
        property = prop;
        property.setListener(listener);
    }
}
