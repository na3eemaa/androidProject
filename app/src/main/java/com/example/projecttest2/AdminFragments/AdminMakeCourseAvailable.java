package com.example.projecttest2.AdminFragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projecttest2.DataBaseHelper;
import com.example.projecttest2.Models.Course;
import com.example.projecttest2.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AdminMakeCourseAvailable#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AdminMakeCourseAvailable extends Fragment {

    private EditText courseNumberTextView;
    private CalendarView calendarView;
    private Button makeAvailableButton;
    private int selectedCourseNumber = -1;
    private DataBaseHelper dbHelper;
    private StringBuilder date = new StringBuilder();

    public AdminMakeCourseAvailable() {
        // Required empty public constructor
    }

    public static AdminMakeCourseAvailable newInstance() {
        return new AdminMakeCourseAvailable();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbHelper = new DataBaseHelper(requireContext(), "Project", null, 1);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_admin_make_course_available, container, false);
        courseNumberTextView = view.findViewById(R.id.course_number);
        calendarView = view.findViewById(R.id.btn_select_date);
        makeAvailableButton = view.findViewById(R.id.btn_make_available);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                // You can handle the selected date here if needed
                // For this example, we won't do anything special with the date
                date.setLength(0);
                month++;
                date.append(String.format("%s-%s-%s", year, month < 10 ? "0" + month : month, dayOfMonth < 10 ? "0" + dayOfMonth: dayOfMonth));
            }
        });

        makeAvailableButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedCourseNumber = Integer.parseInt(courseNumberTextView.getText().toString());
//                courseNumberTextView.setText("Course Number: " + selectedCourseNumber);

                if (selectedCourseNumber != -1) {
//                    long selectedDateInMillis = calendarView.getDate();
//                    Date selectedDate = new Date(selectedDateInMillis);

                    // Format the selected date in your desired format (e.g., "yyyy-MM-dd")
//                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
//                    String formattedDate = dateFormat.format(selectedDate);

                    // Update the course availability in the database
                    DataBaseHelper dbHelper = new DataBaseHelper(getContext(), "Project", null, 1);
                    Course course = dbHelper.getCourse(selectedCourseNumber);
                    if (course != null) {
                        course.setIs_Available("true");
                        course.setAvailable_Until(date.toString());
                        boolean isUpdated = dbHelper.updateCourse(selectedCourseNumber, course);
                        if (isUpdated) {
                            // Course availability updated successfully
                            Toast.makeText(getContext(), "Course is now available until " + date.toString(), Toast.LENGTH_SHORT).show();
                        } else {
                            // Failed to update course availability
                            Toast.makeText(getContext(), "Failed to update course availability", Toast.LENGTH_SHORT).show();
                        }
                    }
                } else {
                    // No course number is selected
                    Toast.makeText(getContext(), "Please select a course number first", Toast.LENGTH_SHORT).show();
                }
                // Reset the selected course number
                selectedCourseNumber = -1;
                System.out.println(dbHelper.getAllCourses());
            }
        });

        return view;
    }
}

