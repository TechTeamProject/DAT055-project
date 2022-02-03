package src;


import java.time.*;
import java.util.*;

public class Model {
    private static LinkedList<Event> Eventlist = new LinkedList<Event>();
    private String language = "SWE";
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

    public void testPrint(){
        Eventlist.get(0).printer();
    }

    public int getMaxDays(int year, int month){
        return YearMonth.of(year, month).lengthOfMonth();
    }

    public String getMonthName(int month){
        if(language.compareTo("SWE") == 0){
            switch (month) {
                case 1:  return "Januari";
                case 2:  return "Februari";
                case 3:  return "Mars";
                case 4:  return "April";
                case 5:  return "Maj";
                case 6:  return "Juni";
                case 7:  return "Juli";
                case 8:  return "Augusti";
                case 9:  return "September";
                case 10: return "Oktober";
                case 11: return "November";
                case 12: return "December";
                default: return "Invalid month";
            }
        }
        else if(language.compareTo("ENG") == 0){
            switch (month) {
                case 1:  return "January";
                case 2:  return "February";
                case 3:  return "March";
                case 4:  return "April";
                case 5:  return "May";
                case 6:  return "June";
                case 7:  return "July";
                case 8:  return "August";
                case 9:  return "September";
                case 10: return "October";
                case 11: return "November";
                case 12: return "December";
                default: return "Invalid month";
            }
        }
        return "Invalid language";
    }

    public String getLanguage(){
        if(language.compareTo("SWE") == 0){
            return "Svenska";
        }
        else if(language.compareTo("ENG") == 0){
            return "English";
        }
        return "Unknown";
    }

    public int getYear(int index) { return Eventlist.get(index).year; }

    public void setYear(int index, int _year) { Eventlist.get(index).year = _year; }

    public int getMonth(int index) { return Eventlist.get(index).month; }

    public void setMonth(int index, int _month){ Eventlist.get(index).month = _month; }

    public int getDay(int index) { return Eventlist.get(index).day; }

    public void setDay(int index, int _day){ Eventlist.get(index).day = _day; }

    public int getHour(int index) { return Eventlist.get(index).hour; }

    public void setHour(int index, int _hour){ Eventlist.get(index).hour = _hour; }

    public int getMinute(int index) { return Eventlist.get(index).minute; }

    public void setMinute(int index, int _minute){ Eventlist.get(index).minute = _minute; }

    public String getTitle(int index) { return Eventlist.get(index).title; }

    public void setTitle(int index , String _title){ Eventlist.get(index).title = _title; }

    public String getDesc(int index) { return Eventlist.get(index).description; }

    public void setDesc(int index, String _description){ Eventlist.get(index).description = _description; }

    public String getLoca(int index) { return Eventlist.get(index).location; }

    public void setLoca(int index, String _location){ Eventlist.get(index).location = _location; }

    public boolean getRepe(int index) { return Eventlist.get(index).repeat; }

    public void setRepe(int index, boolean _repeat){ Eventlist.get(index).repeat = _repeat; }

}
