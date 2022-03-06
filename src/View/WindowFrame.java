
package src.View;
import src.CalenderModel;
import src.ChatControl;
import src.PopUp;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.LocalDateTime;


public class WindowFrame extends JFrame {

    private static WeekView weekView;
    private static YearView yearView;
    private static OptionView optionView;
    private static EventView eventView;
    private static MonthView monthView;
    //LoginView loginView;
    private static BookingView bookView;
    JScrollPane bookingView;
    private static ChatView chatView;
    private static ChatControl chatControl;
    private static CardLayout c1;
    private int currentCard = 1;
    private static JPanel p = new JPanel();
    private PopUp popUp;

    public static void main(String [] args){
        WindowFrame windowFrame = new WindowFrame();
    }

    public WindowFrame() {
        CalenderModel m = new CalenderModel();
        weekView = new WeekView();
        optionView = new OptionView();
        yearView = new YearView();
        eventView = new EventView();
        monthView = new MonthView();
        //loginView = new LoginView();
        bookView = new BookingView();
        bookingView = new JScrollPane(bookView);
        bookingView.setPreferredSize(new Dimension(600, 400));
        chatView = new ChatView();
        popUp = new PopUp("week");

        chatControl = new ChatControl(m, chatView, yearView, optionView, weekView, monthView, bookView, eventView, popUp);

        p = new JPanel();

        JButton bOptions = new JButton("Options");
        JButton bBookings = new JButton("Bookings");
        JButton bWeek = new JButton("Week");
        JButton bYear = new JButton("Year");
        JButton bMonth = new JButton("Month");
        //JButton bLogin = new JButton("Login");
        JButton bEvent = new JButton("Create Event");
        JButton bChat = new JButton("Chat");

        c1 = new CardLayout();

        p.setLayout(c1);

        JPanel options = new JPanel();
        JPanel booking = new JPanel();
        JPanel week = new JPanel();
        JPanel year = new JPanel();
        JPanel month = new JPanel();
        JPanel login = new JPanel();
        JPanel event = new JPanel();
        JPanel chat = new JPanel();

        options.add(optionView);
        booking.add(bookingView);
        week.add(weekView);
        year.add(yearView);
        month.add(monthView);
        //login.add(loginView);
        event.add(eventView);
        chat.add(chatView);

        p.add(options, "options");
        p.add(booking, "bookings");
        p.add(week, "week");
        p.add(year, "year");
        p.add(month, "month");
        p.add(login, "login");
        p.add(event, "event");
        p.add(chat, "chat");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(bOptions);
        buttonPanel.add(bBookings);
        buttonPanel.add(bWeek);
        buttonPanel.add(bMonth);
        buttonPanel.add(bYear);
        buttonPanel.add(bEvent);
        //buttonPanel.add(bLogin);
        buttonPanel.add(bChat);

        JMenuBar menubar = new JMenuBar();
        this.setJMenuBar(menubar);

        menubar.add(bOptions);
        menubar.add(bBookings);
        menubar.add(bWeek);
        menubar.add(bMonth);
        menubar.add(bYear);
        menubar.add(bEvent);
        //menubar.add(bLogin);
        menubar.add(bChat);




        bOptions.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c1.show(p, "options");
            }
        });
        bBookings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c1.show(p, "bookings");
            }
        });
        bWeek.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c1.show(p, "week");
            }
        });
        bMonth.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c1.show(p, "month");
            }
        });
        bYear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c1.show(p, "year");
            }
        });
        bEvent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c1.show(p, "event");
            }
        });
        /*bLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c1.show(p, "login"); }
        });*/
        bChat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c1.show(p, "chat");
            }
        });

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

    public static void changePanel(String panel) {
        c1.show(p,panel);
    }
}
