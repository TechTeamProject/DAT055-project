package src;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * Class used to create a small rightclickpopup with options.
 */
public class PopUp extends JPopupMenu{
    private JMenuItem popup;
    private JMenuItem popupRemove;
    /**
     * @param s, s is a string name to determine which view we are rightclicking in.
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