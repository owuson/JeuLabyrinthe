package com.Labyrinthe.modele;

import java.sql.*;
import java.util.ArrayList;

public class BDManagement {

    private final Connection conn;

    public BDManagement() throws SQLException {
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Labyrinthes", "root", "16*uH2aTQn^9UJUWahZn");
    }

    public ArrayList<Users> getUsers() throws SQLException {
        Users personne;
        ArrayList<Users> listUsers = new ArrayList<Users>();
        Statement stat = conn.createStatement();
        ResultSet rs = stat.executeQuery("SELECT * FROM users;");

        while (rs.next()) {
            if (rs.getString("email") != null) {
                personne = new Soignant(rs.getString("email"), rs.getString("password"));
            } else {
                personne = new Patient(rs.getString("pseudo"), rs.getString("age"));
            }

            listUsers.add(personne);

        }

        return listUsers;

        }
    }
