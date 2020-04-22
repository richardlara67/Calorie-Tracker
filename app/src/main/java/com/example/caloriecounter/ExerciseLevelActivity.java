package com.example.caloriecounter;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class ExerciseLevelActivity extends AppCompatActivity implements View.OnClickListener {
    private String name, gender, age, weight, height, activityLevel;
    private Spinner spnActivityLevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_level);
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
        spnActivityLevel = (Spinner) findViewById(R.id.spnActivityLevel);
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

        activityLevel = spnActivityLevel.getSelectedItem().toString();

        //pass data to ProfileResults activity
        Intent openResultsProfile = new Intent(ExerciseLevelActivity.this, ProfileResultsActivity.class);
        openResultsProfile.putExtra("name", name);
        openResultsProfile.putExtra("age", age);
        openResultsProfile.putExtra("weight", weight);
        openResultsProfile.putExtra("height", height);
        openResultsProfile.putExtra("gender", gender);
        openResultsProfile.putExtra("activityLevel", activityLevel);
        this.startActivity(openResultsProfile);
    }
}
