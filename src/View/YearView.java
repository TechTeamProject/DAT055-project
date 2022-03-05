
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
 * @author  Hanna Pham, Elias Carlsson
 * @version 1.0
 * @since   2022-03-05
 */
public class YearView extends JPanel implements PropertyChangeListener {
    private JLabel header = new JLabel();
    private JButton previous = new JButton("<");
    private JButton next = new JButton(">");
    private LocalDateTime monthtime = LocalDateTime.now();

    /**
     * Constructor method for the YearView class.
     */
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
    }

    /**
     * Fires when an observable changes an observed property.
     * Sets year with setText() method.
     * @param evt - information about the property change
     */

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if(evt.getPropertyName().equals("YearChange")){
            header.setText(String.valueOf(evt.getNewValue()));
        }
    }
}