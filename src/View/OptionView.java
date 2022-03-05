package src.View;


import src.CalenderModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class OptionView extends JPanel implements PropertyChangeListener {
    JButton button1 = new JButton("Appearance");
    JButton button2 = new JButton("Save");
    JButton button3 = new JButton("Load");

    public OptionView() {
        this.setBackground(Color.BLACK);
        JLabel label = new JLabel("Settings", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.PLAIN, 30));

        JPanel p = new JPanel(new GridLayout(5, 1, 10, 10));

        p.add(label);
        p.add(button1);
        p.add(button2);
        p.add(button3);
        this.add(p);
    }

    public void addOptionViewListener(ActionListener a){
        button1.addActionListener(a);
        button2.addActionListener(a);
        button3.addActionListener(a);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}