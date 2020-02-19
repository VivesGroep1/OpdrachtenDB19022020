package Oef2;

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import main.java.dao.Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Controller {
    public TextField txtZoekterm;
    public TextArea txtResultaten;
    public Button btnZoeken;



    public void click_btnZoeken(MouseEvent mouseEvent) throws SQLException {
        String zoekterm = txtZoekterm.getText();
        Connection connection = Database.getInstance().getConnection();
        String sql = "select * from plant where familie = "+ "'"+zoekterm+"'";
        System.out.println(sql);
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        System.out.println(rs.toString());
        while (rs.next()) {
            System.out.println(rs.getString("fgsv"));
            txtResultaten.setText(txtResultaten.getText() + "\r\n" + rs.getString("fgsv"));
        }

    }
}
