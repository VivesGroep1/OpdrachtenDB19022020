package main.java;

import main.java.dao.Database;
import main.java.dao.StudentDao;
import main.java.dao.plantdao;
import main.java.model.plant;
import main.java.utils.IoClass;
import main.java.model.Student;
import main.java.utils.DateUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class MainDao {
    private static final int WIJZIGFAMILIE = 1;
    private static final int WIJZIGTYPE = 2;
    private static final int WIJZIGGESLACHT = 3;
    private static final int WIJZIGSOORT = 4;
    private static final int WIJZIGVARIATIE = 5;
    private static final int WIJZIGPLANTDICHTMIN = 6;
    private static final int WIJZIGPLANTDICHTMAX = 7;
    private static final int WIJZIGFGSV = 8;
    private static final int DELETEPLANT = 9;

    private Connection dbConnection;

    public MainDao() throws SQLException {
        dbConnection = Database.getInstance().getConnection();
    }

    public static void main(String[] args) throws SQLException {
        MainDao main = new MainDao();
        main.daoDemo();
//        main.showByName();
//        main.transactionDemo();
//        main.daoDemoVak();
    }

    /**
     * Werken met de database via de DAO.
     * Deze methode vereist dat we de database Structuur kennen en over
     * model en dao classes beschikken.
     * @throws SQLException
     */
    private void daoDemo() throws SQLException {
        System.out.println("Dao Demo");

        plantdao plantdao =  new plantdao(dbConnection);
        System.out.println("in de Demo");
        List<plant>  planten = plantdao.getAllPlant();
        showplanten("test",planten);

        plant plantID =  plantdao.getplantById(23);
        showplant("plant id test",plantID);

        plant test = new plant("type","familie","geslacht","soort","variatie",5,25,"fgsv");
        plantdao.createPlant(test);

        changePlant(plantdao);


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
            System.out.println(plant.toString());
        }
        System.out.println();
    }
    public void showplant(String titel, plant plant)
    {
        System.out.println(plant.toString());
    }


    /**
     * Studenten wijzigen en verwijderen
     * @param plantdao
     * @throws SQLException
     */
    private void changePlant(plantdao plantdao) throws SQLException {
        while (true) {
            Integer id = IoClass.askForInt("Kies een plant id (Enter is Stop) : ");
            if (id == null)
                break;
            plant plant = plantdao.getplantById(id);
            if (plant != null) {
                System.out.println(plant.toString());
                Integer keuze = crudMenu();
                switch (keuze) {
                    case WIJZIGFAMILIE :
                        String nieuweFamilie = IoClass.askForString("Nieuwe familie naam : ");
                        plant.setFamilie(nieuweFamilie);
                        plantdao.updateplant(plant);
                        System.out.println(plant.toString());
                        break;
                    case WIJZIGTYPE:
                        String nieuweType = IoClass.askForString("Nieuwe Type naam : ");
                        plant.setType(nieuweType);
                        plantdao.updateplant(plant);
                        System.out.println(plant.toString());
                        break;
                    case WIJZIGGESLACHT:
                        String nieuwGeslacht = IoClass.askForString("Nieuwe geslacht naam : ");
                        plant.setGeslacht(nieuwGeslacht);
                        plantdao.updateplant(plant);
                        System.out.println(plant.toString());
                        break;
                    case WIJZIGSOORT:
                        String nieuwSoort = IoClass.askForString("Nieuwe soort naam : ");
                        plant.setSoort(nieuwSoort);
                        plantdao.updateplant(plant);
                        System.out.println(plant.toString());
                        break;
                    case WIJZIGVARIATIE:
                        String nieuwVariatie = IoClass.askForString("Nieuwe Variatie naam : ");
                        plant.setVariatie(nieuwVariatie);
                        plantdao.updateplant(plant);
                        System.out.println(plant.toString());
                        break;
                    case WIJZIGPLANTDICHTMIN:
                        int nieuwplantmin = IoClass.askForInt("Nieuwe plantdichtheid min (getal): ");
                        plant.setPlantdichtheid_min(nieuwplantmin);
                        plantdao.updateplant(plant);
                        System.out.println(plant.toString());
                        break;
                    case WIJZIGPLANTDICHTMAX:
                        int nieuwplantmax = IoClass.askForInt("Nieuwe plantdichtheid max(getal) : ");
                        plant.setPlantdichtheid_max(nieuwplantmax);
                        plantdao.updateplant(plant);
                        System.out.println(plant.toString());
                        break;
                    case WIJZIGFGSV:
                        String nieuwfgsv = IoClass.askForString("Nieuwe fgsv naam : ");
                        plant.setFgsv(nieuwfgsv);
                        plantdao.updateplant(plant);
                        System.out.println(plant.toString());
                        break;

                    case DELETEPLANT :
                        plantdao.deleteplant(plant.getPlant_id());
                        System.out.println(plant.toString());
                        break;
                }
            } else {
                System.out.println("Student met id " + id + " bestaat niet.");
            }
        }
    }

    /**
     * De gebruiker kiest een bewerking
     * Enkel antwoorden met LEGE STRING, 0, 1, 2
     * @return
     */
    private Integer crudMenu() {
        while (true) {
            System.out.println("0 : Exit");
            System.out.println("1 : Wijzig familie");
            System.out.println("2 : Wijzig type");
            System.out.println("3 : Wijzig geslacht");
            System.out.println("4 : Wijzig soort");
            System.out.println("5 : Wijzig variatie");
            System.out.println("6 : Wijzig plantdichtheid min ");
            System.out.println("7 : Wijzig plantdichtheid max");
            System.out.println("8 : Wijzig fgsv");
            System.out.println("9 : Verwijder Student");

            Integer answ = IoClass.askForInt("Maak uw keuze : ");
            if (answ == null)
                answ = 0;
            if (answ >= 0 && answ <= 9) {
                return answ;
            } else {
                System.out.println(answ + " is geen geldige keuze");
            }
        }
    }

    /**
         * @param plantdao
     * @throws SQLException
    */
    private void showByName(plantdao plantdao) throws SQLException {
        while (true) {
            System.out.println("je hoef het niet volledig te typen");
            String nieuweFamilie = IoClass.askForString("op welke familienaam wil je zoeken? : ");
            if(nieuweFamilie== null)
            {
                break;
            }
            List<plant> plantenlijst = plantdao.getplantbyname(nieuweFamilie);
            showplanten("planten bij name",plantenlijst);


        }
    }


}

