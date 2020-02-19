package main.java.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Database  {
    private static  String jdbcDriver;
    private static  String url;
    private static  String user;
    private static  String password;

    private static Database instance;
    private Connection connection;

    private Database() {
        readDbPropertiesFromResource();
        connection = createConnection();
    }

    public static Database getInstance() throws SQLException {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

    private void readDbPropertiesFromResource() {
        ClassLoader cl = Database.class.getClassLoader();
        //TODO 1 : maak een resources directory met een file config.properties (zie cursus)
        //TODO 2 : plaats uw databaseconnectieparameters in de file config.properties
        try (InputStream inputStream =
                     cl.getResourceAsStream("config.properties")) {
            Properties prop = new Properties();
            // load a properties file
            prop.load(inputStream);
<<<<<<< Updated upstream
=======

>>>>>>> Stashed changes
            // get the property value
            jdbcDriver = prop.getProperty("db.jdbcDriver");
            url = prop.getProperty("db.url");
            user = prop.getProperty("db.user");
            password = prop.getProperty("db.password");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private Connection createConnection() {
        //Het database Object aanmaken
        Connection dbConnection = null;
<<<<<<< Updated upstream
        //TODO 3 : Vervolledig de methode createConnection()
=======
        try {

            Class.forName(jdbcDriver);

            dbConnection = DriverManager.getConnection(url,user,password);
            System.out.println("created DB connection...");
        }catch (ClassNotFoundException | SQLException ex){
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null,ex);
        }
>>>>>>> Stashed changes
        return dbConnection;
    }

}
