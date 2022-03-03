package src;


import java.io.Serializable;
import java.time.LocalDateTime;

public class Event implements Serializable {
    private String description;
    private String location;
    private LocalDateTime starttime;
    private LocalDateTime endtime;


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
    }

    public String getDescription(){
        return description;
    }

    public String getLocation(){
        return location;
    }

    public LocalDateTime getStartTime(){ return starttime; }

    public LocalDateTime getEndTime(){ return endtime; }
}