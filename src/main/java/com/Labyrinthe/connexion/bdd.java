package com.Labyrinthe.connexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class bdd {


    public static void main(String[] args) {
        try {
            Connection myconn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Labyrinthe","root","rootroot");
            Statement mystmt = myconn.createStatement();
            ResultSet myrs = mystmt.executeQuery("SELECT * FROM user ");
            while (myrs.next()){
                System.out.println(myrs.getString("pseudo")+ ", " +myrs.getString("username"));
            }

        }catch(Exception exc)
            {
                exc.printStackTrace();

            }

        
    }

}
