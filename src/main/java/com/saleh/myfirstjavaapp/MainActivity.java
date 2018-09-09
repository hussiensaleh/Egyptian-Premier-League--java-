package com.saleh.myfirstjavaapp;

import android.content.Intent;
import android.database.Cursor;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private AdView mAdView;
    ArrayList<match> matches= new ArrayList<>() ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAdView = findViewById(R.id.adView_main);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        Button days = findViewById(R.id.mainDays);

        days.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startNewActivity(DaysActivity.class);
            }
        });
        Button rounds = findViewById(R.id.mainRounds);
        rounds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startNewActivity(RoundsActivity.class);
            }
        });

        Button teams = findViewById(R.id.mainTeamMatches);
        teams.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startNewActivity(TeamsActivity.class);
            }
        });
        Button table = findViewById(R.id.mainTable);
        table.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startNewActivity(TableActivity.class);
            }
        });

    }

    public void startNewActivity(Class Class){
        Intent i =new Intent(this,Class);
        startActivity(i);
    }

}
