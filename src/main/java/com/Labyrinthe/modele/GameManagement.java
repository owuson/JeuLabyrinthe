package com.Labyrinthe.modele;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class GameManagement {

   //public game Game;
  // public patient  User;
  // public bd BDManagement;
    private final Connection conn;

   public GameManagement() throws SQLException {
       conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Labyrinthes", "root", "16*uH2aTQn^9UJUWahZn");
   }

   public void createGame (Patient){

   }

   public playGame() {


   }
}
