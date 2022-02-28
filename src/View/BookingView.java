package src.View;
import src.CalenderModel;
import src.Event;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class BookingView extends JPanel implements ActionListener{
    CalenderModel model;
    public BookingView(CalenderModel m){
        this.model = m;
        LinkedList<Event> list = m.getEvents();
        this.setLayout(new GridLayout(list.size()+1, 1, 5, 5)); //Sätter antal rader till antal events + titel

        JLabel header = new JLabel();
        if(list.size()>0){
            header.setText("Bookings");
        } else{ header.setText("No bookings"); }
        this.add(header);

        int i=0;
        for(Event e : list){//Lägger till en panel för varje event med tid+plats och edit knapp
            JPanel p = new JPanel(new GridLayout(2,2,10,10));
            p.setBorder(new EtchedBorder());
            String title_ = e.getDescription();
            JLabel title = new JLabel(title_);
            String time_ = e.getFormattedStart();
            JLabel time = new JLabel(time_);
            String location_ = e.getLocation();
            JLabel location = new JLabel(location_);
            JButton remove = new JButton("REMOVE");
            remove.addActionListener(this);
            remove.setActionCommand(String.valueOf(i));
            p.add(title);
            p.add(time);
            p.add(location);
            p.add(remove);
            this.add(p);
            i++;
        }
    }

    private void update(){
        //this.removeAll();
        //new BookingView(m);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        int index = Integer.parseInt(cmd);
        model.removeEvent(index);
    }
}