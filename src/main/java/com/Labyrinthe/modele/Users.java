package com.Labyrinthe.modele;

public class Users {

    private String nom;
    private String prenom;

    public Users(String nom, String prenom){

        this.prenom = prenom;
        this.nom = nom;

    }

    public Users() {

    }


    public String getNom(){

        return nom;
    }

    public String getPrenom(){

        return prenom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom){
        this.prenom = prenom;
    }
}
