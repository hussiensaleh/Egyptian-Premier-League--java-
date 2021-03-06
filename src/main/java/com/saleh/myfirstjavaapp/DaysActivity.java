package com.saleh.myfirstjavaapp;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;

public class DaysActivity extends AppCompatActivity  {
    matchesAdapter adapter;
    String date;
    int spinnerPosition;
    ArrayList<String> Calender=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_days);
        //UpButton
        Button upBu =findViewById(R.id.days_home);
        upBu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DaysActivity.this.finish();
            }
        });
        Spinner spinner=findViewById(R.id.date_spinner);
        Calender.addAll(Arrays.asList(getResources().getStringArray(R.array.calender)));
        date= Calendar.getInstance().get(Calendar.DAY_OF_MONTH)+"/"
            +(Calendar.getInstance().get(Calendar.MONTH)+1)+"/"
             +Calendar.getInstance().get(Calendar.YEAR);
        spinnerPosition=Calender.indexOf(date);
        spinner.setSelection(spinnerPosition);
        spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinnerPosition=position;
                date=Calender.get(spinnerPosition);
                ListView listView=findViewById(R.id.days_listView);
                adapter=new matchesAdapter(DaysActivity.this,loadQuery(date));
                listView.setAdapter(adapter);
                listView.setDividerHeight(10);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        retrieveDataFromFB();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    public void next(View view){
        Spinner spinner=findViewById(R.id.date_spinner);
        spinner.setSelection(spinnerPosition+1);
    }

    public void previous(View view){
        Spinner spinner=findViewById(R.id.date_spinner);
        spinner.setSelection(spinnerPosition-1);
    }

    public ArrayList<match> loadQuery(String date){
        ArrayList<match> todayMatches;
        dbManager dbManager =new dbManager(this);
        String[] selectionArgs ={date};
        todayMatches = dbManager.loadMatches("Date Like ? ",selectionArgs,"Time");
        return todayMatches;
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
                    dbManager dbManager=new dbManager(DaysActivity.this);
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
                    Toast.makeText(DaysActivity.this,"Data Retrieved from FB in DaysActivity",Toast.LENGTH_SHORT).show();
                    AddtoDB(matches);
                    date=Calender.get(spinnerPosition);
                    ListView listView=findViewById(R.id.days_listView);
                    adapter=new matchesAdapter(DaysActivity.this,loadQuery(date));
                    listView.setAdapter(adapter);
                    listView.setDividerHeight(10);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Failed to read value
            }
        });
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


}
