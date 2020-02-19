package main.java.model;

import java.util.Date;

public class Student {
    private Integer idStudent;
    private String naam;
    private Date geboorteDatum;

    public Student(Integer idStudent, String naam, Date geboorteDatum) {
        this.idStudent = idStudent;
        this.naam = naam;
        this.geboorteDatum = geboorteDatum;
    }

    //Constructor voor Insert : idStudent wordt gegenereerd door de database.
    public Student(String naam, Date geboorteDatum) {
        this.naam = naam;
        this.geboorteDatum = geboorteDatum;
    }

    //idStudent wordt gegenereerd door de database.
    //enkel invullen als dit nog niet gebeurd is. Primary key!
    public void setIdStudent(int idStudent) {
        if (this.idStudent != null) {
            throw new UnsupportedOperationException("Id change not permitted");
        }
        this.idStudent = idStudent;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public int getIdStudent() {
        return idStudent;
    }

    public String getNaam() {
        return naam;
    }

    public Date getGeboorteDatum() {
        return geboorteDatum;
    }

    @Override
    public String toString() {
        return "Student{" +
                "idStudent=" + idStudent +
                ", naam='" + naam + '\'' +
                ", geboorteDatum=" + geboorteDatum +
                '}';
    }
}
