package com.Labyrinthe.connexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class bdd {


    public static void main(String[] args) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Labyrinthes", "root", "16*uH2aTQn^9UJUWahZn");
            Statement mystmt = conn.createStatement();
            //String sql = "INSERT INTO users (user_name, pseudo, password, email)" + "VALUES ('toto', 'pop', 'tutrouverasjamais', 'tetet@hfhfh.g')";
            //String sql = "UPDATE users SET pseudo = 'jonny' WHERE user_name = 'toto'";
            //String sql = "DELETE FROM users WHERE user_name = 'toto'";
            String sql = "SELECT * FROM users";
            ResultSet result = mystmt.executeQuery(sql);
            while (result.next()) {
                String username = result.getString(2);
                System.out.println(username);
            }
            conn.close();
        } catch (Exception exc) {
            exc.printStackTrace();

        }


    }

}
