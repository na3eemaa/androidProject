package com.example.projecttest2.InstructorFragments;

import static android.content.Context.MODE_PRIVATE;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.projecttest2.DataBaseHelper;
import com.example.projecttest2.Models.Course;
import com.example.projecttest2.Models.InstructorUser;

import com.example.projecttest2.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InstructorViewEditProfile#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InstructorViewEditProfile extends Fragment {

    private EditText email;
    private EditText firstName;
    private EditText lastName;
    private EditText password;
    private EditText mobileNumber;
    private EditText address;
    private EditText specialization;
    private EditText degree;
    private EditText courses;

    private Button btnUpdateProfile;
    private DataBaseHelper dbHelper;
    private SharedPreferences sharedPreferences;
    public InstructorViewEditProfile() {
        // Required empty public constructor
    }

    public static InstructorViewEditProfile newInstance() {
        return new InstructorViewEditProfile();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbHelper = new DataBaseHelper(requireContext(), "Project", null, 1);

    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_instrcutor_view_edit_profile, container, false);

        email = view.findViewById(R.id.emailText);
        firstName = view.findViewById(R.id.FirstNameText);
        lastName = view.findViewById(R.id.LastNameText);
        password = view.findViewById(R.id.PasswordText);
        mobileNumber = view.findViewById(R.id.mobileNumberText);
        address = view.findViewById(R.id.addressText);
        specialization=view.findViewById(R.id.emailText);
        degree=view.findViewById(R.id.degreeText);
        courses=view.findViewById(R.id.Courses);

        sharedPreferences = inflater.getContext().getSharedPreferences("loginPrefs", MODE_PRIVATE);
//        fetchProfile();

        btnUpdateProfile = view.findViewById(R.id.btnUpdateProfile);
        btnUpdateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                updateProfile();
            }
        });

        return view;
    }

//    private void fetchProfile() {
//        String userEmail = sharedPreferences.getString("logged_user", null);
//
//        InstructorUser instructorUser = dbHelper.getInstructorProfile(userEmail);
//
//        email.setText(InstructorUser.getEmail());
//        firstName.setText(InstructorUser.getFirstName());
//        lastName.setText(InstructorUser.getLastName());
//        password.setText(InstructorUser.getPassword());
//        mobileNumber.setText(InstructorUser.getMobileNumber());
//        address.setText(InstructorUser.getAddress());
//        specialization.setText(InstructorUser.getSpecialization());
//        degree.setText(InstructorUser.getDegree());
//        courses.setText((CharSequence) InstructorUser.getCourses());
//    }
//
//    private void updateProfile() {
//        String updated_email = email.getText().toString();
//        String updated_firstName = firstName.getText().toString();
//        String updated_lastName = lastName.getText().toString();
//        String updated_password = password.getText().toString();
//        String updated_mobileNumber = mobileNumber.getText().toString();
//        String updated_address = address.getText().toString();
//        String updated_specialization = specialization.getText().toString();
//        String updated_degree= degree.getText().toString();
//        String updated_courses = courses.getText().toString();
//
//
//
//        InstructorUser updatedInstructorUser = new InstructorUser(updated_email, updated_firstName ,updated_lastName , updated_password , updated_mobileNumber ,updated_address, updated_specialization,updated_degree,updated_courses);
//        boolean success = dbHelper.updateInstructorProfile(updatedInstructorUser);
//
//        if (success) {
//            Toast.makeText(requireContext(), "Profile updated successfully", Toast.LENGTH_SHORT).show();
//        } else {
//            Toast.makeText(requireContext(), "Failed to update Profile", Toast.LENGTH_SHORT).show();
//        }
//    }
}
