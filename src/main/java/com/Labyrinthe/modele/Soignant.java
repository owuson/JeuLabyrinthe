package com.Labyrinthe.modele;

public class Soignant extends User {

    private String email;
    private String password;

    public Soignant(String nom, String prenom, String email, String password) {

        super(nom, prenom);
        this.email = email;
        this.password = password;

    }

    public Soignant(String email, String password) {
        super();
    }

    //


    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
