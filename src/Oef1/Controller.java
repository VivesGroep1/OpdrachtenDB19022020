package Oef1;

import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import main.java.dao.Database;

import java.sql.Connection;
import java.sql.SQLException;

public class Controller {
    public Label lblResultaat;
    Connection connection;

    public  void initialize() throws SQLException {
        connection = Database.getInstance().getConnection();
    }
    public void Click_Vooruit(MouseEvent mouseEvent) {
    }

    public void Click_Achteruit(MouseEvent mouseEvent) {
    }
}
