package com.Labyrinthe.modele;

import javax.swing.*;

public class Soignant extends Users {

    private String email;
    private String password;
    private JTextField textMail;
    private JTextField textPassword;
    private JButton saveButton;
    private JTable table1;
    private JButton updateButton;
    private JButton deleteButton;

    public Soignant(String nom, String prenom, String email, String password) {

        super(nom, prenom);
        this.email = email;
        this.password = password;

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
