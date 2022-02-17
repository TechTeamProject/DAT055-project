
package src.View;
import java.awt.*;
import javax.swing.*;

public class YearView extends JPanel{

    public  YearView(){

        JPanel p = new JPanel(new GridLayout(4,4,20,20));
        JButton button1 = new JButton("January");
        JButton button2 = new JButton("February");
        JButton button3 = new JButton("Mars");
        JButton button4 = new JButton("April");
        JButton button5 = new JButton("May");
        JButton button6 = new JButton("June");
        JButton button7 = new JButton("July");
        JButton button8 = new JButton("August");
        JButton button9 = new JButton("September");
        JButton button10 = new JButton("October");
        JButton button11 = new JButton("November");
        JButton button12 = new JButton("December");

        /*JButton setting = new JButton("Setting");
        JButton booking = new JButton("Booking");
        JButton todo = new JButton("Todo");
        JButton week = new JButton("Week/Month");

        p.add(setting);
        p.add(booking);
        p.add(todo);
        p.add(week);*/
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
        this.add(p);



    }

}


