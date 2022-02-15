package src;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import java.awt.*;

public class StartView extends Popup{


    private JPanel titlePanel;
    private JPanel header;


    private JPanel schedulePanel;

    private JButton newButton;
    private JButton schedule_1;
    private JButton schedule_2;
    private JButton schedule_3;
    private JButton schedule_4;

    private JButton toDO;



    public StartView(){

        this.setLayout(new BorderLayout());

        header = new JPanel(new GridLayout(0,1));
        header.setBorder(new EmptyBorder(10,75,10,75));
        add(header, BorderLayout.PAGE_START);

        titlePanel = new JPanel();
        titlePanel.setBorder(new EtchedBorder());
        titlePanel.add(new JLabel("Calender"));
        header.add(titlePanel);

        schedulePanel = new JPanel(new GridLayout(2,3,20,20));
        schedulePanel.setBorder(new EmptyBorder(20, 50, 20, 50));


        newButton = new JButton("New");
        schedule_1 = new JButton("Schema 1");
        schedule_2 = new JButton("Schema 2");
        schedule_3 = new JButton("Schema 3");
        schedule_4 = new JButton("Schema 4");
        toDO = new JButton("Todo");

        schedulePanel.add(newButton);
        schedulePanel.add(schedule_1);
        schedulePanel.add(schedule_2);
        schedulePanel.add(schedule_3);
        schedulePanel.add(schedule_4);
        schedulePanel.add(toDO);


        add(schedulePanel, BorderLayout.CENTER);


    }


}
