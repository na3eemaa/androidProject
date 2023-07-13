package com.example.projecttest2.AdminFragments;

import android.annotation.SuppressLint;
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
import com.example.projecttest2.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AdminEditCourse#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AdminEditCourse extends Fragment {

    private EditText etCourseNumber;
    private EditText etCourseTitle;
    private EditText etMainTopics;
    private EditText etPrerequisites;
    private EditText etDaysOfWeek;
    private EditText etTimeOfDay;
    private Button btnFetchCourse;
    private Button btnUpdateCourse;

    private DataBaseHelper dbHelper;

    public AdminEditCourse() {
        // Required empty public constructor
    }

    public static AdminEditCourse newInstance() {
        return new AdminEditCourse();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbHelper = new DataBaseHelper(requireContext(), "Project", null, 1);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_admin_edit_course, container, false);

        etCourseNumber = view.findViewById(R.id.etCourseNumber);
        etCourseTitle = view.findViewById(R.id.etCourseTitle);
        etMainTopics = view.findViewById(R.id.etMainTopics);
        etPrerequisites = view.findViewById(R.id.etPrerequisites);
        etDaysOfWeek = view.findViewById(R.id.etDaysOfWeek);
        etTimeOfDay = view.findViewById(R.id.etTimeOfDay);
        btnFetchCourse = view.findViewById(R.id.btnFetchCourse);
        btnUpdateCourse = view.findViewById(R.id.btnUpdateCourse);

        btnFetchCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchCourse();
            }
        });

        btnUpdateCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateCourse();
            }
        });

        return view;
    }

    private void fetchCourse() {
        int courseNumber = Integer.parseInt(etCourseNumber.getText().toString());
        Course course = dbHelper.getCourse(courseNumber);

        if (course != null) {
            etCourseTitle.setText(course.getTitle());
            etMainTopics.setText(course.getMainTopics());
            etPrerequisites.setText(course.getPrerequisites());
            etDaysOfWeek.setText(course.getDaysOfWeek());
            etTimeOfDay.setText(course.getTimeOfDay());
        } else {
            Toast.makeText(requireContext(), "Course not found", Toast.LENGTH_SHORT).show();
        }
    }

    private void updateCourse() {
        int courseNumber = Integer.parseInt(etCourseNumber.getText().toString());
        String courseTitle = etCourseTitle.getText().toString();
        String mainTopics = etMainTopics.getText().toString();
        String prerequisites = etPrerequisites.getText().toString();
        String daysOfWeek = etDaysOfWeek.getText().toString();
        String timeOfDay = etTimeOfDay.getText().toString();

        Course updatedCourse = new Course(courseTitle, mainTopics, prerequisites, daysOfWeek, timeOfDay,null,null);
        boolean success = dbHelper.updateCourse(courseNumber, updatedCourse);

        if (success) {
            Toast.makeText(requireContext(), "Course updated successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(requireContext(), "Failed to update course", Toast.LENGTH_SHORT).show();
        }
    }
}
