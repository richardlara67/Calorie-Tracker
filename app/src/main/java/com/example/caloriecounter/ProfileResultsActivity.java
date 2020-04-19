package com.example.caloriecounter;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ProfileResultsActivity extends AppCompatActivity {
    String name, gender, age, weight, height, activityLevel;
    Button btnConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_results);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //get results from Profile
        Intent profileResults = getIntent();
        name = profileResults.getStringExtra("name");
        age = profileResults.getStringExtra("age");
        weight = profileResults.getStringExtra("weight");
        height = profileResults.getStringExtra("height");
        gender = profileResults.getStringExtra("gender");
        activityLevel = profileResults.getStringExtra("activityLevel");

        //create variables for XML TextViews
        TextView userName = (TextView) findViewById(R.id.txtName);
        TextView userAge = (TextView) findViewById(R.id.txtAge);
        TextView userWeight = (TextView) findViewById(R.id.txtWeight);
        TextView userHeight = (TextView) findViewById(R.id.txtHeight);
        TextView userGender = (TextView) findViewById(R.id.txtGender);
        TextView userActivityLevel = (TextView) findViewById(R.id.txtActivityLevel);
        TextView calories = (TextView) findViewById(R.id.lblDailyCalorieIntake);

        //set text from values gather from Profile
        userName.setText(name);
        userAge.setText("Age: " + age);
        userWeight.setText("Weight: " + weight + " lbs");
        userHeight.setText("Height: " + height + " inches");
        userGender.setText("Gender: " + gender);
        userActivityLevel.setText("Activity level: " + activityLevel);

        double doubleCalories;
        String strCalories;

        //convert user data from string to integers
        double doubleAge = Double.parseDouble(age);
        double doubleHeight = Double.parseDouble(height);
        double doubleWeight = Double.parseDouble(weight);


        //calculate calories for men and women
        if(gender.equals("Male")) {
            doubleCalories = 66 + (6.23 * doubleWeight) + (12.7 * doubleHeight) - (6.8 * doubleAge);
            strCalories = Double.toString(doubleCalories);
            calories.setText("Your daily intake is " + strCalories + " calories");
        }
        else if(gender.equals("Female")) {
            doubleCalories = 655 + (4.35 * doubleWeight) + (4.7 * doubleHeight) - (4.7 * doubleAge);
            strCalories = Double.toString(doubleCalories);
            calories.setText("Your daily intake is " + strCalories + " calories");
        }
        else {
            strCalories = "0";
            calories.setText("Your daily intake is " + strCalories + " calories");
        }

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        btnConfirm = (Button) findViewById(R.id.btnConfirm);
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goMain();
            }
        });
    }
    public void goMain() {
        Intent intent = new Intent(ProfileResultsActivity.this, HomeActivity.class);
        this.startActivity(intent);
    }
}
