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
    String name, gender, age, weight, height, activityLevel, weightGoal;
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
        weightGoal = profileResults.getStringExtra("weightGoal");


        //create variables for XML TextViews
        TextView userName = (TextView) findViewById(R.id.txtName);
        TextView userAge = (TextView) findViewById(R.id.txtAge);
        TextView userWeight = (TextView) findViewById(R.id.txtWeight);
        TextView userHeight = (TextView) findViewById(R.id.txtHeight);
        TextView userGender = (TextView) findViewById(R.id.txtGender);
        TextView userActivityLevel = (TextView) findViewById(R.id.txtActivityLevel);
        TextView userFitnessGoal = (TextView) findViewById(R.id.txtFitnessGoal);
        TextView calories = (TextView) findViewById(R.id.lblCaloriesDay);

        //set text from values gathered from ExerciseLevel activity
        userName.setText(name);
        userAge.setText(getString(R.string.user_age) + ": " + age);
        userWeight.setText(getString(R.string.user_weight) + ": " + weight);
        userHeight.setText(getString(R.string.user_height) + ": " + height);
        userGender.setText(getString(R.string.user_gender) + ": " + gender);
        userActivityLevel.setText(getString(R.string.user_activity) + ": " + activityLevel);
        userFitnessGoal.setText(getString(R.string.user_fitnessGoal) + ": " + weightGoal);

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
            String strCalories = "0";
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

        switch (activityLevel) {
            case "I rarely exercise":
                activityLevel = "1.2";
                doubleActivityLevel = 1.20;
                break;
            case "1 to 3 times per week":
                activityLevel = "1.375";
                doubleActivityLevel = Double.parseDouble(activityLevel);
                break;
            case "3 to 5 times per week":
                activityLevel = "1.55";
                doubleActivityLevel = Double.parseDouble(activityLevel);
                break;
            case "6 to 7 days per week":
                activityLevel = "1.725";
                doubleActivityLevel = Double.parseDouble(activityLevel);
                break;
            case "Twice per day":
                activityLevel = "1.9";
                doubleActivityLevel = Double.parseDouble(activityLevel);
                break;
            default:
                activityLevel = "1.2";
                doubleActivityLevel = Double.parseDouble(activityLevel);
                break;
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

        TextView weightGoalHeading = (TextView) findViewById(R.id.txtFitnessGoalHeading);
        TextView dailyCalories = (TextView) findViewById(R.id.lblCaloriesDay);

        strMaintainWeight = Integer.toString(maintainWeight);
        strLoseWeight = Integer.toString(loseWeight);
        strGainWeight = Integer.toString(gainWeight);

        if(weightGoal.equals("Maintain weight")) {
            weightGoalHeading.setText(weightGoal);
            dailyCalories.setText(strMaintainWeight + " calories per day");
        }
        else if(weightGoal.equals("Lose weight")) {
            weightGoalHeading.setText(weightGoal);
            dailyCalories.setText(strLoseWeight + " calories per day");
        }
        else if(weightGoal.equals("Gain weight")) {
            weightGoalHeading.setText(weightGoal);
            dailyCalories.setText(strGainWeight + " calories per day");
        }
    }

}
