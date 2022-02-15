package src;
import javax.swing.*;
import java.awt.*;




public class WindowFrame extends JFrame {
    StartView startView;
    WeekView weekView;



    public static void main(String [] args){
        WindowFrame windowFrame = new WindowFrame();


    }


    public WindowFrame(){

        //weekView = new WeekView();
        startView = new StartView();

        this.setTitle("Calender");
        this.getContentPane().add(startView);

        this.setSize(1000, 650);
        this.setLocation(400, 250);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }


}
