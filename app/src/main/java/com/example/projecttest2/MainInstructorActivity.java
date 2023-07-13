package com.example.projecttest2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.projecttest2.AdminFragments.AdminMakeCourseAvailable;
import com.example.projecttest2.InstructorFragments.InstructorViewEditProfile;
import com.example.projecttest2.InstructorFragments.InstrcutorViewSchedule;
import com.example.projecttest2.InstructorFragments.InstrcutorViewStudents;
import com.example.projecttest2.InstructorFragments.InstrcutorViewTaughtCourses;
import com.google.android.material.navigation.NavigationView;

public class MainInstructorActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle drawerToggle;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item))
        {
            return true;

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_instructor);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        drawerToggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(drawerToggle );
        drawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.menu3_view_edit_profile:
                    {
                        Fragment ViewEditProfile = new InstructorViewEditProfile();
                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frameLayout, ViewEditProfile);
                        transaction.commit();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        Toast.makeText(MainInstructorActivity.this, "View profile selected", Toast.LENGTH_SHORT).show();
                        break;                    }

                    case R.id.menu3_view_schedule:
                    {
                        Fragment ViewSchedule = new InstrcutorViewSchedule();
                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frameLayout, ViewSchedule);
                        transaction.commit();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        Toast.makeText(MainInstructorActivity.this, "View schedule selected", Toast.LENGTH_SHORT).show();
                        break;
                    }

                    case R.id.menu3_view_my_students:
                    {
                        Fragment ViewStudents = new InstrcutorViewStudents();
                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frameLayout, ViewStudents);
                        transaction.commit();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        Toast.makeText(MainInstructorActivity.this, "View students selected", Toast.LENGTH_SHORT).show();
                        break;
                    }

                    case R.id.menu3_view_courses_taught:
                    {
                        Fragment ViewTaughtCourses = new InstrcutorViewTaughtCourses();
                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frameLayout, ViewTaughtCourses);
                        transaction.commit();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        Toast.makeText(MainInstructorActivity.this, "View Courses Taught selected", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    case R.id.menu3_Logout:
                    {
                        Intent homeIntent = new Intent(MainInstructorActivity.this, LoginSignupScreen.class);
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
        if(drawerLayout.isDrawerOpen(GravityCompat.START))
        {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else
        {
            super.onBackPressed();

        }
    }
}