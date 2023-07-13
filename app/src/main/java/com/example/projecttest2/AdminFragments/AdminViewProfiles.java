package com.example.projecttest2.AdminFragments;

import android.database.Cursor;
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
import com.example.projecttest2.Models.User;
import com.example.projecttest2.R;

import java.util.ArrayList;
import java.util.List;

public class AdminViewProfiles extends Fragment {

    private ListView listViewProfiles;
    private ArrayAdapter<String> profilesAdapter;

    private DataBaseHelper dbHelper;

    public AdminViewProfiles() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_admin_view_profiles, container, false);

        dbHelper = new DataBaseHelper(getActivity(), "Project", null, 1);

        listViewProfiles = view.findViewById(R.id.listView);
        profilesAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1);
        listViewProfiles.setAdapter(profilesAdapter);
        listViewProfiles.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
        loadProfiles();
    }

    private void loadProfiles() {
        // Retrieve all instructor and trainee profiles from the database
        List<Object> profiles = new ArrayList<>();
        profiles.addAll(dbHelper.getAllInstructors());
        profiles.addAll(dbHelper.getAllTrainees());

        // Clear the adapter
        profilesAdapter.clear();

        // Add profile names to the adapter
        for (Object profile : profiles) {
//            String profileName = profile.getFirstName() + " " + profile.getLastName();
            profilesAdapter.add(profile.toString());
        }

        // Notify the adapter about the data change
        profilesAdapter.notifyDataSetChanged();
    }
}
