package src.View;


import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;

public class WeekView extends TopBar {

    private JPanel contentPane;
    private String [] weekDays =  new String[]{"MON" , "TUE", "WED", "THU", "FRI", "SAT", "SUN"};





    public WeekView(){

        contentPane = new JPanel(new GridLayout(1,7));
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
}
