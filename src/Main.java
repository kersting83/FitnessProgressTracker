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
            case 2 -> nutzerVw.nutzerAnzeigen();
            case 3 -> nutzerVw.nutzerLoeschen(leseInt(sc, "Nutzer-ID: "));
            case 4 -> uebungVw.fortschrittAnzeigen(leseInt(sc, "Nutzer-ID: "));
            case 5 -> uebungVw.workoutsAnzeigen(leseInt(sc, "Nutzer-ID: "));
            case 6 -> {
                int id = leseInt(sc, "Workout-ID: ");
                int saetze = leseInt(sc, "Neue Sätze: ");
                int wdh = leseInt(sc, "Neue Wiederholungen: ");
                double kg = leseDouble(sc, "Neues Gewicht in kg: ");
                uebungVw.workoutAendern(id, saetze, wdh, kg);
            }
            case 7 -> uebungVw.workoutLoeschen(leseInt(sc, "Workout-ID: "));
            case 8 -> uebungVw.statistikAnzeigen(leseInt(sc, "Nutzer-ID: "));
            case 0 -> laeuft = false;
            default -> System.out.println("Ungültige Auswahl.");
        }
    }
}