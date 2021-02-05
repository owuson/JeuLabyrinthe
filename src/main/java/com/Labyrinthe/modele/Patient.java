package com.Labyrinthe.modele;

public class Patient extends Users {

    private String pseudo;
    private int age;

    public Patient(String nom, String prenom, String pseudo, int age) {
        super(nom, prenom);
        this.pseudo = pseudo;
        this.age = age;

    }

    public Patient(String user_name, String user_lastname) {
        super();
    }

    public String getPseudo(){
        return pseudo;
    }

    public int getAge(){
        return age;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
