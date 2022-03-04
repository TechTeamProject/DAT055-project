package src;

import src.View.WindowFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Class used to create a small rightclickpopup with options.
 */
public class PopUpJPop extends JPopupMenu{
    private JMenuItem popup;
    private JMenuItem popupRemove;
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
                    WindowFrame.changePanel();
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