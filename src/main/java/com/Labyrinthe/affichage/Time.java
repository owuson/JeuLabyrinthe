package com.Labyrinthe.affichage;

public class Time implements Runnable{

    private final int PAUSE = 1000;
    private int compteurTemps;
    private String str;

    public Time () {

        this.compteurTemps = 500;
        this.str = "Temps restant : 500";

        Thread Time = new Thread(this);
        Time.start();
    }

    public int getCompteurTemps() {

        return compteurTemps;
    }

    public String getStr() {

        return str;
    }

    @Override
    public void run() {

        while (true) {
            try {
                Thread.sleep(PAUSE);
            }
            
            catch (InterruptedException e){}

            this.compteurTemps--;
            this.str = "Temps restant : " + this.compteurTemps;
        }
    }
}
