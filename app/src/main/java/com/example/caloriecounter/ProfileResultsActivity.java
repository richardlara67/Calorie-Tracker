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
    int maintainWeight, loseWeight, gainWeight;

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

        //set text from values gathered from ExerciseLevel activity
        userName.setText(name);
        userAge.setText("Age: " + age);
        userWeight.setText("Weight: " + weight + " lbs");
        userHeight.setText("Height: " + height + " inches");
        userGender.setText("Gender: " + gender);
        userActivityLevel.setText("Activity level: " + activityLevel);

        //displays calories for men and women
        if (gender.equals("Male")) {
            //calls function to calculate calories for males
            calculateCalories("Male");

            //display results
            displayWeightResults();
        }
        else if(gender.equals("Female")) {
            //calls function to calculate calories for males
            calculateCalories("Female");

            //display results
            displayWeightResults();
        }
        else {
            String strCalories;
            strCalories = "0";
            calories.setText(strCalories + " calories");
        }

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Button btnConfirm = (Button) findViewById(R.id.btnConfirm);
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

    private double calculateActivityLevel() {
        double doubleActivityLevel;

        if(activityLevel.equals("little or no exercise")) {
            activityLevel = "1.2";
            doubleActivityLevel = Double.parseDouble(activityLevel);
        }
        else if(activityLevel.equals("light exercise")) {
            activityLevel = "1.375";
            doubleActivityLevel = Double.parseDouble(activityLevel);
        }
        else if(activityLevel.equals("moderate exercise")) {
            activityLevel = "1.55";
            doubleActivityLevel = Double.parseDouble(activityLevel);
        }
        else if(activityLevel.equals("heavy exercise")) {
            activityLevel = "1.725";
            doubleActivityLevel = Double.parseDouble(activityLevel);
        }
        else if(activityLevel.equals("vigorous exercise")) {
            activityLevel = "1.9";
            doubleActivityLevel = Double.parseDouble(activityLevel);
        }
        else {
            activityLevel = "1.2";
            doubleActivityLevel = Double.parseDouble(activityLevel);
        }

        return doubleActivityLevel;
    }

    private void calculateCalories(String gender) {
        int calories;
        double activityLevel, doubleCalories = 0.0;

        activityLevel = calculateActivityLevel();

        //convert user data from string to double
        double doubleAge = Double.parseDouble(age);
        double doubleHeight = Double.parseDouble(height);
        double doubleWeight = Double.parseDouble(weight);

        if (gender.equals("Male")) {
            doubleCalories = (66 + (6.23 * doubleWeight) + (12.7 * doubleHeight) - (6.8 * doubleAge)) * activityLevel;
        }
        else if(gender.equals("Female")) {
            doubleCalories = (655 + (4.35 * doubleWeight) + (4.7 * doubleHeight) - (4.7 * doubleAge)) * activityLevel;

        }
        else {
            doubleCalories = 0.0;
        }

        calories = (int) Math.round(doubleCalories);

        maintainWeight = calories;
        loseWeight = calories - 500;
        gainWeight = calories + 500;
    }

    private void displayWeightResults() {
        String strMaintainWeight, strLoseWeight, strGainWeight;

        TextView calories = (TextView) findViewById(R.id.lblDailyCalorieIntake);
        TextView caloriesLoseWeight = (TextView) findViewById(R.id.lblLoseWeight);
        TextView caloriesGainWeight = (TextView) findViewById(R.id.lblGainWeight);

        strMaintainWeight = Integer.toString(maintainWeight);
        strLoseWeight = Integer.toString(loseWeight);
        strGainWeight = Integer.toString(gainWeight);

        calories.setText(strMaintainWeight + " calories");
        caloriesLoseWeight.setText(strLoseWeight + " calories");
        caloriesGainWeight.setText(strGainWeight + " calories");
    }

}
