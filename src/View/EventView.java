package src.View;
import src.CalenderModel;

import java.awt.*;
import javax.swing.*;

public class EventView extends JPanel {

    public EventView(CalenderModel m){

        JPanel p = new JPanel(); //Panel för event info
        p.setLayout(new GridLayout(6,2, 10,5));
        JPanel p1 = new JPanel();//Panel för event info och save knapp
        p1.setLayout(new BorderLayout());
        JPanel p2 = new JPanel(); //Panel för save knapp
        JLabel title = new JLabel(" Title: ");
        JTextField text1 = new JTextField("");
        JLabel from = new JLabel(" From: ");
        JTextField text2 = new JTextField("");
        JLabel until = new JLabel(" Until: ");
        JTextField text3 = new JTextField("");
        JLabel alarm = new JLabel(" Alarm: ");
        JTextField text4 = new JTextField("");
        JLabel repeat = new JLabel(" Repeat: ");
        JTextField text5 = new JTextField("");
        JLabel location = new JLabel(" Location: ");
        JTextField text6 = new JTextField("");
        JButton button = new JButton("Save");
        text1.setPreferredSize( new Dimension( 200, 35 ) );
        p2.add(button);
        p.add(title);
        p.add(text1);
        p.add(from);
        p.add(text2);
        p.add(until);
        p.add(text3);
        p.add(alarm);
        p.add(text4);
        p.add(repeat);
        p.add(text5);
        p.add(location);
        p.add(text6);
        p1.add(p, BorderLayout.CENTER);
        p1.add(p2, BorderLayout.SOUTH);
        this.add(p1);
    }
}

