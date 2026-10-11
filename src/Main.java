import java.util.*;
import java.sql.*;
import Datenbank.Datenbank;
import Nutzer.Nutzer;
import NutzerDAO.NutzerDAO;
import Uebungsverwaltung.Uebungsverwaltung;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;


public class Main {
    public static void main(String[] args) {

        // 1. Verbindung testen
        try (Connection con = Datenbank.verbindungHerstellen()) {
            System.out.println("Datenbankverbindung erfolgreich.");
        } catch (SQLException e) {
            System.out.println("Keine Verbindung zur Datenbank: " + e.getMessage());
            return;
        }
        // 1.1 Objekte der externen Klassen erzeugen
        NutzerDAO nutzerVw = new NutzerDAO();
        Uebungsverwaltung uebungVw = new Uebungsverwaltung();
        Scanner sc = new Scanner(System.in);
    }
}