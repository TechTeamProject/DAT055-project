package src.View;


import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.DayOfWeek;

public class WeekView extends JPanel implements PropertyChangeListener {

    private JPanel contentPane;
    private JButton previous;
    private JButton next;
    private JLabel weekLabel;
    private String [] weekDays =  new String[]{"MON" , "TUE", "WED", "THU", "FRI", "SAT", "SUN"};
    private JPanel topbar;
    private JLabel mon = new JLabel();
    private JLabel tue = new JLabel();
    private JLabel wed = new JLabel();
    private JLabel thu = new JLabel();
    private JLabel fri = new JLabel();
    private JLabel sat = new JLabel();
    private JLabel sun = new JLabel();
    private JLabel dayLabel[] = {mon,tue,wed,thu,fri,sat,sun};
    public WeekView(){
        topbar = new JPanel();
        contentPane = new JPanel(new GridLayout(1,7));
        contentPane.setPreferredSize(new Dimension(1000,400));
        previous = new JButton("<");
        next = new JButton(">");
        weekLabel = new JLabel();


        this.setLayout(new BorderLayout());
        topbar.add(previous);
        topbar.add(weekLabel);
        topbar.add(next);
        this.add(topbar, BorderLayout.PAGE_START);
        this.add(contentPane, BorderLayout.CENTER);



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
            titelBoxDown.add(dayLabel[i]);
            titleBox.add(titelBoxDown);

            contentPane.add(dayBox);
        }
    }

    public void addWeekViewListener(ActionListener a){
        previous.addActionListener(a);
        next.addActionListener(a);
    }
    public void setWeek(int w){
        weekLabel.setText("Week: " + String.valueOf(w));
    }
    public void setDays(int[] days){
        for(int i=0;i<7;i++){
            dayLabel[i].setText(String.valueOf(days[i]));
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println("TEST: Observer interface works. In the event this is what it says:" + evt);
    }
}
