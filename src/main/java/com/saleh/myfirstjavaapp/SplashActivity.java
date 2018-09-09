package com.saleh.myfirstjavaapp;

import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class SplashActivity extends AppCompatActivity {

    ArrayList<match> matches = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        try {
            matches=loadQuery();
        }catch (Exception ignored){}
        if (matches.size()==0){
            matches();
            Toast.makeText(this,"شكرا لاستخدامك تطبيقنا",Toast.LENGTH_LONG).show();
        }
        retrieveDataFromFB();
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                Intent intent=new Intent(SplashActivity.this,MainActivity.class);
                startActivity(intent);
                SplashActivity.this.finish(); }},4000);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    public void matches(){
        //--------------------------------------------------------------------------------------------------------------------------------------------------------------------\\
        matches.add(new match(1,1,GS(R.string.ithd),GS(R.string.marb),0,0,R.drawable.ithd_logo,R.drawable.marb_logo,-1,"الاولي","31/7/2018","18:00"));
        matches.add(new match(1,2,GS(R.string.ptrj),GS(R.string.zmlk),0,0,R.drawable.ptrj_logo,R.drawable.zmlk_logo,-1,"الاولي","31/7/2018","21:00"));
        matches.add(new match(1,3,GS(R.string.dkhl),GS(R.string.dgla),0,0,R.drawable.dkhl_logo,R.drawable.dgla_logo,-1,"الاولي","1/8/2018" ,"16:00"));
        matches.add(new match(1,4,GS(R.string.entg),GS(R.string.hars),0,0,R.drawable.entg_logo,R.drawable.hars_logo,-1,"الاولي","1/8/2018" ,"18:15"));
        matches.add(new match(1,5,GS(R.string.ngom),GS(R.string.tlae),0,0,R.drawable.ngom_logo,R.drawable.tlae_logo,-1,"الاولي","1/8/2018" ,"21:00"));
        matches.add(new match(1,6,GS(R.string.gona),GS(R.string.msry),0,0,R.drawable.gona_logo,R.drawable.msry_logo,-1,"الاولي","2/8/2018" ,"18:00"));
        matches.add(new match(1,7,GS(R.string.ahly),GS(R.string.isml),0,0,R.drawable.ahly_logo,R.drawable.isml_logo,-1,"الاولي","2/8/2018" ,"21:00"));
        matches.add(new match(1,8,GS(R.string.mqsa),GS(R.string.smha),0,0,R.drawable.mqsa_logo,R.drawable.smha_logo,-1,"الاولي","3/8/2018" ,"18:00"));
        matches.add(new match(1,9,GS(R.string.enpi),GS(R.string.prmd),0,0,R.drawable.enpi_logo,R.drawable.prmd_logo,-1,"الاولي","3/8/2018" ,"21:00"));
        //--------------------------------------------------------------------------------------------------------------------------------------------------------------------\\
        matches.add(new match(2,1,GS(R.string.zmlk),GS(R.string.ithd),0,0,R.drawable.zmlk_logo,R.drawable.ithd_logo,-1,"الثانية","4/8/2018","20:00"));
        matches.add(new match(2,2,GS(R.string.hars),GS(R.string.dkhl),0,0,R.drawable.hars_logo,R.drawable.dkhl_logo,-1,"الثانية","6/8/2018","16:30"));
        matches.add(new match(2,3,GS(R.string.isml),GS(R.string.ptrj),0,0,R.drawable.isml_logo,R.drawable.ptrj_logo,-1,"الثانية","6/8/2018","18:45"));
        matches.add(new match(2,4,GS(R.string.dgla),GS(R.string.gona),0,0,R.drawable.dgla_logo,R.drawable.gona_logo,-1,"الثانية","6/8/2018","21:00"));
        matches.add(new match(2,5,GS(R.string.msry),GS(R.string.ahly),0,0,R.drawable.msry_logo,R.drawable.ahly_logo,-1,"الثانية","7/8/2018","20:00"));
        matches.add(new match(2,6,GS(R.string.prmd),GS(R.string.entg),0,0,R.drawable.prmd_logo,R.drawable.entg_logo,-1,"الثانية","8/8/2018","16:30"));
        matches.add(new match(2,7,GS(R.string.smha),GS(R.string.ngom),0,0,R.drawable.smha_logo,R.drawable.ngom_logo,-1,"الثانية","8/8/2018","18:45"));
        matches.add(new match(2,8,GS(R.string.marb),GS(R.string.mqsa),0,0,R.drawable.marb_logo,R.drawable.mqsa_logo,-1,"الثانية","8/8/2018","18:45"));
        matches.add(new match(2,9,GS(R.string.tlae),GS(R.string.enpi),0,0,R.drawable.tlae_logo,R.drawable.enpi_logo,-1,"الثانية","8/8/2018","21:00"));
        //----------------------------------------------------------------------------------------------------------------------------------------------\\
        matches.add(new match(3,1,GS(R.string.gona),GS(R.string.hars),0,0,R.drawable.gona_logo,R.drawable.hars_logo,-1,"الثالثة","14/8/2018","16:00"));
        matches.add(new match(3,2,GS(R.string.dkhl),GS(R.string.entg),0,0,R.drawable.dkhl_logo,R.drawable.entg_logo,-1,"الثالثة","14/8/2018","16:00"));
        matches.add(new match(3,3,GS(R.string.ptrj),GS(R.string.msry),0,0,R.drawable.ptrj_logo,R.drawable.msry_logo,-1,"الثالثة","14/8/2018","18:15"));
        matches.add(new match(3,4,GS(R.string.enpi),GS(R.string.smha),0,0,R.drawable.enpi_logo,R.drawable.smha_logo,-1,"الثالثة","14/8/2018","18:15"));
        matches.add(new match(3,5,GS(R.string.tlae),GS(R.string.prmd),0,0,R.drawable.tlae_logo,R.drawable.prmd_logo,-1,"الثالثة","14/8/2018","21:00"));
        matches.add(new match(3,6,GS(R.string.ngom),GS(R.string.marb),0,0,R.drawable.ngom_logo,R.drawable.marb_logo,-1,"الثالثة","15/8/2018","20:00"));
        matches.add(new match(3,7,GS(R.string.ithd),GS(R.string.isml),0,0,R.drawable.ithd_logo,R.drawable.isml_logo,-1,"الثالثة","16/8/2018","18:00"));
        matches.add(new match(3,8,GS(R.string.mqsa),GS(R.string.zmlk),0,0,R.drawable.mqsa_logo,R.drawable.zmlk_logo,-1,"الثالثة","16/8/2018","21:00"));
        matches.add(new match(3,9,GS(R.string.ahly),GS(R.string.dgla),0,0,R.drawable.ahly_logo,R.drawable.dgla_logo,-1,"الثالثة","21/8/2018","20:00"));
        //----------------------------------------------------------------------------------------------------------------------------------------------\\
        matches.add(new match(4,1,GS(R.string.dgla),GS(R.string.ptrj),0,0,R.drawable.dgla_logo,R.drawable.ptrj_logo,-1,"الرابعة","10/8/2018","20:00"));
        matches.add(new match(4,2,GS(R.string.marb),GS(R.string.enpi),0,0,R.drawable.marb_logo,R.drawable.enpi_logo,-1,"الرابعة","24/8/2018","16:00"));
        matches.add(new match(4,3,GS(R.string.isml),GS(R.string.mqsa),0,0,R.drawable.isml_logo,R.drawable.mqsa_logo,-1,"الرابعة","24/8/2018","18:15"));
        matches.add(new match(4,4,GS(R.string.hars),GS(R.string.ahly),0,0,R.drawable.hars_logo,R.drawable.ahly_logo,-1,"الرابعة","24/8/2018","21:00"));
        matches.add(new match(4,5,GS(R.string.msry),GS(R.string.ithd),0,0,R.drawable.msry_logo,R.drawable.ithd_logo,-1,"الرابعة","25/8/2018","17:00"));
        matches.add(new match(4,6,GS(R.string.smha),GS(R.string.tlae),0,0,R.drawable.smha_logo,R.drawable.tlae_logo,-1,"الرابعة","26/8/2018","16:00"));
        matches.add(new match(4,7,GS(R.string.prmd),GS(R.string.dkhl),0,0,R.drawable.prmd_logo,R.drawable.dkhl_logo,-1,"الرابعة","26/8/2018","18:15"));
        matches.add(new match(4,8,GS(R.string.entg),GS(R.string.gona),0,0,R.drawable.entg_logo,R.drawable.gona_logo,-1,"الرابعة","26/8/2018","21:00"));
        matches.add(new match(4,9,GS(R.string.zmlk),GS(R.string.ngom),0,0,R.drawable.zmlk_logo,R.drawable.ngom_logo,-1,"الرابعة","27/8/2018","20:00"));
        //----------------------------------------------------------------------------------------------------------------------------------------------\\
        matches.add(new match(5,1,GS(R.string.gona),GS(R.string.dkhl),0,0,R.drawable.gona_logo,R.drawable.dkhl_logo,-1,"الخامسة","31/8/2018","15:30"));
        matches.add(new match(5,2,GS(R.string.ptrj),GS(R.string.hars),0,0,R.drawable.ptrj_logo,R.drawable.hars_logo,-1,"الخامسة","31/8/2018","18:00"));
        matches.add(new match(5,3,GS(R.string.ngom),GS(R.string.isml),0,0,R.drawable.ngom_logo,R.drawable.isml_logo,-1,"الخامسة","31/8/2018","18:00"));
        matches.add(new match(5,4,GS(R.string.tlae),GS(R.string.marb),0,0,R.drawable.tlae_logo,R.drawable.marb_logo,-1,"الخامسة","31/8/2018","21:00"));
        matches.add(new match(5,5,GS(R.string.ithd),GS(R.string.dgla),0,0,R.drawable.ithd_logo,R.drawable.dgla_logo,-1,"الخامسة","28/9/2018","17:00"));
        matches.add(new match(5,6,GS(R.string.enpi),GS(R.string.zmlk),0,0,R.drawable.enpi_logo,R.drawable.zmlk_logo,-1,"الخامسة","1/9/2018" ,"21:00"));
        matches.add(new match(5,7,GS(R.string.smha),GS(R.string.prmd),0,0,R.drawable.smha_logo,R.drawable.prmd_logo,-1,"الخامسة","2/9/2018" ,"18:00"));
        matches.add(new match(5,8,GS(R.string.ahly),GS(R.string.entg),0,0,R.drawable.ahly_logo,R.drawable.entg_logo,-1,"الخامسة","2/9/2018" ,"21:00"));
        matches.add(new match(5,9,GS(R.string.mqsa),GS(R.string.msry),0,0,R.drawable.mqsa_logo,R.drawable.msry_logo,-1,"الخامسة","3/9/2018" ,"20:00"));
        //----------------------------------------------------------------------------------------------------------------------------------------------\\
        matches.add(new match(6,1,GS(R.string.msry),GS(R.string.ngom),0,0,R.drawable.msry_logo,R.drawable.ngom_logo,-1,"السادسة","11/9/2018","18:00"));
        matches.add(new match(6,2,GS(R.string.dkhl),GS(R.string.ahly),0,0,R.drawable.dkhl_logo,R.drawable.ahly_logo,-1,"السادسة","11/9/2018","21:00"));
        matches.add(new match(6,3,GS(R.string.entg),GS(R.string.ptrj),0,0,R.drawable.entg_logo,R.drawable.ptrj_logo,-1,"السادسة","12/9/2018","15:30"));
        matches.add(new match(6,4,GS(R.string.hars),GS(R.string.ithd),0,0,R.drawable.hars_logo,R.drawable.ithd_logo,-1,"السادسة","12/9/2018","15:30"));
        matches.add(new match(6,5,GS(R.string.marb),GS(R.string.smha),0,0,R.drawable.marb_logo,R.drawable.smha_logo,-1,"السادسة","12/9/2018","18:00"));
        matches.add(new match(6,6,GS(R.string.dgla),GS(R.string.mqsa),0,0,R.drawable.dgla_logo,R.drawable.mqsa_logo,-1,"السادسة","12/9/2018","21:00"));
        matches.add(new match(6,7,GS(R.string.prmd),GS(R.string.gona),0,0,R.drawable.prmd_logo,R.drawable.gona_logo,-1,"السادسة","12/9/2018","15:30"));
        matches.add(new match(6,8,GS(R.string.isml),GS(R.string.enpi),0,0,R.drawable.isml_logo,R.drawable.enpi_logo,-1,"السادسة","12/9/2018","18:00"));
        matches.add(new match(6,9,GS(R.string.zmlk),GS(R.string.tlae),0,0,R.drawable.zmlk_logo,R.drawable.tlae_logo,-1,"السادسة","12/9/2018","21:00"));
        //----------------------------------------------------------------------------------------------------------------------------------------------\\
        matches.add(new match(7,1,GS(R.string.mqsa),GS(R.string.hars),0,0,R.drawable.mqsa_logo,R.drawable.hars_logo,-1,"السابعة","17/9/2018","15:30"));
        matches.add(new match(7,2,GS(R.string.ithd),GS(R.string.entg),0,0,R.drawable.ithd_logo,R.drawable.entg_logo,-1,"السابعة","17/9/2018","18:00"));
        matches.add(new match(7,3,GS(R.string.ptrj),GS(R.string.dkhl),0,0,R.drawable.ptrj_logo,R.drawable.dkhl_logo,-1,"السابعة","17/9/2018","18:00"));
        matches.add(new match(7,4,GS(R.string.marb),GS(R.string.prmd),0,0,R.drawable.marb_logo,R.drawable.prmd_logo,-1,"السابعة","17/9/2018","21:00"));
        matches.add(new match(7,5,GS(R.string.tlae),GS(R.string.isml),0,0,R.drawable.tlae_logo,R.drawable.isml_logo,-1,"السابعة","18/9/2018","15:30"));
        matches.add(new match(7,6,GS(R.string.smha),GS(R.string.zmlk),0,0,R.drawable.smha_logo,R.drawable.zmlk_logo,-1,"السابعة","18/9/2018","18:00"));
        matches.add(new match(7,7,GS(R.string.ahly),GS(R.string.gona),0,0,R.drawable.ahly_logo,R.drawable.gona_logo,-1,"السابعة","18/9/2018","21:00"));
        matches.add(new match(7,8,GS(R.string.enpi),GS(R.string.msry),0,0,R.drawable.enpi_logo,R.drawable.msry_logo,-1,"السابعة","19/9/2018","18:00"));
        matches.add(new match(7,9,GS(R.string.ngom),GS(R.string.dgla),0,0,R.drawable.ngom_logo,R.drawable.dgla_logo,-1,"السابعة","19/9/2018","21:00"));
        //----------------------------------------------------------------------------------------------------------------------------------------------\\
        matches.add(new match(8,1,GS(R.string.dkhl),GS(R.string.ithd),0,0,R.drawable.dkhl_logo,R.drawable.ithd_logo,-1,"الثامنة","21/9/2018","15:30"));
        matches.add(new match(8,2,GS(R.string.entg),GS(R.string.mqsa),0,0,R.drawable.entg_logo,R.drawable.mqsa_logo,-1,"الثامنة","21/9/2018","18:00"));
        matches.add(new match(8,3,GS(R.string.ptrj),GS(R.string.gona),0,0,R.drawable.ptrj_logo,R.drawable.gona_logo,-1,"الثامنة","22/9/2018","15:30"));
        matches.add(new match(8,4,GS(R.string.isml),GS(R.string.smha),0,0,R.drawable.isml_logo,R.drawable.smha_logo,-1,"الثامنة","22/9/2018","18:00"));
        matches.add(new match(8,5,GS(R.string.zmlk),GS(R.string.marb),0,0,R.drawable.zmlk_logo,R.drawable.marb_logo,-1,"الثامنة","22/9/2018","21:00"));
        matches.add(new match(8,6,GS(R.string.hars),GS(R.string.ngom),0,0,R.drawable.hars_logo,R.drawable.ngom_logo,-1,"الثامنة","23/9/2018","15:30"));
        matches.add(new match(8,7,GS(R.string.dgla),GS(R.string.enpi),0,0,R.drawable.dgla_logo,R.drawable.enpi_logo,-1,"الثامنة","23/9/2018","18:00"));
        matches.add(new match(8,8,GS(R.string.prmd),GS(R.string.ahly),0,0,R.drawable.prmd_logo,R.drawable.ahly_logo,-1,"الثامنة","23/9/2018","21:00"));
        matches.add(new match(8,9,GS(R.string.msry),GS(R.string.tlae),0,0,R.drawable.msry_logo,R.drawable.tlae_logo,-1,"الثامنة","28/9/2018","19:00"));
        //----------------------------------------------------------------------------------------------------------------------------------------------\\
        matches.add(new match(9,1,GS(R.string.ngom),GS(R.string.entg),0,0,R.drawable.ngom_logo,R.drawable.entg_logo,-1,"التاسعة","30/9/2018","18:00"));
        matches.add(new match(9,2,GS(R.string.mqsa),GS(R.string.dkhl),0,0,R.drawable.mqsa_logo,R.drawable.dkhl_logo,-1,"التاسعة","30/9/2018","21:00"));
        matches.add(new match(9,3,GS(R.string.enpi),GS(R.string.hars),0,0,R.drawable.enpi_logo,R.drawable.hars_logo,-1,"التاسعة","1/10/2018","18:30"));
        matches.add(new match(9,4,GS(R.string.marb),GS(R.string.isml),0,0,R.drawable.marb_logo,R.drawable.isml_logo,-1,"التاسعة","1/10/2018","21:00"));
        matches.add(new match(9,5,GS(R.string.ithd),GS(R.string.gona),0,0,R.drawable.ithd_logo,R.drawable.gona_logo,-1,"التاسعة","2/10/2018","15:00"));
        matches.add(new match(9,6,GS(R.string.ptrj),GS(R.string.ahly),0,0,R.drawable.ptrj_logo,R.drawable.ahly_logo,-1,"التاسعة","2/10/2018","18:00"));
        matches.add(new match(9,7,GS(R.string.zmlk),GS(R.string.prmd),0,0,R.drawable.zmlk_logo,R.drawable.prmd_logo,-1,"التاسعة","2/10/2018","21:00"));
        matches.add(new match(9,8,GS(R.string.smha),GS(R.string.msry),0,0,R.drawable.smha_logo,R.drawable.msry_logo,-1,"التاسعة","3/10/2018","18:00"));
        matches.add(new match(9,9,GS(R.string.tlae),GS(R.string.dgla),0,0,R.drawable.tlae_logo,R.drawable.dgla_logo,-1,"التاسعة","3/10/2018","21:00"));
        //----------------------------------------------------------------------------------------------------------------------------------------------\\
        matches.add(new match(10,1,GS(R.string.dkhl),GS(R.string.ngom),0,0,R.drawable.dkhl_logo,R.drawable.ngom_logo,-1,"العاشرة","5/10/2018","15:00"));
        matches.add(new match(10,2,GS(R.string.entg),GS(R.string.enpi),0,0,R.drawable.entg_logo,R.drawable.enpi_logo,-1,"العاشرة","5/10/2018","18:00"));
        matches.add(new match(10,3,GS(R.string.gona),GS(R.string.mqsa),0,0,R.drawable.gona_logo,R.drawable.mqsa_logo,-1,"العاشرة","6/10/2018","15:00"));
        matches.add(new match(10,4,GS(R.string.isml),GS(R.string.zmlk),0,0,R.drawable.isml_logo,R.drawable.zmlk_logo,-1,"العاشرة","6/10/2018","18:00"));
        matches.add(new match(10,5,GS(R.string.ahly),GS(R.string.ithd),0,0,R.drawable.ahly_logo,R.drawable.ithd_logo,-1,"العاشرة","6/10/2018","21:00"));
        matches.add(new match(10,6,GS(R.string.hars),GS(R.string.tlae),0,0,R.drawable.hars_logo,R.drawable.tlae_logo,-1,"العاشرة","7/10/2018","15:00"));
        matches.add(new match(10,7,GS(R.string.prmd),GS(R.string.ptrj),0,0,R.drawable.prmd_logo,R.drawable.ptrj_logo,-1,"العاشرة","7/10/2018","18:00"));
        matches.add(new match(10,8,GS(R.string.msry),GS(R.string.marb),0,0,R.drawable.msry_logo,R.drawable.marb_logo,-1,"العاشرة","7/10/2018","18:00"));
        matches.add(new match(10,9,GS(R.string.dgla),GS(R.string.smha),0,0,R.drawable.dgla_logo,R.drawable.smha_logo,-1,"العاشرة","7/10/2018","21:00"));
        //----------------------------------------------------------------------------------------------------------------------------------------------\\
        matches.add(new match(11,1,GS(R.string.smha),GS(R.string.hars),0,0,R.drawable.smha_logo,R.drawable.hars_logo,-1,"الحادية عشر","18/10/2018","15:00"));
        matches.add(new match(11,2,GS(R.string.tlae),GS(R.string.entg),0,0,R.drawable.tlae_logo,R.drawable.entg_logo,-1,"الحادية عشر","18/10/2018","15:00"));
        matches.add(new match(11,3,GS(R.string.enpi),GS(R.string.dkhl),0,0,R.drawable.enpi_logo,R.drawable.dkhl_logo,-1,"الحادية عشر","18/10/2018","18:00"));
        matches.add(new match(11,4,GS(R.string.marb),GS(R.string.dgla),0,0,R.drawable.marb_logo,R.drawable.dgla_logo,-1,"الحادية عشر","18/10/2018","21:00"));
        matches.add(new match(11,5,GS(R.string.ngom),GS(R.string.gona),0,0,R.drawable.ngom_logo,R.drawable.gona_logo,-1,"الحادية عشر","19/10/2018","15:00"));
        matches.add(new match(11,6,GS(R.string.isml),GS(R.string.prmd),0,0,R.drawable.isml_logo,R.drawable.prmd_logo,-1,"الحادية عشر","19/10/2018","15:00"));
        matches.add(new match(11,7,GS(R.string.mqsa),GS(R.string.ahly),0,0,R.drawable.mqsa_logo,R.drawable.ahly_logo,-1,"الحادية عشر","19/10/2018","18:00"));
        matches.add(new match(11,8,GS(R.string.zmlk),GS(R.string.msry),0,0,R.drawable.zmlk_logo,R.drawable.msry_logo,-1,"الحادية عشر","19/10/2018","21:00"));
        matches.add(new match(11,9,GS(R.string.ithd),GS(R.string.ptrj),0,0,R.drawable.ithd_logo,R.drawable.ptrj_logo,-1,"الحادية عشر","20/10/2018","19:00"));
        //----------------------------------------------------------------------------------------------------------------------------------------------\\
        matches.add(new match(12,1,GS(R.string.hars),GS(R.string.marb),0,0,R.drawable.hars_logo,R.drawable.marb_logo,-1,"الثانية عشر","1/11/2018","15:00"));
        matches.add(new match(12,2,GS(R.string.ptrj),GS(R.string.mqsa),0,0,R.drawable.ptrj_logo,R.drawable.mqsa_logo,-1,"الثانية عشر","1/11/2018","17:30"));
        matches.add(new match(12,3,GS(R.string.dgla),GS(R.string.zmlk),0,0,R.drawable.dgla_logo,R.drawable.zmlk_logo,-1,"الثانية عشر","1/11/2018","20:30"));
        matches.add(new match(12,4,GS(R.string.gona),GS(R.string.enpi),0,0,R.drawable.gona_logo,R.drawable.enpi_logo,-1,"الثانية عشر","2/11/2018","14:45"));
        matches.add(new match(12,5,GS(R.string.dkhl),GS(R.string.tlae),0,0,R.drawable.dkhl_logo,R.drawable.tlae_logo,-1,"الثانية عشر","2/11/2018","14:45"));
        matches.add(new match(12,6,GS(R.string.entg),GS(R.string.smha),0,0,R.drawable.entg_logo,R.drawable.smha_logo,-1,"الثانية عشر","2/11/2018","17:00"));
        matches.add(new match(12,7,GS(R.string.prmd),GS(R.string.ithd),0,0,R.drawable.prmd_logo,R.drawable.ithd_logo,-1,"الثانية عشر","2/11/2018","20:00"));
        matches.add(new match(12,8,GS(R.string.msry),GS(R.string.isml),0,0,R.drawable.msry_logo,R.drawable.isml_logo,-1,"الثانية عشر","3/11/2018","17:00"));
        matches.add(new match(12,9,GS(R.string.ahly),GS(R.string.ngom),0,0,R.drawable.ahly_logo,R.drawable.ngom_logo,-1,"الثانية عشر","3/11/2018","20:00"));
        //----------------------------------------------------------------------------------------------------------------------------------------------\\
        matches.add(new match(13,1,GS(R.string.zmlk),GS(R.string.hars),0,0,R.drawable.zmlk_logo,R.drawable.hars_logo,-1,"الثالثة عشر","5/11/2018","19:00"));
        matches.add(new match(13,2,GS(R.string.ngom),GS(R.string.ptrj),0,0,R.drawable.ngom_logo,R.drawable.ptrj_logo,-1,"الثالثة عشر","6/11/2018","14:45"));
        matches.add(new match(13,3,GS(R.string.smha),GS(R.string.dkhl),0,0,R.drawable.smha_logo,R.drawable.dkhl_logo,-1,"الثالثة عشر","6/11/2018","17:00"));
        matches.add(new match(13,4,GS(R.string.tlae),GS(R.string.gona),0,0,R.drawable.tlae_logo,R.drawable.gona_logo,-1,"الثالثة عشر","6/11/2018","17:00"));
        matches.add(new match(13,5,GS(R.string.marb),GS(R.string.entg),0,0,R.drawable.marb_logo,R.drawable.entg_logo,-1,"الثالثة عشر","6/11/2018","20:00"));
        matches.add(new match(13,6,GS(R.string.isml),GS(R.string.dgla),0,0,R.drawable.isml_logo,R.drawable.dgla_logo,-1,"الثالثة عشر","7/11/2018","14:45"));
        matches.add(new match(13,7,GS(R.string.msry),GS(R.string.prmd),0,0,R.drawable.msry_logo,R.drawable.prmd_logo,-1,"الثالثة عشر","7/11/2018","17:00"));
        matches.add(new match(13,8,GS(R.string.enpi),GS(R.string.ahly),0,0,R.drawable.enpi_logo,R.drawable.ahly_logo,-1,"الثالثة عشر","7/11/2018","20:00"));
        matches.add(new match(13,9,GS(R.string.mqsa),GS(R.string.ithd),0,0,R.drawable.mqsa_logo,R.drawable.ithd_logo,-1,"الثالثة عشر","8/11/2018","19:00"));
        //----------------------------------------------------------------------------------------------------------------------------------------------\\
        matches.add(new match(14,1,GS(R.string.gona),GS(R.string.smha),0,0,R.drawable.gona_logo,R.drawable.smha_logo,-1,"الرابعة عشر","10/11/2018","14:45"));
        matches.add(new match(14,2,GS(R.string.entg),GS(R.string.zmlk),0,0,R.drawable.entg_logo,R.drawable.zmlk_logo,-1,"الرابعة عشر","10/11/2018","18:00"));
        matches.add(new match(14,3,GS(R.string.hars),GS(R.string.isml),0,0,R.drawable.hars_logo,R.drawable.isml_logo,-1,"الرابعة عشر","11/11/2018","14:45"));
        matches.add(new match(14,4,GS(R.string.dgla),GS(R.string.msry),0,0,R.drawable.dgla_logo,R.drawable.msry_logo,-1,"الرابعة عشر","11/11/2018","17:00"));
        matches.add(new match(14,5,GS(R.string.ptrj),GS(R.string.enpi),0,0,R.drawable.ptrj_logo,R.drawable.enpi_logo,-1,"الرابعة عشر","11/11/2018","17:00"));
        matches.add(new match(14,6,GS(R.string.ahly),GS(R.string.tlae),0,0,R.drawable.ahly_logo,R.drawable.tlae_logo,-1,"الرابعة عشر","11/11/2018","20:00"));
        matches.add(new match(14,7,GS(R.string.dkhl),GS(R.string.marb),0,0,R.drawable.dkhl_logo,R.drawable.marb_logo,-1,"الرابعة عشر","12/11/2018","14:45"));
        matches.add(new match(14,8,GS(R.string.ithd),GS(R.string.ngom),0,0,R.drawable.ithd_logo,R.drawable.ngom_logo,-1,"الرابعة عشر","12/11/2018","17:00"));
        matches.add(new match(14,9,GS(R.string.prmd),GS(R.string.mqsa),0,0,R.drawable.prmd_logo,R.drawable.mqsa_logo,-1,"الرابعة عشر","12/11/2018","20:00"));
        //----------------------------------------------------------------------------------------------------------------------------------------------\\
        matches.add(new match(15,1,GS(R.string.marb),GS(R.string.gona),0,0,R.drawable.marb_logo,R.drawable.gona_logo,-1,"الخامسة عشر","20/11/2018","14:45"));
        matches.add(new match(15,2,GS(R.string.tlae),GS(R.string.ptrj),0,0,R.drawable.tlae_logo,R.drawable.ptrj_logo,-1,"الخامسة عشر","20/11/2018","17:00"));
        matches.add(new match(15,3,GS(R.string.enpi),GS(R.string.ithd),0,0,R.drawable.enpi_logo,R.drawable.ithd_logo,-1,"الخامسة عشر","20/11/2018","20:00"));
        matches.add(new match(15,4,GS(R.string.ngom),GS(R.string.mqsa),0,0,R.drawable.ngom_logo,R.drawable.mqsa_logo,-1,"الخامسة عشر","21/11/2018","14:45"));
        matches.add(new match(15,5,GS(R.string.msry),GS(R.string.hars),0,0,R.drawable.msry_logo,R.drawable.hars_logo,-1,"الخامسة عشر","21/11/2018","17:15"));
        matches.add(new match(15,6,GS(R.string.isml),GS(R.string.entg),0,0,R.drawable.isml_logo,R.drawable.entg_logo,-1,"الخامسة عشر","21/11/2018","20:00"));
        matches.add(new match(15,7,GS(R.string.dgla),GS(R.string.prmd),0,0,R.drawable.dgla_logo,R.drawable.prmd_logo,-1,"الخامسة عشر","21/11/2018","20:00"));
        matches.add(new match(15,8,GS(R.string.smha),GS(R.string.ahly),0,0,R.drawable.smha_logo,R.drawable.ahly_logo,-1,"الخامسة عشر","22/11/2018","17:00"));
        matches.add(new match(15,9,GS(R.string.zmlk),GS(R.string.dkhl),0,0,R.drawable.zmlk_logo,R.drawable.dkhl_logo,-1,"الخامسة عشر","22/11/2018","20:00"));
        //----------------------------------------------------------------------------------------------------------------------------------------------\\
        matches.add(new match(16,1,GS(R.string.ithd),GS(R.string.tlae),0,0,R.drawable.ithd_logo,R.drawable.tlae_logo,-1,"السادسة عشر","24/11/2018","19:00"));
        matches.add(new match(16,2,GS(R.string.ngom),GS(R.string.prmd),0,0,R.drawable.ngom_logo,R.drawable.prmd_logo,-1,"السادسة عشر","25/11/2018","14:45"));
        matches.add(new match(16,3,GS(R.string.entg),GS(R.string.msry),0,0,R.drawable.entg_logo,R.drawable.msry_logo,-1,"السادسة عشر","25/11/2018","17:00"));
        matches.add(new match(16,4,GS(R.string.hars),GS(R.string.dgla),0,0,R.drawable.hars_logo,R.drawable.dgla_logo,-1,"السادسة عشر","25/11/2018","17:00"));
        matches.add(new match(16,5,GS(R.string.mqsa),GS(R.string.enpi),0,0,R.drawable.mqsa_logo,R.drawable.enpi_logo,-1,"السادسة عشر","25/11/2018","20:00"));
        matches.add(new match(16,6,GS(R.string.gona),GS(R.string.zmlk),0,0,R.drawable.gona_logo,R.drawable.zmlk_logo,-1,"السادسة عشر","26/11/2018","14:45"));
        matches.add(new match(16,7,GS(R.string.ptrj),GS(R.string.smha),0,0,R.drawable.ptrj_logo,R.drawable.smha_logo,-1,"السادسة عشر","26/11/2018","17:00"));
        matches.add(new match(16,8,GS(R.string.ahly),GS(R.string.marb),0,0,R.drawable.ahly_logo,R.drawable.marb_logo,-1,"السادسة عشر","26/11/2018","20:00"));
        matches.add(new match(16,9,GS(R.string.dkhl),GS(R.string.isml),0,0,R.drawable.dkhl_logo,R.drawable.isml_logo,-1,"السادسة عشر","27/11/2018","19:00"));
        //----------------------------------------------------------------------------------------------------------------------------------------------\\
        AddtoDB(matches);
    }

    public void AddtoDB(ArrayList<match> matches){
        deleteAllMatches();
        dbManager db=new dbManager(this);
        Long id=db.insertMatches(matches);
        if (id!=-1){
            //Toast.makeText(this,"Added",Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(this,"Add Error",Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteAllMatches(){
        dbManager dbManager=new dbManager(this);
        dbManager.deleteAll();
    }

    public ArrayList<match> loadQuery(){
        dbManager dbManager =new dbManager(this);
        return dbManager.loadMatches(null,null,"ID");
    }

    public void retrieveDataFromFB(){
        final ArrayList<match> matches=new ArrayList<>();
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("matches");

        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<HashMap<String,String>> list=(ArrayList<HashMap<String,String>>)dataSnapshot.getValue();
                ArrayList<HashMap<String,Long>> list1=(ArrayList<HashMap<String,Long>>)dataSnapshot.getValue();
                if (list!=null&&list1!=null) {
                    dbManager dbManager=new dbManager(SplashActivity.this);
                    dbManager.deleteAll();
                    matches.clear();

                    for (int i = 0; i < list.size(); i++) {
                        Long score1 =list1.get(i).get("team1Score");
                        Long score2 =list1.get(i).get("team2Score");
                        Long logo1 =list1.get(i).get("team1LogoInt");
                        Long logo2 =list1.get(i).get("team2LogoInt");
                        Long winner =list1.get(i).get("winner");
                        Long roundId =list1.get(i).get("roundId");
                        Long roundMatchId =list1.get(i).get("roundMatchId");
                        int Image1 = 0;
                        switch (logo1.intValue())  {
                            case 2131165281 :Image1=R.drawable.ithd_logo ; break;  case 2131165282 :Image1=R.drawable.marb_logo ; break;
                            case 2131165299 :Image1=R.drawable.ptrj_logo ; break;  case 2131165304 :Image1=R.drawable.zmlk_logo ; break;
                            case 2131165273 :Image1=R.drawable.dkhl_logo ; break;  case 2131165272 :Image1=R.drawable.dgla_logo ; break;
                            case 2131165275 :Image1=R.drawable.entg_logo ; break;  case 2131165277 :Image1=R.drawable.hars_logo ; break;
                            case 2131165285 :Image1=R.drawable.ngom_logo ; break;  case 2131165301 :Image1=R.drawable.tlae_logo ; break;
                            case 2131165276 :Image1=R.drawable.gona_logo ; break;  case 2131165284 :Image1=R.drawable.msry_logo ; break;
                            case 2131165268 :Image1=R.drawable.ahly_logo ; break;  case 2131165280 :Image1=R.drawable.isml_logo ; break;
                            case 2131165283 :Image1=R.drawable.mqsa_logo ; break;  case 2131165300 :Image1=R.drawable.smha_logo ; break;
                            case 2131165274 :Image1=R.drawable.enpi_logo ; break;  case 2131165298 :Image1=R.drawable.prmd_logo ; break;
                        }
                        int Image2=0;
                        switch (logo2.intValue()) {
                            case 2131165281 :Image2=R.drawable.ithd_logo ; break;  case 2131165282 :Image2=R.drawable.marb_logo ; break;
                            case 2131165299 :Image2=R.drawable.ptrj_logo ; break;  case 2131165304 :Image2=R.drawable.zmlk_logo ; break;
                            case 2131165273 :Image2=R.drawable.dkhl_logo ; break;  case 2131165272 :Image2=R.drawable.dgla_logo ; break;
                            case 2131165275 :Image2=R.drawable.entg_logo ; break;  case 2131165277 :Image2=R.drawable.hars_logo ; break;
                            case 2131165285 :Image2=R.drawable.ngom_logo ; break;  case 2131165301 :Image2=R.drawable.tlae_logo ; break;
                            case 2131165276 :Image2=R.drawable.gona_logo ; break;  case 2131165284 :Image2=R.drawable.msry_logo ; break;
                            case 2131165268 :Image2=R.drawable.ahly_logo ; break;  case 2131165280 :Image2=R.drawable.isml_logo ; break;
                            case 2131165283 :Image2=R.drawable.mqsa_logo ; break;  case 2131165300 :Image2=R.drawable.smha_logo ; break;
                            case 2131165274 :Image2=R.drawable.enpi_logo ; break;  case 2131165298 :Image2=R.drawable.prmd_logo ; break;
                        }
                        matches.add(new match(
                                  roundId.intValue()      , roundMatchId.intValue()
                                , list.get(i).get("team1"), list.get(i).get("team2")
                                , score1.intValue()       , score2.intValue()
                                , Image1                  , Image2
                                , winner.intValue()
                                , list.get(i).get("round")
                                , list.get(i).get("date")
                                , list.get(i).get("time")));
                    }
                    Toast.makeText(SplashActivity.this,"Data Retrieved from FB in Splash Activity",Toast.LENGTH_SHORT).show();
                    AddtoDB(matches);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Failed to read value
            }
        });
    }

    public String GS(int resource){ return getResources().getString(resource);}

}
