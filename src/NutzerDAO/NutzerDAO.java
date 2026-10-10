package NutzerDAO;
import java.sql.*;
import java.util.*;


public class NutzerDAO {

    public void nutzerAnlegen(String vorname, String nachname, String email, double gewicht){
        String sql = """
                INSERT INTO nutzer (vorname, nachname, email, gewicht)
                VALUES (?, ?, ?, ?);
                """;
        try (Connection con = Datenbank.verbindungHerstellen();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, vorname);
            ps.setString(2, nachname);
            ps.setString(3, email);
            ps.setDouble(4, gewicht);
            System.out.println("Nutzer wurde angelegt. ");
        }catch (SQLException e) {
            System.out.println("Fehler beim Anlegen des Nutzers: " + e.getMessage());
        }
    }
    return liste;
}
