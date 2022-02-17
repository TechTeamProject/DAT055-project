package src.View;

import javax.swing.*;
import java.awt.*;

public class OptionView extends JPanel {

    public OptionView(){

        JLabel label = new JLabel("Setting",SwingConstants.CENTER);
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

}
