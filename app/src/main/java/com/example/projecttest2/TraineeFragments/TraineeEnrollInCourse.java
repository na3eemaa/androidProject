package com.example.projecttest2.TraineeFragments;
import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.projecttest2.DataBaseHelper;
import com.example.projecttest2.Models.Course;
import com.example.projecttest2.R;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TraineeEnrollInCourse extends Fragment {

    private EditText courseNumberEditText;
    private Button enrollButton;
    private DataBaseHelper dbHelper;
    private SharedPreferences sharedPreferences;

    public TraineeEnrollInCourse() {
        // Required empty public constructor
    }

    public static TraineeEnrollInCourse newInstance() {
        return new TraineeEnrollInCourse();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_trainee_enroll_in_course, container, false);

        dbHelper = new DataBaseHelper(getActivity(), "Project", null, 1);
        courseNumberEditText = view.findViewById(R.id.CourseNumber);
        enrollButton = view.findViewById(R.id.EnrollButton);
        sharedPreferences = inflater.getContext().getSharedPreferences("loginPrefs", MODE_PRIVATE);

        enrollButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enrollInCourse();
            }
        });

        return view;
    }

    private void enrollInCourse() {
        String courseNumberText = courseNumberEditText.getText().toString().trim();
        int courseNumber = Integer.parseInt(courseNumberText);

        Course course = dbHelper.getCourse(courseNumber);

        if (course != null && course.isCourseAvailable()) {
            String email = sharedPreferences.getString("logged_user", null);
            // TODO: fix
            dbHelper.insertEnroll(email, String.valueOf(courseNumber), "approved");
//            dbHelper.insertEnroll(email, String.valueOf(courseNumber), "pending");
            // Course is available for enrollment
            Toast.makeText(getActivity(), "Enrolling in course: " + courseNumber, Toast.LENGTH_SHORT).show();
            // enroll logic
        } else {
            // Course is not available for enrollment
            Toast.makeText(getActivity(), "Course " + courseNumber + " is not available for registration or not a valid course", Toast.LENGTH_SHORT).show();
        }
    }

}
