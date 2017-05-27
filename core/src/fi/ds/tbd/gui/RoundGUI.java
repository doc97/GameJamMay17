package fi.ds.tbd.gui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.utils.Align;

/**
 *
 * @author Daniel
 */
public class RoundGUI extends GUI {

    private final Property<Integer> p1Score;
    private final Property<Integer> p1Health;
    private final Property<Integer> p2Score;
    private final Property<Integer> p2Health;
    private final Property<Integer> roundTime;
    
    public RoundGUI(UI ui, Property<Integer> p1Score, Property<Integer> p2Score,
            Property<Integer> p1Health, Property<Integer> p2Health,
            Property<Integer> roundTime) {
        super(ui);
        this.p1Score = p1Score;
        this.p2Score = p2Score;
        this.p1Health = p1Health;
        this.p2Health = p2Health;
        this.roundTime = roundTime;
    }
    
    @Override
    public void create() {
        ui.root.clear();
        
        LabelStyle labelStyle = new LabelStyle();
        labelStyle.font = new BitmapFont();
        labelStyle.fontColor = Color.WHITE;
        
        PropertyLabel p1HealthLbl = new PropertyLabel(p1Health, labelStyle);
        PropertyLabel p1ScoreLbl = new PropertyLabel(p1Score, labelStyle);
        PropertyLabel p2HealthLbl = new PropertyLabel(p2Health, labelStyle);
        PropertyLabel p2ScoreLbl = new PropertyLabel(p2Score, labelStyle);
        PropertyLabel roundTimeLbl = new PropertyLabel(roundTime, labelStyle);
        
        ui.root.add(p1HealthLbl).align(Align.topLeft).expandX();
        ui.root.add(p1ScoreLbl);
        ui.root.add(roundTimeLbl).width(96).align(Align.center);
        ui.root.add(p2ScoreLbl);
        ui.root.add(p2HealthLbl).align(Align.topRight).expandX();
        ui.root.row();
        ui.root.add().colspan(5).expand();
    }
}
