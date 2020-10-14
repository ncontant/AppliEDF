package com.example.appliedf;

public class Releve {
    protected String numcpt;
    protected String hp;
    protected String hc;
    protected String raison;

    public Releve(String numcpt, String hp, String hc, String raison) {
        this.numcpt = numcpt;
        this.hp = hp;
        this.hc = hc;
        this.raison = raison;
    }

    public String getNumcpt() {
        return numcpt;
    }

    public void setNumcpt(String numcpt) {
        this.numcpt = numcpt;
    }

    public String getHp() {
        return hp;
    }

    public void setHp(String hp) {
        this.hp = hp;
    }

    public String getHc() {
        return hc;
    }

    public void setHc(String hc) {
        this.hc = hc;
    }

    public String getRaison() {
        return raison;
    }

    public void setRaison(String raison) {
        this.raison = raison;
    }
}
