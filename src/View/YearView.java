
package src.View;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.time.Year;


public class YearView extends JPanel implements ActionListener{

    private int currentYear;
    private int previousYear;
    final int n = 10;
    final int i = 1;
    public  YearView(){

        JPanel p = new JPanel(new GridLayout(4,4,20,20));
        JButton button1 = new JButton("January");
        button1.addActionListener(this);
        button1.setActionCommand("1");
        JButton button2 = new JButton("February");
        button2.addActionListener(this);
        button2.setActionCommand("2");
        JButton button3 = new JButton("March");
        button3.addActionListener(this);
        button3.setActionCommand("3");
        JButton button4 = new JButton("April");
        button4.addActionListener(this);
        button4.setActionCommand("4");
        JButton button5 = new JButton("May");
        button5.addActionListener(this);
        button5.setActionCommand("5");
        JButton button6 = new JButton("June");
        button6.addActionListener(this);
        button6.setActionCommand("6");
        JButton button7 = new JButton("July");
        button7.addActionListener(this);
        button7.setActionCommand("7");
        JButton button8 = new JButton("August");
        button8.addActionListener(this);
        button8.setActionCommand("8");
        JButton button9 = new JButton("September");
        button9.addActionListener(this);
        button9.setActionCommand("9");
        JButton button10 = new JButton("October");
        button10.addActionListener(this);
        button10.setActionCommand("10");
        JButton button11 = new JButton("November");
        button11.addActionListener(this);
        button11.setActionCommand("11");
        JButton button12 = new JButton("December");
        button12.addActionListener(this);
        button12.setActionCommand("12");
        setPreferredSize( new Dimension( 600, 400 ) );

        JButton previous = new JButton("<");
        JButton next = new JButton(">");
        JPanel panel = new JPanel();
        JPanel p1 = new JPanel();
        currentYear = Year.now().getValue();
        previousYear = currentYear -1;
        JLabel header = new JLabel((currentYear) +"");
        p1.add(previous, BorderLayout.NORTH);
        p1.add(header);
        p1.add(next);

        p.add(button1);
        p.add(button2);
        p.add(button3);
        p.add(button4);
        p.add(button5);
        p.add(button6);
        p.add(button7);
        p.add(button8);
        p.add(button9);
        p.add(button10);
        p.add(button11);
        p.add(button12);
        this.setLayout(new BorderLayout());
        this.add(p1, BorderLayout.NORTH);
        this.add(p, BorderLayout.CENTER);

            next.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if( i< n){
                        currentYear = currentYear+1;
                        header.setText(String.valueOf(currentYear));
                    }
                }
            });
        previous.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if( i< n){
                    currentYear = currentYear-1;
                    header.setText(String.valueOf(currentYear));
                }
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        int month = Integer.parseInt(cmd);
        //visa monthView med datum month/currentYear
    }
}





