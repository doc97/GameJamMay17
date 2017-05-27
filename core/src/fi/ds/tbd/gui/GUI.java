package fi.ds.tbd.gui;

/**
 *
 * @author Daniel
 */
public abstract class GUI {
    
    protected UI ui;
    
    public GUI(UI ui) {
        this.ui = ui;
    }
    
    public abstract void create();
}
