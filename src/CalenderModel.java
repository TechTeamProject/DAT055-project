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
 * CalenderModel is a class that can be used to store data in a calendar.
 * It creates a LocalDateTime object for the current time shown in the program and also
 * a list of events that may happen in the schedule.
 * The class is Observable using PropertyChangeSupport
 *
 */
public class CalenderModel {
    private static LinkedList<Event> Eventlist = new LinkedList<>();
    private LocalDateTime viewdate; //viewdate är det aktuella objektet man ser i View.
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public CalenderModel() {
        viewdate = LocalDateTime.now();
    }

    /**
     * @param start - Where the event starts
     * @param end   - Where the event ends
     * @param title - The title of the event
     *              firePropertyChange alerts listener. It sends the starting time as oldvalue and endtime as newvalue
     */
    public void addEvent(LocalDateTime start, LocalDateTime end, String title, String location) {
        Eventlist.add(new Event(start, end, title, location));
        support.firePropertyChange("NewEvent", null, null);
    }

    public void removeEvent(int index) {
        Eventlist.remove(index);
        support.firePropertyChange("RemoveEvent", null, null);
    }

    public LinkedList<Event> getEvents() {
        return Eventlist;
    }

    /**
     * Returnerar time som view tittar på just nu
     *
     * @return LocalDateTime objekt
     */
    public LocalDateTime getViewTime() {
        support.firePropertyChange("currentTime", null, viewdate);
        return viewdate;
    }

    /**
     * Setter för årtal. positivt tal för frammåt och minustal för bakåt.
     * support.firePropertyChange triggar propertyChange i aktuell view
     *
     * @param y (antal år framåt)
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
     * Metod för att returnera månad
     *
     * @return Month (månader)
     */
    public Month getMonth() {
        return viewdate.getMonth();
    }

    /**
     * Setter för månad
     * support.firePropertyChange triggar propertyChange i aktuell view
     *
     * @param y (antal månader framåt)
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
     * Metod för att returnera dag
     *
     * @return int (dag)
     */
    public int getDay() {
        return viewdate.getDayOfMonth();
    }

    /**
     * Setter för dagar. d>0 för framåt, d<0 för bakåt.
     * support.firePropertyChange triggar propertyChange i aktuell view
     * Den första uppdaterar view om aktuell tid. Den andra uppdaterar vilken förändring som skett.
     *
     * @param d (dagar)
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