package src;
import src.Server.ChatServer;
import src.View.*;

import javax.swing.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.LinkedList;

import static javax.swing.SwingUtilities.isRightMouseButton;

public class Control implements PropertyChangeListener, Serializable {
    private static WindowFrame windowFrame;
    private static CalenderModel model;
    private static ChatView chatView;
    private static ClientThread clientThread;
    private ChatServer server;
    private static YearView yearView;
    private static OptionView optionView;
    private static WeekView weekView;
    private static MonthView monthView;
    private static BookingView bookingView;
    private static EventView eventView;
    private PopUp popup;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private LocalDateTime eventtime;
    private weekViewListener weeklistener = new weekViewListener();
    private String eventname;

    public Control(WindowFrame wf, CalenderModel m, ChatView c, YearView y, OptionView o, WeekView w, MonthView mv, BookingView b, EventView e, PopUp p){
        windowFrame = wf;
        model = m;
        chatView = c;
        yearView = y;
        optionView = o;
        weekView = w;
        monthView = mv;
        bookingView = b;
        eventView = e;
        popup = p;

        windowFrame.addWindowFrameListener(new windowFrameListener());
        chatView.addChatFieldListener(new chatListener());
        chatView.addTopButtonsListener(new topButtonsListener());
        yearView.addYearViewListener(new yearViewListener());
        optionView.addOptionViewListener(new optionViewListener());
        weekView.addWeekViewListener(weeklistener);
        weekView.addWeekViewActionListener(new weekViewListener());
        monthView.addMonthViewListener(new monthViewListener());
        bookingView.addBookingViewListener(new bookingViewListener());
        eventView.addEventViewListener(new eventViewListener());
        popup.addPopupListener(new popupListener());

        //Listeners added to Observable here
        model.addPropertyChangeListener(weekView);
        model.addPropertyChangeListener(monthView);
        model.addPropertyChangeListener(yearView);
        model.addPropertyChangeListener(bookingView);

        model.addPropertyChangeListener(this);

        //Updates in Model to set all the views.
        model.getViewTime();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if(evt.getPropertyName().equals("NewEvent") | evt.getPropertyName().equals("LoadedEvents") | evt.getPropertyName().equals("RemoveEvent")){
            bookingView.addBookingViewListener(new bookingViewListener());

        }
        else if(evt.getPropertyName().equals("NewMessage")){
            System.out.println("Funkar");
        }
    }

    private class chatListener implements KeyListener {
        public void keyPressed(KeyEvent ke){
            if(ke.getKeyCode()==KeyEvent.VK_ENTER){

                if (chatView.getFieldText().compareTo("") != 0) {
                    if(clientThread.Alive()){
                        clientThread.addNextMessage(chatView.getFieldText());
                        chatView.setFieldText("");
                    }
                    else{
                        chatView.printText("You are not connected to a server!");
                        Sound.playError();
                    }

                }
            }
        }
        public void keyTyped(KeyEvent e) {}
        public void keyReleased(KeyEvent e) {}
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
                    Sound.playStartHost();
                    break;
                case "Connect to server":
                    chatView.switchMiddlePanel("IpArea");
                    break;
                case "Go back":
                    chatView.switchMiddlePanel("ChatArea");
                    break;
                case "Confirm":
                    if (chatView.getIpText().compareTo("") != 0 && chatView.getNameText().compareTo("") != 0) {
                        if(!clientThread.Alive()){

                            clientThread = new ClientThread(chatView.getNameText(), chatView.getIpText(), 23476);
                            clientThread.addPropertyChangeListener(chatView);

                            chatView.switchMiddlePanel("ChatArea");
                            Sound.playConnected();
                        }
                        else{
                            chatView.switchMiddlePanel("ChatArea");
                            chatView.printText("Already connected");
                            Sound.playError();
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
                    model.getViewTime();
                    break;
                case "next":
                    model.setYear(1);
                    model.getViewTime();
                    break;
                case "1","2","3","4","5","6","7","8","9","10","11","12":
                    model.setMonth(Integer.parseInt(str)-model.getMonth().getValue());
                    model.getViewTime();
                    WindowFrame.changePanel("month");
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
                    updateWeeklisteners(); //update mouselisener for loaded events
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
                    model.getViewTime();
                    updateWeeklisteners();
                    break;
                case ">":
                    model.setDay(7);
                    model.getViewTime();
                    updateWeeklisteners();
                    break;
            }
        }
        @Override
        public void mouseClicked(MouseEvent e) {}
        @Override
        public void mousePressed(MouseEvent e) {}

        @Override
        public void mouseReleased(MouseEvent e) {
            //If mouse released and BUTTON3 = RightClick has been used
            if (isRightMouseButton(e)) {

                //popup were mouse pressed
                popup.show(e.getComponent(), e.getX(), e.getY());
                //Saves name of event
                eventname = e.getComponent().getName();

                //A block to save x coordinates of rightclick in weekview to add to eventview the current time
                eventtime = model.getViewTime().minusDays(model.getViewTime().getDayOfWeek().getValue()-1);


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
                    LocalDateTime eventendtime = eventtime.plusHours(1);

                String formatedtime = eventtime.format(formatter);
                String formatedtimeEnd = eventendtime.format(formatter);
                EventView.setEventTime(formatedtime, formatedtimeEnd);
            }

        }

        @Override
        public void mouseEntered(MouseEvent e) {}
        @Override
        public void mouseExited(MouseEvent e) {}

    }
    private class monthViewListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            String str = e.getActionCommand();
            if(str.equals("<") | str.equals(">")){
                switch (str) {
                    case "<":
                        model.setMonth(-1);
                        model.getViewTime();
                        break;
                    case ">":
                        model.setMonth(+1);
                        model.getViewTime();
                        break;
                }
            }
            else {
                model.setDay(Integer.parseInt(str)-model.getDay());
                model.getViewTime();
                WindowFrame.changePanel("week");
            }
        }
    }


    private class eventViewListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {

            String str = e.getActionCommand();
            if(str.equals("Save")){
                String title = eventView.getTitle();
                String fromTime = eventView.getStartTime();
                String untilTime = eventView.getEndTime();
                String loc = eventView.getLoc();

                String fromYearStr = fromTime.substring(0,4);
                String fromMonthStr = fromTime.substring(5,7);
                String fromDayStr = fromTime.substring(8,10);
                String fromHourStr = fromTime.substring(11,13);
                String fromMinuteStr = fromTime.substring(14,16);
                String endYearStr = untilTime.substring(0,4);
                String endMonthStr = untilTime.substring(5,7);
                String endDayStr =  untilTime.substring(8,10);
                String endHourStr = untilTime.substring(11,13);
                String endMinuteStr = untilTime.substring(14,16);

                int fromTimeYear = Integer.parseInt(fromYearStr);
                int fromTimeMonth = Integer.parseInt(fromMonthStr);
                int fromTimeDay = Integer.parseInt(fromDayStr);
                int fromTimeHour = Integer.parseInt(fromHourStr);
                int fromTimeMinute = Integer.parseInt(fromMinuteStr);
                int endTimeYear = Integer.parseInt(endYearStr);
                int endTimeMonth = Integer.parseInt(endMonthStr);
                int untilTimeDay = Integer.parseInt(endDayStr);
                int untilTimeHour = Integer.parseInt(endHourStr);
                int untilTimeMinute = Integer.parseInt(endMinuteStr);


                if (title.isBlank()) {
                    JOptionPane.showMessageDialog(null, "No title", "Error", JOptionPane.ERROR_MESSAGE);
                }


                else if(fromTimeYear != endTimeYear|| fromTimeMonth != endTimeMonth|| fromTimeDay != untilTimeDay || fromTimeHour > untilTimeHour ||
                        (fromTimeHour == untilTimeHour && fromTimeMinute == untilTimeMinute) || (fromTimeHour == untilTimeHour && fromTimeMinute > untilTimeMinute)){
                    JOptionPane.showMessageDialog(null, "Invalid time", "Error", JOptionPane.ERROR_MESSAGE);
                }


                else {
                    try {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                        LocalDateTime fromdatetime = LocalDateTime.parse(fromTime, formatter);
                        LocalDateTime untildatetime = LocalDateTime.parse(untilTime, formatter);
                        model.addEvent(fromdatetime, untildatetime, title, loc);
                        updateWeeklisteners();  //To update new event with a mouselistener
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
                    WindowFrame.changePanel("event");
                    System.out.println("Panel should be switched");
                    break;
                case "Remove Event":

                    for (int i = 0; i < model.getEvents().size(); i++) {
                        System.out.println("Detta tår i eventname: " + eventname + "Detta står i model.getEVent(i): " + model.getEvents().get(i).getDescription());
                        if (eventname.equals(model.getEvents().get(i).getDescription())) {
                            model.removeEvent(i);
                            updateWeeklisteners(); //Updaterar, tar bort gammal mouselistener
                            System.out.println("Event should be removed");
                        }
                    }
                    break;
            }
        }
    }
    public class windowFrameListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String str = e.getActionCommand();
            WindowFrame.changePanel(str);
        }
    }

    /**
     * Simple getmethod to retrieve eventlist
     * @return LinkedList</Event>
     */
    public static LinkedList<Event> getCalenderEvents() {
            return model.getEvents();
    }

    public static String getMessage(){
        return clientThread.getMessage();
    }

    public void updateWeeklisteners() {
        WeekView.UpdateViewEventListener(weeklistener);
    }
}
