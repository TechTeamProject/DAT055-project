package src.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

public class ChatView extends JPanel{
    private JPanel topPanel;
    private JPanel ipPanel;
    private JPanel middlePanel;
    private JScrollPane scrollPane;
    private JLabel label;
    private static JTextField ipField;
    private static JTextField field;
    private static JTextArea area;
    private static CardLayout cardLayout;
    private JButton hostButton;
    private JButton connectButton;
    private JButton chatButton;

    public void printText(String text) {
        if(text.trim().isEmpty()) return;
        area.append(text+"\n");
    }
    public String getFieldText(){ return field.getText(); }
    public void setFieldText(String text){ field.setText(text); }

    public String getIpText(){ return ipField.getText(); }

    public ChatView(){
        cardLayout = new CardLayout();
        ipPanel = new JPanel();
        middlePanel = new JPanel();
        topPanel = new JPanel();

        ipField = new JTextField("", 16);
        field = new JTextField();
        area = new JTextArea();
        label = new JLabel("Enter the Ip Adress:");
        scrollPane = new JScrollPane(area);

        hostButton = new JButton("Host server");
        connectButton = new JButton("Connect to server");
        chatButton = new JButton("Chat");

        topPanel.setLayout(new GridLayout());
        topPanel.add(hostButton);
        topPanel.add(connectButton);
        topPanel.add(chatButton);

        area.setEditable(false);
        ipPanel.add(label);
        ipPanel.add(ipField);

        middlePanel.setLayout(cardLayout);
        middlePanel.add(scrollPane, "ChatArea");
        middlePanel.add(ipPanel, "IpArea");

        setLayout(new BorderLayout());
        add(topPanel, BorderLayout.NORTH);
        add(field, BorderLayout.SOUTH);
        add(middlePanel, BorderLayout.CENTER);
    }
    public void switchMiddlePanel(String panel){
        cardLayout.show(middlePanel, panel);
    }
    public void addIpFieldListener(KeyListener k){
        ipField.addKeyListener(k);
    }
    public void addTopButtonsListener(ActionListener a){
        hostButton.addActionListener(a);
        connectButton.addActionListener(a);
        chatButton.addActionListener(a);
    }
    public void addChatFieldListener(KeyListener k){
        field.addKeyListener(k);
    }
}

