package src.View;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class OptionView extends JPanel implements PropertyChangeListener {

    public OptionView(){

        JLabel label = new JLabel("Settings",SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.PLAIN, 30));

        JPanel p = new JPanel(new GridLayout(5,1,10,10));

        JButton button1 = new JButton("Appearance");
        JButton button2 = new JButton("Share");
        JButton button3 = new JButton("Download");
        JButton button4 = new JButton("Upload");

        p.add(label);
        p.add(button1);
        p.add(button2);
        p.add(button3);
        p.add(button4);
        this.add(p);

    }
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println("TEST: Observer interface works. In the event this is what it says:" + evt);
    }
}
