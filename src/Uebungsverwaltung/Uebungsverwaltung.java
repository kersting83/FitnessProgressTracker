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
    public void fortschrittAnzeigen(int nutzer_id) {
        String sql = """
                SELECT n.vorname, n.nachname, COUNT(w.workout_id) AS anzahl
                FROM nutzer n
                LEFT JOIN workouts w ON n.nutzer_id = w.nutzer_id
                WHERE n.nutzer_id = ?
                GROUP BY n.nachname;
                """;
        try (Connection con = Datenbank.verbindungHerstellen();
             PreparedStatement ps = con.prepareStatement(sql)){
            ps.setInt(1, nutzer_id);
            try(ResultSet rs = ps.executeQuery()) {
                if(rs.next()) {
                    int anzahl = rs.getInt("anzahl");
                    System.out.println("Nutzer: " + rs.getString("Vorname "
                            + "Nachname "));
                    System.out.println("Workouts: " + anzahl);
                    System.out.println("Level: " + ermittleLevel(anzahl));
                }else {
                    System.out.println("Nutzer nicht gefunden. ");
                }
            }
        }catch(SQLException e) {
            System.out.println("Fehler: " + e.getMessage());
        }
    }
}
