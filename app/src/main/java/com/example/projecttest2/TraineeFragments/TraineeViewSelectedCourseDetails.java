package com.example.projecttest2.TraineeFragments;

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
 * Use the {@link TraineeViewSelectedCourseDetails#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TraineeViewSelectedCourseDetails extends Fragment {

    private EditText etCourseNumber;
    private EditText etCourseTitle;
    private EditText etMainTopics;
    private EditText etPrerequisites;
    private EditText etDaysOfWeek;
    private EditText etTimeOfDay;
    private Button btnFetchCourse;
    private Button btnUpdateCourse;

    private DataBaseHelper dbHelper;

    public TraineeViewSelectedCourseDetails() {
        // Required empty public constructor
    }

    public static TraineeViewSelectedCourseDetails newInstance() {
        return new TraineeViewSelectedCourseDetails();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbHelper = new DataBaseHelper(requireContext(), "Project", null, 1);
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_trainee_view_selected_course_details, container, false);

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


}
