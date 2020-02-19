package main.java.model;

//TODO 6 : In de database een Tabel vak maken met deze kolommen
public class Vak {
    private Integer idVak;
    private String naam;
    private Integer idDocent;
    private Integer studiePunten;

    public Vak(Integer idVak, String naam, Integer idDocent, Integer studiePunten) {
        this.idVak = idVak;
        this.naam = naam;
        this.idDocent = idDocent;
        this.studiePunten = studiePunten;
    }

    //Constructor voor Insert : idStudent wordt gegenereerd door de database.
    public Vak(String naam, Integer idDocent, Integer studiePunten) {
        this.naam = naam;
        this.idDocent = idDocent;
        this.studiePunten = studiePunten;
    }

    //idStudent wordt gegenereerd door de database.
    //enkel invullen als dit nog niet gebeurd is. Primary key!
    public void setIdVak(Integer idVak) {
        if (this.idVak != null) {
            throw new UnsupportedOperationException("Id change not permitted");
        }
        this.idVak = idVak;
    }

    public Integer getIdVak() {
        return idVak;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public Integer getIdDocent() {
        return idDocent;
    }

    public void setIdDocent(Integer idDocent) {
        this.idDocent = idDocent;
    }

    public Integer getStudiePunten() {
        return studiePunten;
    }

    public void setStudiePunten(Integer studiePunten) {
        this.studiePunten = studiePunten;
    }

    @Override
    public String toString() {
        return "Vak{" +
                "idVak=" + idVak +
                ", naam='" + naam + '\'' +
                ", idDocent=" + idDocent +
                ", studiePunten=" + studiePunten +
                '}';
    }
}
