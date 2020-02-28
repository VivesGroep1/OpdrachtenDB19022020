package Oef1;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import main.java.dao.Database;
import main.java.dao.plantdao;
import main.java.model.Student;
import main.java.model.plant;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Controller {
    public Label lblResultaat;
    public TextField ZoekTExtfield;
    public Button zoekbutton;
    Connection connection;
    int getal = 1;
    private Connection dbConnection;

    public void initialize() throws SQLException {
        connection = Database.getInstance().getConnection();
        dbConnection = Database.getInstance().getConnection();

        System.out.println("test");
    }

    public void Click_Vooruit(MouseEvent mouseEvent) throws SQLException {
       try {
           plantdao plantdao =  new plantdao(dbConnection);

           plant plantID =  plantdao.getplantById(getal);
           lblResultaat.setText(plantID.toString());
           getal++;
        }
        catch (Exception e)
        {
            lblResultaat.setText("u zit op de laatse plant");
        }
    }

    public void Click_Achteruit(MouseEvent mouseEvent) throws SQLException {
        if(getal > 0)
        {
            plantdao plantdao =  new plantdao(dbConnection);

            plant plantID =  plantdao.getplantById(getal);
            lblResultaat.setText(plantID.toString());
            getal--;
        }
        else
        {
            lblResultaat.setText("u zit op de eerste plant");
        }
    }

    public void zoekbuttonClicked(MouseEvent mouseEvent) {
        String zoekkterm = ZoekTExtfield.getText();


    }
}
