package Oef2;

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import main.java.dao.Database;
import main.java.dao.plantdao;
import main.java.model.plant;
import main.java.utils.IoClass;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class Controller {
    public TextField txtZoekterm;
    public TextArea txtResultaten;
    public Button btnZoeken;
    private Connection dbConnection;

    public void initialize() throws SQLException {
        dbConnection = Database.getInstance().getConnection();

        System.out.println("test");
    }


    public void click_btnZoeken(MouseEvent mouseEvent) throws SQLException {
        plantdao plantdao =  new plantdao(dbConnection);

        showByName(plantdao);

    }
    /** BEGIN HulpMethoden voor daoDemo **/
    /**
     * Print de lijst van de studenten uit
     * @param titel
     * @param planten
     */
    public void showplanten(String titel, List<plant> planten) {
        System.out.println("Lijst Studenten : " + titel);
        for (plant plant : planten) {
            txtResultaten.setText(txtResultaten.getText()+ "\r\n"+plant.toString());
        }
        System.out.println();
    }

    /**
     * @param plantdao
     * @throws SQLException
     */
    private void showByName(plantdao plantdao) throws SQLException {
        while (true) {
            String nieuweFamilie = txtZoekterm.getText();
            if(nieuweFamilie== null)
            {
                break;
            }
            List<plant> plantenlijst = plantdao.getplantbyname(nieuweFamilie);
            showplanten("planten bij name",plantenlijst);
            break;

        }

    }
}
