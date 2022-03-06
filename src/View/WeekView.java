package src.View;


import src.CalenderModel;
import src.ChatControl;
import src.Event;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.text.StyledEditorKit;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.*;

import static java.awt.GridBagConstraints.*;

public class WeekView extends JPanel implements PropertyChangeListener, Serializable {

    private JPanel contentPane;
    private String [] weekDays =  new String[]{"MON" , "TUE", "WED", "THU", "FRI", "SAT", "SUN"};
    private LinkedList<JButton> eventButton = new LinkedList<>();
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
    private LinkedList<Event> eventlist = new LinkedList<Event>();
    private LinkedList<JPanel> dayBox = new LinkedList<>();
    private int gridycount = 4;
    GridBagConstraints con = new GridBagConstraints();
    private LocalDateTime eventtime;
    private int hour;
    private Color lightgreen = new Color(229,255,204);
    private static LinkedList<JLabel> eventlabel = new LinkedList<>();

    /**
     * WeekView, hämtar eventlista från controller samt får aktuell tid genom observerinterface från model
     * Constructor skapar panel med 7 st dagspaneler där event laddas in.
     * @Authors David Behdadpoor, Erik Gustavsson
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

    public void addobjects(Component componente, Container yourcontainer, GridBagLayout layout, GridBagConstraints gbc, int gridx, int gridy, int gridwidth, int gridheigth, int ipady){

        gbc.gridx = gridx;
        gbc.gridy = gridy;

        gbc.gridwidth = gridwidth;
        gbc.gridheight = gridheigth;

        gbc.ipady = ipady;

        layout.setConstraints(componente, gbc);
        yourcontainer.add(componente);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

        //Used to set time equal to modeltime
        if (evt.getPropertyName().equals("currentTime") ) {
            String time = evt.getNewValue().toString();
            weektime = LocalDateTime.parse(time);
            System.out.println("Current time " + weektime);
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
                eventlist = ChatControl.getCalenderEvents();
                System.out.println("propertychange remove from weekview");
                loadEvent();
                this.revalidate();
                this.repaint();

        }
    }



    /**
     * En metod som konverterar en events start respektive sluttid till en sträng
     * @param e den aktuella eventet vars start samt sluttid ska beräknas
     * @return returnerar start respektive sluttid i HH:mm eller H:mm format beroende på om timmarna är ensiffriga
     * samt lägger till en 0 i slutet av start samt slutminuterna ifall de är ensiffriga så att de alltid skrivs i mm format
     */

    private String getEventTime(Event e){
        String eventStartHour = String.valueOf(e.getStartTime().getHour());
        String eventEndHour = String.valueOf(e.getEndTime().getHour());
        String eventStartMinute = String.valueOf(e.getStartTime().getMinute());
        String eventEndMinute = String.valueOf(e.getEndTime().getMinute());

        return eventStartHour + ":" + eventStartMinute + "-" + eventEndHour + ":" + eventEndMinute;

    }

    /**
     * En hjälpmedtod som beräknar hur lång en event är, som sedan ska användas för att bestämma längden på eventen.
     * @param e
     * @return
     */

    private long timeDiff(Event e){
        Duration diffDuration = Duration.between(e.getEndTime(), e.getStartTime());
        return diffDuration.toMinutes();
    }


    /**
     * En metod som kollar igenom de 7 aktuella dagarna, kollar om något event ligger och lägger ut.
     * Metoden raderar först allt innan det läggs ut på nytt så att man kan bläddra mellan veckorna.
     * //TODO fixa så att det laddas in på nytt vid ny vecka
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

        //These forloops iterates through the seven dayboxes and check every event if anyone is on the same day, if so it loads them.
         for (int i=0; i<7; i++) {
             for (int y = 0; y < eventlist.size(); y++) {

                 LocalDateTime eventtime = eventlist.get(y).getStartTime();
                 if (weektime.plusDays(i).getDayOfMonth() == eventtime.getDayOfMonth() && weektime.plusDays(i).getMonth() == eventtime.getMonth() && weektime.plusDays(i).getYear() == eventtime.getYear()) {
                     System.out.println("How many times does this happen?");
                     GridBagConstraints c = new GridBagConstraints();

                     if (hour < eventtime.getHour()) {

                         // om senare starttid, lägg in med gridx lägre än tidigare
                         hour = eventtime.getHour();
                     }

                        //TODO Lägga in i vilken ordning events under samma dag hamnar mha c.gridy
                     c.gridx = 0;
                     c.gridy = gridycount;
                     gridycount++;
                     c.fill = GridBagConstraints.BOTH;
                     c.weighty = 0.5;
                     c.weightx = 0.5;
                     c.ipady = 0;
                     c.anchor = CENTER;

                     //Eventsblocks design
                     eventlabel.add(new JLabel("<html>" + eventlist.get(y).getDescription() + " <br/>" +
                             getEventTime(eventlist.get(y)) + "</html>", SwingConstants.CENTER));
                     eventlabel.getLast().setBackground(lightgreen);
                     eventlabel.getLast().setOpaque(true);
                     eventlabel.getLast().setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

                     eventlabel.getLast().setName(eventlist.get(y).getDescription());

                     //ActionMap actionMap = eventlabel.getActionMap();
                     //eventlabel.setActionMap(actionMap);

                     dayBox.get(i).add(eventlabel.getLast(), c);

                 }
             }
         }
    }


    private void setConstraint(LocalDateTime time) {
            //How many events that day
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
