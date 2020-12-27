package com.Labyrinthe.Jeu;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static Scene scene;


    public static void main(String[] args){
        // Creation de la fenêtre graphique de l'application
        JFrame fenetre = new JFrame("Jeu du labyrinthe");
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.setSize(1400, 720);
        fenetre.setLocationRelativeTo(null);
        fenetre.setResizable(true);
        fenetre.setAlwaysOnTop(true);


        scene = new Scene();

        fenetre.setContentPane(scene); // La fenêtre est associé à la scene du jeu
        fenetre.setVisible(true);
    }

    public void paintComponent(Graphics g){

        //super.paintComponent(g);
        Graphics g2 = (Graphics2D)g;

        /*
         g2.drawImage();
         g2.drawImage();
        */
    }
}
