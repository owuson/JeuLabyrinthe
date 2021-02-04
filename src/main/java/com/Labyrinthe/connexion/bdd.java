package com.Labyrinthe.connexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class bdd {


    public static void main(String[] args) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Labyrinthe", "root", "rootroot");
            Statement mystmt = conn.createStatement();
            ResultSet resultat = mystmt.executeQuery("SELECT * FROM user");
            while (resultat.next()) {
                System.out.println(resultat.getInt("userId")+ resultat.getString("email") + resultat.getString("password") +resultat.getDate("userBirthday") +resultat.getString("pseudo") + ", " + resultat.getString("userName"));
            }

        } catch (Exception exc) {
            exc.printStackTrace();

        }


    }

}
