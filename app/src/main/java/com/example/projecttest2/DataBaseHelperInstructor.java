package com.example.projecttest2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.projecttest2.Models.InstructorUser;

public class DataBaseHelperInstructor extends SQLiteOpenHelper {

    public DataBaseHelperInstructor(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
//        String query = "CREATE TABLE INSTRUCTORUSER (EMAIL TEXT PRIMARY KEY," +
//                "FIRST_NAME TEXT," +
//                "LAST_NAME TEXT," +
//                "PASSWORD TEXT," +
//                "MOBILE_NUMBER TEXT," +
//                "ADDRESS TEXT," +
//                "SPECIALIZATION TEXT," +
//                "DEGREE TEXT," +
//                "COURSES TEXT)";
//        sqLiteDatabase.execSQL(query);
    }



    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean checkAlreadyExist(String email) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String query = "Select EMAIL From INSTRUCTORUSER Where EMAIL = '"+email  +"'";
        System.out.println(query);
        Cursor cursor = sqLiteDatabase.rawQuery(query,null);

        if (cursor.getCount() == 0)
        {
            return false;
        }
        else
            return true;
    }
    public void insertCustomer(InstructorUser user) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("EMAIL", user.getEmail());
        contentValues.put("FIRST_NAME", user.getFirstName());
        contentValues.put("LAST_NAME", user.getLastName());
        contentValues.put("PASSWORD", user.getPassword());
        contentValues.put("MOBILE_NUMBER", user.getMobileNumber());
        contentValues.put("SPECIALIZATION", user.getSpecialization());
        contentValues.put("DEGREE", user.getDegree());
        contentValues.put("COURSES", String.valueOf(user.getCourses()));


        sqLiteDatabase.insert("INSTRUCTORUSER", null, contentValues);
    }
    public Cursor getAllCustomers() {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM INSTRUCTORUSER", null);
    }

}

