package com.Labyrinthe.Jeu;

import java.util.Date;

public class Game {

    private int time;
    private int level;
    private int score;
    private int nbrSuccess;
    private Date date;

    public Game(int time, int level, int score, int nbrSuccess, Date date){

        this.time = time;
        this.level = level;
        this.score = score;
        this.nbrSuccess = nbrSuccess;
        this.date = date;
    }

    public int getTime(){
        return time;
    }

    public int getLevel(){
        return level;
    }

    public int getScore(){
        return score;
    }

    public int getNbrSuccess(){
        return nbrSuccess;
    }

    public Date date(){
        return date;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setNbrSuccess(int nbrSuccess) {
        this.nbrSuccess = nbrSuccess;
    }

    public void setDate(Date date){
        this.date = date;
    }
}
