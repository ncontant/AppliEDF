package com.example.appliedf;

public class Client {
    protected String nomPrenom;
    protected String email;
    protected String adresse;
    protected String tel;

    public Client(String nomPrenom, String email, String adresse, String tel) {
        this.nomPrenom = nomPrenom;
        this.email = email;
        this.adresse = adresse;
        this.tel = tel;
    }

    public String getNomPrenom() {
        return nomPrenom;
    }

    public void setNomPrenom(String nomPrenom) {
        this.nomPrenom = nomPrenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
