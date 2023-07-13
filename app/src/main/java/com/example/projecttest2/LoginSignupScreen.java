package com.example.projecttest2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginSignupScreen extends AppCompatActivity {

    private EditText emailEditText, passwordEditText;
    private CheckBox rememberMeCheckbox;
    private Button loginButton, signupButton;
    private TextView signupTextView;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DataBaseHelper dataBaseHelper = new DataBaseHelper(this, "Project", null, 1);
        SQLiteDatabase sqLiteDatabase = dataBaseHelper.getReadableDatabase();
        sqLiteDatabase.close();
        setContentView(R.layout.screen_signup_login);

        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        rememberMeCheckbox = findViewById(R.id.rememberMeCheckbox);
        loginButton = findViewById(R.id.loginButton);
        signupButton = findViewById(R.id.signupButton);
        signupTextView = findViewById(R.id.signupTextView);

        sharedPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        boolean rememberMe = sharedPreferences.getBoolean("rememberMe", false);
        if (rememberMe) {
            String savedEmail = sharedPreferences.getString("email", "");
            emailEditText.setText(savedEmail);
            rememberMeCheckbox.setChecked(true);
        }
        // Set onClickListener for loginButton
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();

               authenticateUser(email, password);


            }
        });

        // Set onClickListener for signupButton
        Button signupButton = findViewById(R.id.signupButton);
        Intent i = new Intent(LoginSignupScreen.this, SignUp1.class);
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(i);
                finish();
            }
        });

        // Set onClickListener for signupTextView
        signupTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signupIntent = new Intent(LoginSignupScreen.this, SignUp1.class);
                startActivity(signupIntent);
            }
        });
    }
    private void authenticateUser(String email, String password) {
        boolean flag = false; // Flag to check if a user with a specific role has been found

        // Check for admin user
        DataBaseHelper dataBaseHelper = new DataBaseHelper(this, "Project", null, 1);
        SQLiteDatabase sqLiteDatabase = dataBaseHelper.getReadableDatabase();

        String queryAdmin = "SELECT * FROM USER WHERE EMAIL = ? AND PASSWORD = ?";
        String[] selectionArgsAdmin = {email, password};

        Cursor cursorAdmin = sqLiteDatabase.rawQuery(queryAdmin, selectionArgsAdmin);

        if (cursorAdmin.getCount() > 0) {
            if (rememberMeCheckbox.isChecked()) {
                saveUserCredentials(email, password);
            }
            Intent homeIntent = new Intent(LoginSignupScreen.this, MainAdminActivity.class);
            startActivity(homeIntent);
            finish();
            flag = true; // Set the flag to true since an admin user has been found
        }

        cursorAdmin.close();
        // Check for instructor user
        if (!flag) { // If an admin user has not been found
            DataBaseHelperInstructor dataBaseHelperInstructor = new DataBaseHelperInstructor(this, "Project", null, 1);
            SQLiteDatabase sqLiteDatabaseInstructor = dataBaseHelperInstructor.getReadableDatabase();

            String queryInstructor = "SELECT * FROM " + DataBaseHelper.INSTRUCTOR_TABLE + " WHERE EMAIL = ? AND PASSWORD = ?";
            String[] selectionArgsInstructor = {email, password};

            Cursor cursorInstructor = sqLiteDatabaseInstructor.rawQuery(queryInstructor, selectionArgsInstructor);

            if (cursorInstructor.getCount() > 0) {
                if (rememberMeCheckbox.isChecked()) {
                    saveUserCredentials(email, password);
                }
                Intent homeIntent = new Intent(LoginSignupScreen.this, MainInstructorActivity.class);
                startActivity(homeIntent);
                finish();
                flag = true; // Set the flag to true since an instructor user has been found
            }

            cursorInstructor.close();
        }

        // Check for trainee user
        if (!flag) { // If an admin or instructor user has not been found
            DataBaseHelperTrainee dataBaseHelperTrainee = new DataBaseHelperTrainee(this, "Project", null, 1);
            SQLiteDatabase sqLiteDatabaseTrainee = dataBaseHelperTrainee.getReadableDatabase();

            String queryTrainee = "SELECT * FROM TRAINEEUSER WHERE EMAIL = ? AND PASSWORD = ?";
            String[] selectionArgsTrainee = {email, password};

            Cursor cursorTrainee = sqLiteDatabaseTrainee.rawQuery(queryTrainee, selectionArgsTrainee);

            if (cursorTrainee.getCount() > 0) {
                if (rememberMeCheckbox.isChecked()) {
                    saveUserCredentials(email, password);
                }
                Intent homeIntent = new Intent(LoginSignupScreen.this, MainTraineeActivity.class);
                startActivity(homeIntent);
                finish();
                flag = true; // Set the flag to true since a trainee user has been found
            }

            cursorTrainee.close();
        }

        sqLiteDatabase.close();

        if (!flag) {
            Toast.makeText(LoginSignupScreen.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
        } else {
            saveSession(email);
        }
    }



    private void saveUserCredentials(String email, String password) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("email", email);
        editor.putString("password", password);
        editor.putBoolean("rememberMe", rememberMeCheckbox.isChecked());
        editor.apply();
    }

    private void saveSession(String email) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("logged_user", email);
        editor.apply();
    }
}
