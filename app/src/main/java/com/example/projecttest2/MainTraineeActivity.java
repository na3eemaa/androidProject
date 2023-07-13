package com.example.projecttest2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar; // Correct import statement
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.projecttest2.AdminFragments.AdminCreateNewCourse;
import com.example.projecttest2.TraineeFragments.TraineeCenterCourseHistory;
import com.example.projecttest2.TraineeFragments.TraineeCourseHistory;
import com.example.projecttest2.TraineeFragments.TraineeEnrollInCourse;
import com.example.projecttest2.TraineeFragments.TraineeNotificationCenter;
import com.example.projecttest2.TraineeFragments.TraineeSearch;
import com.example.projecttest2.TraineeFragments.TraineeViewEditProfile;
import com.example.projecttest2.TraineeFragments.TraineeViewSelectedCourseDetails;
import com.google.android.material.navigation.NavigationView;

public class MainTraineeActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle drawerToggle;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_trainee);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu2_view_edit_profile: {
                        Fragment ViewEditProfile = new TraineeViewEditProfile();
                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frameLayout, ViewEditProfile);
                        transaction.commit();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        Toast.makeText(MainTraineeActivity.this, "view edit profile selected", Toast.LENGTH_SHORT).show();
                        break; // Add break statement to exit the switch block
                    }
                    case R.id.menu2_notification_center: {
                        Fragment NotificationCenter = new TraineeNotificationCenter();
                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frameLayout, NotificationCenter);
                        transaction.commit();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        Toast.makeText(MainTraineeActivity.this, "Notification Center selected", Toast.LENGTH_SHORT).show();
                        break; // Add break statement to exit the switch block
                    }
                    case R.id.menu2_search: {
                        Fragment Search = new TraineeSearch();
                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frameLayout, Search);
                        transaction.commit();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        Toast.makeText(MainTraineeActivity.this, "Search selected", Toast.LENGTH_SHORT).show();
                        break; // Add break statement to exit the switch block
                    }
                    case R.id.menu2_view_course_details: {
                        Fragment ViewCourseDetails = new TraineeViewSelectedCourseDetails();
                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frameLayout, ViewCourseDetails);
                        transaction.commit();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        Toast.makeText(MainTraineeActivity.this, "View Course Details selected", Toast.LENGTH_SHORT).show();
                        break; // Add break statement to exit the switch block
                    }
                    case R.id.menu2_enroll_in_course: {
                        Fragment EnrollInCourse = new TraineeEnrollInCourse();
                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frameLayout, EnrollInCourse);
                        transaction.commit();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        Toast.makeText(MainTraineeActivity.this, "Enroll In Course selected", Toast.LENGTH_SHORT).show();
                        break; // Add break statement to exit the switch block
                    }
                    case R.id.menu2_trainee_course_history: {
                        Fragment CourseHistory = new TraineeCourseHistory();
                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frameLayout, CourseHistory);
                        transaction.commit();
                        Toast.makeText(MainTraineeActivity.this, "Course History selected", Toast.LENGTH_SHORT).show();
                        break; // Add break statement to exit the switch block
                    }
                    case R.id.menu2_center_course_history: {
                        Fragment CenterCourseHistory = new TraineeCenterCourseHistory();
                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frameLayout, CenterCourseHistory);
                        transaction.commit();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        Toast.makeText(MainTraineeActivity.this, "Center Course History selected", Toast.LENGTH_SHORT).show();
                        break; // Add break statement to exit the switch block
                    }
                    case R.id.menu2_Logout:
                    {
                        Intent homeIntent = new Intent(MainTraineeActivity.this, LoginSignupScreen.class);
                        startActivity(homeIntent);
                        finish();
                        break;
                    }
                }
                return false;
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
