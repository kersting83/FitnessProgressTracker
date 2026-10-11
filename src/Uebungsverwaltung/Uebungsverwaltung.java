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
    public void workoutsAnzeigen(int nutzer_id) {
        String sql = """
                SELECT w.workout_id, w.datum, u.name AS uebung,
                       w. saetze, w.wiederholungen, w.gewicht_kg
                FROM workouts w
                JOIN uebungen u ON w.uebung_id = u.uebung_id
                WHERE w.nutzer_id = ?;
                ORDER BY w.datum DESC;
                """;
        try (Connection con = Datenbank.verbindungHerstellen();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, nutzer_id);
            try (ResultSet rs = ps.executeQuery()) {
                boolean gefunden = false;
                System.out.printf("%-4s %-12s %-18s %6s %5s %8s%n",
                        "ID", "Datum", "Übung", "Sätze", "Wdh", "kg");
                while (rs.next()) {
                    gefunden = true;
                    System.out.printf("%-4d %-12s %-18s %6d %5d %8.1f%n",
                            rs.getInt("workout_id"),
                            rs.getDate("datum"),
                            rs.getString("uebung"),
                            rs.getInt("seatze"),
                            rs.getInt("wiederholungen"),
                            rs.getDouble("gewicht_kg"));
                }
                if (!gefunden) {
                    System.out.println("Keine Workouts gefunden.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Fehler: " + e.getMessage());
        }
    }
    public void workoutAendern(int workoutId, int saetze, int wiederholungen, double gewicht_kg) {
        String sql = """
                UPDATE workouts
                SET saetze = ?,
                wiederholungen = ?,
                gewicht_kg = ?
                WHERE workout_id = ?;
                """;
        try(Connection con = Datenbank.verbindungHerstellen();
            PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, saetze);
            ps.setInt(2, wiederholungen);
            ps.setDouble(3, gewicht_kg);
            ps.setInt(4, workoutId);
            int zeilen = ps.executeUpdate();
            if (zeilen == 0) {
                System.out.println("Workout nicht gefunden. ");
            }else {
                System.out.println("Workout wurde geändert.");
            }
        }catch(SQLException e) {
            System.out.println("Fehler beim Ändern: " + e.getMessage());
        }
    }
    public void workoutLoeschen(int workoutId) {
        String sql = """
                   DELETE FROM workouts
                    WHERE workout_id = ?;
                    """;

        try (Connection con = Datenbank.verbindungHerstellen();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, workoutId);
            int zeilen = ps.executeUpdate();
            if (zeilen == 0) {
                System.out.println("Workout nicht gefunden.");
            } else {
                System.out.println("Workout wurde gelöscht.");
            }
        } catch (SQLException e) {
            System.out.println("Fehler beim Löschen: " + e.getMessage());
        }
        }
}
