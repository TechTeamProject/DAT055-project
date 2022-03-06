
package src.View;
import src.CalenderModel;
import src.Control;
import src.PopUp;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/**
 * @author Hanna Pham
 * @version 1.0
 * @since 2022-03-06
 * WindowFrame is a view class meant to create a frame and organize different panels/buttons.
 * It intended usage is in a calenderprogram.
 * Windowframe creates the main window and also different panels and buttons to enable navigation.
 */
public class WindowFrame extends JFrame {

    private static WeekView weekView;
    private static YearView yearView;
    private static OptionView optionView;
    private static EventView eventView;
    private static MonthView monthView;
    private static BookingView bookView;
    private JScrollPane bookingView;
    private static ChatView chatView;
    private static CardLayout c1;
    private static JPanel p = new JPanel();
    private PopUp popUp;

    JButton bOptions = new JButton("Options");
    JButton bBookings = new JButton("Bookings");
    JButton bWeek = new JButton("Week");
    JButton bYear = new JButton("Year");
    JButton bMonth = new JButton("Month");
    JButton bEvent = new JButton("Create Event");
    JButton bChat = new JButton("Chat");

    /**
     * Main - for creating the windowfram and start the program
     * @param args
     */
    public static void main(String [] args){
        WindowFrame windowFrame = new WindowFrame();
    }

    /**
     * Constructor
     * Creates all needed viewpanels and also an instance of a controlclass.
     * The constructor organizes were buttons/panels are and sizes/locations
     */
    public WindowFrame() {
        CalenderModel m = new CalenderModel();
        weekView = new WeekView();
        optionView = new OptionView();
        yearView = new YearView();
        eventView = new EventView();
        monthView = new MonthView();
        bookView = new BookingView();
        bookingView = new JScrollPane(bookView);
        bookingView.setPreferredSize(new Dimension(600, 400));
        chatView = new ChatView();
        popUp = new PopUp("week");

        Control control = new Control(this, m, chatView, yearView, optionView, weekView, monthView, bookView, eventView, popUp);

        p = new JPanel();
        c1 = new CardLayout();
        p.setLayout(c1);

        JPanel options = new JPanel();
        JPanel booking = new JPanel();
        JPanel week = new JPanel();
        JPanel year = new JPanel();
        JPanel month = new JPanel();
        JPanel event = new JPanel();
        JPanel chat = new JPanel();

        options.add(optionView);
        booking.add(bookingView);
        week.add(weekView);
        year.add(yearView);
        month.add(monthView);
        event.add(eventView);
        chat.add(chatView);

        p.add(options, "options");
        p.add(booking, "bookings");
        p.add(week, "week");
        p.add(year, "year");
        p.add(month, "month");
        p.add(event, "event");
        p.add(chat, "chat");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(bOptions);
        buttonPanel.add(bBookings);
        buttonPanel.add(bWeek);
        buttonPanel.add(bMonth);
        buttonPanel.add(bYear);
        buttonPanel.add(bEvent);
        buttonPanel.add(bChat);

        JMenuBar menubar = new JMenuBar();
        this.setJMenuBar(menubar);

        menubar.add(bOptions);
        menubar.add(bBookings);
        menubar.add(bWeek);
        menubar.add(bMonth);
        menubar.add(bYear);
        menubar.add(bEvent);
        menubar.add(bChat);

        getContentPane().add(p, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.NORTH);

        this.setTitle("Calender");
        this.setSize(1000, 500);
        this.setLocation(400, 250);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.add(p);
        c1.show(p, "week");
    }

    /**
     * Adds actionlisteners to buttons in WindowFrame
     * @param a - Actionlistener object
     */
    public void addWindowFrameListener(ActionListener a){
        bBookings.addActionListener(a);
        bChat.addActionListener(a);
        bEvent.addActionListener(a);
        bMonth.addActionListener(a);
        bOptions.addActionListener(a);
        bWeek.addActionListener(a);
        bYear.addActionListener(a);
        bBookings.setActionCommand("bookings");
        bChat.setActionCommand("chat");
        bEvent.setActionCommand("event");
        bMonth.setActionCommand("month");
        bOptions.setActionCommand("options");
        bWeek.setActionCommand("week");
        bYear.setActionCommand("year");
    }
    /**
     * Method to change panel in Windowframe
     * @param panel String with name of desired panel
     */
    public static void changePanel(String panel) {
        c1.show(p,panel);
    }
}
