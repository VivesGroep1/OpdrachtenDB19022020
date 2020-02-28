package test;

import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

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

    public void ZoekButton_clicked(MouseEvent mouseEvent) {
        controleKeuzeZoekterm();
        ZetZoekinfo();
    }
    public void ZetZoekinfo()
    {
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
        }
        if(TypeRadiobutton.isSelected())
        {
            keuzenummer=2;
        }
        if(GeslcahtRadioButton.isSelected())
        {
            keuzenummer=3;
        }
        if(SoortRadioButton.isSelected())
        {
            keuzenummer=4;
        }
        if(variatieRadioButton.isSelected())
        {
            keuzenummer=5;
        }
        if(plantdichtheidMinRadiobutton.isSelected())
        {
            keuzenummer=6;
        }
        if(plantdichtheidMaxRadiobutton.isSelected())
        {
            keuzenummer=7;
        }
        if(fgsvRadiobutton.isSelected())
        {
            keuzenummer=8;
        }
        return keuzenummer;
    }
}
