package src.View;


import src.ChatControl;
import src.Event;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.text.StyledEditorKit;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

import static java.awt.GridBagConstraints.*;

public class WeekView extends JPanel implements PropertyChangeListener, Serializable {

    private JPanel contentPane;
    private String [] weekDays =  new String[]{"MON" , "TUE", "WED", "THU", "FRI", "SAT", "SUN"};
    private LinkedList<JButton> eventButton = new LinkedList<>();
    private JPanel header;
    private JPanel monthTitlePanel;
    private JLabel monthTitle;
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

    public WeekView() {

        header = new JPanel(new GridLayout(1,3));
        monthTitlePanel = new JPanel();
        monthTitle = new JLabel();
        previousButton = new JButton("<");
        nextButton = new JButton(">");
        monthTitlePanel.add(previousButton);
        monthTitlePanel.add(monthTitle);
        monthTitlePanel.add(nextButton);
        header.add(monthTitlePanel);

        //The Weekdaysfield
        contentPane = new JPanel(new GridLayout(1, 7));
        contentPane.setPreferredSize(new Dimension(1000, 400));

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
            Insets inset = new Insets(10, 0, 0, 0);
            Insets noinset = new Insets(0,0,0,0);

            dayBox.getLast().add(dateBox, c);

            //Panel with week dates.
            JPanel titelBoxDown = new JPanel();
            String stringdate = Integer.toString(weektime.getDayOfMonth() + i);
            title.add(new JLabel(stringdate));
            title.getLast().setVerticalAlignment(JLabel.TOP);

            titelBoxDown.add(title.getLast());

            //Weekdatepanel added to each dayBox
            c.gridx = 0;
            c.gridy = 1;
            c.weighty = 0.1;
            c.weightx = 0.5;
            c.fill = GridBagConstraints.BOTH;
            c.anchor = FIRST_LINE_END;

            dayBox.getLast().add(titelBoxDown, c);

            JButton test3 = new JButton("Test3");
            c.gridx = 0;
            c.gridy = 2;
            c.fill = GridBagConstraints.BOTH;
            c.weighty = 0.5;
            c.weightx = 0.5;
            c.ipady = 0;
            c.anchor = FIRST_LINE_START;
            c.insets = noinset;

          //  dayBox.getLast().add(test3, c);

            JPanel test4 = new JPanel();
            test4.setVisible(true);
            c.gridy = 3;
            c.fill = GridBagConstraints.BOTH;
            c.weighty = 0.5;
            c.weightx = 0.5;
            c.anchor = FIRST_LINE_START;

            //dayBox.getLast().add(test4, c);
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

        if (evt.getPropertyName().equals("currentTime")) {
            String time = evt.getNewValue().toString();
            weektime = LocalDateTime.parse(time);
            System.out.println("Current time " + weektime);
        }

        if (evt.getPropertyName().equals("DayChangePlus") && (weektime.plusDays(7).getDayOfMonth() == evt.getNewValue().hashCode()) )  {
          //  System.out.println(evt.getNewValue());
          //  int newday = evt.getNewValue().hashCode();
          //  System.out.println("HASHCODE "+ newday);
                weektime = weektime.plusDays(7);
            loadEvent();
        }
        else if (evt.getPropertyName().equals("DayChangeMin") && (weektime.minusDays(7).getDayOfMonth() == evt.getNewValue().hashCode())) {
            weektime = weektime.minusDays(7);
            System.out.println("Minus, Day= " + weektime.getDayOfMonth());
            loadEvent();
        }
        //Sets the monthtitle
        monthTitle.setText(weektime.getMonth().toString());

        //Sets days
        for (int i=0; i<7; i++) {
            title.get(i).setText(Integer.toString(weektime.plusDays(i).getDayOfMonth()));
        }

        //For testing
       // System.out.println("TEST in WEEK: Observer sees this:" + evt);

        //Vid nytt event uppdateras weekview
        if (evt.getPropertyName().equals("NewEvent")) {
                eventlist = ChatControl.getCalenderEvents();

                loadEvent();
            for (Event event : eventlist) {
                System.out.println("Nytt Event " + event.getDescription());
            }

            //För testning


        }
    }

    /**
     * En metod som kollar igenom de 7 aktuella dagarna, kollar om något event ligger och lägger ut.
     * Metoden raderar först allt innan det läggs ut på nytt så att man kan bläddra mellan veckorna.
     * //TODO fixa så att det laddas in på nytt vid ny vecka
     */
    private void loadEvent() {
        Collections.sort(eventlist);

        //In all 7 dayBoxes, delete every component except first one (dateBox)
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

         for (int i=0; i<7; i++) {
             for (int y = 0; y < eventlist.size(); y++) {

                 LocalDateTime eventtime = eventlist.get(y).getStartTime();
                 if (weektime.plusDays(i).getDayOfMonth() == eventtime.getDayOfMonth() && weektime.plusDays(i).getMonth() == eventtime.getMonth() && weektime.plusDays(i).getYear() == eventtime.getYear()) {
                     System.out.println("How many times does this happen?");
                     GridBagConstraints c = new GridBagConstraints();
                     int hour = eventtime.getHour();
                        //TODO Lägga in i vilken ordning events under samma dag hamnar mha c.gridy
                     c.gridx = 0;
                     c.gridy = gridycount;
                     gridycount++;
                     c.fill = GridBagConstraints.BOTH;
                     c.weighty = 0.5;
                     c.weightx = 0.5;
                     c.ipady = 0;
                     c.anchor = FIRST_LINE_START;
                     dayBox.get(i).add(new JButton(eventlist.get(y).getDescription()), c);

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
    public void  addWeekViewActionListener(ActionListener a) {
        System.out.println("It happens");
        previousButton.addActionListener(a);
        nextButton.addActionListener(a);
    }
}
