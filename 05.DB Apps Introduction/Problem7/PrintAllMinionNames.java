package Problem7;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class PrintAllMinionNames {
    static final String URL = "jdbc:mysql://localhost:3308/";
    static final String DB = "minions_db";
    static final String USER = "root";
    static final String PASS = "1234";

    public static void main(String[] args) throws SQLException {
        Properties props = new Properties();
        props.setProperty("user", USER);
        props.setProperty("password", PASS);

        Connection conn = DriverManager.getConnection(URL + DB, props);
        Statement statement = conn.createStatement();

        String query = "SELECT name FROM minions";
        ResultSet rs = statement.executeQuery(query);
        List<String> minionsInOrder = new ArrayList<>();
        while (rs.next()) {
            minionsInOrder.add(rs.getString("name"));
        }

        for (int i = 0; i < minionsInOrder.size() / 2; i++) {
            System.out.println(minionsInOrder.get(i));
            System.out.println(minionsInOrder.get(minionsInOrder.size() - 1 - i));
        }

        rs.close();
        statement.closeOnCompletion();
        conn.close();
    }
}
