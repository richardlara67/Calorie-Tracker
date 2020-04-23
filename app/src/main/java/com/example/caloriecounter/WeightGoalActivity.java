package com.example.caloriecounter;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

public class WeightGoalActivity extends AppCompatActivity implements View.OnClickListener {
    private String name, gender, age, weight, height, activityLevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight_goal);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Get data
        Intent profileResults = getIntent();
        name = profileResults.getStringExtra("name");
        age = profileResults.getStringExtra("age");
        weight = profileResults.getStringExtra("weight");
        height = profileResults.getStringExtra("height");
        gender = profileResults.getStringExtra("gender");
        activityLevel = profileResults.getStringExtra("activityLevel");

        //create variables for XML TextViews
        TextView userName = (TextView) findViewById(R.id.txtUserName);
        userName.setText(name);

        Button btnSubmit = (Button) findViewById(R.id.submitBtn);
        btnSubmit.setOnClickListener(this);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        String weightGoal = "N/A";

        //Get resources for Radio Buttons
        RadioButton btnMaintainWeight = (RadioButton) findViewById(R.id.btnMaintainWeight);
        RadioButton btnLoseWeight = (RadioButton) findViewById(R.id.btnLoseWeight);
        RadioButton btnGainWeight = (RadioButton) findViewById(R.id.btnGainWeight);

        if(btnMaintainWeight.isChecked()) {
            weightGoal = "Maintain weight";
        }
        if(btnLoseWeight.isChecked()) {
            weightGoal = "Lose weight";
        }
        if(btnGainWeight.isChecked()) {
            weightGoal = "Gain weight";
        }

        //pass data to ProfileResults activity
        Intent openResultsProfile = new Intent(WeightGoalActivity.this, ProfileResultsActivity.class);
        openResultsProfile.putExtra("name", name);
        openResultsProfile.putExtra("age", age);
        openResultsProfile.putExtra("weight", weight);
        openResultsProfile.putExtra("height", height);
        openResultsProfile.putExtra("gender", gender);
        openResultsProfile.putExtra("activityLevel", activityLevel);
        openResultsProfile.putExtra("weightGoal", weightGoal);
        this.startActivity(openResultsProfile);
    }
}
