package src;


import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.*;
import java.time.*;
import java.util.*;
import java.time.LocalDateTime;

/**
 * @author Erik Gustavsson
 * @version 1.0
 * @since 2022-03-06
 * CalenderModel is a class that can be used to store data in a calendar.
 * It creates a LocalDateTime object for the current time shown in the program and also
 * a list of events that may happen in the schedule.
 * The class is Observable using PropertyChangeSupport

 */
public class CalenderModel {
    private static LinkedList<Event> Eventlist = new LinkedList<>();
    private LocalDateTime viewdate; //viewdate är det aktuella datum+tid användaren ser i views.
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    /**
     * Adds Observers that listens for changes in this class
     * @param listener - An object implementing propertychangelistener
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    /**
     * Constructor - setting current time to irl time
     */
    public CalenderModel() {
        viewdate = LocalDateTime.now();
    }

    /**
     * Method to create event and add to eventlist. It also notifies observers since data has been altered.
     * @param start - Where the event starts
     * @param end   - Where the event ends
     * @param title - The title of the event
     * PropertyChangeEvent sends starting time as oldvalue and endtime as newvalue
     */
    public void addEvent(LocalDateTime start, LocalDateTime end, String title, String location) {
        Eventlist.add(new Event(start, end, title, location));
        support.firePropertyChange("NewEvent", null, null);
    }

    /**
     * Method to remove Event
     * @param index of which event in the eventlist to remove
     */
    public void removeEvent(int index) {
        Eventlist.remove(index);
        support.firePropertyChange("RemoveEvent", null, null);
    }

    /**
     * @return A list of Events
     */
    public LinkedList<Event> getEvents() {
        return Eventlist;
    }

    /**
     * @return LocalDateTime objekt
     * Returns current time and notifies observers
     * @return LocalDateTime Object - the current time in Calender
     * PropertyChangeEvent sends "propertyname" and current time as newvalue
     */
    public LocalDateTime getViewTime() {
        support.firePropertyChange("currentTime", null, viewdate);
        return viewdate;
    }

    /**
     * Setter to alter year. Also notifies observers
     * @param y +- int of years to increase/decrease
     * PropertyChangeEvent sends int oldvalue as year before change and
     * int newValue as year after change
     */
    public void setYear(int y) {
        int oldValue = viewdate.getYear();
        if (y > 0) {
            viewdate = viewdate.plusYears(y);
        } else if (y < 0) {
            y = y * -1;
            viewdate = viewdate.minusYears(y);
        }
        int newValue = viewdate.getYear();

        support.firePropertyChange("YearChange", oldValue, newValue);
    }

    /**
     * Getter method to retrieve current month
     * @return Month - Month Object from java.time
     */
    public Month getMonth() {
        return viewdate.getMonth();
    }

    /**
     * Setter to set current month. Also notifies observers.
     * @param y int months to increase/decrease month
     */
    public void setMonth(int y) {
        int oldValue = viewdate.getMonthValue();
        if (y > 0) {
            viewdate = viewdate.plusMonths(y);
            int newValue = viewdate.getMonthValue();
            support.firePropertyChange("MonthChangePlus", oldValue, newValue);

        } else if (y < 0) {
            y = y * -1;
            viewdate = viewdate.minusMonths(y);
            int newValue = viewdate.getMonthValue();
            support.firePropertyChange("MonthChangeMin", oldValue, newValue);
        }
    }

    /**
     * Getter for retrieving current day.
     * @return int day
     */
    public int getDay() {
        return viewdate.getDayOfMonth();
    }

    /**
     * Setter to set day
     * Increases or decreases the current date. Also notifies observers.
     * @param d int (number of days)
     */
    public void setDay(int d) {
        int oldValue = viewdate.getDayOfMonth();
        if (d > 0) {
            viewdate = viewdate.plusDays(d);
            int newValue = viewdate.getDayOfMonth();
            support.firePropertyChange("DayChangePlus", oldValue, newValue);
        } else if (d < 0) {
            d = d * -1;
            viewdate = viewdate.minusDays(d);
            int newValue = viewdate.getDayOfMonth();
            support.firePropertyChange("DayChangeMin", oldValue, newValue);
        }

    }

    /**
     * Saves eventlist with all existing events to file
     */
    public void save() {
        JFileChooser fc = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("DAT files", "dat");
        fc.setFileFilter(filter);
        int returnVal = fc.showSaveDialog(null);
        String filepath = null;
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            try {
                String filename = file.getCanonicalPath();
                if (!file.getName().endsWith(".dat")) {
                    file = new File(filename + ".dat");
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            filepath = file.getAbsolutePath();
        }
        try {
            assert filepath != null;
            FileOutputStream output = new FileOutputStream(filepath);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(output);
            objectOutputStream.writeObject(Eventlist);
            objectOutputStream.flush();
            objectOutputStream.close();
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null,"No such file or directory: " + filepath, "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IOException e){
            JOptionPane.showMessageDialog(null,"Something went wrong", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Loads file and inserts into a file
     */
    public void load() {
        JFileChooser fc = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("DAT files", "dat");
        fc.setFileFilter(filter);
        int returnVal = fc.showOpenDialog(null);
        String filepath = null;
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            filepath = file.getAbsolutePath();
        }
        try {
            assert filepath != null;
            FileInputStream input = new FileInputStream(filepath);
            ObjectInputStream objectInputStream = new ObjectInputStream(input);
            LinkedList<Event> list = (LinkedList<Event>) objectInputStream.readObject();
            objectInputStream.close();
            Eventlist = list;
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "No such file or directory"+filepath, "Load failed", JOptionPane.ERROR_MESSAGE);
        }
        catch (IOException | ClassNotFoundException e){
            JOptionPane.showMessageDialog(null, "Something went wrong", "Error", JOptionPane.ERROR_MESSAGE);
        }
        support.firePropertyChange("LoadedEvents", null, null);
    }
}