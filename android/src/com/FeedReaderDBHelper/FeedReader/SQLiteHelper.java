package com.FeedReaderDBHelper.FeedReader;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.*;

import java.util.ArrayList;


public class SQLiteHelper extends SQLiteOpenHelper {

        private static final int DATABASE_VERSION = 1;
        public static final String DATABASE_NAME = "SQLiteDatabase.db";
        public static final String TABLE_NAME = "LETTERS_RESULTS";
        public static final String COLUMN_ID = "ID";
        public static final String COLUMN_USER = "USER";
        public static final String COLUMN_SCORE = "SCORE";
        private SQLiteDatabase database;

        public SQLiteHelper(Context context) {
                super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        public void insertRecord(UserScore contact) {
                database = this.getReadableDatabase();
                ContentValues contentValues = new ContentValues();
                contentValues.put(COLUMN_USER, contact.getUser());
                contentValues.put(COLUMN_SCORE, contact.getScore());
                database.insert(TABLE_NAME, null, contentValues);
                database.close();
        }

        public void updateRecord(UserScore contact) {
                database = this.getReadableDatabase();
                ContentValues contentValues = new ContentValues();
                contentValues.put(COLUMN_USER, contact.getUser());
                contentValues.put(COLUMN_SCORE, contact.getScore());
                database.update(TABLE_NAME, contentValues, COLUMN_ID + " = ?", new String[]{contact.getID()});
                database.close();
        }

        public void deleteRecord(UserScore contact) {
                database = this.getReadableDatabase();
                database.delete(TABLE_NAME, COLUMN_ID + " = ?", new String[]{contact.getID()});
                database.close();
        }

        public ArrayList<UserScore> getAllRecords() {
                database = this.getReadableDatabase();
                Cursor cursor = database.query(TABLE_NAME, null, null, null, null, null, null);
                ArrayList<UserScore> contacts = new ArrayList<UserScore>();
                UserScore userScore;
                if (cursor.getCount() > 0) {
                        for (int i = 0; i < cursor.getCount(); i++) {
                                cursor.moveToNext();
                                userScore = new UserScore();
                                userScore.setID(cursor.getString(0));
                                userScore.setUser(cursor.getString(1));
                                userScore.setScore(cursor.getString(2));
                                contacts.add(userScore);
                        }
                }
                cursor.close();
                database.close();
                return contacts;
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
                db.execSQL("create table " + TABLE_NAME + " ( " + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_USER + " VARCHAR, " + COLUMN_SCORE + " VARCHAR);");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
                db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
                onCreate(db);
        }
}