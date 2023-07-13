package com.example.projecttest2;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.projecttest2.Models.Course;
import com.example.projecttest2.Models.InstructorUser;
import com.example.projecttest2.Models.TraineeUser;
import com.example.projecttest2.Models.User;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {
    public static String ADMIN_TABLE = "USER";
    public static String INSTRUCTOR_TABLE = "INSTRUCTORUSER";
    public static String TRAINEE_TABLE = "TRAINEEUSER";
    public static String ENROLL_TABLE = "ENROLLTABLE";
    public static final String COURSE_TABLE = "COURSE";
    public static final String COLUMN_COURSE_NUMBER = "COURSE_NUMBER";
    public static final String COLUMN_COURSE_TITLE = "COURSE_TITLE";
    public static final String COLUMN_COURSE_MAIN_TOPICS = "COURSE_MAIN_TOPICS";
    public static final String COLUMN_PREREQUISITES = "PREREQUISITES";
    public static final String COLUMN_DAYS_OF_WEEK = "DAYS_OF_WEEK";
    public static final String COLUMN_TIME_OF_DAY = "TIME_OF_DAY";
    public static final String COLUMN_IS_AVAILABLE = "IS_AVAILABLE";
    public static final String COLUMN_AVAILABLE_UNTIL = "AVAILABLE_UNTIL";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_EMAIL = "EMAIL";
    public static final String COLUMN_STATUS = "STATUS";


    private Context context; // Add a Context variable

    public DataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = String.format("CREATE TABLE %s (EMAIL TEXT PRIMARY KEY," +
                "FIRST_NAME TEXT," +
                "LAST_NAME TEXT," +
                "PASSWORD TEXT)", ADMIN_TABLE);
        sqLiteDatabase.execSQL(query);

        query = "CREATE TABLE " + INSTRUCTOR_TABLE + " (EMAIL TEXT PRIMARY KEY," +
                "FIRST_NAME TEXT," +
                "LAST_NAME TEXT," +
                "PASSWORD TEXT," +
                "MOBILE_NUMBER TEXT," +
                "ADDRESS TEXT," +
                "SPECIALIZATION TEXT," +
                "DEGREE TEXT," +
                "COURSES TEXT)";
        sqLiteDatabase.execSQL(query);

        query = "CREATE TABLE " + TRAINEE_TABLE + " (EMAIL TEXT PRIMARY KEY," +
                "FIRST_NAME TEXT," +
                "LAST_NAME TEXT," +
                "PASSWORD TEXT," +
                "MOBILE_NUMBER TEXT," +
                "ADDRESS TEXT)";
        sqLiteDatabase.execSQL(query);

        // Query to create the COURSE table
        query = "CREATE TABLE " + COURSE_TABLE + " (" +
                COLUMN_COURSE_NUMBER + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_COURSE_TITLE + " TEXT," +
                COLUMN_COURSE_MAIN_TOPICS + " TEXT," +
                COLUMN_PREREQUISITES + " TEXT," +
                COLUMN_DAYS_OF_WEEK + " TEXT," +
                COLUMN_TIME_OF_DAY + " TEXT," +
                COLUMN_IS_AVAILABLE + " TEXT," +
                COLUMN_AVAILABLE_UNTIL + " TEXT" +
                ")";
        sqLiteDatabase.execSQL(query);

        query = "CREATE TABLE " + ENROLL_TABLE + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_COURSE_NUMBER + " TEXT," +
                COLUMN_EMAIL + " TEXT," +
                COLUMN_STATUS + " TEXT" +
                ")";
        sqLiteDatabase.execSQL(query);
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public Course getCourse(int courseNumber) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String query = "SELECT * FROM " + COURSE_TABLE + " WHERE " + COLUMN_COURSE_NUMBER + " = ?";
        Cursor cursor = sqLiteDatabase.rawQuery(query, new String[]{String.valueOf(courseNumber)});

        Course course = null;
        if (cursor.moveToFirst()) {
            @SuppressLint("Range") String courseTitle = cursor.getString(cursor.getColumnIndex(COLUMN_COURSE_TITLE));
            @SuppressLint("Range") String courseMainTopics = cursor.getString(cursor.getColumnIndex(COLUMN_COURSE_MAIN_TOPICS));
            @SuppressLint("Range") String prerequisites = cursor.getString(cursor.getColumnIndex(COLUMN_PREREQUISITES));
            @SuppressLint("Range") String daysOfWeek = cursor.getString(cursor.getColumnIndex(COLUMN_DAYS_OF_WEEK));
            @SuppressLint("Range") String timeOfDay = cursor.getString(cursor.getColumnIndex(COLUMN_TIME_OF_DAY));
            @SuppressLint("Range") String isAvailable = cursor.getString(cursor.getColumnIndex(COLUMN_IS_AVAILABLE)); // Add the isAvailable value
            @SuppressLint("Range") String availableUntil = cursor.getString(cursor.getColumnIndex(COLUMN_AVAILABLE_UNTIL)); // Add the availableUntil value

            course = new Course(courseTitle, courseMainTopics, prerequisites, daysOfWeek, timeOfDay, isAvailable, availableUntil);

        }

        cursor.close();

        return course;
    }

    public List<InstructorUser> getAllInstructors() {
        List<InstructorUser> instructors = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + INSTRUCTOR_TABLE, null);

        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") String email = cursor.getString(cursor.getColumnIndex("EMAIL"));
                @SuppressLint("Range") String firstName = cursor.getString(cursor.getColumnIndex("FIRST_NAME"));
                @SuppressLint("Range") String lastName = cursor.getString(cursor.getColumnIndex("LAST_NAME"));
                @SuppressLint("Range") String password = cursor.getString(cursor.getColumnIndex("PASSWORD"));
                @SuppressLint("Range") String mobileNumber = cursor.getString(cursor.getColumnIndex("MOBILE_NUMBER"));
                @SuppressLint("Range") String address = cursor.getString(cursor.getColumnIndex("ADDRESS"));
                @SuppressLint("Range") String specialization = cursor.getString(cursor.getColumnIndex("SPECIALIZATION"));
                @SuppressLint("Range") String degree = cursor.getString(cursor.getColumnIndex("DEGREE"));
                @SuppressLint("Range") List<String> courses = Arrays.asList(cursor.getString(cursor.getColumnIndex("COURSES")).split(", "));

                InstructorUser instructor = new InstructorUser(email, firstName, lastName, password, mobileNumber, address, specialization, degree, courses);
                instructors.add(instructor);
            }
            while (cursor.moveToNext());
        }

        cursor.close();
        return instructors;
    }

    public List<TraineeUser> getAllTrainees() {
        List<TraineeUser> trainees = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TRAINEE_TABLE, null);

        if (cursor.moveToFirst()) {
            do {
                // Retrieve the trainee details
                @SuppressLint("Range") String email = cursor.getString(cursor.getColumnIndex("EMAIL"));
                @SuppressLint("Range") String firstName = cursor.getString(cursor.getColumnIndex("FIRST_NAME"));
                @SuppressLint("Range") String lastName = cursor.getString(cursor.getColumnIndex("LAST_NAME"));
                @SuppressLint("Range") String password = cursor.getString(cursor.getColumnIndex("PASSWORD"));
                @SuppressLint("Range") String mobileNumber = cursor.getString(cursor.getColumnIndex("MOBILE_NUMBER"));
                @SuppressLint("Range") String address = cursor.getString(cursor.getColumnIndex("ADDRESS"));

                // Create a TraineeUser object
                TraineeUser trainee = new TraineeUser(email, firstName, lastName, password, mobileNumber, address);
                // Add the trainee to the list
                trainees.add(trainee);
            }
            while (cursor.moveToNext());
        }

        cursor.close();
        return trainees;
    }

    public TraineeUser getTraineeProfile(String searchEmail) {

        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TRAINEE_TABLE + " where " + COLUMN_EMAIL + " = ?", new String[]{searchEmail});
        TraineeUser trainee = null;

        if (cursor.moveToFirst()) {
            // Retrieve the trainee details
            @SuppressLint("Range") String email = cursor.getString(cursor.getColumnIndex("EMAIL"));
            @SuppressLint("Range") String firstName = cursor.getString(cursor.getColumnIndex("FIRST_NAME"));
            @SuppressLint("Range") String lastName = cursor.getString(cursor.getColumnIndex("LAST_NAME"));
            @SuppressLint("Range") String password = cursor.getString(cursor.getColumnIndex("PASSWORD"));
            @SuppressLint("Range") String mobileNumber = cursor.getString(cursor.getColumnIndex("MOBILE_NUMBER"));
            @SuppressLint("Range") String address = cursor.getString(cursor.getColumnIndex("ADDRESS"));

            // Create a TraineeUser object
            trainee = new TraineeUser(email, firstName, lastName, password, mobileNumber, address);
        }

        cursor.close();
        return trainee;
    }
    public InstructorUser getInstructorProfile(String searchEmail) {

        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + INSTRUCTOR_TABLE + " where " + COLUMN_EMAIL + " = ?", new String[]{searchEmail});
        InstructorUser instructor = null;

        if (cursor.moveToFirst()) {
            // Retrieve the trainee details
            @SuppressLint("Range") String email = cursor.getString(cursor.getColumnIndex("EMAIL"));
            @SuppressLint("Range") String firstName = cursor.getString(cursor.getColumnIndex("FIRST_NAME"));
            @SuppressLint("Range") String lastName = cursor.getString(cursor.getColumnIndex("LAST_NAME"));
            @SuppressLint("Range") String password = cursor.getString(cursor.getColumnIndex("PASSWORD"));
            @SuppressLint("Range") String mobileNumber = cursor.getString(cursor.getColumnIndex("MOBILE_NUMBER"));
            @SuppressLint("Range") String address = cursor.getString(cursor.getColumnIndex("ADDRESS"));
            @SuppressLint("Range") String specialization = cursor.getString(cursor.getColumnIndex("specialization"));
            @SuppressLint("Range") String degree = cursor.getString(cursor.getColumnIndex("DEGREE"));
            @SuppressLint("Range") String courses = cursor.getString(cursor.getColumnIndex("COURSES"));

            // Create a TraineeUser object
            instructor = new InstructorUser(email, firstName, lastName, password, mobileNumber, address,specialization,degree, Collections.singletonList(courses));
        }

        cursor.close();
        return instructor;
    }


    private List<String> getCoursesForInstructor(String email) {
        List<String> courses = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT COURSES FROM " + INSTRUCTOR_TABLE + " WHERE EMAIL = ?", new String[]{email});

        if (cursor.moveToFirst()) {
            @SuppressLint("Range") String coursesString = cursor.getString(cursor.getColumnIndex("COURSES"));
            courses = parseCoursesString(coursesString);
        }

        cursor.close();
        return courses;
    }

    private List<String> parseCoursesString(String coursesString) {
        List<String> courses = new ArrayList<>();
        if (coursesString != null && !coursesString.isEmpty()) {
            String[] coursesArray = coursesString.split(",");
            for (String course : coursesArray) {
                courses.add(course.trim());
            }
        }
        return courses;
    }


    public boolean updateCourse(int courseNumber, Course updatedCourse) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_COURSE_TITLE, updatedCourse.getTitle());
        contentValues.put(COLUMN_COURSE_MAIN_TOPICS, updatedCourse.getMainTopics());
        contentValues.put(COLUMN_PREREQUISITES, updatedCourse.getPrerequisites());
        contentValues.put(COLUMN_DAYS_OF_WEEK, updatedCourse.getDaysOfWeek());
        contentValues.put(COLUMN_TIME_OF_DAY, updatedCourse.getTimeOfDay());
        contentValues.put(COLUMN_IS_AVAILABLE, updatedCourse.getIs_Available());
        contentValues.put(COLUMN_AVAILABLE_UNTIL, updatedCourse.getAvailable_Until());


        int rowsAffected = sqLiteDatabase.update(COURSE_TABLE, contentValues, COLUMN_COURSE_NUMBER + " = ?",
                new String[]{String.valueOf(courseNumber)});

        return rowsAffected > 0;
    }
//    public boolean updateTraineeProfile(String email, TraineeUser updatedTraineeUser) {
//        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(aaa, updatedTraineeUser.getEmail());
//        contentValues.put(aaa, updatedTraineeUser.getFirstName());
//        contentValues.put(aaa, updatedTraineeUser.getLastName());
//        contentValues.put(aaa, updatedTraineeUser.getPassword());
//        contentValues.put(aaa, updatedTraineeUser.getMobileNumber());
//        contentValues.put(aaa, updatedTraineeUser.getAddress());
//
//
//
//        int rowsAffected = sqLiteDatabase.update(COURSE_TABLE, contentValues, COLUMN_COURSE_NUMBER + " = ?",
//                new String[]{String.valueOf(courseNumber)});
//
//        return rowsAffected > 0;
//    }


    public boolean checkAlreadyExist(String email) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String query = "Select EMAIL From USER Where EMAIL = '" + email + "'";
        System.out.println(query);
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);

        if (cursor.getCount() == 0) {
            return false;
        } else
            return true;
    }

    public void insertCustomer(User user) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("EMAIL", user.getEmail());
        contentValues.put("FIRST_NAME", user.getFirstName());
        contentValues.put("LAST_NAME", user.getLastName());
        contentValues.put("PASSWORD", user.getPassword());
        sqLiteDatabase.insert("USER", null, contentValues);
    }
    public void insertEnroll(String email, String courseNumber, String status ) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_EMAIL, email);
        contentValues.put(COLUMN_COURSE_NUMBER, courseNumber);
        contentValues.put(COLUMN_STATUS, status);

        sqLiteDatabase.insert(ENROLL_TABLE, null, contentValues);
    }

    public Cursor getAllCustomers() {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM USER", null);
    }

    public void insertCourse(Course course) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_COURSE_TITLE, course.getTitle());
        contentValues.put(COLUMN_COURSE_MAIN_TOPICS, course.getMainTopics());
        contentValues.put(COLUMN_PREREQUISITES, course.getPrerequisites());
        contentValues.put(COLUMN_DAYS_OF_WEEK, course.getDaysOfWeek());
        contentValues.put(COLUMN_TIME_OF_DAY, course.getTimeOfDay());
        long id = sqLiteDatabase.insert(COURSE_TABLE, null, contentValues);
        System.out.println("************************ " + id);
    }

    public List<Course> getAllCourses() {
        List<Course> courses = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + COURSE_TABLE, null);

        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") int courseNumber = cursor.getInt(cursor.getColumnIndex(COLUMN_COURSE_NUMBER));
                @SuppressLint("Range") String courseTitle = cursor.getString(cursor.getColumnIndex(COLUMN_COURSE_TITLE));
                @SuppressLint("Range") String courseMainTopics = cursor.getString(cursor.getColumnIndex(COLUMN_COURSE_MAIN_TOPICS));
                @SuppressLint("Range") String prerequisites = cursor.getString(cursor.getColumnIndex(COLUMN_PREREQUISITES));
                @SuppressLint("Range") String daysOfWeek = cursor.getString(cursor.getColumnIndex(COLUMN_DAYS_OF_WEEK));
                @SuppressLint("Range") String timeOfDay = cursor.getString(cursor.getColumnIndex(COLUMN_TIME_OF_DAY));
                @SuppressLint("Range") String isAvailable = cursor.getString(cursor.getColumnIndex(COLUMN_IS_AVAILABLE)); // Add the isAvailable value
                @SuppressLint("Range") String availableUntil = cursor.getString(cursor.getColumnIndex(COLUMN_AVAILABLE_UNTIL)); // Add the availableUntil value
                Course course = new Course(courseTitle, courseMainTopics, prerequisites, daysOfWeek, timeOfDay, isAvailable, availableUntil, courseNumber);
                courses.add(course);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return courses;
    }

    @SuppressLint("Range")
    public int getStudentsNumber(String courseNumber) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select count(*) from " + ENROLL_TABLE + " where " + COLUMN_COURSE_NUMBER + " = ? and status = \"approved\"", new String[]{courseNumber});
        int studentCount = -1;

        if (cursor.moveToFirst()) {
            studentCount = cursor.getInt(cursor.getColumnIndex("count(*)"));
        }

        cursor.close();
        return studentCount;
    }

    public List<Course> getEnrolledInCourses(String email) {
        List<Course> courses = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
//        select * from enorll, cource where cource.id = enroll.courceNumber and enroll.email = ? and enroll.status = "approved"

        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * from " + ENROLL_TABLE + ", " + COURSE_TABLE +
                " WHERE " + COURSE_TABLE + "." + COLUMN_COURSE_NUMBER + " = " + ENROLL_TABLE + "." + COLUMN_COURSE_NUMBER
                + " and " + ENROLL_TABLE + "." + COLUMN_EMAIL + " = ? and " + ENROLL_TABLE + "." + COLUMN_STATUS + " = 'approved'" , new String[]{email});

        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") int courseNumber = cursor.getInt(cursor.getColumnIndex(COLUMN_COURSE_NUMBER));
                @SuppressLint("Range") String courseTitle = cursor.getString(cursor.getColumnIndex(COLUMN_COURSE_TITLE));
                @SuppressLint("Range") String courseMainTopics = cursor.getString(cursor.getColumnIndex(COLUMN_COURSE_MAIN_TOPICS));
                @SuppressLint("Range") String prerequisites = cursor.getString(cursor.getColumnIndex(COLUMN_PREREQUISITES));
                @SuppressLint("Range") String daysOfWeek = cursor.getString(cursor.getColumnIndex(COLUMN_DAYS_OF_WEEK));
                @SuppressLint("Range") String timeOfDay = cursor.getString(cursor.getColumnIndex(COLUMN_TIME_OF_DAY));
                @SuppressLint("Range") String isAvailable = cursor.getString(cursor.getColumnIndex(COLUMN_IS_AVAILABLE)); // Add the isAvailable value
                @SuppressLint("Range") String availableUntil = cursor.getString(cursor.getColumnIndex(COLUMN_AVAILABLE_UNTIL)); // Add the availableUntil value
                Course course = new Course(courseTitle, courseMainTopics, prerequisites, daysOfWeek, timeOfDay, isAvailable, availableUntil);
                courses.add(course);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return courses;
    }

    public boolean updateTraineeProfile(TraineeUser updatedTraineeUser) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("FIRST_NAME", updatedTraineeUser.getFirstName());
        contentValues.put("LAST_NAME", updatedTraineeUser.getLastName());
        contentValues.put("PASSWORD", updatedTraineeUser.getPassword());
        contentValues.put("MOBILE_NUMBER", updatedTraineeUser.getMobileNumber());
        contentValues.put("ADDRESS", updatedTraineeUser.getAddress());
        contentValues.put("EMAIL", updatedTraineeUser.getEmail());


        int rowsAffected = sqLiteDatabase.update(TRAINEE_TABLE, contentValues, "EMAIL" + " = ?",
                new String[]{String.valueOf(updatedTraineeUser.getEmail())});

        return rowsAffected > 0;
    }
//    public boolean updateInstructorProfile(InstructorUser updatedInstructorUser) {
//        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("FIRST_NAME", updatedInstructorUser.getFirstName());
//        contentValues.put("LAST_NAME", updatedInstructorUser.getLastName());
//        contentValues.put("PASSWORD", updatedInstructorUser.getPassword());
//        contentValues.put("MOBILE_NUMBER", updatedInstructorUser.getMobileNumber());
//        contentValues.put("ADDRESS", updatedInstructorUser.getAddress());
//        contentValues.put("EMAIL", updatedInstructorUser.getEmail());
//        contentValues.put("SPECIALIZATION", updatedInstructorUser.getSpecialization());
//        contentValues.put("DEGREE", updatedInstructorUser.getDegree());
//        contentValues.put("COURSES", updatedInstructorUser.getCourses());
//
//
//        int rowsAffected = sqLiteDatabase.update(INSTRUCTOR_TABLE, contentValues, "EMAIL" + " = ?",
//                new String[]{String.valueOf(updatedInstructorUser.getEmail())});
//
//        return rowsAffected > 0;
//    }

    public List<Course> searchCoursesByName(String searchTerm) {
        List<Course> courses = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + COURSE_TABLE + " where " + COLUMN_COURSE_TITLE + " like '%" + searchTerm + "%' ", null);
        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") int courseNumber = cursor.getInt(cursor.getColumnIndex(COLUMN_COURSE_NUMBER));
                @SuppressLint("Range") String courseTitle = cursor.getString(cursor.getColumnIndex(COLUMN_COURSE_TITLE));
                @SuppressLint("Range") String courseMainTopics = cursor.getString(cursor.getColumnIndex(COLUMN_COURSE_MAIN_TOPICS));
                @SuppressLint("Range") String prerequisites = cursor.getString(cursor.getColumnIndex(COLUMN_PREREQUISITES));
                @SuppressLint("Range") String daysOfWeek = cursor.getString(cursor.getColumnIndex(COLUMN_DAYS_OF_WEEK));
                @SuppressLint("Range") String timeOfDay = cursor.getString(cursor.getColumnIndex(COLUMN_TIME_OF_DAY));
                @SuppressLint("Range") String isAvailable = cursor.getString(cursor.getColumnIndex(COLUMN_IS_AVAILABLE)); // Add the isAvailable value
                @SuppressLint("Range") String availableUntil = cursor.getString(cursor.getColumnIndex(COLUMN_AVAILABLE_UNTIL)); // Add the availableUntil value
                Course course = new Course(courseTitle, courseMainTopics, prerequisites, daysOfWeek, timeOfDay, isAvailable, availableUntil);
                courses.add(course);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return courses;

    }
}
