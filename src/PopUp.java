package src;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static java.awt.event.MouseEvent.BUTTON3;

/**
 * PopUp is an abstract class that provide subuser Jpanel, Mouselistener and a function for rightclick.
 * TODO - We might add a function for opening a windowsframe for options, create events etc. Under construction
 */
public abstract class PopUp extends JPanel implements MouseListener {

    public PopUp() {

    }

    /**
     * A funcion that creates a rightclick window
     * @param e mouseevent from user.
     * @param s ,a String for what kind of window.
     */
    public static void doPop(MouseEvent e, String s) {
        if (e.getButton() == BUTTON3) {
            PopUpJPop menu = new PopUpJPop(s);
            menu.show(e.getComponent(), e.getX(), e.getY());
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }


}