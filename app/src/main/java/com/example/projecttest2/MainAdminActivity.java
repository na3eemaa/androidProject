package com.example.projecttest2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.projecttest2.AdminFragments.AdminCreateNewCourse;
import com.example.projecttest2.AdminFragments.AdminEditCourse;
import com.example.projecttest2.AdminFragments.AdminMakeCourseAvailable;
import com.example.projecttest2.AdminFragments.AdminReviewApplications;
import com.example.projecttest2.AdminFragments.AdminViewOfferingHistory;
import com.example.projecttest2.AdminFragments.AdminViewProfiles;
import com.google.android.material.navigation.NavigationView;

public class MainAdminActivity extends AppCompatActivity {
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
        setContentView(R.layout.activity_main_admin);

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
                   case R.id.menu_create_course:
                   {
                       Fragment createCourseFragment = new AdminCreateNewCourse();
                       FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                       transaction.replace(R.id.frameLayout, createCourseFragment);
                       transaction.commit();
                       drawerLayout.closeDrawer(GravityCompat.START);
                       Toast.makeText(MainAdminActivity.this, "Create selected", Toast.LENGTH_SHORT).show();
                       break;

                   }

                   case R.id.menu_make_course_available:
                   {
                       Fragment makeCourseAvailable = new AdminMakeCourseAvailable();
                       FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                       transaction.replace(R.id.frameLayout, makeCourseAvailable);
                       transaction.commit();
                       drawerLayout.closeDrawer(GravityCompat.START);
                       Toast.makeText(MainAdminActivity.this, "Make course available selected", Toast.LENGTH_SHORT).show();
                       break;
                   }

                   case R.id.menu_view_profiles:
                   {
                       Fragment viewProfiles = new AdminViewProfiles();
                       FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                       transaction.replace(R.id.frameLayout, viewProfiles);
                       transaction.commit();
                       drawerLayout.closeDrawer(GravityCompat.START);
                       Toast.makeText(MainAdminActivity.this, "view profiles selected", Toast.LENGTH_SHORT).show();
                       break;
                   }

                   case R.id.menu_edit_course:
                   {
                       Fragment editCourse = new AdminEditCourse();
                       FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                       transaction.replace(R.id.frameLayout, editCourse);
                       transaction.commit();
                       drawerLayout.closeDrawer(GravityCompat.START);
                       Toast.makeText(MainAdminActivity.this, "edit courses selected", Toast.LENGTH_SHORT).show();
                       break;
                   }

                   case R.id.menu_review_applications:
                   {
                       Fragment reviewApplications = new AdminReviewApplications();
                       FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                       transaction.replace(R.id.frameLayout, reviewApplications);
                       transaction.commit();
                       drawerLayout.closeDrawer(GravityCompat.START);
                       Toast.makeText(MainAdminActivity.this, "review applications selected", Toast.LENGTH_SHORT).show();
                       break;
                   }

                   case R.id.menu_view_offering_history:
                   {
                       Fragment viewOfferingHistory = new AdminViewOfferingHistory();
                       FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                       transaction.replace(R.id.frameLayout, viewOfferingHistory);
                       transaction.commit();
                       drawerLayout.closeDrawer(GravityCompat.START);
                       Toast.makeText(MainAdminActivity.this, "view history selected", Toast.LENGTH_SHORT).show();
                       break;
                   }
                   case R.id.menu_Logout:
                   {
                       Intent homeIntent = new Intent(MainAdminActivity.this, LoginSignupScreen.class);
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