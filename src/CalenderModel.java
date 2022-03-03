package src;


import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.*;
import java.time.*;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.*;
import java.time.LocalDateTime;

public class CalenderModel {
    private static LinkedList<Event> Eventlist = new LinkedList<Event>();
    private String language = "SWE"; //Programmet startar med svenska.
    private Color color = Color.WHITE; //Programmet startar med vit färg.
    private LocalDateTime now = LocalDateTime.now();
    private YearMonth yearMonthObject = YearMonth.of(2022, 2);
    private int daysInMonth = yearMonthObject.lengthOfMonth(); //28
    private LocalDateTime viewdate; //viewdate är det aktuella objektet man ser i View.
    private Calendar viewweek;
    Locale swe;
    Locale eng = new Locale("en");
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public CalenderModel() {
        viewdate = LocalDateTime.now();
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }

    /**
     * @param start - Where the event starts
     * @param end   - Where the event ends
     * @param title - The title of the event
     *              firePropertyChange alerts listener. It sends the starting time as oldvalue and endtime as newvalue
     */
    public void addEvent(LocalDateTime start, LocalDateTime end, String title, String location) {
        this.Eventlist.add(new Event(start, end, title, location));
        support.firePropertyChange("NewEvent", start, end);
    }

    public void removeEvent(int index) {
        Eventlist.remove(index);
        support.firePropertyChange("RemoveEvent", 1, 0);
    }

    public LinkedList<Event> getEvents() {
        return this.Eventlist;
    }

    //Tar in år och månad och returnerar antal dagar i den månaden.
    public int getMaxDays(int year, int month) {
        return YearMonth.of(year, month).lengthOfMonth();
    }

    /**
     * Returnerar time som view tittar på just nu
     *
     * @return LocalDateTime objekt
     */
    public LocalDateTime getViewTime() {
        return viewdate;
    }

    /**
     * Returnerar verklig tid just nu
     *
     * @return
     */
    public LocalDateTime getRealTime() {
        return now;
    }

    /**
     * Skriv in ett LocalDateTime objekt och returnera en int.
     *
     * @param date object
     * @return an int
     */
    public int getDaysInMonth2(LocalDateTime date) {
        int year = date.getYear();
        Month month = date.getMonth();
        yearMonthObject = YearMonth.of(year, month);
        return yearMonthObject.lengthOfMonth();
    }

    /**
     * Metod för att få antal dagar i månaden
     *
     * @return int (antal dagar)
     */
    public int getDaysInMonth() {
        int year = viewdate.getYear();
        Month month = viewdate.getMonth();
        yearMonthObject = YearMonth.of(year, month);
        return yearMonthObject.lengthOfMonth();
    }

    /**
     * Metod för att returnera årtal
     *
     * @return int (årtal)
     */
    public int getViewdateYear() {
        return viewdate.getYear();
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

        } else if (y < 0) {
            y = y * -1;
            viewdate = viewdate.minusMonths(y);
        }
        int newValue = viewdate.getMonthValue();
        support.firePropertyChange("MonthChange", oldValue, newValue);
    }

    public int getWeek(){
        TemporalField woy = WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear();
        int weekNumber = viewdate.get(woy);
        return weekNumber;
    }

    /**
     * Metod för att returnera dag
     *
     * @return int (dag)
     */
    public int getDay() {
        return viewdate.getDayOfMonth();
    }

    public int[] getWeekDays(){
        int[] days = new int[7];
        LocalDateTime monday = viewdate.minusDays(viewdate.getDayOfWeek().getValue()-1);
        for(int i=0;i<7;i++){
            int dayVal = monday.plusDays(i).getDayOfMonth();
            days[i] = dayVal;
        }
        return days;
    }

    /**
     * Setter för dagar. d>0 för framåt, d<0 för bakåt.
     * support.firePropertyChange triggar propertyChange i aktuell view
     *
     * @param d (dagar)
     */
    public void setDay(int d) {
        int oldValue = viewdate.getDayOfMonth();
        if (d > 0) {
            viewdate = viewdate.plusDays(d);
        } else if (d < 0) {
            d = d * -1;
            viewdate = viewdate.minusDays(d);
        }
        int newValue = viewdate.getDayOfMonth();
        support.firePropertyChange("DayChange", oldValue, newValue);
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
            FileOutputStream output = new FileOutputStream(filepath);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(output);
            objectOutputStream.writeObject(this.Eventlist);
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
            FileInputStream input = new FileInputStream(filepath);
            ObjectInputStream objectInputStream = new ObjectInputStream(input);
            LinkedList<Event> list = (LinkedList<Event>) objectInputStream.readObject();
            objectInputStream.close();
            this.Eventlist = list;
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "No such file or directory"+filepath, "Load failed", JOptionPane.ERROR_MESSAGE);
        }
        catch (IOException | ClassNotFoundException e){
            JOptionPane.showMessageDialog(null, "Something went wrong", "Error", JOptionPane.ERROR_MESSAGE);
        }
        support.firePropertyChange("LoadedEvents", 1, 0);
    }
}
