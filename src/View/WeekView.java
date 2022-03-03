package src.View;


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
import java.time.LocalDateTime;
import java.util.LinkedList;

import static java.awt.GridBagConstraints.*;

public class WeekView extends JPanel implements PropertyChangeListener {

    private JPanel contentPane;
    private String [] weekDays =  new String[]{"MON" , "TUE", "WED", "THU", "FRI", "SAT", "SUN"};
    private LinkedList<JButton> eventButton = new LinkedList<>();
    private JPanel header;
    private JPanel monthTitlePanel;
    private JLabel monthTitle;
    private JPanel body;
    private JButton previousButton;
    private JButton nextButton;
    private LocalDateTime weektime;

    public WeekView() {

        header = new JPanel(new GridLayout(1,3));
        monthTitlePanel = new JPanel();
        monthTitle = new JLabel("JANUARI 2022");
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
            JPanel dayBox = new JPanel(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();

            dayBox.setBorder(new EtchedBorder());
            dayBox.setBackground(Color.lightGray);


            //upper half of the titleBox
            JPanel dateBox = new JPanel();
            dateBox.setBackground(Color.RED);

            dateBox.add(new JLabel(weekDays[i]));
            c.gridx = 0;
            c.gridy = 0;
            c.fill = GridBagConstraints.BOTH;
            c.anchor = FIRST_LINE_START;
            c.weighty = 0.1;
            c.weightx = 0.5;
            Insets inset = new Insets(10, 0, 0, 0);
            //   c.insets = inset;

            dayBox.add(dateBox, c);


            //lower half of the titleBox
            JPanel titelBoxDown = new JPanel();
            JLabel title = new JLabel("?");
            title.setVerticalAlignment(JLabel.TOP);

            titelBoxDown.add(title);

            c.gridx = 0;
            c.gridy = 1;
            c.weighty = 0.1;
            c.weightx = 0.5;
            c.fill = GridBagConstraints.BOTH;
            c.anchor = FIRST_LINE_END;
            dayBox.add(titelBoxDown, c);


            JButton test = new JButton("Test1");
            c.gridx = 0;
            c.gridy = 2;
            c.fill = GridBagConstraints.BOTH;
            c.weighty = 0.5;
            c.weightx = 0.5;
            c.anchor = FIRST_LINE_START;

            dayBox.add(test, c);

            JButton test2 = new JButton("Test2");
            c.gridx = 0;
            c.gridy = 3;
            c.fill = GridBagConstraints.BOTH;
            c.weighty = 0.5;
            c.weightx = 0.5;
            c.anchor = FIRST_LINE_START;

            dayBox.add(test2, c);

            JButton test3 = new JButton("Test3");
            c.gridx = 0;
            c.gridy = 4;
            c.fill = GridBagConstraints.BOTH;
            c.weighty = 0.5;
            c.weightx = 0.5;
            c.anchor = FIRST_LINE_START;

            dayBox.add(test3, c);

            JPanel test4 = new JPanel();
            test4.setVisible(true);
            c.gridy = 5;
            c.fill = GridBagConstraints.BOTH;
            c.weighty = 0.5;
            c.weightx = 0.5;
            c.anchor = FIRST_LINE_START;

            dayBox.add(test4, c);

            /*
            LinkedList<JPanel> filler = new LinkedList<>();
            for (int y=0; y<40; y++) {
                filler.add(y,new JPanel());
                c.gridy=6+y;
                c.fill = GridBagConstraints.BOTH;
                c.weighty = 0.5;
                c.weightx = 0.5;
                c.anchor = FIRST_LINE_START;
                dayBox.add(filler.get(y),c);
            }
            */
            contentPane.add(dayBox);

        }
    }
    @Override
    public void propertyChange(PropertyChangeEvent evt) {

        if (evt.getPropertyName().equals("currentTime")) {
            String time = evt.getNewValue().toString();
            weektime = LocalDateTime.parse(time);
            System.out.println("Current time " + weektime);
        }

        if (evt.getPropertyName().equals("DayChangePlus") && (weektime.plusDays(7).getDayOfMonth() == evt.getNewValue().hashCode()) )  {
            System.out.println(evt.getNewValue());
            int newday = evt.getNewValue().hashCode();
            System.out.println("HASHCODE "+ newday);

                weektime = weektime.plusDays(7);
        }
        else if (evt.getPropertyName().equals("DayChangeMin") && (weektime.minusDays(7).getDayOfMonth() == evt.getNewValue().hashCode())) {
            weektime = weektime.minusDays(7);
            System.out.println("Minus, Day= " + weektime.getDayOfMonth());
        }


        System.out.println("TEST in WEEK: Observer sees this:" + evt);
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
