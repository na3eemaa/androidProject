package com.example.projecttest2.AdminFragments;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.projecttest2.DataBaseHelper;
import com.example.projecttest2.Models.Course;
import com.example.projecttest2.R;

import java.util.List;

public class AdminViewOfferingHistory extends Fragment {

    private ListView listViewCourses;
    private ArrayAdapter<Course> coursesAdapter;

    private DataBaseHelper dbHelper;

    public AdminViewOfferingHistory() {
        // Required empty public constructor
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_admin_view_offering_history, container, false);

        dbHelper = new DataBaseHelper(getActivity(),"Project", null , 1);

        listViewCourses = view.findViewById(R.id.listView4);
        coursesAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1);
        listViewCourses.setAdapter(coursesAdapter);
        listViewCourses.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Handle click event if needed
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadCourses();
    }

    private void loadCourses() {
        // Retrieve all courses from the database
        List<Course> courses = dbHelper.getAllCourses();

        for (Course course:courses) {
            int numOfStudents = dbHelper.getStudentsNumber(course.getCourseNumber().toString());
            course.setNumberOfStudents(numOfStudents);
        }

        // Clear the adapter
        coursesAdapter.clear();

        // Add courses to the adapter
        coursesAdapter.addAll(courses);

        // Notify the adapter about the data change
        coursesAdapter.notifyDataSetChanged();
    }
}
