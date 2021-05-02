package com.Labyrinthe.Jeu;

import com.Labyrinthe.affichage.Time;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.swing.*;
import java.awt.*;


public class Scene extends JPanel {

    private Time time;
    private JLabel timeLabel;

    // Constructor
    public Scene(int duree){

        super();
        time = new Time(duree);
        timeLabel = new JLabel(time.getStr(), JLabel.RIGHT);
        this.add(timeLabel, BorderLayout.EAST);
    }

    public void refresh() {
        timeLabel.setText(time.getStr());
    }

    public void restart(int duree) {
        time = new Time(duree);
    }
    public boolean is_gameover() {
        // if (time.getCompteurTemps()== 0) {
        //     return true ;
        // }  else {'('
        //     return false ;
        // }
        return (time.getCompteurTemps()== 0);
    }
}
