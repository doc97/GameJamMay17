package fi.ds.tbd.gui;

/**
 *
 * @author Daniel
 * @param <T>
 */
public class Property<T> {
    private PropertyListener<T> listener;
    private T t;
    
    public Property(T t) { this.t = t; }
    public Property() { }
    
    public T value() {
        return t;
    }
    
    public void set(T t) {
        this.t = t;
        if (listener != null)
            listener.notifyChange(t);
    }
    
    public void setListener(PropertyListener<T> listener) {
        this.listener = listener;
    }
}
