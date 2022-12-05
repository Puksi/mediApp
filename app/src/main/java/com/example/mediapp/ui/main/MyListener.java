package com.example.mediapp.ui.main;

public interface MyListener {
    public void addMedikament(Medikament medikament);
    public void setIds(int i, long l);
    public int getIdFromMain();
    public long getLIdFromMain();
    public Medikament returnMedikament(int i);
}
