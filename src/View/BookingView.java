package src.View;
import src.CalenderModel;
import src.Event;
import src.Weather;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedList;
/**
 * This class is a JPanel that displays all user booked events.
 *
 * @author  Elias Carlsson, Hanna Pham
 * @version 1.0
 * @since   2022-03-05
 */
public class BookingView extends JPanel implements PropertyChangeListener {
    private ArrayList<JButton> buttonArr = new ArrayList<>();
    CalenderModel model;
    public BookingView(CalenderModel m){
        model=m;
        JLabel header = new JLabel();
        header.setText("No bookings");
        this.add(header);
        JLabel temp = new JLabel(Weather.getTemperature() + " In Gothenburg");
        this.add(temp);
    }

    public void addBookingViewListener(ActionListener a){
        for(JButton b : buttonArr){
            b.addActionListener(a);
        }
    }

    /**
     * Fires when an observable changes an observed property.
     * Remakes the JPanel with new event list.
     * @param evt - information about the property change
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if(evt.getPropertyName().equals("NewEvent") | evt.getPropertyName().equals("LoadedEvents") | evt.getPropertyName().equals("RemoveEvent")){
        this.removeAll();
        buttonArr.clear();

        LinkedList<Event> list = model.getEvents();
        this.setLayout(new GridLayout(list.size()+1, 1, 5, 5)); //Sätter antal rader till antal events + titel

        JLabel header = new JLabel();
        if(list.size()>0){
            header.setText("Bookings");
        } else{ header.setText("No bookings"); }
        this.add(header);
        JLabel temp = new JLabel(Weather.getTemperature() + " In Gothenburg");
        this.add(temp);

        int i=0;
        for(Event e : list){//Lägger till en panel för varje event med tid+plats och edit knapp
            JPanel p = new JPanel(new GridLayout(2,2,10,10));
            p.setBorder(new EtchedBorder());
            String title_ = e.getDescription();
            JLabel title = new JLabel(title_);
            LocalDateTime startdatetime = e.getStartTime();
            LocalDateTime enddatetime = e.getEndTime();
            DateTimeFormatter format_ = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
            String starttime_ = startdatetime.format(format_);
            String endtime_ = enddatetime.format(format_);
            JLabel starttime = new JLabel("From: "+starttime_);
            JLabel endtime = new JLabel("Until: "+ endtime_);
            JPanel time = new JPanel(new GridLayout(2,1));
            time.add(starttime);
            time.add(endtime);
            String location_ = e.getLocation();
            JLabel location = new JLabel(location_);
            JButton remove = new JButton("REMOVE");
            remove.setActionCommand(String.valueOf(i));
            buttonArr.add(remove);
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