package src.View;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;

public class WeekView extends TopBar {

    private JPanel contentPane;



    public WeekView(){

        //ContentPane

        contentPane = new JPanel(new GridLayout(1,7));
        add(contentPane);

        for(int i=0; i<7; ++i){
            JPanel dayBox = new JPanel(new GridLayout(8,1));
            dayBox.setBorder(new EtchedBorder());
            JPanel titleBox = new JPanel();
            dayBox.add(titleBox);
            titleBox.setBorder(new EtchedBorder());
            contentPane.add(dayBox);
        }


    }
}
