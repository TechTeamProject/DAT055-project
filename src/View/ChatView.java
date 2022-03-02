package src.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
/**
 * This class is used as the view for the chat.
 *
 * @author  Oliver Brottare
 * @version 1.0
 * @since   2022-03-02
 */
public class ChatView extends JPanel{
    private JPanel topPanel;
    private JPanel ipPanel;
    private JPanel middlePanel;
    private JScrollPane scrollPane;
    private JLabel label;
    private static JTextField ipField;
    private static JTextField field;
    private static JTextArea area;
    private CardLayout cardLayout;
    private JButton hostButton;
    private JButton connectButton;
    private JButton chatButton;

    /**
     * Constructor method for the ChatView class.
     */
    public ChatView(){
        cardLayout = new CardLayout();
        ipPanel = new JPanel();
        middlePanel = new JPanel();
        topPanel = new JPanel();

        ipField = new JTextField("", 32);
        field = new JTextField();
        area = new JTextArea(20,32);
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

    /**
     * This method is used to add text to the chat area.
     * @param text The text that is going to be added to the chat area.
     */
    public void printText(String text) {
        if(text.trim().isEmpty()) return;
        area.append(text+"\n");
    }

    /**
     * Getter method used the retrieve the text written in the chat field.
     * @return String The the written in the chat field.
     */
    public String getFieldText(){ return field.getText(); }

    /**
     * Setter method used to set the text in the chat field.
     * @param text The text that will write over the text in the chat field.
     */
    public void setFieldText(String text){ field.setText(text); }

    /**
     * Getter method used to get the text in the ipField.
     * @return String the text in the ipField.
     */
    public String getIpText(){ return ipField.getText(); }

    /**
     * A method that switches the current middle panel.
     * @param panel The panel that it switches to.
     */
    public void switchMiddlePanel(String panel){
        cardLayout.show(middlePanel, panel);
    }

    /**
     * A method that adds a KeyListener to the ipField.
     * @param k The KeyListener that is added.
     */
    public void addIpFieldListener(KeyListener k){
        ipField.addKeyListener(k);
    }

    /**
     * A method that adds a KeyListener to the chat field-
     * @param k The KeyListener that is added.
     */
    public void addChatFieldListener(KeyListener k){
        field.addKeyListener(k);
    }

    /**
     * A method that adds a ActionListener to the three buttons at the top.
     * @param a The ActionListener that is added.
     */
    public void addTopButtonsListener(ActionListener a){
        hostButton.addActionListener(a);
        connectButton.addActionListener(a);
        chatButton.addActionListener(a);
    }
}

