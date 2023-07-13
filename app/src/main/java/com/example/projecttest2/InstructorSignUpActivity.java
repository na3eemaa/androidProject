package com.example.projecttest2;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.projecttest2.Models.InstructorUser;

import java.util.Arrays;
import java.util.List;


public class InstructorSignUpActivity extends AppCompatActivity {
    private ImageView profileImageView;
    private static final int PICK_IMAGE_REQUEST = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructor_sign_up);
        profileImageView = findViewById(R.id.profileImageView);

        Button SignupButton = (Button) findViewById(R.id.SignUp);
        SignupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText EmailEditText = (EditText) findViewById(R.id.EmailTextField);
                EditText FirstNameText = (EditText) findViewById(R.id.FirstNameText);
                EditText LastNameText = (EditText) findViewById(R.id.LastNameText);
                EditText PasswordText = (EditText) findViewById(R.id.PasswordText);
                EditText PasswordConfirmationText = (EditText) findViewById(R.id.ConfirmPasswordText);
                EditText mobileNumberText = (EditText) findViewById(R.id.mobileNumberField);
                EditText addressText = (EditText) findViewById(R.id.addressText);
                EditText specializationText = (EditText) findViewById(R.id.specializationText);
                Spinner degreeSpinner = findViewById(R.id.degreeSpinner);
                EditText coursesText = (EditText)findViewById(R.id.coursesText);



                String email = EmailEditText.getText().toString();
                String FirstName = FirstNameText.getText().toString();
                String LastName = LastNameText.getText().toString();
                String Password = PasswordText.getText().toString();
                String ConfirmPassword = PasswordConfirmationText.getText().toString();
                String address = addressText.getText().toString();
                String mobileNumber = mobileNumberText.getText().toString();
                String specialization = specializationText.getText().toString();
                String degree = degreeSpinner.getSelectedItem().toString();
                String coursesString = coursesText.getText().toString();
                List<String> courses = Arrays.asList(coursesString.split(","));

                DataBaseHelperInstructor dataBaseHelperInstructor = new DataBaseHelperInstructor(InstructorSignUpActivity.this, "Project", null, 1);
                boolean d = dataBaseHelperInstructor.checkAlreadyExist(email);
                if (email.isEmpty() || FirstName.isEmpty() || LastName.isEmpty() || Password.isEmpty() || ConfirmPassword.isEmpty() || address.isEmpty() || mobileNumber.isEmpty() || specialization.isEmpty() || degree.isEmpty() || degree.isEmpty()
                        || validateOtherFields(FirstName) == false || validateOtherFields(LastName) == false || validatePassowrd(Password) == false) {
                    if (email.isEmpty()) {
                        String myToast = "Email Field is empty.";
                        Toast toast = Toast.makeText(InstructorSignUpActivity.this, myToast, Toast.LENGTH_LONG);
                        toast.show();
                        EditText text = (EditText) findViewById(R.id.EmailTextField);
                        text.setBackgroundColor(Color.parseColor("#30FF0000"));
                    }
                    if (!validateOtherFields(FirstName)) {
                        String myToast = "First Name must have a Minimum 3 characters and maximum 20 characters.";
                        Toast toast = Toast.makeText(InstructorSignUpActivity.this, myToast, Toast.LENGTH_LONG);
                        toast.show();
                        EditText text = (EditText) findViewById(R.id.FirstNameText);
                        text.setBackgroundColor(Color.parseColor("#30FF0000"));
                    }
                    if (!validateOtherFields(LastName)) {
                        String myToast = "Last name must have a Minimum 3 characters and maximum 20 characters.";
                        Toast toast = Toast.makeText(InstructorSignUpActivity.this, myToast, Toast.LENGTH_LONG);
                        toast.show();
                        EditText text = (EditText) findViewById(R.id.LastNameText);
                        text.setBackgroundColor(Color.parseColor("#30FF0000"));
                    }
                    if (validatePassowrd(Password) == false) {
                        EditText text = (EditText) findViewById(R.id.PasswordText);
                        text.setBackgroundColor(Color.parseColor("#30FF0000"));
                        String myToast = "Password must have a Minimum 8 characters and maximum 15 characters. It must contain at least one number, one lowercase letter, and one uppercase letter.";
                        Toast toast = Toast.makeText(InstructorSignUpActivity.this, myToast, Toast.LENGTH_LONG);
                        toast.show();
                    }
                    if (ConfirmPassword.isEmpty()) {
                        Toast toast = Toast.makeText(InstructorSignUpActivity.this, "Please Confirm the password", Toast.LENGTH_LONG);
                        toast.show();
                        EditText text = (EditText) findViewById(R.id.ConfirmPasswordText);
                        text.setBackgroundColor(Color.parseColor("#30FF0000"));
                    }

                    Toast toast = Toast.makeText(InstructorSignUpActivity.this, "Please Fill all the Fields", Toast.LENGTH_SHORT);
                    toast.show();
                } else if (Password.compareTo(ConfirmPassword) != 0) {
                    Toast toast = Toast.makeText(InstructorSignUpActivity.this, "The Passwords Don't Match", Toast.LENGTH_SHORT);
                    toast.show();
                } else if (d == true) {
                    Toast toast = Toast.makeText(InstructorSignUpActivity.this, "Email Already Exists", Toast.LENGTH_SHORT);
                    toast.show();
                } else if (!email.contains("@") || !email.contains(".")) {
                    PasswordText.setBackgroundColor(Color.parseColor("#30FF0000"));
                    Toast toast = Toast.makeText(InstructorSignUpActivity.this, "Please Enter a valid Email", Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    EditText text1 = (EditText) findViewById(R.id.EmailTextField);
                    text1.setBackgroundColor(Color.parseColor("#30ffffff"));
                    text1.setText("");

                    EditText text2 = (EditText) findViewById(R.id.FirstNameText);
                    text2.setBackgroundColor(Color.parseColor("#30ffffff"));
                    text2.setText("");

                    EditText text3 = (EditText) findViewById(R.id.LastNameText);
                    text3.setBackgroundColor(Color.parseColor("#30ffffff"));
                    text3.setText("");

                    EditText text4 = (EditText) findViewById(R.id.PasswordText);
                    text4.setBackgroundColor(Color.parseColor("#30ffffff"));
                    text4.setText("");

                    EditText text5 = (EditText) findViewById(R.id.ConfirmPasswordText);
                    text5.setBackgroundColor(Color.parseColor("#30ffffff"));
                    text5.setText("");

                    EditText text6 = (EditText) findViewById(R.id.mobileNumberField);
                    text5.setBackgroundColor(Color.parseColor("#30ffffff"));
                    text5.setText("");

                    EditText text7 = (EditText) findViewById(R.id.addressText);
                    text5.setBackgroundColor(Color.parseColor("#30ffffff"));
                    text5.setText("");

                    EditText text8 = (EditText) findViewById(R.id.specializationText);
                    text5.setBackgroundColor(Color.parseColor("#30ffffff"));
                    text5.setText("");

                    Spinner text9 = (Spinner) findViewById(R.id.degreeSpinner);
                    text5.setBackgroundColor(Color.parseColor("#30ffffff"));
                    text5.setText("");

                    EditText text10 = (EditText) findViewById(R.id.coursesText);
                    text5.setBackgroundColor(Color.parseColor("#30ffffff"));
                    text5.setText("");


                  InstructorUser u = new InstructorUser(email,
                            FirstName,
                            LastName,
                            Password,
                            mobileNumber,
                            address,
                            specialization,
                            degree,
                            courses);

                    dataBaseHelperInstructor.insertCustomer(u);
                    Toast toast = Toast.makeText(InstructorSignUpActivity.this, "New User Added", Toast.LENGTH_SHORT);
                    toast.show();
                    Cursor alluser = dataBaseHelperInstructor.getAllCustomers();
                    System.out.println("PEOPLE NUMBER = " + alluser.getCount());
                }
            }
        });

        Button GoBackButton = (Button) findViewById(R.id.backButton);
        Intent i = new Intent(InstructorSignUpActivity.this, SignUp1.class);
        GoBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(i);
                finish();
            }
        });

        Button selectImageButton =(Button) findViewById(R.id.selectImageButton);

        selectImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectImageFromGallery(view);
            }
        });
    }
    // Method to open the gallery and select an image
    public void selectImageFromGallery(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    public boolean validatePassowrd(String password) {
        if (password.length() < 8 || password.length() > 15) {
            return false;
        }
        if (!password.matches(".*[0-9].*")) {
            return false;
        }
        if (!password.matches(".*[A-Z].*")) {
            return false;
        }
        if (!password.matches(".*[a-z].*")) {
            return false;
        }

        return true;

    }

    public boolean validateOtherFields(String field) {
        if (field.length() < 3 || field.length() > 20) {
            return false;
        }
        return true;
    }

    // Method to handle the selected image result
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            Uri imageUri = data.getData();
            // Display the selected image in the ImageView using Glide or any other image loading library
            Glide.with(this)
                    .load(imageUri)
                    .into(profileImageView);

        }

    }
}
