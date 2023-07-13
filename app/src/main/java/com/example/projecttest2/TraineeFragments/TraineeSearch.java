package com.example.projecttest2.TraineeFragments;
import static android.content.Context.MODE_PRIVATE;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.projecttest2.DataBaseHelper;
import com.example.projecttest2.Models.Course;
import com.example.projecttest2.R;

import java.util.List;
import java.util.stream.Collectors;

public class TraineeSearch extends Fragment {

    private ListView listViewCourses;
    private ArrayAdapter<Course> coursesAdapter;
    private SharedPreferences sharedPreferences;
    private Button button;
    private DataBaseHelper dbHelper;
    private EditText searchTerm;



    public TraineeSearch() {
        // Required empty public constructor
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_trainee_search, container, false);

        dbHelper = new DataBaseHelper(getActivity(),"Project", null , 1);
        button = view.findViewById(R.id.search1);
        searchTerm=view.findViewById(R.id.searchTerm);
        listViewCourses = view.findViewById(R.id.listView3);
        coursesAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1);
        listViewCourses.setAdapter(coursesAdapter);
        sharedPreferences = inflater.getContext().getSharedPreferences("loginPrefs", MODE_PRIVATE);
        listViewCourses.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Handle click event if needed
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchCourses();
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void SearchCourses() {
        String SearchTerm = searchTerm.getText().toString();
        // Retrieve all courses from the database
        List<Course> courses = dbHelper.searchCoursesByName(SearchTerm).stream().filter(c -> c.isCourseAvailable()).collect(Collectors.toList());
if(courses.isEmpty())
    Toast.makeText(requireContext(), "No results found matching your search", Toast.LENGTH_SHORT).show();
        // Clear the adapter
        coursesAdapter.clear();

        // Add courses to the adapter
        coursesAdapter.addAll(courses);

        // Notify the adapter about the data change
        coursesAdapter.notifyDataSetChanged();
    }
}
