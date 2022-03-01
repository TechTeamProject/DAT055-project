
package src.View;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.time.Year;


public class YearView extends JPanel implements PropertyChangeListener {

    private int currentYear;
    final int n = 10;
    final int i = 1;
    public  YearView(){

        JPanel p = new JPanel(new GridLayout(4,4,20,20));
        JButton button1 = new JButton("January");
        JButton button2 = new JButton("February");
        JButton button3 = new JButton("March");
        JButton button4 = new JButton("April");
        JButton button5 = new JButton("May");
        JButton button6 = new JButton("June");
        JButton button7 = new JButton("July");
        JButton button8 = new JButton("August");
        JButton button9 = new JButton("September");
        JButton button10 = new JButton("October");
        JButton button11 = new JButton("November");
        JButton button12 = new JButton("December");
        setPreferredSize( new Dimension( 600, 400 ) );

        JButton previous = new JButton("<");
        JButton next = new JButton(">");
        JPanel panel = new JPanel();
        JPanel p1 = new JPanel();
        currentYear = Year.now().getValue();
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
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println("TEST: Observer interface works. In the event this is what it says:" + evt);
    }
}