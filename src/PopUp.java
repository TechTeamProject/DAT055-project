package src;

import javax.swing.*;
import java.awt.event.ActionListener;

/**@author Erik Gustavsson
 * @version 1.0
 * @since 2022-03-06
 *
 * Class used to create a small rightclickpopup with options.
 * Using JPopupMenu superclass
 */
public class PopUp extends JPopupMenu{
    private JMenuItem popup;
    private JMenuItem popupRemove;

    /**Constructor
     *
     * @param s, s is a string name to determine which view we are rightclicking in.
     *           Possibility to enable rightclick in more panels if needed.
     */
    public PopUp(String s) {
        if (s.equals("week")) {
            popup = new JMenuItem("Create Event");
            popupRemove = new JMenuItem("Remove Event");
            add(popup);
            add(popupRemove);
        }
    }

    public void  addPopupListener(ActionListener a) {
        popup.addActionListener(a);
        popupRemove.addActionListener(a);
    }
}