package src;

import javax.swing.*;

public class Application extends JFrame {
    private JFrame frame;

    public static void main(String [] args){
        new Application();
    }

    public Application() {
        frame = new JFrame("Calender");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 650);
        frame.setLocation(400, 250);
        frame.setVisible(true);
    }

}
