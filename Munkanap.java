package com.trevott.fizu;

public class Munkanap {
    private int év;
    private int hónap;
    private int nap;
    private String napNeve;
    private String napTípusa;
    private String napKezdete;
    private String napVége;
    private String túlóraKezdete;
    private String túlóraVége;
    private double fizetés;
    private double munkaÓra;

    public Munkanap(int év, int hónap, int nap, String napNeve, String napTípusa, String napKezdete, String napVége, String túlóraKezdete, String túlóraVége, double fizetés,
                    double munkaÓra) {
        this.év = év;
        this.hónap = hónap;
        this.nap = nap;
        this.napNeve = napNeve;
        this.napTípusa = napTípusa;
        this.napKezdete = napKezdete;
        this.napVége = napVége;
        this.túlóraKezdete = túlóraKezdete;
        this.túlóraVége = túlóraVége;
        this.fizetés = fizetés;
        this.munkaÓra = munkaÓra;
    }

    public String getÉv() {
        return Integer.toString(év);
    }

    public String getHónap() {
        return Integer.toString(hónap);
    }

    public String getNap() {
        return Integer.toString(nap);
    }

    public String getNapNeve() {
        return napNeve;
    }

    public String getNapTípusa() {
        return napTípusa;
    }

    public String getNapKezdete() {
        return napKezdete;
    }

    public String getNapVége() {
        return napVége;
    }

    public String getTúlóraKezdete() {
        return túlóraKezdete;
    }

    public String getTúlóraVége() {
        return túlóraVége;
    }

    public void setFizetés(int fizetés) {
        this.fizetés = fizetés;
    }

    public String getFizetés() {
        return Double.toString(fizetés);
    }

    public  String getMunkaÓra() {
        return Double.toString(munkaÓra);
    }

    public void setÉv(int év) {
        this.év = év;
    }

    public void setHónap(int hónap) {
        this.hónap = hónap;
    }

    public void setNap(int nap) {
        this.nap = nap;
    }

    public void setNapNeve(String napNeve) {
        this.napNeve = napNeve;
    }

    public void setNapTípusa(String napTípusa) {
        this.napTípusa = napTípusa;
    }

    public void setNapKezdete(String napKezdete) {
        this.napKezdete = napKezdete;
    }

    public void setNapVége(String napVége) {
        this.napVége = napVége;
    }

    public void setTúlóraKezdete(String túlóraKezdete) {
        this.túlóraKezdete = túlóraKezdete;
    }

    public void setTúlóraVége(String túlóraVége) {
        this.túlóraVége = túlóraVége;
    }
}
