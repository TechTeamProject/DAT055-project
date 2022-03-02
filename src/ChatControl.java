package src;
import src.Server.ChatServer;
import src.View.*;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

public class ChatControl {
    private CalenderModel model;
    private static ChatView chatView;
    private ServerThread serverThread;
    private ChatServer server;
    private Sound sound;
    private static YearView yearView;
    private static OptionView optionView;
    private static WeekView weekView;
    private static MonthView monthView;
    private static BookingView bookingView;
    public ChatControl(CalenderModel m, ChatView c, YearView y, OptionView o, WeekView w, MonthView mv, BookingView b){
        model = m;
        sound = new Sound();
        chatView = c;
        yearView = y;
        optionView = o;
        weekView = w;
        monthView = mv;
        bookingView = b;

        chatView.addChatFieldListener(new chatListener());
        chatView.addTopButtonsListener(new topButtonsListener());
        yearView.addYearViewListener(new yearViewListener());
        optionView.addOptionViewListener(new optionViewListener());
        weekView.addWeekViewListener(new weekViewListener());
        monthView.addMonthViewListener(new monthViewListener());
        bookingView.addBookingViewListener(new bookingViewListener());

        //Listeners added to Observable here
        m.addPropertyChangeListener(optionView);
        m.addPropertyChangeListener(weekView);
        m.addPropertyChangeListener(monthView);
        m.addPropertyChangeListener(yearView);
        m.addPropertyChangeListener(bookingView);
    }
    public ChatControl(boolean fake){
        //Fake initializer for static values
    }

    public void printText(String text){
        chatView.printText(text);
    }

    private class chatListener implements KeyListener {
        public void keyPressed(KeyEvent ke){
            if(ke.getKeyCode()==KeyEvent.VK_ENTER){

                if (chatView.getFieldText().compareTo("") != 0) {
                    if(serverThread.Alive()){
                        serverThread.addNextMessage(chatView.getFieldText());
                        chatView.setFieldText("");
                    }
                    else{
                        chatView.printText("You are not connected to a server!");
                        sound.playError();
                    }

                }
            }
        }
        public void keyTyped(KeyEvent e) {}
        public void keyReleased(KeyEvent e) {}
    }
    private class ipListener implements KeyListener{
        public void keyPressed(KeyEvent ke) {
            if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
                if (chatView.getIpText().compareTo("") != 0) {
                    if(!serverThread.Alive()){
                        serverThread = new ServerThread("AddUserClass", chatView.getIpText(), 23476);
                        chatView.switchMiddlePanel("ChatArea");
                        sound.playConnected();
                    }
                    else{
                        chatView.switchMiddlePanel("ChatArea");
                        chatView.printText("Already connected");
                        sound.playError();
                    }

                }
            }
        }
        @Override public void keyReleased (KeyEvent ke){}
        @Override public void keyTyped (KeyEvent ke){}
    }

    private class topButtonsListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            String str = e.getActionCommand();
            switch (str) {
                case "Host server":
                    chatView.switchMiddlePanel("ChatArea");
                    server = new ChatServer(23476);
                    Thread serverT = new Thread(server);
                    serverT.start();
                    sound.playStartHost();
                    break;
                case "Connect to server":
                    chatView.switchMiddlePanel("IpArea");
                    break;
                case "Go back":
                    chatView.switchMiddlePanel("ChatArea");
                    break;
                case "Confirm":
                    if (chatView.getIpText().compareTo("") != 0 && chatView.getNameText().compareTo("") != 0) {
                        if(!serverThread.Alive()){
                            serverThread = new ServerThread(chatView.getNameText(), chatView.getIpText(), 23476);
                            chatView.switchMiddlePanel("ChatArea");
                            sound.playConnected();
                        }
                        else{
                            chatView.switchMiddlePanel("ChatArea");
                            chatView.printText("Already connected");
                            sound.playError();
                        }

                    }
                    break;
            }

        }
    }
    private class yearViewListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            String str = e.getActionCommand();
            switch (str) {
                case "prev":
                    model.setYear(-1);
                    break;
                case "next":
                    model.setYear(1);
                    break;
            }
        }
    }
    private class optionViewListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String str = e.getActionCommand();
            switch (str) {
                case "Appearance":

                    break;
                case "Save":
                    model.save();
                    break;
                case "Load":
                    model.load();
                    break;
            }
        }
    }
    private class weekViewListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            String str = e.getActionCommand();
            switch (str) {
                case "f":

                    break;
                case "t":

                    break;
            }
        }
    }
    private class monthViewListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            String str = e.getActionCommand();
            switch (str) {
                case "o":

                    break;
                case "p":

                    break;
            }
        }
    }
    private class bookingViewListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            String str = e.getActionCommand();
            switch (str) {
                case "i":

                    break;
                case "l":

                    break;
            }
        }
    }
}
