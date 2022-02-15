package src;

import javax.swing.*;
import java.awt.*;

public class WeekView extends JPanel {
    public JPanel header;
    public JButton settingButton;
    public JPanel contentPane;
    public JPanel monday;
    public JPanel tuesday;
    public JPanel wednesday;
    public JPanel thursday;
    public JPanel friday;
    public JPanel saturday;
    public JPanel sunday;



    public WeekView(){

        setLayout(new BorderLayout());
       // header = new JPanel(new GridLayout(1,6));
        header = new JPanel();

        header.setBackground(Color.green);
        add(header, BorderLayout.PAGE_START);

        settingButton = new JButton("Inställningar");
        header.add(settingButton);
      /*  header.add(new JPanel());
        header.add(new JPanel());
        header.add(new JPanel());
        header.add(new JPanel());
        header.add(new JPanel());
        header.add(new JPanel());*/

        contentPane = new JPanel(new GridLayout(1,7));
        contentPane.setBackground(Color.blue);
        add(contentPane);

        monday = new JPanel();
        contentPane.add(monday);
        monday.setBackground(Color.red);
        tuesday = new JPanel();
        contentPane.add(tuesday);
        tuesday.setBackground(Color.orange);monday = new JPanel();
        contentPane.add(monday);
        monday.setBackground(Color.red);


    }
}
