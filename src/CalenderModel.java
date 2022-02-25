package src;


import java.awt.*;
import java.time.*;
import java.util.*;
import java.time.LocalDateTime;

public class CalenderModel extends Lighthouse {
    private LinkedList<Event> Eventlist = new LinkedList<Event>();
    private String language = "SWE"; //Programmet startar med svenska.
    private Color color = Color.WHITE; //Programmet startar med vit färg.
    private LocalDateTime now = LocalDateTime.now();
    private YearMonth yearMonthObject = YearMonth.of(2022, 2);
    private int daysInMonth = yearMonthObject.lengthOfMonth(); //28
    private LocalDateTime viewdate = LocalDateTime.now(); //viewdate är det aktuella objektet man ser i View.

    public CalenderModel() {
        //constructor
    }

    /**
     * @param start - Where the event starts
     * @param end - Where the event ends
     * @param title - The title of the event
     */
    public void addEvent(LocalDateTime start, LocalDateTime end, String title, String location) {
        this.Eventlist.add(new Event(start, end, title, location));
    }

    public LinkedList<Event> getEvents(){
        return this.Eventlist;
    }

    //Printar ut lite info om eventet på index 0 i EventList. Används bara för att testa.
    public void testPrint(){
        Eventlist.get(0).printer();
    }



    //Tar in år och månad och returnerar antal dagar i den månaden.
    public int getMaxDays(int year, int month){
        return YearMonth.of(year, month).lengthOfMonth();
    }

    /**
     * Returnerar time som view tittar på just nu
     * @return LocalDateTime objekt
     */
    public LocalDateTime getViewTime() {
        return viewdate;
    }

    /**
     * Returnerar verklig tid just nu
     * @return
     */
    public LocalDateTime getRealTime() {
        return now;
    }

    /**
     * Skriv in ett LocalDateTime objekt och returnera en int.
     * @param date object
     * @return an int
     */
    public int getDaysInMonth2(LocalDateTime date) {
        int year = date.getYear();
        Month month = date.getMonth();
        yearMonthObject = YearMonth.of(year, month);
        return yearMonthObject.lengthOfMonth();
    }

    /**
     * Metod för att få antal dagar i månaden
     * @return int (antal dagar)
     */
    public int getDaysInMonth() {
        int year = viewdate.getYear();
        Month month = viewdate.getMonth();
        yearMonthObject = YearMonth.of(year, month);
        return yearMonthObject.lengthOfMonth();
    }
    /**
     * Metod för att returnera årtal
     * @return int (årtal)
     */
    public int getYear() {
        return viewdate.getYear();
    }

    /**
     * Setter för årtal. positivt tal för frammåt och minustal för bakåt.
     * @param y (antal år framåt)
     */
    public void setYear(int y) {
        if (y > 0) {
            viewdate = viewdate.plusYears(y);
        }
        else if (y < 0) {
            y = y*-1;
            viewdate = viewdate.minusYears(y);
        }
    }
    /**
     * Metod för att returnera månad
     * @return Month (månader)
     */
    public Month getMonth() {
        return viewdate.getMonth();
    }
    /**
     * Setter för månad
     * @param y (antal månader framåt)
     */
    public void setMonth(int y) {
        if (y > 0) {
           viewdate = viewdate.plusMonths(y);
        }
        else if (y < 0) {
            y = y*-1;
           viewdate = viewdate.minusMonths(y);
        }
    }
    /**
     * Metod för att returnera dag
     * @return int (dag)
     */
    public int getDay() {
        return viewdate.getDayOfMonth();
    }

    /**
     * Setter för dagar. d>0 för framåt, d<0 för bakåt.
     * @param d (dagar)
     */
    public void setDay(int d) {
        if (d > 0) {
           viewdate = viewdate.plusDays(d);
        }
        else if (d < 0) {
            d = d*-1;
           viewdate = viewdate.minusDays(d);
        }
    }

    /**
     * --------------------------------------------------------------------------------------------------------
     * Metoder nedan från innan UML
     * --------------------------------------------------------------------------------------------------------
     */

    //Enkel funktion som bara kan göra ett ljud hittils. Är meningen att den ska kunna göra flera ljud.
    public void playSound(String type){
        if(type.compareTo("ERROR") == 0){
            Toolkit.getDefaultToolkit().beep();
        }
    }
    /*
    public void setLanguage(String _language){ language = _language; }

    public Color getColor(){ return color; }

    public void setColor(Color _color){ color = _color; }

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
*/
}
