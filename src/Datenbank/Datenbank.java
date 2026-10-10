package Datenbank;
import java.util.*;
import java.sql.*;

public class Datenbank {
    private static final String url = "jdbc:postgresql://localhost:5432/defaultdb";
    private static final String user = "postgres";
    private static final String password = "bbq";

    public static Connection verbindungHerstellen() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}
//Die Klasse, die eine Verbindung zur Datenbank herstellt. Sie hat keine andere Funktion und demnach auch keine
// anderen Methoden, außer der Konstruktormethode (Zeile 10 bis 11).
// Die Variablen sind hier bewusst als "private" und "final" deklariert, damit man nicht von außen auf sie zugreifen
// und sie verändern kann. (Zeile 6 bis 8)