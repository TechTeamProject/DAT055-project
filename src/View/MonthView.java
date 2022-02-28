package src.View;
import java.awt.event.*;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class MonthView extends JPanel implements PropertyChangeListener {

    Calendar cal1 = new GregorianCalendar();
    Calendar cal2 = new GregorianCalendar(
            cal1.get(Calendar.YEAR), cal1.get(Calendar.MONTH), 1);

    private CalendarPanel dc = new CalendarPanel(cal2);
    private JButton previous = new JButton("<");
    private JButton next = new JButton(">");


    MonthView() {
        this.add(previous);
        this.add(next);
        this.add(dc);
        this.setBackground(Color.darkGray);
        this.setPreferredSize(new Dimension( 1000, 400 ));

        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(dc);
                dc = dc.moveToNextMonth();
                add(dc);
                repaint();
                revalidate();
            }
        });

        previous.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(dc);
                dc = dc.moveToPreviousMonth();
                add(dc);
                repaint();
                revalidate();
            }
        });
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println("TEST: Observer interface works. In the event this is what it says:" + evt);
    }
}

class CalendarPanel extends JPanel {

    public Calendar calendar;
    private int currentYear;
    private int currentMonth;
    private int numOfDaysInMonth;
    private int starDay;
    private int previousMonth;

    public CalendarPanel(Calendar calendar) {

        this.calendar = calendar;
        setLayout(new BorderLayout());
        currentYear = calendar.get(Calendar.YEAR);
        currentMonth = calendar.get(Calendar.MONTH);
        numOfDaysInMonth = getMaximum(currentMonth);
        starDay = calendar.get(Calendar.DAY_OF_WEEK);
        previousMonth = getPreviousMonth(currentMonth);

        JLabel calendarHeader = new JLabel((currentMonth + 1) + "/" + currentYear);
        calendarHeader.setHorizontalAlignment(JLabel.CENTER);


        JPanel panel = new JPanel(new GridLayout(0, 7, 0, 0));
        JLabel l1 = new JLabel("Sunday");
        l1.setHorizontalAlignment(JLabel.CENTER);
        JLabel l2 = new JLabel("Monday");
        l2.setHorizontalAlignment(JLabel.CENTER);
        JLabel l3 = new JLabel("Tuesday");
        l3.setHorizontalAlignment(JLabel.CENTER);
        JLabel l4 = new JLabel("Wednesday");
        l4.setHorizontalAlignment(JLabel.CENTER);
        JLabel l5 = new JLabel("Thursday");
        l5.setHorizontalAlignment(JLabel.CENTER);
        JLabel l6 = new JLabel("Friday");
        l6.setHorizontalAlignment(JLabel.CENTER);
        JLabel l7 = new JLabel("Saturday");
        l7.setHorizontalAlignment(JLabel.CENTER);

        panel.add(l1);
        panel.add(l2);
        panel.add(l3);
        panel.add(l4);
        panel.add(l5);
        panel.add(l6);
        panel.add(l7);

        int i;

        int numOfDaysInPreviousMonth = getMaximum(previousMonth);
        int previousMonthDayToDisplay = numOfDaysInPreviousMonth - starDay + 2;

        for (i = 1; i < starDay; i++) {
            JButton b = new JButton(previousMonthDayToDisplay + "");
            b.setHorizontalAlignment(JButton.RIGHT);
            b.setForeground(Color.lightGray);
            panel.add(b);
            previousMonthDayToDisplay++;
        }

        for (int j = 1 ; j <= numOfDaysInMonth; j++, i++) {
            JButton b = new JButton(j + "");
            b.setHorizontalAlignment(JButton.RIGHT);
            panel.add(b);
        }

        for (int j = 1 ; i <= 42; j++, i++) {
            JButton b = new JButton(j + "");
            b.setHorizontalAlignment(JLabel.RIGHT);
            b.setForeground(Color.LIGHT_GRAY);
            panel.add(b);
        }

        add(calendarHeader, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);
    }

    private int getPreviousMonth(int month) {
        if (month == 0) {
            return 11;
        }

        return month - 1;
    }

    private int getMaximum(int month) {
        int maximum = 0;
        switch (month) {
            case 0:
                maximum = 31;
                break;
            case 1:
                if (isLeap())
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

    private boolean isLeap() {
        return (currentYear % 4 == 0 && currentYear % 100 != 0) ||
                currentYear % 400 == 0;
    }

    public CalendarPanel moveToPreviousMonth() {
        return new CalendarPanel(new GregorianCalendar(
                currentYear,
                currentMonth - 1,
                1));
    }

    public CalendarPanel moveToNextMonth() {
        return new CalendarPanel(new GregorianCalendar(
                currentYear,
                currentMonth + 1,
                1));
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(1000, 360);
    }
}