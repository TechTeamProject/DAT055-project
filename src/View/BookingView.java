package src.View;
import src.Event;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class BookingView extends JPanel {
    public BookingView(LinkedList<Event> list){
        update(list);
    }
    private void update(LinkedList<Event> list){
        this.removeAll();
        this.setLayout(new GridLayout(list.size()+1, 1, 5, 5)); //Sätter antal rader till antal events + titel
        JLabel header = new JLabel("Bookings");
        this.add(header);
        for(Event e : list){//Lägger till en panel för varje event med tid+plats och edit knapp
            JPanel p = new JPanel(new GridLayout(2,2,10,10));
            String title_ = e.getDescription();
            JLabel title = new JLabel(title_);
            String time_ = e.getFormattedStart();
            JLabel time = new JLabel(time_);
            String location_ = e.getLocation();
            JLabel location = new JLabel(location_);
            JButton edit = new JButton("EDIT");
            p.add(title);
            p.add(time);
            p.add(location);
            p.add(edit);
            this.add(p);
        }
    }
    private class Listener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            //Edit knapp

        }
    }
}
