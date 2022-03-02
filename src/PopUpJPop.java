package src;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Class used to create the Rightclickwindows. Created from PopUp abstract class.
 */
public class PopUpJPop extends JPopupMenu{
    JMenuItem popup;
    JMenuItem popupRemove;

    /**
     * @param s, s is a string name to determine which view we are rightclicking in.
     */
    public PopUpJPop(String s) {
        if (s.equals("week")) {
            popup = new JMenuItem("Create Event");
            popupRemove = new JMenuItem("Remove Event");
            add(popup);
            add(popupRemove);
            popup.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("Event Created");
                    //TODO send info to controller to change panel to eventpanel

                }
            });
            popupRemove.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("Event Removed");
                    //TODO, remove event. Or put this into the controller
                }
            });
        }

    }
}