package src.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
/**
 * This class is used as the view for all months of the year.
 *
 * @author  Hanna Pham
 * @version 1.0
 * @since   2022-03-05
 */
public class OptionView extends JPanel {
    JButton button2 = new JButton("Save");
    JButton button3 = new JButton("Load");

    /**
     * Constructor method for the OptionView class.
     */
    public OptionView() {
        this.setBackground(Color.BLACK);
        JLabel label = new JLabel("Settings", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.PLAIN, 30));
        JPanel p = new JPanel(new GridLayout(5, 1, 10, 10));
        p.add(label);
        p.add(button2);
        p.add(button3);
        this.add(p);
    }
    /**
     * A method that adds a ActionListener to the buttons
     * @param a The ActionListener that is added.
     */
    public void addOptionViewListener(ActionListener a){
        button2.addActionListener(a);
        button3.addActionListener(a);
    }
}