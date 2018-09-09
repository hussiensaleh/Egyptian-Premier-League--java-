package com.saleh.myfirstjavaapp;

public class tableTeam {

     int rank         ;
     String name      ;
     int playedMatches;
     int win          ;
     int lose         ;
     int draw         ;
     int plus         ;
     int minus        ;
     int farq         ;
     int points       ;
    public tableTeam(int rank,String name,int playedMatches,int win  ,int draw ,int lose ,int plus ,int minus,int farq ,int points){
        this.rank  =rank;
        this.name  =name;
        this.playedMatches=playedMatches;
        this.win   =win;
        this.lose  =lose;
        this.draw  =draw;
        this.plus  =plus;
        this.minus =minus;
        this.farq  =farq;
        this.points=points;
    }

    public int getRank() {
        return rank;
    }

    public int getPlayedMatches() {
        return playedMatches;
    }

    public String getName() {
        return name;
    }

    public int getWin() {
        return win;
    }

    public int getLose() {
        return lose;
    }

    public int getDraw() {
        return draw;
    }

    public int getMinus() {
        return minus;
    }

    public int getFarq() {
        return farq;
    }

    public int getPlus() {
        return plus;
    }

    public int getPoints() {
        return points;
    }

}
