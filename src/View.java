package src;
import javax.swing.*;
import java.awt.*;


public class View extends JPanel {
    public View(String s){
        JPanel p = new JPanel();
        if(s=="start"){
            p.setLayout(new BorderLayout());
            JPanel p1 = new JPanel(new GridLayout(1,6,5,5));
            JPanel p2 = new JPanel(new GridLayout(1,3,5,5));
            JButton newschema = new JButton("New");
            JButton schema1 = new JButton("schema1");
            JButton schema2 = new JButton("schema2");
            JButton schema3 = new JButton("schema3");
            JButton schema4 = new JButton("schema4");
            JButton seeall = new JButton("See All");
            p.add(p1, BorderLayout.PAGE_START);
            p.add(p2,BorderLayout.PAGE_END);
        }
        else if(s=="week"){
        }
        else if(s=="month"){
        }
        else if(s=="year"){
        }
    }
}
