
package src.View;
import java.awt.*;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.*;
import java.time.LocalDateTime;
import java.time.Year;


public class YearView extends JPanel implements PropertyChangeListener {
    private JLabel header = new JLabel();
    private JButton previous = new JButton("<");
    private JButton next = new JButton(">");
    private LocalDateTime monthtime = LocalDateTime.now();
    private JButton button1 = new JButton("January");
    private JButton button2 = new JButton("February");
    private JButton button3 = new JButton("March");
    private JButton button4 = new JButton("April");
    private JButton button5 = new JButton("May");
    private JButton button6 = new JButton("June");
    private JButton button7 = new JButton("July");
    private JButton button8 = new JButton("August");
    private JButton button9 = new JButton("September");
    private JButton button10 = new JButton("October");
    private JButton button11 = new JButton("November");
    private JButton button12 = new JButton("December");

    public  YearView(){

        JPanel p = new JPanel(new GridLayout(4,4,20,20));
        setPreferredSize( new Dimension( 600, 400 ) );

        previous.setActionCommand("prev");
        next.setActionCommand("next");
        JPanel p1 = new JPanel();
        header.setText(String.valueOf(Year.now().getValue()));
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
    }

    public void addYearViewListener(ActionListener a){
        previous.addActionListener(a);
        next.addActionListener(a);
        button1.addActionListener(a);
        button2.addActionListener(a);
        button3.addActionListener(a);
        button4.addActionListener(a);
        button5.addActionListener(a);
        button6.addActionListener(a);
        button7.addActionListener(a);
        button8.addActionListener(a);
        button9.addActionListener(a);
        button10.addActionListener(a);
        button11.addActionListener(a);
        button12.addActionListener(a);
        button1.setActionCommand("1");
        button2.setActionCommand("2");
        button3.setActionCommand("3");
        button4.setActionCommand("4");
        button5.setActionCommand("5");
        button6.setActionCommand("6");
        button7.setActionCommand("7");
        button8.setActionCommand("8");
        button9.setActionCommand("9");
        button10.setActionCommand("10");
        button11.setActionCommand("11");
        button12.setActionCommand("12");


    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if(evt.getPropertyName().equals("YearChange")){
            header.setText(String.valueOf(evt.getNewValue()));
        }
    }
}