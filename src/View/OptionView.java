package src.View;

import src.Lighthouse;
import src.Ships;
import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeListener;

public class OptionView extends JPanel implements Ships {
    private Lighthouse bean = new Lighthouse();

    @Override
    public void setupShip() {
      //  bean.addPropertyChangeListener();
      //  bean.addPropertyChangeListener(e ->
        //        label.setText((String) e.getNewValue())
    }
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


        //bean.addPropertyChangeListener(e -> button1.getActionCommand());
    }

}
