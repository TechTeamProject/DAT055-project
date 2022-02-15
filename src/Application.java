package src;

import javax.swing.*;

public class Application extends JFrame {

    public static void main(String [] args){
        new Application();

    }

    public Application() {

        this.setTitle("Calender");
        this.setSize(1000, 650);
        this.setLocation(400, 250);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

}
