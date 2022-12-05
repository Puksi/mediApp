package com.example.mediapp.ui.main;

public class Medikament {
    private String medikament_name;
    private int uhrzeit;
    private int anzahl_medikamente;
    private String kommentar;

    public int getUhrzeit() {
        return uhrzeit;
    }

    public void setUhrzeit(int uhrzeit) {
        this.uhrzeit = uhrzeit;
    }

    public int getAnzahl_medikamente() {
        return anzahl_medikamente;
    }

    public void setAnzahl_medikamente(int anzahl_medikamente) {
        this.anzahl_medikamente = anzahl_medikamente;
    }

    public String getKommentar() {
        return kommentar;
    }

    public void setKommentar(String kommentar) {
        this.kommentar = kommentar;
    }

    public String getMedikament_name() {
        return medikament_name;
    }

    public void setMedikament_name(String medikament_name) {
        this.medikament_name = medikament_name;
    }

    public Medikament(String medikament_name) {
        this.medikament_name = medikament_name;
    }

    public Medikament(String medikament_name, int uhrzeit, int anzahl_medikamente, String kommentar) {
        this.medikament_name = medikament_name;
        this.uhrzeit = uhrzeit;
        this.anzahl_medikamente = anzahl_medikamente;
        this.kommentar = kommentar;
    }
}
