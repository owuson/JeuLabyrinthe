package com.Labyrinthe.modele;

import java.sql.*;
import java.util.ArrayList;

public class BDManagement {
    private final Connection conn;
    public BDManagement() throws SQLException {
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Labyrinthes", "root", "16*uH2aTQn^9UJUWahZn");
    }

    public <User> ArrayList<User> getUser() throws SQLException {
        User personne;
        ArrayList<User> listUsers = new ArrayList<User>();
        Statement mystmt = conn.createStatement();
        ResultSet rs = mystmt.executeQuery("SELECT * FROM users;");
        while(rs.next()){
            if(rs.getString("email") != null){
                personne = new Soignant(rs.getString("email"), rs.getString("password"));
            } else {
                personne = new Patient(rs.getString("user_name"), rs.getString("user_lastname"));
            }

            listUsers.add(personne);
        }

        return listUsers;
    }
}
