package Oef1;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import main.java.dao.Database;
import main.java.model.Student;

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

    public void initialize() throws SQLException {
        connection = Database.getInstance().getConnection();
        System.out.println("test");
    }

    public void Click_Vooruit(MouseEvent mouseEvent) throws SQLException {
       try {
            String sql = "select * from plant where plant_id = " + getal;
            System.out.println(sql);
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            System.out.println(rs.toString());
            while (rs.next()) {
                lblResultaat.setText(rs.getString("fgsv"));
            }
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
            String sql = "select * from plant where plant_id = " + getal;
            System.out.println(sql);
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            System.out.println(rs.toString());
            while (rs.next()) {
                lblResultaat.setText(rs.getString("fgsv"));
            }
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
