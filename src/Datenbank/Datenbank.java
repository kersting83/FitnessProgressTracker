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
