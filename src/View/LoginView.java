package src.View;

import javax.swing.*;
import java.awt.*;

public class LoginView extends JPanel {
    public LoginView(){
        JPanel p = new JPanel();
        JPanel p1 = new JPanel();

        JButton login = new JButton("Login");
        JButton register = new JButton("Register");

        p.setLayout(new GridLayout(3,1));

        JLabel username = new JLabel(" Username: ");
        JTextField text1 = new JTextField("");
        JLabel password = new JLabel(" Password: ");
        JTextField text2 = new JTextField("");
        text1.setPreferredSize( new Dimension( 100, 35 ) );

        p.add(username);
        p.add(text1);
        p.add(password);
        p.add(text2);
        p.add(login);
        p.add(register);

        this.add(p);


    }
}
