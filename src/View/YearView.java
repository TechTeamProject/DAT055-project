
package src.View;
import java.awt.*;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.*;
import java.time.LocalDateTime;
import java.time.Year;

/**
 * This class is used as the view for all months of the year.
 *
 * @author  Hanna Pham
 * @version 1.0
 * @since   2022-03-05
 */
public class YearView extends JPanel implements PropertyChangeListener {
    private JLabel header = new JLabel();
    private JButton previous = new JButton("<");
    private JButton next = new JButton(">");
    private LocalDateTime yeartime;
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

    /**
     * Constructor method for the YearView class.
     */
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

    /**
     * A method that adds a ActionListener to the previous, next buttons
     * @param a The ActionListener that is added.
     */

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

    /**
     * Fires when an observable changes an observed property.
     * Sets year with setText() method.
     * @param evt - information about the property change
     */

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("currentTime") ) {
            String time = evt.getNewValue().toString();
            yeartime = LocalDateTime.parse(time);
            System.out.println("Current time " + yeartime);
            header.setText(String.valueOf(yeartime.getYear()));
        }
        if(evt.getPropertyName().equals("YearChange")){
            header.setText(String.valueOf(yeartime.getYear()));
        }
    }
}