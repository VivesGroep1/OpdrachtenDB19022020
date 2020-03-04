package test;

import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import main.java.dao.Database;
import main.java.model.plant;
import test.dao.plantdao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Controller {
    public RadioButton familieradiobutton;
    public RadioButton TypeRadiobutton;
    public RadioButton GeslcahtRadioButton;
    public RadioButton SoortRadioButton;
    public RadioButton variatieRadioButton;
    public TextField zoektermTextfield;
    public TextArea UitkomstTextarea;
    public RadioButton plantdichtheidMinRadiobutton;
    public RadioButton plantdichtheidMaxRadiobutton;
    public RadioButton fgsvRadiobutton;
    public Label zoekInfolabel;
    public ToggleGroup keuze;
    public Button zoekButton;
    public int keuzenummer;
    public String Keuze;
    private Connection dbConnection;

    public void initialize() throws SQLException {
        dbConnection = Database.getInstance().getConnection();
    }
    public void ZoekButton_clicked(MouseEvent mouseEvent) throws SQLException {
        UitkomstTextarea.setText("");
        controleKeuzeZoekterm();
        ZetZoekinfo();
        plantdao plantdao = new plantdao(dbConnection);
        showByName(plantdao);
    }
    public void ZetZoekinfo()
    {
        if(keuzenummer==0)
        {
            zoekInfolabel.setText("maak een keuze op wat je wilt zoeken");
        }
        if(keuzenummer==1)
        {
            zoekInfolabel.setText("kies op naam van familie het hoeft niet volledig te zijn");
        }
        if(keuzenummer==2)
        {
            zoekInfolabel.setText("kies op naam van type het hoeft niet volledig te zijn");
        }
        if(keuzenummer==3)
        {
            zoekInfolabel.setText("kies op naam van geslacht het hoeft niet volledig te zijn");
        }
        if(keuzenummer==4)
        {
            zoekInfolabel.setText("kies op naam van soort het hoeft niet volledig te zijn");
        }
        if(keuzenummer==5)
        {
            zoekInfolabel.setText("kies op naam van variatie het hoeft niet volledig te zijn");
        }
        if(keuzenummer==6)
        {
            zoekInfolabel.setText("je moet een cijfer invullen en we zullen alles teruggeven wat erboven zit");
        }
        if(keuzenummer==7)
        {
            zoekInfolabel.setText("je moet een cijfer invullen en we zullen alles teruggeven wat eronder zit");
        }
        if(keuzenummer==8)
        {
            zoekInfolabel.setText("kies op naam van fgsv het hoeft niet volledig te zijn");
        }

    }
    public int controleKeuzeZoekterm()
    {
         keuzenummer =0 ;
        if(familieradiobutton.isSelected())
        {
            keuzenummer=1;
            Keuze = "familie";
        }
        if(TypeRadiobutton.isSelected())
        {
            keuzenummer=2;
            Keuze="type";
        }
        if(GeslcahtRadioButton.isSelected())
        {
            keuzenummer=3;
            Keuze="geslacht";
        }
        if(SoortRadioButton.isSelected())
        {
            keuzenummer=4;
            Keuze="soort";
        }
        if(variatieRadioButton.isSelected())
        {
            keuzenummer=5;
            Keuze="variatie";
        }
        if(plantdichtheidMinRadiobutton.isSelected())
        {
            keuzenummer=6;
            Keuze="plantdichtheid_min";
        }
        if(plantdichtheidMaxRadiobutton.isSelected())
        {
            keuzenummer=7;
            Keuze="plantdichtheid_max";
        }
        if(fgsvRadiobutton.isSelected())
        {
            keuzenummer=8;
            Keuze="fgsv";
        }
        return keuzenummer;
    }

    public void zoekterm(MouseEvent mouseEvent) {
        controleKeuzeZoekterm();
        ZetZoekinfo();
    }

    /** BEGIN HulpMethoden voor daoDemo **/
    /**
     * Print de lijst van de studenten uit
     * @param titel
     * @param planten
     */
    public void showplanten(String titel, List<plant> planten) {
        System.out.println("Lijst planten : " + titel);
        for (plant plant : planten) {
            UitkomstTextarea.setText(UitkomstTextarea.getText()+ "\r\n"+plant.toString());
            System.out.println(plant.toString());
        }
        System.out.println();
    }

    /**
     * @param plantdao
     * @throws SQLException
     */
    private void showByName(plantdao plantdao) throws SQLException {
        while (true) {
            String zoekterm = zoektermTextfield.getText();
            System.out.println(zoekterm);
            if(zoekterm== null)
            {
                break;
            }
            List<plant> plantenlijst = plantdao.getplantbykeuze(Keuze,zoekterm);
            showplanten("planten bij "+Keuze,plantenlijst);
            break;

        }

    }

}
