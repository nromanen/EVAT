package utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class AzureConnection {

    public static Connection getConnection() {
        Connection connection = null;
        try (InputStream input = new FileInputStream("/Users/illyashulman/EventExpress/src/main/resources/azure.properties")) {
            Properties prop = new Properties();
            prop.load(input);
            connection = DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("user"), prop.getProperty("password"));
            System.out.println("Connected");
        } catch (IOException | SQLException ex) {
            System.out.println("Connection error");
            ex.printStackTrace();
        }
        return connection;
    }



}
