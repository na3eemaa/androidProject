package com.example.projecttest2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ui.AppBarConfiguration;

import com.example.projecttest2.databinding.ActivitySignUp2Binding;

public class SignUp1 extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivitySignUp2Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySignUp2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

//        setSupportActionBar(binding.toolbar);

//        NavController navController = Navigation.findNavController(SignUp1.this, R.id.nav_host_fragment_content_sign_up1);
//        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
//
//        binding.fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        Button adminButton = findViewById(R.id.adminButton);
        Button traineeButton = findViewById(R.id.traineeButton);
        Button instructorButton = findViewById(R.id.instructorButton);

        // Set onClickListener for admin Button
        adminButton = (Button)findViewById(R.id.adminButton);
        Intent i = new Intent(SignUp1.this,AdminSignUpActivity.class);
        adminButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(i);
                finish();
            }
        });



        // Set onClickListener for trainee Button
        traineeButton = (Button)findViewById(R.id.traineeButton);
        Intent j = new Intent(SignUp1.this,TraineeSignUpActivity.class);
        traineeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(j);
                finish();
            }
        });
        // Set onClickListener for instructor Button
        instructorButton = (Button)findViewById(R.id.instructorButton);
        Intent k = new Intent(SignUp1.this,InstructorSignUpActivity.class);
        instructorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(k);
                finish();
            }
        });
        Button GoBackButton = (Button) findViewById(R.id.backButton1);
        Intent l = new Intent(SignUp1.this, LoginSignupScreen.class);
        GoBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(l);
                finish();
            }
        });


   /* @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_sign_up);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }*/
}
}
