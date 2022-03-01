package src.View;


import src.CalenderModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class OptionView extends JPanel implements ActionListener, PropertyChangeListener {
    CalenderModel m;
    public OptionView(CalenderModel m){
        this.m = m;
        JLabel label = new JLabel("Settings",SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.PLAIN, 30));

        JPanel p = new JPanel(new GridLayout(5,1,10,10));

        JButton button1 = new JButton("Appearance");
        JButton button2 = new JButton("Share");
        JButton button3 = new JButton("Save");
        button3.addActionListener(this);
        JButton button4 = new JButton("Load");
        button4.addActionListener(this);

        p.add(label);
        p.add(button1);
        p.add(button2);
        p.add(button3);
        p.add(button4);
        this.add(p);
    }
    @Override
    public void propertyChange(PropertyChangeEvent evt){

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        if(cmd.equals("Save")){
            m.save();
        }
        else if(cmd.equals("Load")){
            m.load();
        }
    }
}
