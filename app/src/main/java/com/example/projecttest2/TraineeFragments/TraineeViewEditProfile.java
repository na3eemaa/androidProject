package com.example.projecttest2.TraineeFragments;

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
import com.example.projecttest2.Models.TraineeUser;
import com.example.projecttest2.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TraineeViewEditProfile#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TraineeViewEditProfile extends Fragment {

    private EditText email;
    private EditText firstName;
    private EditText lastName;
    private EditText password;
    private EditText mobileNumber;
    private EditText address;
    private Button btnUpdateProfile;
    private DataBaseHelper dbHelper;
    private SharedPreferences sharedPreferences;
    public TraineeViewEditProfile() {
        // Required empty public constructor
    }

    public static TraineeViewEditProfile newInstance() {
        return new TraineeViewEditProfile();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbHelper = new DataBaseHelper(requireContext(), "Project", null, 1);

    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_trainee_view_edit_profile, container, false);

        email = view.findViewById(R.id.emailText);
        firstName = view.findViewById(R.id.FirstNameText);
        lastName = view.findViewById(R.id.LastNameText);
        password = view.findViewById(R.id.PasswordText);
        mobileNumber = view.findViewById(R.id.mobileNumberText);
        address = view.findViewById(R.id.addressText);
        sharedPreferences = inflater.getContext().getSharedPreferences("loginPrefs", MODE_PRIVATE);
        fetchProfile();

        btnUpdateProfile = view.findViewById(R.id.btnUpdateProfile);
        btnUpdateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateProfile();
            }
        });

        return view;
    }

    private void fetchProfile() {
        String userEmail = sharedPreferences.getString("logged_user", null);

        TraineeUser traineeUser = dbHelper.getTraineeProfile(userEmail);

        email.setText(traineeUser.getEmail());
        firstName.setText(traineeUser.getFirstName());
        lastName.setText(traineeUser.getLastName());
        password.setText(traineeUser.getPassword());
        mobileNumber.setText(traineeUser.getMobileNumber());
        address.setText(traineeUser.getAddress());
    }

    private void updateProfile() {
        String updated_email = email.getText().toString();
        String updated_firstName = firstName.getText().toString();
        String updated_lastName = lastName.getText().toString();
        String updated_password = password.getText().toString();
        String updated_mobileNumber = mobileNumber.getText().toString();
        String updated_address = address.getText().toString();



        TraineeUser updatedTraineeUser = new TraineeUser(updated_email, updated_firstName ,updated_lastName , updated_password , updated_mobileNumber ,updated_address);
        boolean success = dbHelper.updateTraineeProfile(updatedTraineeUser);

        if (success) {
            Toast.makeText(requireContext(), "Profile updated successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(requireContext(), "Failed to update Profile", Toast.LENGTH_SHORT).show();
        }
    }
}
