package main.java.dao;

import main.java.model.Vak;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class VakDao {
    //TODO 7 De SQL queries ingeven
    private static final String GETALLVAK = "";
    private static final String GETVAKBYID = "";
    private static final String INSERTVAK = "";
    private static final String UPDATEVAK =
            "UPDATE vak SET naam = ?, idDocent = ?, studiePunten = ? WHERE idVak = ?";
    private static final String DELETEVAK =  "";

    private Connection dbConnection;

    private PreparedStatement stmtSelectById;
    private PreparedStatement stmtInsert;
    private PreparedStatement stmtUpdate;
    private PreparedStatement stmtDelete;

    public VakDao(Connection dbConnection) throws SQLException {
        //TODO 8 Constructor VakDao() vervolledigen
        //  * Connectie bijghouden
        //  * Alle preparedStatements initialiseren
    }

    public List<Vak> getAllVak() {
        List<Vak> vakList = new ArrayList<>();
        //TODO 9 : Vervolledig methode getAllVak met Statement
        return vakList;
    }

    public Vak getVakById(Integer idVak) {
        Vak vak = null;
        //TODO 10 : Vervolledig methode getVakById met PreparedStatement stmtSelectById
        return vak;
    }

    public void createVak(Vak vak) {
        //TODO 11 : Vervolledig methode createVak met PreparedStatement stmtInsert
        //Vergeet niet de id gegenereerd door de database op te vragen
    }

    public Integer updateVak(Vak vak) throws SQLException {
        //            "UPDATE vak SET naam = ?, idDocent = ?, studiePunten = ? WHERE idVak = ?";
        stmtUpdate.setString(1, vak.getNaam());
        stmtUpdate.setInt(2, vak.getIdDocent());
        stmtUpdate.setInt(3, vak.getStudiePunten());
        stmtUpdate.setInt(4, vak.getIdVak());
        return stmtUpdate.executeUpdate();
    }

    public Integer deleteVak(Integer idVak) throws SQLException {
        //TODO 12 : Vervolledig methode deleteVak met PreparedStatement stmtDelete
        return 0;
    }

}
