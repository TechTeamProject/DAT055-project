package src;

import java.util.*;

public class Model {
    private static LinkedList<Event> Eventlist = new LinkedList<Event>();
    public static class Event {
        private int year;
        private int month;
        private int day;
        private int hour;
        private int minute;
        private String title;
        private String description;
        private String location;
        private boolean repeat;

        public Event(int const_year, int const_month, int const_day, int const_hour, int const_minute, String const_title){
            year = const_year;
            month = const_month;
            day = const_day;
            hour = const_hour;
            minute = const_minute;
            title = const_title;
        }

        public void printer(){
            System.out.println(year + "-" + month + "-" + day + " Time " + hour + ":" + minute + " Description: " + title);
        }
    }

    public void addEvent(int year, int month, int day, int hour, int minute, String title) {
        this.Eventlist.add(new Event(year,month,day,hour, minute, title));
    }

    public int getYear(int index) {
        return Eventlist.get(index).year;
    }

    public void testPrint(){
        Eventlist.get(0).printer();
    }
}
