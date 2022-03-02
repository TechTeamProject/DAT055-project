package src.View;
import src.CalenderModel;
import src.Event;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;

public class BookingView extends JPanel implements ActionListener, PropertyChangeListener {
    private static CalenderModel model;
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
            LocalDateTime datetime = e.getStarttime();
            DateTimeFormatter format_ = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
            String time_ = datetime.format(format_);
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

    public void addBookingViewListener(ActionListener a){

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        int index = Integer.parseInt(cmd);
        model.removeEvent(index);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println("Före if sats");
        if(evt.getPropertyName().equals("NewEvent") | evt.getPropertyName().equals("LoadedEvents") | evt.getPropertyName().equals("RemoveEvent")){
        this.removeAll();

        LinkedList<Event> list = model.getEvents();
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
            LocalDateTime datetime = e.getStarttime();
            DateTimeFormatter format_ = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
            String time_ = datetime.format(format_);
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
        this.revalidate();
        this.repaint();
        System.out.println("Test");
    }
    }
}