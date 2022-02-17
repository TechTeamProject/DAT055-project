
package src.View;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.function.ToDoubleBiFunction;


public class WindowFrame extends JFrame {

    StartView startView;
    WeekView weekView;
    YearView yearView;
    OptionView optionView;
    EventView eventView;
    ToDoView todoView;
    //MonthView monthView;
    LoginView loginView;
    private CardLayout c1;
    private int currentCard = 1;

    public static void main(String [] args){
        WindowFrame windowFrame = new WindowFrame();
    }

    public WindowFrame(){

        weekView = new WeekView();
        startView = new StartView();
        optionView = new OptionView();
        yearView = new YearView();
        eventView = new EventView();
        todoView = new ToDoView();
        //monthView = new MonthView();
        loginView = new LoginView();

        JPanel p = new JPanel();

        JButton b1 = new JButton("Start");
        JButton b2 = new JButton("Setting");
        JButton b3 = new JButton("Booking");
        //JButton b4 = new JButton("Todo");
        JButton b5 = new JButton("Week");
        JButton b7 = new JButton("Year");
        JButton b6 = new JButton("Month");
        JButton b8 = new JButton("Login");


        c1 = new CardLayout();

        p.setLayout(c1);

        JPanel start = new JPanel();
        JPanel setting = new JPanel();
        JPanel booking = new JPanel();
        JPanel todo = new JPanel();
        JPanel week = new JPanel();
        JPanel year = new JPanel();
        JPanel month = new JPanel();
        JPanel login = new JPanel();

        start.add(startView);
        setting.add(optionView);
        //booking.add(BookingView);
        //todo.add(todoView);
        week.add(weekView);
        year.add(yearView);
        //month.add(monthView);
        login.add(loginView);

        p.add(start, "panel1");
        p.add(setting, "panel2");
        p.add(booking, "panel3");
        p.add(todo, "panel4");
        p.add(week, "panel5");
        p.add(year, "panel7");
        p.add(month,"panel6");
        p.add(login,"panel8");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(b1);
        buttonPanel.add(b2);
        buttonPanel.add(b3);
        //buttonPanel.add(b4);
        buttonPanel.add(b5);
        buttonPanel.add(b6);
        buttonPanel.add(b7);
        buttonPanel.add(b8);

        JMenuBar menubar = new JMenuBar();
        this.setJMenuBar(menubar);

        menubar.add(b1);
        menubar.add(b2);
        menubar.add(b3);
        menubar.add(b5);
        menubar.add(b6);
        menubar.add(b7);
        menubar.add(b8);
        b1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {

                c1.show(p, "panel1");
                currentCard = 1;
            }
        });
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c1.show(p, "panel2");
            }
        });
        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                c1.show(p, "panel3");


            }
        });


        /*b4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c1.show(p, "panel4");
            }
        });*/


        b5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c1.show(p,"panel5");
            }
        });
        b6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c1.show(p,"panel6");
            }
        });
        b7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c1.show(p,"panel7");
            }
        });
        b8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c1.show(p,"panel8");
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
    }



}