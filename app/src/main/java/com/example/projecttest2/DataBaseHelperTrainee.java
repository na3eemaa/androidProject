package com.example.projecttest2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.projecttest2.Models.TraineeUser;

public class DataBaseHelperTrainee extends SQLiteOpenHelper {
    public DataBaseHelperTrainee(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
//        String query = "CREATE TABLE TRAINEEUSER (EMAIL TEXT PRIMARY KEY," +
//                "FIRST_NAME TEXT," +
//                "LAST_NAME TEXT," +
//                "PASSWORD TEXT," +
//                "MOBILE_NUMBER TEXT," +  // Add this line to include the missing column
//                "ADDRESS TEXT)";
//        sqLiteDatabase.execSQL(query);
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean checkTraineeAlreadyExist(String email) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String query = "Select EMAIL From TRAINEEUSER Where EMAIL = '"+email  +"'";
        System.out.println(query);
        Cursor cursor = sqLiteDatabase.rawQuery(query,null);

        if (cursor.getCount() == 0)
        {
            return false;
        }
        else
            return true;
    }
    public void insertTrainee(TraineeUser user) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("EMAIL", user.getEmail());
        contentValues.put("FIRST_NAME", user.getFirstName());
        contentValues.put("LAST_NAME", user.getLastName());
        contentValues.put("PASSWORD", user.getPassword());
        contentValues.put("MOBILE_NUMBER", user.getMobileNumber());
        contentValues.put("ADDRESS", user.getAddress());

        sqLiteDatabase.insert("TRAINEEUSER", null, contentValues);
        Log.d("Database", "Inserted trainee user: " + user.toString());

    }
    public Cursor getAllCustomers() {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM TRAINEEUSER", null);
    }
}

