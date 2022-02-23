package src;

import java.beans.PropertyChangeListener;

/**
 * The observer interface for our observers
 */
public interface Ships {

    /**
     * Add a bean and connect a propertychangelistener to the bean.
     */
    void setupShip();

}
