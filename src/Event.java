package src;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event {
    private String description;
    private String location;
    private boolean repeat;
    private LocalDateTime starttime;
    private LocalDateTime endtime;
    private DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    private String formattedStart;
    private String formattedEnd;


    /**
     * Constructor
     * @param start - When the event is started
     * @param end - When the event is ended
     * @param title - name of event
     * formattedStart/End used to format to more convinient date
     */
    public Event(LocalDateTime start, LocalDateTime end, String title, String loc){
        this.starttime = start;
        this.endtime = end;
        this.description = title;
        this.location = loc;
        formattedStart = start.format(format);
        formattedEnd = end.format(format);
    }

    public String getDescription(){
        return description;
    }

    public String getLocation(){
        return location;
    }

    public String getFormattedStart(){
        return formattedStart;
    }
    public String getFormattedEnd(){
        return formattedEnd;
    }

    public void printer(){
       //System.out.println(year + "-" + month + "-" + day + " Time " + hour + ":" + minute + " Description: " + title);
        System.out.println("Starttime " + formattedStart + " Endtime: " + formattedEnd + " Description: " + description);
    }
}