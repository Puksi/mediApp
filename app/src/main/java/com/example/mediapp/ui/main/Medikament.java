package com.example.mediapp.ui.main;

import java.util.Date;

public class Medikament {
    String medikament_anzeige;
    private String medikament_name;
    private boolean imKalender = false;
    private boolean einnahme_frueh = false;
    private boolean einnahme_mittag = false;
    private boolean einnahme_abends = false;
    private int anzahl_medikamente = 0;
    private String kommentar = "";
    private Date ZeitEingenommen;

    public Date getZeitEingenommen() {
        return ZeitEingenommen;
    }

    public void setZeitEingenommen(Date zeitEingenommen) {
        ZeitEingenommen = zeitEingenommen;
    }
// TODO: 07/12/2022 kommentar fehlt hier

    public boolean isImKalender() {
        return imKalender;
    }

    public void setImKalender(boolean imKalender) {
        this.imKalender = imKalender;
    }

    public boolean isEinnahme_frueh() {
        return einnahme_frueh;
    }

    public void setEinnahme_frueh(boolean einnahme_frueh) {
        this.einnahme_frueh = einnahme_frueh;
    }

    public boolean isEinnahme_mittag() {
        return einnahme_mittag;
    }

    public void setEinnahme_mittag(boolean einnahme_mittag) {
        this.einnahme_mittag = einnahme_mittag;
    }

    public boolean isEinnahme_abends() {
        return einnahme_abends;
    }

    public void setEinnahme_abends(boolean einnahme_abends) {
        this.einnahme_abends = einnahme_abends;
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

    public Medikament(String medikament_anzeige, String medikament_name) {
        this.medikament_anzeige = medikament_anzeige;
        this.medikament_name = medikament_name;
    }

    public String getMedikament_anzeige() {
        return medikament_anzeige;
    }

    public void setMedikament_anzeige(String medikament_anzeige) {
        this.medikament_anzeige = medikament_anzeige;
    }

    public Medikament(String medikament_anzeige, String medikament_name, boolean imKalender, boolean einnahme_frueh, boolean einnahme_mittag, boolean einnahme_abends, int anzahl_medikamente, String kommentar) {
        this.medikament_anzeige = medikament_anzeige;
        this.medikament_name = medikament_name;
        this.imKalender = imKalender;
        this.einnahme_frueh = einnahme_frueh;
        this.einnahme_mittag = einnahme_mittag;
        this.einnahme_abends = einnahme_abends;
        this.anzahl_medikamente = anzahl_medikamente;
        this.kommentar = kommentar;
    }

    @Override
    public String toString() {
        return this.medikament_name;
    }
}
