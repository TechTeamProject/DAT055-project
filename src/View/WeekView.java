package src.View;


import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class WeekView extends TopBar implements PropertyChangeListener {

    private JPanel contentPane;
    private String [] weekDays =  new String[]{"MON" , "TUE", "WED", "THU", "FRI", "SAT", "SUN"};

    public WeekView(){

        contentPane = new JPanel(new GridLayout(1,7));
        contentPane.setPreferredSize(new Dimension(1000,400));
        add(contentPane);

        for(int i=0; i<weekDays.length; ++i){

            //seven vertical boxes representing each day of the week
            JPanel dayBox = new JPanel(new GridLayout(8,1));
            dayBox.setBorder(new EtchedBorder());

            //Each dayBox has a titleBox consisting of two vertical boxes
            JPanel titleBox = new JPanel(new GridLayout(2,1));
            titleBox.setBorder(new EtchedBorder());
            dayBox.add(titleBox);

            //upper half of the titleBox
            JPanel dateBox = new JPanel();
            titleBox.add(dateBox);
            dateBox.add(new JLabel(weekDays[i]));

            //lower half of the titleBox
            JPanel titelBoxDown = new JPanel();
            titelBoxDown.add(new JLabel("?"));
            titleBox.add(titelBoxDown);

            contentPane.add(dayBox);
        }
    }

    public void addWeekViewListener(ActionListener a){

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println("TEST: Observer interface works. In the event this is what it says:" + evt);
    }
}
