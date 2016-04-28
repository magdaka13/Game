package com.HighScoreDB;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.sql.Database;
import com.badlogic.gdx.sql.DatabaseCursor;
import com.badlogic.gdx.sql.DatabaseFactory;
import com.badlogic.gdx.sql.SQLiteGdxException;


public class HighScoresDB {

    Database dbHandler;

    public static final String TABLE_SCORES = "HighScores";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_USER = "user";
    public static final String COLUMN_SCORE = "score";


    private static final String DATABASE_NAME = "highscores.db";
    private static final int DATABASE_VERSION = 1;

    // Database creation sql statement
    private static final String DATABASE_CREATE = "create table if not exists "
            + TABLE_SCORES
            + "(" + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_USER + " text not null, "
            + COLUMN_SCORE + " integer );";

    public HighScoresDB() {
        Gdx.app.log("HighScoresDB", "creation started");
        dbHandler = DatabaseFactory.getNewDatabase(DATABASE_NAME,
                DATABASE_VERSION, DATABASE_CREATE, null);

        dbHandler.setupDatabase();
        try {
            dbHandler.openOrCreateDatabase();
            dbHandler.execSQL(DATABASE_CREATE);
        } catch (SQLiteGdxException e) {
            e.printStackTrace();
        }

        Gdx.app.log("HighScoresDB", "created successfully");

        /*
        try {
     //       dbHandler.execSQL("INSERT INTO HighScores ('user','score') VALUES ('Filip',0)");
  //          dbHandler.execSQL("INSERT INTO HighScores ('user','score') VALUES ('Borys',0)");
    //        dbHandler.execSQL("INSERT INTO HighScores ('user','score') VALUES ('Magda',0)");
      //      dbHandler.execSQL("INSERT INTO HighScores ('user','score') VALUES ('Szymon',0)");
        } catch (SQLiteGdxException e) {
            e.printStackTrace();
        }
        */
    }

    public void UpdateRec(int s,String u)
    {
        try {
            dbHandler
                    .execSQL("UPDATE HighScores set score="+s+" where user='"+u+"'");
        } catch (SQLiteGdxException e) {
            e.printStackTrace();
        }
    }

    public String[] SelectAll(){
        String[] scores;

        scores=new String[4];
        DatabaseCursor cursor = null;

        try {
            cursor = dbHandler.rawQuery("SELECT * FROM HighScores order by score");
        } catch (SQLiteGdxException e) {
            e.printStackTrace();
        }
        int i=0;
        while (cursor.next()) {

            Gdx.app.log("HighScores fromDB", String.valueOf(cursor.getString(1))+" "+String.valueOf(cursor.getString(2)));
            scores[i]=String.valueOf(cursor.getString(1))+"               "+String.valueOf(cursor.getString(2));
            i=i+1;
        }

        return scores;
    }

    public void dispose()
    {
        try {
            dbHandler.closeDatabase();
        } catch (SQLiteGdxException e) {
            e.printStackTrace();
        }
        dbHandler = null;
        //     Gdx.app.log("HighScoresDB", "dispose");

    }
}