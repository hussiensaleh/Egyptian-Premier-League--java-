package com.saleh.myfirstjavaapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;



public class MatchDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_details);
        // Up Button
        Button upBu =findViewById(R.id.details_home);
        upBu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MatchDetails.this.finish();
            }
        });

        AdView mAdView1 = findViewById(R.id.adView_details1);
        AdView mAdView2 = findViewById(R.id.adView_details2);
        //load  Ad from google
        AdRequest adRequest = new AdRequest.Builder().build();
        //add ads in adViews
        mAdView1.loadAd(adRequest);
        mAdView2.loadAd(adRequest);

        //get Round Id&&Match RoundId
        Intent i=getIntent();
        String RoundId=i.getIntExtra("roundId",0)+"";
        String MatchRoundId=i.getIntExtra("matchRoundId",0)+"";

        //get the match data from Database
        //and Display them
        display(loadMatchFromDatabase(RoundId,MatchRoundId));

        // get Video from Fire base and
        // load Video in youtubePlayer
        getGoals(RoundId,MatchRoundId);
    }

    public match loadMatchFromDatabase(String roundId,String matchRoundId){
        dbManager dbManager=new dbManager(MatchDetails.this);
        String[] projectionArg={roundId,matchRoundId};
        return dbManager.loadMatches("RoundId =? and MatchRoundId =?",projectionArg,"ID").get(0);
    }

    public void display(match match){
        TextView team1TV=findViewById(R.id.D_team1name);
        team1TV.setText(match.getTeam1());
        TextView team2TV=findViewById(R.id.D_team2name);
        team2TV.setText(match.getTeam2());
        Button team1Logo =findViewById(R.id.D_team1logo);
        team1Logo.setBackground(getResources().getDrawable(match.getTeam1Image()));
        Button team2Logo =findViewById(R.id.D_team2logo);
        team2Logo.setBackground(getResources().getDrawable(match.getTeam2Image()));
        TextView score1 =findViewById(R.id.D_team1score);
        score1.setText(match.getTeam1Score()+"");
        TextView score2 =findViewById(R.id.D_team2score);
        score2.setText(match.getTeam2Score()+"");
        TextView time =findViewById(R.id.D_time_tv);
        time.setText(match.getTime());
        TextView date =findViewById(R.id.D_date_tv);
        date.setText(match.getDate());
        TextView status =findViewById(R.id.D_status_tv);
        switch (match.getWinner()){
            case -1 :
                status.setText("لم تبدا بعد");
                break;
            case 0  :
                status.setText("    الان    ");
                break;
            case 1  :
                status.setText("   انتهت   ");
                break;
            case 2  :
                status.setText("   انتهت   ");
                break;
            case 3  :
                status.setText("   انتهت   ");
                break;
        }
    }

    public void getGoals(String roundId,String matchRoundId){
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef= firebaseDatabase.getReference("goals").child(roundId).child(matchRoundId);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value =dataSnapshot.getValue(String.class);
                playVideo(value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void playVideo(final String videoId){
        YouTubePlayerFragment youtubeFragment = (YouTubePlayerFragment)
                getFragmentManager().findFragmentById(R.id.D_youtubeFragment);
        youtubeFragment.initialize("YOUR API KEY",
                new YouTubePlayer.OnInitializedListener() {
                    @Override
                    public void onInitializationSuccess(YouTubePlayer.Provider provider,
                                                        YouTubePlayer youTubePlayer, boolean b) {
                        // do any work here to cue video, play video, etc.
                        youTubePlayer.cueVideo(videoId);
                    }
                    @Override
                    public void onInitializationFailure(YouTubePlayer.Provider provider,
                                                        YouTubeInitializationResult youTubeInitializationResult) {

                    }
                });
    }

}
