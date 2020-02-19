package main.java;

import main.java.dao.Database;
import main.java.dao.StudentDao;
import main.java.utils.IoClass;
import main.java.model.Student;
import main.java.utils.DateUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class MainDao {
    private static final int WIJZIGNAAM = 1;
    private static final int DELETESTUDENT = 2;

    private Connection dbConnection;

    public MainDao() throws SQLException {
        dbConnection = Database.getInstance().getConnection();
    }

    public static void main(String[] args) throws SQLException {
        MainDao main = new MainDao();
        main.daoDemo();
        main.showByName();
        main.transactionDemo();
        main.daoDemoVak();
    }

    /**
     * Werken met de database via de DAO.
     * Deze methode vereist dat we de database Structuur kennen en over
     * model en dao classes beschikken.
     * @throws SQLException
     */
    private void daoDemo() throws SQLException {
        System.out.println("Dao Demo");
        StudentDao studentDao = new StudentDao(dbConnection);

        List<Student> studenten = studentDao.getAllStudent();
        showStudents("Opstart Programma", studenten);

        createStudents(studentDao);

        studenten = studentDao.getAllStudent();
        showStudents("Na toevoegen Studenten", studenten);

        changeStudents(studentDao);

        studenten = studentDao.getAllStudent();
        showStudents("Na update Studenten", studenten);
    }

    /** BEGIN HulpMethoden voor daoDemo **/
    /**
     * Print de lijst van de studenten uit
     * @param titel
     * @param studenten
     */
    public void showStudents(String titel, List<Student> studenten) {
        System.out.println("Lijst Studenten : " + titel);
        for (Student student : studenten) {
            System.out.println(student.toString());
        }
        System.out.println();
    }

    /**
     * Maakt Studenten aan en plaatst ze in de database
     * @param studentDao
     * @throws SQLException
     */
    private void createStudents(StudentDao studentDao) throws SQLException {
        Student student1 = new Student("Albert",
                                DateUtils.dateFromYMD(1879, 3, 14));
        studentDao.createStudent(student1);
        Student student2 = new Student("Richard",
                                DateUtils.dateFromYMD(1918, 5, 11));
        studentDao.createStudent(student2);
    }

    /**
     * Studenten wijzigen en verwijderen
     * @param studentDao
     * @throws SQLException
     */
    private void changeStudents(StudentDao studentDao) throws SQLException {
        while (true) {
            Integer id = IoClass.askForInt("Kies een student id (Enter is Stop) : ");
            if (id == null)
                break;
            Student student = studentDao.getStudentById(id);
            if (student != null) {
                System.out.println(student.toString());
                Integer keuze = crudMenu();
                switch (keuze) {
                    case WIJZIGNAAM :
                        String nieuweNaam = IoClass.askForString("Nieuwe naam : ");
                        student.setNaam(nieuweNaam);
                        studentDao.updateStudent(student);
                        System.out.println(student.toString());
                        break;
                    case DELETESTUDENT :
                        studentDao.deleteStudent(student.getIdStudent());
                        System.out.println(student.toString());
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
            System.out.println("1 : Wijzig Naam");
            System.out.println("2 : Verwijder Student");
            Integer answ = IoClass.askForInt("Maak uw keuze : ");
            if (answ == null)
                answ = 0;
            if (answ >= 0 && answ <= 2) {
                return answ;
            } else {
                System.out.println(answ + " is geen geldige keuze");
            }
        }
    }
    /** END   HulpMethoden voor daoDemo **/

    private void transactionDemo() {
        try {
            System.out.println("Dao Demo");
            StudentDao studentDao = new StudentDao(dbConnection);
            dbConnection.setAutoCommit(false);

            Student student1 = new Student("Jan",
                              DateUtils.dateFromYMD(2000, 3, 14));
            studentDao.createStudent(student1);

            Student student2 = new Student("Deze naam is veel te lang",
                    DateUtils.dateFromYMD(2000, 1, 1));
            studentDao.createStudent(student2);

            dbConnection.commit();
            System.out.println("Save Succesfull");
        } catch (SQLException e) {
            try {
                System.out.println(e.getMessage());
                dbConnection.rollback();
                System.out.println("Save rolled Back");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    //TODO 5 : Vervolledig de methode showByName()
    /**
     * Vraag de gebruiker om de naam van de student. Deze mag onvolledig zijn omdat
     *    de query LIKE gebruikt.
     * Zolang de gebruiker iets ingeeft
     *    => haal je de lijst van studenten op met StudentDao.getStudentByNaam(String naam)
     *    => Toon je de lijst van studenten met deze naam
     * Als de gebruiker een lege String ingeeft verlaat je de methode.
     */
    private void showByName() {
    }

    //TODO 13 : Vervolledig de methode daoDemoVak()
    // * Enkele vakken aanmaken en bewaren in de database
    // * een vak meer studiepunten geven
    // * een vak verwijderen
    // * de lijst van vakken tonen
    private void daoDemoVak() {
    }

}

