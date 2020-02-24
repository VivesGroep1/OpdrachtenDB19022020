package main.java.dao;

import main.java.model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StudentDao {
    private static final String GETALLSTUDENT =
            "SELECT * FROM student";
    private static final String GETSTUDENTBYID =
            "SELECT * FROM student WHERE idStudent = ?";
    private static final String INSERTSTUDENT =
            "INSERT INTO student (naam, geboortedatum) VALUES (?, ?)";
    private static final String UPDATESTUDENT =
            "UPDATE student SET naam = ?, geboortedatum = ? WHERE idStudent = ?";
    private static final String DELETESTUDENT =
            "DELETE FROM student WHERE idStudent = ?";
    private static final String GETSTUDENTBYNAAM =
            "SELECT * FROM student WHERE naam LIKE ?";

    private Connection dbConnection;

    private PreparedStatement stmtSelectById;
    private PreparedStatement stmtInsert;
    private PreparedStatement stmtUpdate;
    private PreparedStatement stmtDelete;

    public StudentDao(Connection dbConnection) throws SQLException {
        this.dbConnection = dbConnection;
        stmtSelectById = dbConnection.prepareStatement(GETSTUDENTBYID);
        stmtInsert =   dbConnection.prepareStatement(INSERTSTUDENT,
                                              Statement.RETURN_GENERATED_KEYS);
        stmtUpdate     = dbConnection.prepareStatement(UPDATESTUDENT);
        stmtDelete     = dbConnection.prepareStatement(DELETESTUDENT);
    }

    public List<Student> getAllStudent() {
        List<Student> studentList = new ArrayList<>();
        try {
            Statement stmt = dbConnection.createStatement();
            ResultSet rs = stmt.executeQuery(GETALLSTUDENT);
            while (rs.next()) {
                Student student =
                    new Student(rs.getInt("idStudent"),
                                rs.getString("Naam"),
                                rs.getDate("GeboorteDatum"));
                studentList.add(student);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return studentList;
    }

    public Student getStudentById(Integer idStudent) throws SQLException {
        Student student = null;
        stmtSelectById.setInt(1, idStudent);
        ResultSet rs = stmtSelectById.executeQuery();
        if (rs.next()) {
            student = new Student(rs.getInt("idStudent"),
                                  rs.getString("Naam"),
                                  rs.getDate("GeboorteDatum"));
        }
        return student;
    }

    public void createStudent(Student student) throws SQLException {
        stmtInsert.setString(1, student.getNaam());
        stmtInsert.setDate(2,new Date(student.getGeboorteDatum().getTime()));
        stmtInsert.executeUpdate();
        ResultSet rs = stmtInsert.getGeneratedKeys();
        rs.next();
        Integer idStudent = rs.getInt(1);
        student.setIdStudent(idStudent);
    }

    public Integer updateStudent(Student student) throws SQLException {
        stmtUpdate.setString(1, student.getNaam());
        stmtUpdate.setDate(2, (Date) student.getGeboorteDatum());
        stmtUpdate.setInt(3, student.getIdStudent());
        return stmtUpdate.executeUpdate();
    }

    public Integer deleteStudent(Integer idStudent) throws SQLException {
        stmtDelete.setInt(1, idStudent);
        return stmtDelete.executeUpdate(); //Aantal gewist.
    }

    public List<Student> getStudentByNaam(String naam) {
        List<Student> studentList = new ArrayList<>();
        //TODO 4 : Vervolledig de methode getStudentByNaam(String naam) en gebruik de query GETSTUDENTBYNAAM
        //WHERE naam LIKE ?
        //=> Zorg ervoor dat als de parameter naam bijvoorbeeld "der" is,
        //dat de parameter "*der*" wordt, zodat de gebruiker niet zelf de * moet ingeven
        return studentList;
    }

}
