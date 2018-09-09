package com.saleh.myfirstjavaapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;

public class TableActivity extends AppCompatActivity {

    ArrayList<tableTeam> tableTeams = new ArrayList<>();
    tableAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);
        //Up Button
        Button upBu = findViewById(R.id.table_home);
        upBu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TableActivity.this.finish();
            }
        });
        AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        tableTeams = teams();
        addToDb(setTable(loadFromDatabase()));

        adapter = new tableAdapter(this, getTableFromDB());
        ListView listView = findViewById(R.id.table_listView);
        listView.setAdapter(adapter);

    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    public ArrayList<match> loadFromDatabase() {
        dbManager dbManager = new dbManager(this);
        return dbManager.getAllmatches();
    }

    public ArrayList<tableTeam> teams() {

        ArrayList<tableTeam> Teams = new ArrayList<>();
        Teams.add(new tableTeam(1, GS(R.string.zmlk), 0, 0, 0, 0, 0, 0, 0, 0));
        Teams.add(new tableTeam(2, GS(R.string.ahly), 0, 0, 0, 0, 0, 0, 0, 0));
        Teams.add(new tableTeam(3, GS(R.string.ptrj), 0, 0, 0, 0, 0, 0, 0, 0));
        Teams.add(new tableTeam(4, GS(R.string.ithd), 0, 0, 0, 0, 0, 0, 0, 0));
        Teams.add(new tableTeam(5, GS(R.string.mqsa), 0, 0, 0, 0, 0, 0, 0, 0));
        Teams.add(new tableTeam(6, GS(R.string.ngom), 0, 0, 0, 0, 0, 0, 0, 0));
        Teams.add(new tableTeam(7, GS(R.string.enpi), 0, 0, 0, 0, 0, 0, 0, 0));
        Teams.add(new tableTeam(8, GS(R.string.tlae), 0, 0, 0, 0, 0, 0, 0, 0));
        Teams.add(new tableTeam(9, GS(R.string.smha), 0, 0, 0, 0, 0, 0, 0, 0));
        Teams.add(new tableTeam(10, GS(R.string.marb), 0, 0, 0, 0, 0, 0, 0, 0));
        Teams.add(new tableTeam(11, GS(R.string.prmd), 0, 0, 0, 0, 0, 0, 0, 0));
        Teams.add(new tableTeam(12, GS(R.string.isml), 0, 0, 0, 0, 0, 0, 0, 0));
        Teams.add(new tableTeam(13, GS(R.string.msry), 0, 0, 0, 0, 0, 0, 0, 0));
        Teams.add(new tableTeam(14, GS(R.string.dgla), 0, 0, 0, 0, 0, 0, 0, 0));
        Teams.add(new tableTeam(15, GS(R.string.hars), 0, 0, 0, 0, 0, 0, 0, 0));
        Teams.add(new tableTeam(16, GS(R.string.entg), 0, 0, 0, 0, 0, 0, 0, 0));
        Teams.add(new tableTeam(17, GS(R.string.dkhl), 0, 0, 0, 0, 0, 0, 0, 0));
        Teams.add(new tableTeam(18, GS(R.string.gona), 0, 0, 0, 0, 0, 0, 0, 0));
        return Teams;
    }

    public ArrayList<tableTeam> setTable(ArrayList<match> matches) {
        for (int iT = 0; iT < tableTeams.size(); iT++) {
            for (int i = 0; i < matches.size(); i++) {
                tableTeam tableTeam = tableTeams.get(iT);
                if (matches.get(i).getWinner() != -1 && matches.get(i).getWinner() != 0) {
                    //team1
                    if (tableTeam.name.equals(matches.get(i).getTeam1())) {
                        switch (matches.get(i).getWinner()) {
                            //win
                            case 1: { tableTeam.points += 3; tableTeam.win += 1; break; }
                            //lose
                            case 2: { tableTeam.lose += 1; break; }
                            //draw
                            case 3: {  tableTeam.draw += 1;  tableTeam.points += 1;  break; }
                        }
                        tableTeam.plus += matches.get(i).getTeam1Score();
                        tableTeam.minus += matches.get(i).getTeam2Score();
                        tableTeam.playedMatches += 1;
                    }
                    //team2
                    if (tableTeam.name.equals(matches.get(i).getTeam2())) {
                        switch (matches.get(i).getWinner()) {
                            //win
                            case 2: { tableTeam.points += 3; tableTeam.win += 1; break; }
                            //lose
                            case 1: { tableTeam.lose += 1; break; }
                            //draw
                            case 3: { tableTeam.draw += 1; tableTeam.points += 1; break; }
                        }
                        tableTeam.plus += matches.get(i).getTeam2Score();
                        tableTeam.minus += matches.get(i).getTeam1Score();
                        tableTeam.playedMatches += 1;
                    }
                    tableTeam.farq = tableTeam.plus - tableTeam.minus;
                }
            }
            deleteAllTableTeams();
        }
        return tableTeams;
    }

    public void addToDb(ArrayList<tableTeam> tableTeams) {
        dbManager dbManager = new dbManager(this);
        dbManager.insertTable(tableTeams);
        dbManager.close();
    }

    public ArrayList<tableTeam> getTableFromDB() {
        dbManager dbManager = new dbManager(this);
        return  dbManager.loadTable();
    }

    public void deleteAllTableTeams() {
        dbManager dbManager = new dbManager(this);
        dbManager.deleteAllTableTeams();
    }

    public String GS(int resource){ return getResources().getString(resource); }

}
