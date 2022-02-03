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

    public void setYear(int index, int _year) {
        Eventlist.get(index).year = _year;
    }

    public void testPrint(){
        Eventlist.get(0).printer();
    }

    public void setMonth(int index, int _month){
        Eventlist.get(index).month = _month;
    }

    public void setDay(int index, int _day){
        Eventlist.get(index).day = _day;
    }

    public void setHour(int index, int _hour){
        Eventlist.get(index).hour = _hour;
    }

    public void setMinute(int index, int _minute){
        Eventlist.get(index).minute = _minute;
    }

    public void setTitle(int index , String _title){
        Eventlist.get(index).title = _title;
    }

    public void setDesc(int index, String _description){
        Eventlist.get(index).description = _description;
    }

    public void setLoca(int index, String _location){
        Eventlist.get(index).location = _location;
    }

    public void setRepe(int index, boolean _repeat){
        Eventlist.get(index).repeat = _repeat;
    }
}
