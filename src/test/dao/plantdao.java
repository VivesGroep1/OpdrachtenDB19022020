package test.dao;

import main.java.dao.StudentDao;
import main.java.model.plant;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class plantdao {
    private static final String GETALLPLANTEN =
            "SELECT * FROM plant";
    private static final String GETPLANTBYID =
            "SELECT * FROM plant WHERE plant_id = ?";
    private static final String INSERTPLANT =
            "INSERT INTO plant (type, familie,geslacht,soort,variatie,plantdichtheid_min,plantdichtheid_max,fgsv) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATEPLANT =
            "UPDATE plant SET  type=? ,familie = ?, geslacht = ?, soort = ?, variatie = ?, plantdichtheid_min = ?, plantdichtheid_max = ?, fgsv = ? WHERE plant_id = ?";
    private static final String DELETEPLANT =
            "DELETE FROM plant WHERE plant_id = ?";
    private static final String GETPLANTBYNAME =
            "SELECT * FROM plant WHERE familie LIKE ?";

    private Connection dbConnection;

    private PreparedStatement stmtSelectById;
    private PreparedStatement stmSelectAll;
    private PreparedStatement stmtInsert;
    private PreparedStatement stmtUpdate;
    private PreparedStatement stmtDelete;
    private PreparedStatement stmgetbyname;


    public plantdao(Connection dbConnection) throws SQLException {
        this.dbConnection = dbConnection;
        stmtSelectById = dbConnection.prepareStatement(GETPLANTBYID);
        stmSelectAll = dbConnection.prepareStatement(GETALLPLANTEN);
        stmtInsert =   dbConnection.prepareStatement(INSERTPLANT,
                Statement.RETURN_GENERATED_KEYS);
        stmtUpdate     = dbConnection.prepareStatement(UPDATEPLANT);
        stmtDelete     = dbConnection.prepareStatement(DELETEPLANT);
        stmgetbyname = dbConnection.prepareStatement(GETPLANTBYNAME);
    }
    public List<plant> getAllPlant() {
        List<plant> plantenlijst = new ArrayList<>();
        try {
            Statement stmt = dbConnection.createStatement();
            ResultSet rs = stmt.executeQuery(GETALLPLANTEN);
            while (rs.next()) {
                plant plant =
                        new plant(rs.getInt("plant_id"),
                                rs.getString("type"),
                                rs.getString("familie"),
                                rs.getString("geslacht"),
                                rs.getString("soort"),
                                rs.getString("variatie"),
                                rs.getInt("plantdichtheid_min"),
                                rs.getInt("plantdichtheid_max"),
                                rs.getString("fgsv"));
                plantenlijst.add(plant);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("in de catch");
        }
        return plantenlijst;
    }

     //vragen waarom dit niet werkt
    public plant getplantById(Integer plant_id) throws SQLException {
        plant plant = null;
         stmtSelectById.setInt(1, plant_id);
        ResultSet rs = stmtSelectById.executeQuery();
        if (rs.next()) {
            plant = new plant(rs.getInt("plant_id"),
                    rs.getString("type"),
                    rs.getString("familie"),
                    rs.getString("geslacht"),
                    rs.getString("soort"),
                    rs.getString("variatie"),
                    rs.getInt("plantdichtheid_min"),
                    rs.getInt("plantdichtheid_max"),
                    rs.getString("fgsv"));
        }
        return plant;
    }

    public void createPlant(plant plant) throws SQLException {
//       stmtInsert.setInt(1, 60); // hulp vragen bij functie om max id te vragen...
        stmtInsert.setString(1, plant.getType());
        stmtInsert.setString(2,plant.getFamilie());
        stmtInsert.setString(3,plant.getGeslacht());
        stmtInsert.setString(4,plant.getSoort());
        stmtInsert.setString(5,plant.getVariatie());
        stmtInsert.setInt(6,plant.getPlantdichtheid_min());
        stmtInsert.setInt(7,plant.getPlantdichtheid_max());
        stmtInsert.setString(8,plant.getFgsv());
        stmtInsert.executeUpdate();
        System.out.println("gelukt");
    }

    public Integer updateplant(plant plant) throws SQLException {
        stmtUpdate.setString(1, plant.getType());
        stmtUpdate.setString(2,plant.getFamilie());
        stmtUpdate.setString(3,plant.getGeslacht());
        stmtUpdate.setString(4,plant.getSoort());
        stmtUpdate.setString(5,plant.getVariatie());
        stmtUpdate.setInt(6,plant.getPlantdichtheid_min());
        stmtUpdate.setInt(7,plant.getPlantdichtheid_max());
        stmtUpdate.setString(8,plant.getFgsv());
        stmtUpdate.setInt(9,plant.getPlant_id());
        return stmtUpdate.executeUpdate();
    }
    public Integer deleteplant(Integer plant_ID) throws SQLException {
        stmtDelete.setInt(1, plant_ID);
        return stmtDelete.executeUpdate(); //Aantal gewist.
    }

    public List<plant> getplantbyname(String naam) throws SQLException {
        List<plant> plantenlijst = new ArrayList<>();
        //TODO 4 : Vervolledig de methode getStudentByNaam(String naam) en gebruik de query GETSTUDENTBYNAAM
        //WHERE naam LIKE ?
        //=> Zorg ervoor dat als de parameter naam bijvoorbeeld "der" is,
        //dat de parameter "*der*" wordt, zodat de gebruiker niet zelf de * moet ingeven
        naam = "%" + naam + "%";
        try {
            System.out.println("in de try");
            System.out.println(naam);
            stmgetbyname.setString(1,naam);
            ResultSet rss = stmgetbyname.executeQuery();
            while (rss.next()) {
                plant plant =
                        new plant(rss.getInt("plant_id"),
                                rss.getString("type"),
                                rss.getString("familie"),
                                rss.getString("geslacht"),
                                rss.getString("soort"),
                                rss.getString("variatie"),
                                rss.getInt("plantdichtheid_min"),
                                rss.getInt("plantdichtheid_max"),
                                rss.getString("fgsv"));
                plantenlijst.add(plant);

            }
        } catch (SQLException ex) {
            System.out.println("in de catch");
            Logger.getLogger(StudentDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("in de catch");
        }

        return plantenlijst;
    }

}
