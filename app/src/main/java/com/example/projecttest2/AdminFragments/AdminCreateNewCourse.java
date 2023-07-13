package com.example.projecttest2.AdminFragments;

import android.os.Bundle;
import android.text.TextUtils;
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

public class AdminCreateNewCourse extends Fragment {

    private EditText etCourseTitle;
    private EditText etMainTopics;
    private EditText etPrerequisites;
    private EditText etDaysOfWeek;
    private EditText etTimeOfDay;
    private Button btnAddCourse;

    private DataBaseHelper dbHelper;

    public AdminCreateNewCourse() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_admin_create_new_course, container, false);

        dbHelper = new DataBaseHelper(getActivity(),"Project",null,1);

        etCourseTitle = view.findViewById(R.id.etCourseTitle);
        etMainTopics = view.findViewById(R.id.etMainTopics);
        etPrerequisites = view.findViewById(R.id.etPrerequisites);
        etDaysOfWeek = view.findViewById(R.id.etDaysOfWeek);
        etTimeOfDay = view.findViewById(R.id.etTimeOfDay);
        btnAddCourse = view.findViewById(R.id.btnAddCourse);

        btnAddCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCourse();
            }
        });

        return view;
    }

    private void addCourse() {
        String courseTitle = etCourseTitle.getText().toString().trim();
        String mainTopics = etMainTopics.getText().toString().trim();
        String prerequisites = etPrerequisites.getText().toString().trim();
        String daysOfWeek = etDaysOfWeek.getText().toString().trim();
        String timeOfDay = etTimeOfDay.getText().toString().trim();

        if (TextUtils.isEmpty(courseTitle) || TextUtils.isEmpty(mainTopics) ||
                TextUtils.isEmpty(prerequisites) || TextUtils.isEmpty(daysOfWeek) ||
                TextUtils.isEmpty(timeOfDay)) {
            Toast.makeText(getActivity(), "Please fill in all the fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Validate days of week
        if (!isValidDaysOfWeek(daysOfWeek)) {
            Toast.makeText(getActivity(), "Invalid days of week. Please enter one of the following: M/W, S/M, S/W, T/R, S, M, T, W, R", Toast.LENGTH_SHORT).show();
            return;
        }

        // Validate time of day
        if (!isValidTimeOfDay(timeOfDay)) {
            Toast.makeText(getActivity(), "Invalid time of day. Please enter a valid time.", Toast.LENGTH_SHORT).show();
            return;
        }

        Course course = new Course(courseTitle, mainTopics, prerequisites, daysOfWeek, timeOfDay,null,null);
        dbHelper.insertCourse(course);

        Toast.makeText(getActivity(), "Course added successfully", Toast.LENGTH_SHORT).show();

        // Clear the input fields
        etCourseTitle.getText().clear();
        etMainTopics.getText().clear();
        etPrerequisites.getText().clear();
        etDaysOfWeek.getText().clear();
        etTimeOfDay.getText().clear();
    }

    // Validate the days of week input
    private boolean isValidDaysOfWeek(String daysOfWeek) {
        // Define valid days of week values
        String[] validDays = {"M/W", "S/M", "S/W", "T/R", "S", "M", "T", "W", "R"};

        // Check if the input is contained in the valid days array
        for (String day : validDays) {
            if (day.equalsIgnoreCase(daysOfWeek)) {
                return true;
            }
        }
        return false;
    }

    // Validate the time of day input
    // Validate the time of day input
    private boolean isValidTimeOfDay(String timeOfDay) {
        // Define valid time of day values
        String[] validTimes = {"8-9", "9-10", "10-11" , "11-12","12-13","13-14","14-15","15-16"};

        // Check if the input is contained in the valid times array
        for (String time : validTimes) {
            if (time.equals(timeOfDay)) {
                return true;
            }
        }
        return false;
    }


}
