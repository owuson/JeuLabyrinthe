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

        JPanel panneau = new JPanel();
        fenetre.setContentPane(panneau);
        fenetre.setVisible(true);
        panneau.setBackground(Color.GRAY);

    }
}