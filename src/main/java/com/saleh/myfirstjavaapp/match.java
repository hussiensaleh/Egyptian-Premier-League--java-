package com.saleh.myfirstjavaapp;

public class match {

    private String team1;
    private String team2;
    private int roundId;
    private int roundMatchId;
    private int team1Score;
    private int team2Score;
    private int team1Image;
    private int team2Image;
    private int winner;
    private String date;
    private String time;
    private String round;

    public  match( int roundId,int roundMatchId,String team1,String team2,int team1Score,int team2Score
            ,int team1Image,int team2Image,int winner,String round,String date,String time){
        this.roundId=roundId;
        this.roundMatchId=roundMatchId;
        this.team1=team1;
        this.team2=team2;
        this.team1Score=team1Score;
        this.team2Score=team2Score;
        this.team1Image=team1Image;
        this.team2Image=team2Image;
        this.winner=winner;
        this.date=date;
        this.time=time;
        this.round=round;
    }

    public int getRoundId() {
        return roundId;
    }

    public int getRoundMatchId() {
        return roundMatchId;
    }

    public String getTeam1() {
        return team1;
    }

    public String getTeam2() {
        return team2;
    }

    public int getTeam1Score() {
        return team1Score;
    }

    public int getTeam2Score() {
        return team2Score;
    }

    public int getTeam1Image() {
        return team1Image;
    }

    public int getTeam2Image() {
        return team2Image;
    }

    public int getWinner() {
        return winner;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getRound() {
        return round;
    }
}
