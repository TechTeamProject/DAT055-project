package src.View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class TopBar extends JPanel {
    private JPanel header;
    private JPanel monthTitlePanel;
    private JLabel monthTitle;
    private JButton previousButton;
    private JButton nextButton;

    public TopBar(){

        setLayout(new BorderLayout());
        setSize(1000,500);
        header = new JPanel(new GridLayout(1,3));
        header.setBorder(new EmptyBorder(6,6,6,6));
        add(header, BorderLayout.PAGE_START);

        //The middle of the header
        monthTitlePanel = new JPanel();
        monthTitle = new JLabel("JANUARI 2022");
        previousButton = new JButton("<");
        nextButton = new JButton(">");
        monthTitlePanel.add(previousButton);
        monthTitlePanel.add(monthTitle);
        monthTitlePanel.add(nextButton);
        header.add(monthTitlePanel);
    }
}
