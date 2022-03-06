package src.View;

import java.awt.event.*;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.Locale;

/**
 * This class is a JPanel that displays all days in a month.
 *
 * @author  Elias Carlsson
 * @version 1.0
 * @since   2022-03-05
 */

public class MonthView extends JPanel implements PropertyChangeListener {

    private JPanel daypanel;
    private JPanel monthpanel;
    private JPanel topbar;
    private JButton previous = new JButton("<");
    private JButton next = new JButton(">");
    private JLabel monthLabel, mon, tue, wed, thu, fri, sat, sun;
    private LocalDateTime monthtime;
    private JButton b1 = new JButton(), b2 = new JButton(), b3= new JButton(), b4= new JButton(), b5= new JButton(),
            b6= new JButton(), b7= new JButton(), b8= new JButton(), b9= new JButton(), b10= new JButton(),
            b11= new JButton(), b12= new JButton(), b13= new JButton(), b14= new JButton(), b15= new JButton(),
            b16= new JButton(), b17= new JButton(), b18= new JButton(), b19= new JButton(), b20= new JButton(),
            b21= new JButton(), b22= new JButton(), b23= new JButton(), b24= new JButton(), b25= new JButton(),
            b26= new JButton(), b27= new JButton(), b28= new JButton(), b29= new JButton(), b30= new JButton(),
            b31= new JButton(), b32= new JButton(), b33= new JButton(), b34= new JButton(), b35= new JButton(),
            b36= new JButton(), b37= new JButton(), b38= new JButton(), b39= new JButton(), b40= new JButton(),
            b41= new JButton(), b42 = new JButton();
    private JButton[] buttons = {b1,b2,b3,b4,b5,b6,b7,b8,b9,b10,
            b11,b12,b13,b14,b15,b16,b17,b18,b19,
            b20,b21,b22,b23,b24,b25,b26,b27,b28,b29,
            b30,b31,b32,b33,b34,b35,b36,b37,b38,b39,
            b40,b41,b42};

    MonthView() {
        monthpanel = new JPanel(new GridLayout( 6,7,0,0));
        topbar = new JPanel();
        monthLabel = new JLabel();
        daypanel = new JPanel(new GridLayout(0, 7, 0, 0));
        mon = new JLabel("MON");
        mon.setHorizontalAlignment(JLabel.CENTER);
        tue = new JLabel("TUE");
        tue.setHorizontalAlignment(JLabel.CENTER);
        wed = new JLabel("WED");
        wed.setHorizontalAlignment(JLabel.CENTER);
        thu = new JLabel("THU");
        thu.setHorizontalAlignment(JLabel.CENTER);
        fri = new JLabel("FRI");
        fri.setHorizontalAlignment(JLabel.CENTER);
        sat = new JLabel("SAT");
        sat.setHorizontalAlignment(JLabel.CENTER);
        sun = new JLabel("SUN");
        sun.setHorizontalAlignment(JLabel.CENTER);

        daypanel.add(mon);
        daypanel.add(tue);
        daypanel.add(wed);
        daypanel.add(thu);
        daypanel.add(fri);
        daypanel.add(sat);
        daypanel.add(sun);

        for(JButton b : buttons){
            monthpanel.add(b);
        }

        this.setLayout(new BorderLayout());
        topbar.add(previous);
        topbar.add(monthLabel);
        topbar.add(next);

        JPanel panel = new JPanel(new BorderLayout());
        this.add(topbar, BorderLayout.PAGE_START);
        panel.add(daypanel,BorderLayout.PAGE_START);
        panel.add(monthpanel, BorderLayout.CENTER);
        this.add(panel, BorderLayout.CENTER);
        this.setBackground(Color.darkGray);
        this.setPreferredSize(new Dimension(1000, 400));

    }

    public void addMonthViewListener(ActionListener a) {
        previous.addActionListener(a);
        next.addActionListener(a);
        for(JButton b: buttons){
            b.addActionListener(a);
        }
    }
    /**
     * Fires when an observable changes an observed property.
     * Sets text with setText() method.
     * @param evt - information about the property change
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {

        if(evt.getPropertyName().equals("currentTime")) {
            String time = evt.getNewValue().toString();
            monthtime = LocalDateTime.parse(time);
            setTexts();
        }
        if (evt.getPropertyName().equals("MonthChangePlus")) {
            monthtime = monthtime.plusMonths(1);
            setTexts();
        }
        if(evt.getPropertyName().equals("MonthChangeMin")){
            monthtime = monthtime.minusMonths(1);
            setTexts();
        }
    }

    /**
     * Sets the text on all labels and buttons to correct value
     */
    private void setTexts(){
        int dayofmonth = monthtime.getDayOfMonth();
        int firstdayofmonth = monthtime.minusDays(dayofmonth - 1).getDayOfWeek().getValue();
        int daysinprevmonth = getMaximum(monthtime.minusMonths(1).getMonthValue()-1);

        int i = 0;
        for (int p = 0; p < firstdayofmonth - 1; p++) {
            buttons[p].setText(String.valueOf(daysinprevmonth-(firstdayofmonth-2)+p));
            buttons[p].setActionCommand(String.valueOf((firstdayofmonth-2)*-1));
            buttons[p].setForeground(Color.lightGray);
            i++;
        }
        for (int k = 1; k <= getMaximum(monthtime.getMonthValue()-1); k++) {
            buttons[i].setText(String.valueOf(k));
            buttons[i].setActionCommand(buttons[i].getText());
            buttons[i].setForeground(Color.BLACK);
            i++;
        }
        for (int o = 1; i < 42; o++) {
            buttons[i].setText(String.valueOf(o));
            buttons[i].setActionCommand(String.valueOf(Integer.parseInt(buttons[i].getText())+getMaximum(monthtime.getMonthValue()-1)));
            buttons[i].setForeground(Color.lightGray);
            i++;
        }
        monthLabel.setText(monthtime.getMonth().getDisplayName(TextStyle.FULL,Locale.ENGLISH)+" "+monthtime.getYear());
    }

    /**
     * Gets number of days in a month
     * @param month - month number 1-12
     * @return - days in month
     */
    private int getMaximum(int month) {
        int maximum = 0;
        switch (month) {
            case 0:
                maximum = 31;
                break;
            case 1:
                if(isLeap())
                maximum = 29;
                else
                    maximum = 28;
                break;
            case 2:
                maximum = 31;
                break;
            case 3:
                maximum = 30;
                break;
            case 4:
                maximum = 31;
                break;
            case 5:
                maximum = 30;
                break;
            case 6:
                maximum = 31;
                break;
            case 7:
                maximum = 31;
                break;
            case 8:
                maximum = 30;
                break;
            case 9:
                maximum = 31;
                break;
            case 10:
                maximum = 30;
                break;
            case 11:
                maximum = 31;
                break;
        }

        return maximum;
    }

    /**
     * Calculates if current year is a leap year
     * @return - true/false if current year is a leap year
     */
    private boolean isLeap(){
        return ((monthtime.getYear() % 4 == 0) && (monthtime.getYear() % 100 != 0)) || (monthtime.getYear() % 400 == 0);
    }
}
