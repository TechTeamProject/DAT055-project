package src.View;
import src.CalenderModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.*;

public class EventView extends JPanel implements ActionListener {
    CalenderModel model;

    JTextField text1;

    JTextField text2;

    JTextField text3;

    JTextField text4;

    public EventView(CalenderModel m){
        this.model=m;
        JPanel p = new JPanel(); //Panel för event info
        p.setLayout(new GridLayout(6,2, 10,5));
        JPanel p1 = new JPanel();//Panel för event info och save knapp
        p1.setLayout(new BorderLayout());
        JPanel p2 = new JPanel(); //Panel för save knapp

        JLabel title = new JLabel(" Title: ");
        text1 = new JTextField("");
        text1.addActionListener(this);

        JLabel from = new JLabel(" From: ");
        text2 = new JTextField("yyyy-MM-dd HH:mm");
        text2.addActionListener(this);

        JLabel until = new JLabel(" Until: ");
        text3 = new JTextField("yyyy-MM-dd HH:mm");
        text3.addActionListener(this);

        JLabel location = new JLabel(" Location: ");
        text4 = new JTextField("");
        text4.addActionListener(this);

        JButton button = new JButton("Save");
        button.addActionListener(this);
        button.setActionCommand("save");

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

    @Override
    public void actionPerformed(ActionEvent e) {
        String str=e.getActionCommand();
        if(str.equals("save")){
        String title = text1.getText();
        String fromtime = text2.getText();
        String untiltime = text3.getText();
        String loc = text4.getText();
        try{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime fromdatetime = LocalDateTime.parse(fromtime, formatter);
            LocalDateTime untildatetime = LocalDateTime.parse(untiltime, formatter);
            model.addEvent(fromdatetime,untildatetime,title,loc);
            text1.setText("");
            text2.setText("yyyy-MM-dd HH:mm");
            text3.setText("yyyy-MM-dd HH:mm");
            text4.setText("");
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, "Wrong date/time format", "Error", JOptionPane.ERROR_MESSAGE);
        }
        }
    }
}

