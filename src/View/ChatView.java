package src.View;

import src.ChatControl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * This class is used as the view for the chat.
 *
 * @author  Oliver Brottare
 * @version 1.0
 * @since   2022-03-02
 */
public class ChatView extends JPanel implements PropertyChangeListener {
    private final JPanel middlePanel;
    private final JButton goBackButton;
    private final JButton confirmButton;
    private static JTextField ipField;
    private static JTextField nameField;
    private static JTextField field;
    private static JTextArea area;
    private final CardLayout cardLayout;
    private final JButton hostButton;
    private final JButton connectButton;

    /**
     * Constructor method for the ChatView class.
     */
    public ChatView(){
        cardLayout = new CardLayout();
        JPanel ipPanel = new JPanel();
        middlePanel = new JPanel();
        JPanel topPanel = new JPanel();
        JPanel chatPanel = new JPanel();

        field = new JTextField();
        area = new JTextArea(20,32);

        JLabel ipLabel = new JLabel("Enter the Ip Adress:");
        ipField = new JTextField("", 32);
        JLabel nameLabel = new JLabel("Choose your user name:");
        nameField = new JTextField("", 32);
        confirmButton = new JButton("Confirm");
        goBackButton = new JButton("Go back");

        hostButton = new JButton("Host server");
        connectButton = new JButton("Connect to server");

        topPanel.setLayout(new GridLayout(1,2));
        topPanel.add(hostButton);
        topPanel.add(connectButton);

        ipPanel.setLayout(new GridLayout(3, 2));
        ipPanel.add(ipLabel);
        ipPanel.add(ipField);
        ipPanel.add(nameLabel);
        ipPanel.add(nameField);
        ipPanel.add(goBackButton);
        ipPanel.add(confirmButton);

        area.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(area);
        chatPanel.setLayout(new BorderLayout());
        chatPanel.add(scrollPane, BorderLayout.CENTER);
        chatPanel.add(field, BorderLayout.SOUTH);
        middlePanel.setLayout(cardLayout);
        middlePanel.add(chatPanel, "ChatArea");
        middlePanel.add(ipPanel, "IpArea");

        setLayout(new BorderLayout());
        add(topPanel, BorderLayout.NORTH);
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
     * @return String The written in the chat field.
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
     * Getter method to retrieve the name written into the name field.
     * @return String The text in the nameField
     */
    public String getNameText(){ return  nameField.getText(); }

    /**
     * A method that switches the current middle panel.
     * @param panel The panel that it switches to.
     */
    public void switchMiddlePanel(String panel){
        cardLayout.show(middlePanel, panel);
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
        goBackButton.addActionListener(a);
        confirmButton.addActionListener(a);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if(evt.getPropertyName().equals("NewMessage")){
            //System.out.println("Funkar");
            printText(ChatControl.getMessage());
        }
    }
}

