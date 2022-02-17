package src;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

public class ToDoView {
    private JPanel header;
    private JPanel titlePanel;
    ToDo t = new ToDo();
    JFrame test = new JFrame();
    JPanel boxes;
    private class Listener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String str = e.getActionCommand();
            System.out.println(str);
        }
    }

    public class abc extends Object{
        String text;
        public String getText(){
            return text;
        }
    }

    private class M_ButtonListener extends MouseAdapter {
        public void mouseClicked(MouseEvent e) {
            System.out.println(e.paramString());
            System.out.println(e.getSource().toString());
            String s = e.getSource().toString();
            System.out.println(s.indexOf("text="));
            System.out.println(s.lastIndexOf("]"));
            String d = e.getSource().toString().substring(s.indexOf("text=")+5, s.lastIndexOf("]"));
            if(e.getButton() == MouseEvent.BUTTON3){
                System.out.println("bruh");

                boxes.remove(t.getIndexToDo(d));
                t.removeToDo(d);

                boxes.revalidate();
                boxes.repaint();

            }
        }
        }

    public ToDoView() {

        test.setTitle("ToDoList");
        test.setSize(400, 400);
        test.setLocation(200, 200);

        test.setLayout(new BorderLayout());
        header = new JPanel(new GridLayout(0,1));
        header.setBorder(new EmptyBorder(10,75,10,75));
        test.add(header, BorderLayout.NORTH);

        titlePanel = new JPanel();
        titlePanel.setBorder(new EtchedBorder());
        titlePanel.add(new JLabel("ToDoList"));
        header.add(titlePanel);




        /*
        JCheckBox checkBox1 = new JCheckBox("test");
        JCheckBox checkBox2 = new JCheckBox("Fixa todo");
        JCheckBox checkBox3 = new JCheckBox("Tycker ni vi är");
        JCheckBox checkBox4= new JCheckBox("klara för idag?");
        */

        boxes = new JPanel(new GridLayout(t.getSizeList(),1,20,20));
        boxes.setBorder(new EmptyBorder(0, 100, 20, 50));
        M_ButtonListener m2 = new M_ButtonListener();
        Listener l1 = new Listener();
        for(int i = 0; i < t.getSizeList(); i++){
            JCheckBox checkBox1 = new JCheckBox(t.getTitleToDo(i));
            checkBox1.addMouseListener(m2);
            checkBox1.addActionListener(l1);
            boxes.add(checkBox1);
        }
        //checkBox1.setBounds(0,0, 100,100);

        //M_ButtonListener m2 = new M_ButtonListener();

        //checkBox1.addMouseListener(m2);


/*
        //boxes.add(checkBox1);
        //boxes.add(checkBox2);
        boxes.add(checkBox1);
        //boxes.add(tester1);
        boxes.add(checkBox2);
        //boxes.add(tester2);
        boxes.add(checkBox3);
        //boxes.add(tester3);
        boxes.add(checkBox4);
        //boxes.add(tester4);
*/




        test.add(boxes, BorderLayout.CENTER);

        test.setVisible(true);
    }
}
