package Uebungsverwaltung;
import java.sql.*;

public class Uebungsverwaltung {

    //Bedingungen: Level nach Anzahl der Workouts
    public static String ermittleLevel(int anzahl) {
        if (anzahl < 5){
            return "Anfänger";
        }else if (anzahl < 15){
            return "Fortgeschritten";
        }else {
            return "Profi";
        }
    }
}
