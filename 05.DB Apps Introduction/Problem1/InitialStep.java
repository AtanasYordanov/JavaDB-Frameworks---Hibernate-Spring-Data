package Problem1;

import java.sql.*;
import java.util.Properties;

public class InitialStep {
    static final String URL = "jdbc:mysql://localhost:3308/";
    static final String DB = "minions_db";
    static final String USER = "root";
    static final String PASS = "1234";

    public static void main(String[] args) throws SQLException {
        Properties props = new Properties();
        props.setProperty("user", USER);
        props.setProperty("password", PASS);

        Connection conn = DriverManager.getConnection(URL, props);
        Statement statement = conn.createStatement();

        //Create database
        String query = "DROP DATABASE IF EXISTS minions_db";
        statement.executeUpdate(query);

        query = "CREATE DATABASE minions_db";
        statement.executeUpdate(query);

        conn = DriverManager.getConnection(URL + DB, USER, PASS);
        statement = conn.createStatement();

        //Create tables
        query = "CREATE TABLE towns(\n" +
                "id INT PRIMARY KEY AUTO_INCREMENT,\n" +
                "name VARCHAR(255) NOT NULL,\n" +
                "country VARCHAR(100) NOT NULL\n" +
                ")";
        statement.executeUpdate(query);

        query = "CREATE TABLE minions (\n" +
                "id INT PRIMARY KEY AUTO_INCREMENT,\n" +
                "name VARCHAR(255) NOT NULL,\n" +
                "age INT,\n" +
                "town_id INT,\n" +
                "CONSTRAINT fk_minion_towns\n" +
                "FOREIGN KEY (town_id)\n" +
                "REFERENCES towns(id)\n" +
                ")";
        statement.executeUpdate(query);

        query = "CREATE TABLE villains (\n" +
                "id INT PRIMARY KEY AUTO_INCREMENT,\n" +
                "name VARCHAR(255) NOT NULL,\n" +
                "evilness_factor ENUM('good', 'bad', 'evil', 'super evil')\n" +
                ")";
        statement.executeUpdate(query);

        query = "CREATE TABLE minions_villains(\n" +
                "minion_id INT,\n" +
                "villain_id INT,\n" +
                "CONSTRAINT fk_mv_minions\n" +
                "FOREIGN KEY (minion_id)\n" +
                "REFERENCES minions(id),\n" +
                "CONSTRAINT fk_mv_villains\n" +
                "FOREIGN KEY (villain_id)\n" +
                "REFERENCES villains(id),\n" +
                "PRIMARY KEY (minion_id, villain_id)\n" +
                ")";
        statement.executeUpdate(query);

        //Insert data
        query = "INSERT INTO towns(name, country)\n" +
                "VALUES('town1', 'country1'),('town2', 'country2'),\n" +
                "('town3', 'country3'),('town4', 'country4'),\n" +
                "('town5', 'country5')";
        statement.executeUpdate(query);

        query = "INSERT INTO minions(name, age, town_id)\n" +
                "VALUES('min1', 15, 2),('min2', 25, 3),('min3', 19, 5),\n" +
                "('min4', 23, 4),('min5', 50, 1)";
        statement.executeUpdate(query);

        query = "INSERT INTO villains(name, evilness_factor)\n" +
                "VALUES('vil1', 'bad'),('vil2', 'super evil'),\n" +
                "('vil3', 'evil'),('vil4', 'good'),\n" +
                "('vil5', 'evil')";
        statement.executeUpdate(query);

        query = "INSERT INTO minions_villains(minion_id, villain_id)\n" +
                "VALUES(2, 1),(3, 5),(1, 3),(5, 2),(4, 4),(1, 4),(3, 2),(2, 2),(1, 2),(4, 2)";
        statement.executeUpdate(query);


        //Close connections
        statement.closeOnCompletion();
        conn.close();
    }
}
