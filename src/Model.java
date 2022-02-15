package src;


import java.awt.*;
import java.time.*;
import java.util.*;

public class Model {
    private LinkedList<Event> Eventlist = new LinkedList<Event>();
    private String language = "SWE"; //Programmet startar med svenska.
    private Color color = Color.WHITE; //Programmet startar med vit färg.


    //Skapar ett nytt Event och lägger den på sista platsen i LinkedListen EventList.
    public void addEvent(int year, int month, int day, int hour, int minute, String title) {
        this.Eventlist.add(new Event(year,month,day,hour, minute, title));
    }

    //Printar ut lite info om eventet på index 0 i EventList. Används bara för att testa.
    public void testPrint(){
        Eventlist.get(0).printer();
    }

    //Enkel funktion som bara kan göra ett ljud hittils. Är meningen att den ska kunna göra flera ljud.
    public void playSound(String type){
        if(type.compareTo("ERROR") == 0){
            Toolkit.getDefaultToolkit().beep();
        }
    }

    //Tar in år och månad och returnerar antal dagar i den månaden.
    public int getMaxDays(int year, int month){
        return YearMonth.of(year, month).lengthOfMonth();
    }

    //Tar in månadens siffra och returnerar namnet på måndaden. Tar hänsyn till vilket språk man har satt på.
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


    //Setters och getters här nedanför för.

    public String getLanguage(){
        if(language.compareTo("SWE") == 0){
            return "Svenska";
        }
        else if(language.compareTo("ENG") == 0){
            return "English";
        }
        return "Unknown";
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
