package src.View;


import src.ChatControl;
import src.Event;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.LocalDateTime;
import java.util.*;

import static java.awt.GridBagConstraints.FIRST_LINE_START;

public class WeekVieDemo extends JPanel implements PropertyChangeListener {


    private JPanel header;
    private JPanel monthTitlePanel;
    private JLabel monthTitle;
    private JButton previousButton;
    private JButton nextButton;

    private JPanel mainPanel;

    private GridBagConstraints gbc;
    private GridBagLayout theLayout;

    private LocalDateTime weektime = LocalDateTime.now();
    private LinkedList<Event> eventlist = new LinkedList<Event>();

    private LinkedList<JPanel> weekDays =new LinkedList<>();
    private String [] weekDayNames =  new String[]{"Sunday" , "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};

    private int gridycount = 4;

    public WeekVieDemo() {

        //header
        setLayout(new BorderLayout());
        setSize(1000,500);
        header = new JPanel(new GridLayout(1,3));
        header.setBorder(new EmptyBorder(6,6,6,6));
        add(header, BorderLayout.PAGE_START);

        //The middle of the header
        monthTitlePanel = new JPanel();
        monthTitle = new JLabel("Week ");
        previousButton = new JButton("<");
        nextButton = new JButton(">");
        monthTitlePanel.add(previousButton);
        monthTitlePanel.add(monthTitle);
        monthTitlePanel.add(nextButton);
        header.add(monthTitlePanel);

        mainPanel = new JPanel(new GridLayout(1, 7));
        mainPanel.setPreferredSize(new Dimension(1000, 400));
        add(mainPanel, BorderLayout.CENTER);


        theLayout = new GridBagLayout();
        gbc = new GridBagConstraints();
        gbc.weightx = 0.1;
        gbc.weighty = 0.1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.PAGE_START;


        for(int i=0; i<7; i+=1){

            weekDays.add(new JPanel(theLayout));
            JPanel dayBox = weekDays.get(i);
            dayBox.setBorder(new EtchedBorder());
            JButton dayTitleBtn = new JButton(weekDayNames[i]);
            addobjects(dayTitleBtn, dayBox, theLayout, gbc, 0 , 0, 1,1,10);
            mainPanel.add(dayBox);
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


        //Sets the monthtitle
        monthTitle.setText(weektime.getMonth().toString());

        //For testing
        System.out.println("TEST in WEEK: Observer sees this:" + evt);

        if (evt.getPropertyName().equals("NewEvent")) {
            eventlist = ChatControl.getCalenderEvents();


            for (Event event : eventlist) {
                System.out.println("Nytt Event " + event.getDescription());
            }

        }
    }




    public void addWeekViewListener(MouseListener a) {
        mainPanel.addMouseListener(a);
    }
    public void  addWeekViewActionListener(ActionListener a) {
        System.out.println("It happens");
        previousButton.addActionListener(a);
        nextButton.addActionListener(a);
    }
}




