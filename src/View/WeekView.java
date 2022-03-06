package src.View;

import src.Control;
import src.Event;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.*;

import static java.awt.GridBagConstraints.*;

public class WeekView extends JPanel implements PropertyChangeListener, Serializable {

    private JPanel contentPane;
    private String [] weekDays =  new String[]{"MON" , "TUE", "WED", "THU", "FRI", "SAT", "SUN"};
    private JPanel header;
    private JPanel monthTitlePanel;
    private JLabel weekLabel;
    private JLabel monthTitle;
    private JLabel yearLabel;
    private JPanel body;
    private JButton previousButton;
    private JButton nextButton;
    private LocalDateTime weektime = LocalDateTime.now();
    private LinkedList<JLabel> title = new LinkedList<>();
    private LinkedList<Event> eventlist = new LinkedList<>();
    private LinkedList<JPanel> dayBox = new LinkedList<>();
    private int gridycount = 4;
    private Color lightgreen = new Color(229,255,204);
    private static LinkedList<JLabel> eventlabel = new LinkedList<>();

    /**
     * The class that represents the weekly schedule which retrieves the event list from
     * the controller and gets current time through the observer interface from the model
     *
     * @author  David Behdadpoor, Erik Gustavsson
     * @version 1.1.0
     * @since   2022-02-20
     */
    public WeekView() {

        header = new JPanel(new GridLayout(1,3));
        monthTitlePanel = new JPanel();
        weekLabel = new JLabel();
        monthTitle = new JLabel();
        yearLabel = new JLabel();
        previousButton = new JButton("<");
        nextButton = new JButton(">");
        monthTitlePanel.add(monthTitle);
        monthTitlePanel.add(yearLabel);
        monthTitlePanel.add(weekLabel);
        header.add(previousButton);
        header.add(monthTitlePanel);
        header.add(nextButton);

        //The Weekdaysfield
        contentPane = new JPanel(new GridLayout(1, 7));
        contentPane.setPreferredSize(new Dimension(1000, 400));
        contentPane.setName("contentpane");

        //Both put together
        body = new JPanel(new BorderLayout());
        body.add(header, BorderLayout.NORTH);
        body.add(contentPane, BorderLayout.CENTER);
        add(body);

        for (int i = 0; i < weekDays.length; ++i) {

            //seven vertical boxes representing each day of the week
            dayBox.add(new JPanel(new GridBagLayout()));
            GridBagConstraints c = new GridBagConstraints();

            dayBox.getLast().setBorder(new EtchedBorder());
            dayBox.getLast().setBackground(Color.lightGray);

            //upper half of the titleBox
            JPanel dateBox = new JPanel();
            dateBox.add(new JLabel(weekDays[i]));
            c.gridx = 0;
            c.gridy = 0;
            c.fill = GridBagConstraints.BOTH;
            c.anchor = FIRST_LINE_START;
            c.weighty = 0.1;
            c.weightx = 0.5;
            c.weighty = REMAINDER;
            Insets inset = new Insets(0, 0, 0, 0);
            Insets noinset = new Insets(0,0,0,0);

            //Panel with week dates.
            JPanel titelBoxDown = new JPanel();
            String stringdate = Integer.toString(weektime.getDayOfMonth() + i);
            title.add(new JLabel(stringdate));
            title.getLast().setVerticalAlignment(JLabel.TOP);

            titelBoxDown.add(title.getLast());

            dayBox.getLast().add(dateBox, c);

            //Weekdatepanel added to each dayBox
            c.gridx = 0;
            c.gridy = 1;
            c.weighty = 0.1;
            c.weightx = 0.5;
            c.fill = GridBagConstraints.BOTH;
            c.anchor = FIRST_LINE_END;
            c.insets = inset;

            dayBox.getLast().add(titelBoxDown, c);
            contentPane.add(dayBox.getLast());
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

        //Used to set time equal to modeltime
        if (evt.getPropertyName().equals("currentTime") ) {
            String time = evt.getNewValue().toString();
            weektime = LocalDateTime.parse(time);
        }

        //Checks if 7 days have been added to current time in model, if so, reload the weekview. Same with minus 7 days.
        if (evt.getPropertyName().equals("DayChangePlus") && (weektime.plusDays(7).getDayOfMonth() == evt.getNewValue().hashCode()) )  {
            weektime = weektime.plusDays(7);
            loadEvent();
        }
        else if (evt.getPropertyName().equals("DayChangeMin") && (weektime.minusDays(7).getDayOfMonth() == evt.getNewValue().hashCode())) {
            weektime = weektime.minusDays(7);
            loadEvent();
        }
        //Sets the monthtitle
        monthTitle.setText(weektime.getMonth().toString());

        //Sets the weeklabel
        TemporalField woy = WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear();
        int weekNumber = weektime.get(woy);
        weekLabel.setText("   Week: " + weekNumber);

        //Sets the yearlabel
        yearLabel.setText(String.valueOf(weektime.getYear()));

        //Sets days
        int dayofweek = weektime.getDayOfWeek().getValue()-1;
        for (int i=0; i<7; i++) {
            if(i<=dayofweek){
                title.get(i).setText(Integer.toString(weektime.minusDays(dayofweek-i).getDayOfMonth()));
            } else {
                title.get(i).setText(Integer.toString(weektime.plusDays(i-dayofweek).getDayOfMonth()));
            }
        }

        //Vid nytt event uppdateras weekview
        if ((evt.getPropertyName().equals("NewEvent")) || (evt.getPropertyName().equals("RemoveEvent")) || (evt.getPropertyName().equals("LoadedEvents"))) {
                eventlist = Control.getCalenderEvents();
                loadEvent();
                this.revalidate();
                this.repaint();

        }
    }




    /**
     * A method that converts the start and end time of an event to a string
     * @param e the current event whose start and end time are to be calculated
     * @return returns start and end time in HH: mm or H: mm format depending on whether the hours are single digits and adds a 0 at
     * the beginning of the start and the end minutes if they are single digits so that they are always written in mm format
     */

    private String getEventTime(Event e){

        String eventStartHour = String.valueOf(e.getStartTime().getHour());
        String eventEndHour = String.valueOf(e.getEndTime().getHour());
        String eventStartMinute = String.valueOf(e.getStartTime().getMinute());
        String eventEndMinute = String.valueOf(e.getEndTime().getMinute());

        if(Integer.parseInt(eventStartMinute)/10 == 0 && Integer.parseInt(eventEndMinute)/10 == 0){
            return eventStartHour + ":0" + eventStartMinute + "-" + eventEndHour + ":0" + eventEndMinute;
        }

        if(Integer.parseInt(eventStartMinute)/10 == 0){
            return eventStartHour + ":0" + eventStartMinute + "-" + eventEndHour + ":" + eventEndMinute;
        }

        if(Integer.parseInt(eventEndMinute)/10 == 0){
            return eventStartHour + ":" + eventStartMinute + "-" + eventEndHour + ":0" + eventEndMinute;
        }

        return eventStartHour + ":" + eventStartMinute + "-" + eventEndHour + ":" + eventEndMinute;
    }

    /**
     * A method that checks the current week and checks if any event is on and off.
     * The method first deletes everything before it is re-posted so that you can scroll between the weeks.
     */

    private void loadEvent() {
        Collections.sort(eventlist);

        //In all 7 dayBoxes, delete every component except first two (dateBox)
        for (int i=0; i < 7; i++) {
            if (dayBox.get(i).getComponentCount() <= 3) {
                for (int y=2; y < (dayBox.get(i).getComponentCount()); y++ ) {
                    dayBox.get(i).remove(y);
                }
            }
            else if (dayBox.get(i).getComponentCount() > 3) {
                for (int y = (dayBox.get(i).getComponentCount()-1); y > 1; y--) {
                    dayBox.get(i).remove(y);
                }
            }
        }
        LocalDateTime monday = weektime.minusDays(weektime.getDayOfWeek().getValue()-1);
        //These for loops iterates through the seven-day boxes and check every event if anyone is on the same day, if so it loads them.
         for (int i=0; i<7; i++) {
             for (int y = 0; y < eventlist.size(); y++) {

                 LocalDateTime eventtime = eventlist.get(y).getStartTime();

                 if (monday.plusDays(i).getDayOfMonth() == eventtime.getDayOfMonth() && monday.plusDays(i).getMonth() == eventtime.getMonth() && monday.plusDays(i).getYear() == eventtime.getYear()) {
                     GridBagConstraints c = new GridBagConstraints();

                     c.gridx = 0;
                     c.gridy = gridycount;
                     gridycount++;
                     c.fill = GridBagConstraints.BOTH;
                     c.weighty = 0.5;
                     c.weightx = 0.5;
                     c.ipady = 0;
                     c.anchor = CENTER;

                     //Events blocks design
                     eventlabel.add(new JLabel("<html>" + eventlist.get(y).getDescription() + " <br/>" +
                             getEventTime(eventlist.get(y)) + " <br/>" + eventlist.get(y).getLocation() + "</html>", SwingConstants.CENTER));
                     eventlabel.getLast().setBackground(lightgreen);
                     eventlabel.getLast().setOpaque(true);
                     eventlabel.getLast().setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
                     eventlabel.getLast().setName(eventlist.get(y).getDescription());

                     dayBox.get(i).add(eventlabel.getLast(), c);

                 }
             }
         }
    }

    public void addWeekViewListener(MouseListener a) {
        contentPane.addMouseListener(a);

    }
    public static void UpdateViewEventListener(MouseListener a) {
        //Loop through an remove all listeners
        for (int i=0; i < eventlabel.size(); i++) {
            eventlabel.get(i).removeMouseListener(a);
        }
        for (int i=0; i< eventlabel.size(); i++) {
            eventlabel.get(i).addMouseListener(a);
        }
    }
    public void  addWeekViewActionListener(ActionListener a) {
        previousButton.addActionListener(a);
        nextButton.addActionListener(a);
    }
}
