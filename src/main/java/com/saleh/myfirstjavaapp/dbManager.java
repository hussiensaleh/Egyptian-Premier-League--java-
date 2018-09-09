package com.saleh.myfirstjavaapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class dbManager extends SQLiteOpenHelper{


    private static final String DATABASE_NAME = "MATCHES";

    private static final String TABLE_NAME = "matches";

    private static final String COLUMN_ID = "ID";
    private static final String COLUMN_RoundId     ="RoundId";
    private static final String COLUMN_MatchRoundId="MatchRoundId";
    private static final String COLUMN_Team1       ="Team1";
    private static final String COLUMN_Team2       ="Team2";
    private static final String COLUMN_Score1      ="Score1";
    private static final String COLUMN_Score2      ="Score2";
    private static final String COLUMN_Team1Logo   ="Team1Logo";
    private static final String COLUMN_Team2Logo   ="Team2Logo";
    private static final String COLUMN_Winner      ="Winner";
    private static final String COLUMN_Round       ="Round";
    private static final String COLUMN_Date        ="Date";
    private static final String COLUMN_Time        ="Time";
    private static final int DATABASE_VERSION = 1;

    // Database creation sql statement
    private static final String DATABASE_CREATE = "create table "+TABLE_NAME+ "("
            + COLUMN_ID+ " integer primary key autoincrement , "
            +COLUMN_RoundId  +" integer,"
            +COLUMN_MatchRoundId +" integer, "
            +COLUMN_Team1    +" TEXT , "
            +COLUMN_Team2    +" TEXT , "
            +COLUMN_Score1   +" integer , "
            +COLUMN_Score2   +" integer , "
            +COLUMN_Team1Logo+" integer , "
            +COLUMN_Team2Logo+" integer , "
            +COLUMN_Winner   +" integer , "
            +COLUMN_Round    +" integer , "
            +COLUMN_Date     +" integer , "
            +COLUMN_Time     +" integer )";

    private static final String TABLE_NAME1 = "MY_TABLE";

    private static final String COLUMN_ID1 = "ID";
    private static final String COLUMN_NAME = "Name";
    private static final String COLUMN_PLAYED = "Played";
    private static final String COLUMN_WIN = "Win";
    private static final String COLUMN_DRAW = "Draw";
    private static final String COLUMN_LOSE = "Lose";
    private static final String COLUMN_PLUS = "Plus";
    private static final String COLUMN_MINES = "Mines";
    private static final String COLUMN_FARQ = "Farq";
    private static final String COLUMN_POINTS = "Points";

    // Database creation sql statement
    private static final String DATABASE_CREATE1 = "create table "+TABLE_NAME1+
            "(" + COLUMN_ID1+ " integer primary key autoincrement , "
            +COLUMN_NAME +" TEXT ," +
            COLUMN_PLAYED +" integer ," +
            COLUMN_WIN +" integer ," +
            COLUMN_DRAW +" integer," +
            COLUMN_LOSE +" integer," +
            COLUMN_PLUS +" integer," +
            COLUMN_MINES +" integer," +
            COLUMN_FARQ +" integer," +
            COLUMN_POINTS +" integer" +
            ")";

    dbManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE1);
        db.execSQL(DATABASE_CREATE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME1);
        onCreate(db);
    }

    public long insertMatches(ArrayList<match> matches) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        // `id` and `timestamp` will be inserted automatically.
        // no need to add them
        long id=0;
        for (int x=0;x<matches.size();x++) {
            values.put("RoundId"   ,matches.get(x).getRoundId()   );
            values.put("MatchRoundId",matches.get(x).getRoundMatchId() );
            values.put("Team1"          ,matches.get(x).getTeam1());
            values.put("Team2"          ,matches.get(x).getTeam2());
            values.put( "Score1"    ,matches.get(x).getTeam1Score()  );
            values.put("Score2"    ,matches.get(x).getTeam2Score()   );
            values.put("Team1Logo" ,matches.get(x).getTeam1Image()   );
            values.put("Team2Logo" ,matches.get(x).getTeam2Image()   );
            values.put("Winner"    ,matches.get(x).getWinner()   );
            values.put("Round"         ,matches.get(x).getRound() );
            values.put("Date"         ,matches.get(x).getDate()  );
            values.put("Time"         ,matches.get(x).getTime()  );

            id = db.insert(TABLE_NAME, null, values);
            values.clear();
        }
        // insert row

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }

    public ArrayList<match> getAllmatches() {
        ArrayList<match> matches = new ArrayList<>();

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(TABLE_NAME,null,null,null,null,null,null,null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                match match=new match(
                                 cursor.getInt(cursor.getColumnIndex("RoundId"))
                                ,cursor.getInt(cursor.getColumnIndex("MatchRoundId"))
                                ,cursor.getString(cursor.getColumnIndex("Team1"))
                                ,cursor.getString(cursor.getColumnIndex("Team2"))
                                ,cursor.getInt(cursor.getColumnIndex("Score1"))
                                ,cursor.getInt(cursor.getColumnIndex("Score2"))
                                ,cursor.getInt(cursor.getColumnIndex("Team1Logo"))
                                ,cursor.getInt(cursor.getColumnIndex("Team2Logo"))
                                ,cursor.getInt(cursor.getColumnIndex("Winner"))
                                ,cursor.getString(cursor.getColumnIndex("Round"))
                                ,cursor.getString(cursor.getColumnIndex("Date"))
                                ,cursor.getString(cursor.getColumnIndex("Time")));

                matches.add(match);
            } while (cursor.moveToNext());
        }

        // close db connection
        cursor.close();
        db.close();

        // return matches
        return matches;
    }

    public ArrayList<match> loadMatches(String selection,String[] selectionArgs,String oder){

        ArrayList<match> matches = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME,
                null,
                selection,
                selectionArgs,null,null,oder);

        if (cursor.moveToFirst()){
            cursor.moveToFirst();

            do {
                matches.add(new match( cursor.getInt(cursor.getColumnIndex("RoundId"))
                        ,cursor.getInt(cursor.getColumnIndex("MatchRoundId"))
                        ,cursor.getString(cursor.getColumnIndex("Team1"))
                        ,cursor.getString(cursor.getColumnIndex("Team2"))
                        ,cursor.getInt(cursor.getColumnIndex("Score1"))
                        ,cursor.getInt(cursor.getColumnIndex("Score2"))
                        ,cursor.getInt(cursor.getColumnIndex("Team1Logo"))
                        ,cursor.getInt(cursor.getColumnIndex("Team2Logo"))
                        ,cursor.getInt(cursor.getColumnIndex("Winner"))
                        ,cursor.getString(cursor.getColumnIndex("Round"))
                        ,cursor.getString(cursor.getColumnIndex("Date"))
                        ,cursor.getString(cursor.getColumnIndex("Time"))));
            } while (cursor.moveToNext());
            cursor.close();
        }

        db.close();
        return matches;
    }

    public void deleteAll(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME,null,null);
    }

    public Long insertTable(ArrayList<tableTeam> tableTeams){
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        // `id` and `timestamp` will be inserted automatically.
        // no need to add them
        long id=0;
        for (int x=0;x<tableTeams.size();x++) {
            values.put("Name"  ,tableTeams.get(x).getName());
            values.put("Played",tableTeams.get(x).getPlayedMatches());
            values.put("Win"   ,tableTeams.get(x).getWin());
            values.put("Draw"  ,tableTeams.get(x).getDraw());
            values.put("Lose"  ,tableTeams.get(x).getLose());
            values.put("Plus"  ,tableTeams.get(x).getPlus());
            values.put("Mines" ,tableTeams.get(x).getMinus());
            values.put("Farq"  ,tableTeams.get(x).getFarq());
            values.put("Points",tableTeams.get(x).getPoints());

            id = db.insert(TABLE_NAME1, null, values);
            values.clear();
        }
        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }

    public ArrayList<tableTeam> loadTable(){
        ArrayList<tableTeam> tableTeams = new ArrayList<>();

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(TABLE_NAME1,null,null,null,null,null,"Points DESC  , Farq  DESC , Plus DESC ",null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                tableTeam tableTeam=new tableTeam(
                        cursor.getInt(cursor.getColumnIndex("ID"))
                        ,cursor.getString(cursor.getColumnIndex("Name"))
                        ,cursor.getInt(cursor.getColumnIndex("Played"))
                        ,cursor.getInt(cursor.getColumnIndex("Win"))
                        ,cursor.getInt(cursor.getColumnIndex("Draw"))
                        ,cursor.getInt(cursor.getColumnIndex("Lose"))
                        ,cursor.getInt(cursor.getColumnIndex("Plus"))
                        ,cursor.getInt(cursor.getColumnIndex("Mines"))
                        ,cursor.getInt(cursor.getColumnIndex("Farq"))
                        ,cursor.getInt(cursor.getColumnIndex("Points")));

                tableTeams.add(tableTeam);
            } while (cursor.moveToNext());
        }

        // close db connection
        cursor.close();
        db.close();

        // return matches
        return tableTeams;
    }

    public void update(ContentValues values,String[] selectionArg){
        SQLiteDatabase db = this.getWritableDatabase();
        db.update(TABLE_NAME1, values,"Name Like ?",selectionArg);
    }
    public void deleteAllTableTeams(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME1,null,null);
    }


}
