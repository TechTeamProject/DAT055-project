package src;
import src.Server.ChatServer;
import src.View.*;

import javax.swing.*;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.LinkedList;

import static java.awt.event.MouseEvent.BUTTON3;
import static javax.swing.SwingUtilities.isRightMouseButton;

public class  ChatControl implements PropertyChangeListener, Serializable {
    private static CalenderModel model;
    private static ChatView chatView;
    private ClientThread serverThread;
    private ChatServer server;
    private Sound sound;
    private static YearView yearView;
    private static OptionView optionView;
    private static WeekView weekView;
    private static MonthView monthView;
    private static BookingView bookingView;
    private static EventView eventView;
    private PopUp popup;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private static LinkedList<Event> Eventlist = new LinkedList<Event>();

    public ChatControl(CalenderModel m, ChatView c, YearView y, OptionView o, WeekView w, MonthView mv, BookingView b, EventView e, PopUp p){
        model = m;
        sound = new Sound();
        chatView = c;
        yearView = y;
        optionView = o;
        weekView = w;
        monthView = mv;
        bookingView = b;
        eventView = e;
        popup = p;

        chatView.addChatFieldListener(new chatListener());
        chatView.addTopButtonsListener(new topButtonsListener());
        yearView.addYearViewListener(new yearViewListener());
        optionView.addOptionViewListener(new optionViewListener());
        weekView.addWeekViewListener(new weekViewListener());
        weekView.addWeekViewActionListener(new weekViewListener());
        monthView.addMonthViewListener(new monthViewListener());
        bookingView.addBookingViewListener(new bookingViewListener());
        eventView.addEventViewListener(new eventViewListener());
        popup.addPopupListener(new popupListener());


        //Listeners added to Observable here
        model.addPropertyChangeListener(optionView);
        model.addPropertyChangeListener(weekView);
        model.addPropertyChangeListener(monthView);
        model.addPropertyChangeListener(yearView);
        model.addPropertyChangeListener(bookingView);
        model.addPropertyChangeListener(this);

        //Updates in Model to set all the views.
        m.getViewTime();
    }
    public ChatControl(boolean fake){
        //Fake initializer for static values
    }

    public void printText(String text){
        chatView.printText(text);
    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if(evt.getPropertyName().equals("NewEvent") | evt.getPropertyName().equals("LoadedEvents") | evt.getPropertyName().equals("RemoveEvent")){
            bookingView.addBookingViewListener(new bookingViewListener());
        }
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
                        serverThread = new ClientThread("AddUserClass", chatView.getIpText(), 23476);
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
                            serverThread = new ClientThread(chatView.getNameText(), chatView.getIpText(), 23476);
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
    private class weekViewListener extends JPopupMenu implements MouseListener, ActionListener{

        public void actionPerformed(ActionEvent e) {
            String str = e.getActionCommand();
            switch (str) {
                case "<":
                    model.setDay(-7);
                    break;
                case ">":
                    model.setDay(7);
                    break;
            }
        }
        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {
            //If mouse released and BUTTON3 = RightClick has been used
            if (isRightMouseButton(e)) {
                //popup
                popup.show(e.getComponent(), e.getX(), e.getY());

                //A block to save x coordinates of rightclick in weekview to add to eventview the current time
                LocalDateTime eventtime = model.getViewTime();
                if (e.getX() > 145 && e.getX() < 285)  {
                    eventtime = eventtime.plusDays(1);
                }
                else if (e.getX() > 285 && e.getX() < 428){
                    eventtime = eventtime.plusDays(2);
                }
                else if (e.getX() > 428 && e.getX() < 570) {
                    eventtime = eventtime.plusDays(3);
                }
                else if (e.getX() > 570 && e.getX() < 712) {
                    eventtime = eventtime.plusDays(4);
                }
                else if (e.getX() > 712 && e.getX() < 854) {
                    eventtime = eventtime.plusDays(5);
                }
                else if (e.getX() > 854 && e.getX() < 1000) {
                    eventtime = eventtime.plusDays(6);
                }
                String formatedtime = eventtime.format(formatter);
                EventView.setEventTime(formatedtime);
            }
            else if (isRightMouseButton(e)) {

            }
        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }
    private class monthViewListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            String str = e.getActionCommand();
            switch (str) {
                case "<":
                    model.setMonth(-1);
                    break;
                case ">":
                    model.setMonth(+1);
                    break;
                case "day":
                    //todo: switch to weekview
                    break;
            }
        }
    }
    private class eventViewListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            String str = e.getActionCommand();
            if(str.equals("Save")){
                String title = eventView.getTitle();
                String fromtime = eventView.getStartTime();
                String untiltime = eventView.getEndTime();
                String loc = eventView.getLoc();

                if (title.isBlank()) {
                    JOptionPane.showMessageDialog(null, "No title", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    try {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                        LocalDateTime fromdatetime = LocalDateTime.parse(fromtime, formatter);
                        LocalDateTime untildatetime = LocalDateTime.parse(untiltime, formatter);
                        model.addEvent(fromdatetime, untildatetime, title, loc);
                        eventView.setTitle("");
                        eventView.setStartTime("yyyy-MM-dd HH:mm");
                        eventView.setEndTime("yyyy-MM-dd HH:mm");
                        eventView.setLoc("");
                    } catch (InputMismatchException ex) {
                        JOptionPane.showMessageDialog(null, "Wrong date/time format", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }
    }
    private class bookingViewListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            String str = e.getActionCommand();
            int index = Integer.parseInt(str);
            model.removeEvent(index);
        }
    }

    private class popupListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String str = e.getActionCommand();
            switch (str) {
                case "Create Event":
                    WindowFrame.changePanel();
                    System.out.println("Panel should be switched");
                    break;
                case "Remove Event":
                   // model.removeEvent(model.getEvents().getLast().hashCode());
                    model.removeEvent(0);
                    System.out.println("Event should be removed");
                    break;
            }
        }
    }

    /**
     * Simple getmethod to retrieve eventlist
     * @return LinkedList</Event>
     */
    public static LinkedList<Event> getCalenderEvents() {
            return Eventlist = model.getEvents();
    }




}
