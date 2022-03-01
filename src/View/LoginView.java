package src.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.*;

public class LoginView extends JPanel implements ActionListener{
     JTextField username_text;
     JPasswordField password_text;
     JLabel username, password, message;
     JButton login, register;
     JPanel p;
    public LoginView(){
        p = new JPanel();
        p.setLayout(new GridLayout(4,1));
        login = new JButton("Login");
        register = new JButton("Register");

        username = new JLabel(" Username: ");
        username_text = new JTextField("");
        password = new JLabel(" Password: ");
        password_text = new JPasswordField("");

        message = new JLabel();
        username.setPreferredSize( new Dimension( 100, 35 ) );

        p.add(username);
        p.add(username_text);
        p.add(password);
        p.add(password_text);
        p.add(login);
        p.add(register);
        p.add(message);
        this.add(p);
        login.addActionListener(this);
        register.addActionListener(this);
    }

        @Override
        public void actionPerformed(ActionEvent e){
            String userName = username_text.getText();
            String password = password_text.getText();
            if (userName != null) {
                message.setText(" Hello " + userName + "");
            }
        }



}
