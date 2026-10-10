package Uebungsverwaltung;
import java.sql.*;
import Datenbank.Datenbank;

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
    // Dieser Abschnitt wird von der API genutzt
    public int zaehleWorkouts(int nutzer_id) throws SQLException {
        String sql = """
                SELECT COUNT(*)
                FROM workouts
                WHERE nutzer_id = ?;
                """;
        try(Connection con = Datenbank.verbindungHerstellen();
            PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, nutzer_id);
            try (ResultSet rs = ps.executeQuery()) {
                rs.next();
                return rs.getInt(1);
            }
        }
    }
}
