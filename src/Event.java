package src;

public class Event {
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