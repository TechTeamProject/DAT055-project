package src.View;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

public class EventView extends JPanel {

    private JTextField text1;

    private static JTextField text2;

    private static JTextField text3;

    private JTextField text4;

    JButton button = new JButton("Save");

    public EventView(){
        JPanel p = new JPanel(); //Panel för event info
        p.setLayout(new GridLayout(6,2, 10,5));
        JPanel p1 = new JPanel();//Panel för event info och save knapp
        p1.setLayout(new BorderLayout());
        JPanel p2 = new JPanel(); //Panel för save knapp

        JLabel title = new JLabel(" Title: ");
        text1 = new JTextField("");

        JLabel from = new JLabel(" From: ");
        text2 = new JTextField("yyyy-MM-dd HH:mm");

        JLabel until = new JLabel(" Until: ");
        text3 = new JTextField("yyyy-MM-dd HH:mm");

        JLabel location = new JLabel(" Location: ");
        text4 = new JTextField("");

        text1.setPreferredSize( new Dimension( 200, 35 ) );
        p2.add(button);
        p.add(title);
        p.add(text1);
        p.add(from);
        p.add(text2);
        p.add(until);
        p.add(text3);
        p.add(location);
        p.add(text4);
        p1.add(p, BorderLayout.CENTER);
        p1.add(p2, BorderLayout.SOUTH);
        this.add(p1);
    }

    public void addEventViewListener(ActionListener a){
        button.addActionListener(a);
    }

    public String getTitle(){
        return text1.getText();
    }
    public String getStartTime(){
        return text2.getText();
    }
    public String getEndTime(){
        return text3.getText();
    }
    public String getLoc(){
        return text4.getText();
    }
    public void setTitle(String s){
        text1.setText(s);
    }
    public void setStartTime(String s){
        text2.setText(s);
    }
    public void setEndTime(String s){
        text3.setText(s);
    }
    public void setLoc(String s){
        text4.setText(s);
    }

    public static void setEventTime(String s) {
        text2.setText(s);
        text3.setText(s);
    }
}

