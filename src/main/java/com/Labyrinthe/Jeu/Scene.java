package com.Labyrinthe.Jeu;

import com.Labyrinthe.affichage.Time;

import javax.swing.*;

public class Scene extends JPanel {

    private Time time;
    // Constructor
    public Scene(){

        super();
        time = new Time();
    }

    //g2.drawString(this.time.toStr(), 5, 25)
}
