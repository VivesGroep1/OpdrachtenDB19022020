package test.dao;

public class plant {
    private Integer plant_id;
    private String Type;
    private String familie;
    private String geslacht;
    private String soort;
    private String variatie;
    private Integer plantdichtheid_min;
    private Integer plantdichtheid_max;
    private String fgsv;

    public plant(Integer plant_id, String type, String familie, String geslacht, String soort, String variatie, Integer plantdichtheid_min, Integer plantdichtheid_max, String fgsv) {
        this.plant_id = plant_id;
        Type = type;
        this.familie = familie;
        this.geslacht = geslacht;
        this.soort = soort;
        this.variatie = variatie;
        this.plantdichtheid_min = plantdichtheid_min;
        this.plantdichtheid_max = plantdichtheid_max;
        this.fgsv = fgsv;
    }
    public plant(String type, String familie, String geslacht, String soort, String variatie, Integer plantdichtheid_min, Integer plantdichtheid_max, String fgsv) {
        Type = type;
        this.familie = familie;
        this.geslacht = geslacht;
        this.soort = soort;
        this.variatie = variatie;
        this.plantdichtheid_min = plantdichtheid_min;
        this.plantdichtheid_max = plantdichtheid_max;
        this.fgsv = fgsv;
    }

    public Integer getPlant_id() {
        return plant_id;
    }

    public void setPlant_id(Integer plant_id) {
        this.plant_id = plant_id;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getFamilie() {
        return familie;
    }

    public void setFamilie(String familie) {
        this.familie = familie;
    }

    public String getGeslacht() {
        return geslacht;
    }

    public void setGeslacht(String geslacht) {
        this.geslacht = geslacht;
    }

    public String getSoort() {
        return soort;
    }

    public void setSoort(String soort) {
        this.soort = soort;
    }

    public String getVariatie() {
        return variatie;
    }

    public void setVariatie(String variatie) {
        this.variatie = variatie;
    }

    public Integer getPlantdichtheid_min() {
        return plantdichtheid_min;
    }

    public void setPlantdichtheid_min(Integer plantdichtheid_min) {
        this.plantdichtheid_min = plantdichtheid_min;
    }

    public Integer getPlantdichtheid_max() {
        return plantdichtheid_max;
    }

    public void setPlantdichtheid_max(Integer plantdichtheid_max) {
        this.plantdichtheid_max = plantdichtheid_max;
    }

    public String getFgsv() {
        return fgsv;
    }

    public void setFgsv(String fgsv) {
        this.fgsv = fgsv;
    }
    @Override
    public String toString() {
        return "plant{" +
                "plant_id =" + plant_id +
                ", type ='" + Type + '\'' +
                ", familie =" + familie + '\'' +
                ", geslacht =" + geslacht + '\'' +
                ", soort =" + soort + '\'' +
                ", variatie =" + variatie + '\'' +
                ", plantdichtheid min =" + plantdichtheid_min + '\'' +
                ", plantdichtheid max =" + plantdichtheid_max + '\'' +
                ", fgsv=" + fgsv +
                '}';
    }

}
