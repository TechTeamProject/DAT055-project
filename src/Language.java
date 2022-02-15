package src;

public class Language {

    public String langUsername(String language){
        if(language.compareTo("ENG") == 0){return "Username"; }
        else if(language.compareTo("SWE") == 0){return "Användarnamn"; }

        return "Invalid language";
    }

    public String langPassword(String language){
        if(language.compareTo("ENG") == 0){return "Password"; }
        else if(language.compareTo("SWE") == 0){return "Lösenord"; }

        return "Invalid language";
    }

    public String langRegister(String language){
        if(language.compareTo("ENG") == 0){return "Register"; }
        else if(language.compareTo("SWE") == 0){return "Registrera"; }

        return "Invalid language";
    }

    public String langCalendar(String language){
        if(language.compareTo("ENG") == 0){return "Calendar"; }
        else if(language.compareTo("SWE") == 0){return "Kalender"; }

        return "Invalid language";
    }

    public String langFiles(String language){
        if(language.compareTo("ENG") == 0){return "Files"; }
        else if(language.compareTo("SWE") == 0){return "Filer"; }

        return "Invalid language";
    }

    public String langNew(String language){
        if(language.compareTo("ENG") == 0){return "New"; }
        else if(language.compareTo("SWE") == 0){return "Ny"; }

        return "Invalid language";
    }

    public String langSeeAll(String language){
        if(language.compareTo("ENG") == 0){return "See All"; }
        else if(language.compareTo("SWE") == 0){return "Se Alla"; }

        return "Invalid language";
    }

    public String langMore(String language){
        if(language.compareTo("ENG") == 0){return "More"; }
        else if(language.compareTo("SWE") == 0){return "Mer"; }

        return "Invalid language";
    }

    public String langSettings(String language){
        if(language.compareTo("ENG") == 0){return "Settings"; }
        else if(language.compareTo("SWE") == 0){return "Inställningar"; }

        return "Invalid language";
    }

    public String langBookings(String language){
        if(language.compareTo("ENG") == 0){return "Bookings"; }
        else if(language.compareTo("SWE") == 0){return "Bokningar"; }

        return "Invalid language";
    }

    public String langWeekMonth(String language){
        if(language.compareTo("ENG") == 0){return "Week // Month"; }
        else if(language.compareTo("SWE") == 0){return "Väcka // Månad"; }

        return "Invalid language";
    }

    public String langClose(String language){
        if(language.compareTo("ENG") == 0){return "Close"; }
        else if(language.compareTo("SWE") == 0){return "Stäng"; }

        return "Invalid language";
    }

    public String langSave(String language){
        if(language.compareTo("ENG") == 0){return "Save"; }
        else if(language.compareTo("SWE") == 0){return "Spara"; }

        return "Invalid language";
    }

    public String langCreateToDo(String language){
        if(language.compareTo("ENG") == 0){return "Create ToDo"; }
        else if(language.compareTo("SWE") == 0){return "Skapa ToDo"; }

        return "Invalid language";
    }

    public String langRemoveToDo(String language){
        if(language.compareTo("ENG") == 0){return "Remove ToDo "; }
        else if(language.compareTo("SWE") == 0){return "Ta Bort ToDo"; }

        return "Invalid language";
    }

    public String langEdit(String language){
        if(language.compareTo("ENG") == 0){return "Edit"; }
        else if(language.compareTo("SWE") == 0){return "Ändra"; }

        return "Invalid language";
    }

    public String langAppearance(String language){
        if(language.compareTo("ENG") == 0){return "Appearance"; }
        else if(language.compareTo("SWE") == 0){return "Utseende"; }

        return "Invalid language";
    }

    public String langShare(String language){
        if(language.compareTo("ENG") == 0){return "Share"; }
        else if(language.compareTo("SWE") == 0){return "Dela"; }

        return "Invalid language";
    }

    public String langDownload(String language){
        if(language.compareTo("ENG") == 0){return "Download"; }
        else if(language.compareTo("SWE") == 0){return "Ladda Ner"; }

        return "Invalid language";
    }

    public String langCreateEvent(String language){
        if(language.compareTo("ENG") == 0){return "Create Event"; }
        else if(language.compareTo("SWE") == 0){return "Skapa Händelse"; }

        return "Invalid language";
    }

    public String langRemoveEvent(String language){
        if(language.compareTo("ENG") == 0){return "Remove Event"; }
        else if(language.compareTo("SWE") == 0){return "Ta Bort Händelse"; }

        return "Invalid language";
    }

    public String langTitle(String language){
        if(language.compareTo("ENG") == 0){return "Title"; }
        else if(language.compareTo("SWE") == 0){return "Titel"; }

        return "Invalid language";
    }

    public String langFrom(String language){
        if(language.compareTo("ENG") == 0){return "From"; }
        else if(language.compareTo("SWE") == 0){return "Från"; }

        return "Invalid language";
    }

    public String langUntil(String language){
        if(language.compareTo("ENG") == 0){return "Until"; }
        else if(language.compareTo("SWE") == 0){return "Tills"; }

        return "Invalid language";
    }

    //Tar in månadens siffra och returnerar namnet på måndaden. Tar hänsyn till vilket språk man har satt på.
    public String langMonthName(int month, String language){
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
}
