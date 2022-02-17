package src.View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class TopBar extends JPanel {
    private JPanel header;
    private JPanel settingPanel;
    private JButton settingButton;
    private JButton bookingButton;
    private JButton todoButton;
    private JButton weekMonthButton;

    private JPanel monthTitlePanel;
    private JLabel monthTitle;
    private JButton previousButton;
    private JButton nextButton;

    private JPanel bookingPanel;


    public TopBar(){

        setLayout(new BorderLayout());
        header = new JPanel(new GridLayout(1,3));
        header.setBorder(new EmptyBorder(6,6,6,6));
        add(header, BorderLayout.PAGE_START);

        //The left side of the header
        settingPanel = new JPanel(new GridLayout());
        settingButton = new JButton("Settings");
        settingPanel.add(settingButton);
        settingPanel.add(new JPanel());
        settingPanel.add(new JPanel());
        header.add(settingPanel);

        //The middle of the header
        monthTitlePanel = new JPanel();
        monthTitle = new JLabel("JANUARI 2022");
        previousButton = new JButton("<");
        nextButton = new JButton(">");
        monthTitlePanel.add(previousButton);
        monthTitlePanel.add(monthTitle);
        monthTitlePanel.add(nextButton);
        header.add(monthTitlePanel);

        //The right side of the header
        bookingPanel = new JPanel(new GridLayout(1,3,10,0));
        bookingButton = new JButton("Bookings");
        bookingPanel.add(bookingButton);
        todoButton = new JButton("Todolist");
        bookingPanel.add(todoButton);
        weekMonthButton = new JButton("Week // Month");
        bookingPanel.add(weekMonthButton);
        header.add(bookingPanel);

    }
}
