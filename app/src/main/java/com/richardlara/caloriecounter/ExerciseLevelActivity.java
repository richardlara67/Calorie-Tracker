package com.RichardLara.caloriecounter;

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
import android.widget.Toast;

public class ExerciseLevelActivity extends AppCompatActivity implements View.OnClickListener {
    private String name, gender, age, weight, height, activityLevel;
    private RadioGroup exerciseLevelGroup;

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
        exerciseLevelGroup = (RadioGroup) findViewById(R.id.exerciseLevelGroup);

        TextView userName = (TextView) findViewById(R.id.txtUserName);
        userName.setText(name);

        Button btnSubmit = (Button) findViewById(R.id.submitBtn);
        btnSubmit.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(exerciseLevelGroup.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "Please select an option", Toast.LENGTH_SHORT).show();
        }
        else {
            //Get text of selected radio button from Exercise Radio Group
            int selectedId = exerciseLevelGroup.getCheckedRadioButtonId();
            View radioButton = exerciseLevelGroup.findViewById(selectedId);
            int idx = exerciseLevelGroup.indexOfChild(radioButton);
            RadioButton selectedOption = (RadioButton) exerciseLevelGroup.getChildAt(idx);
            activityLevel = selectedOption.getText().toString();

            //pass data to ProfileResults activity
            Intent openResultsProfile = new Intent(ExerciseLevelActivity.this, WeightGoalActivity.class);
            openResultsProfile.putExtra("name", name);
            openResultsProfile.putExtra("age", age);
            openResultsProfile.putExtra("weight", weight);
            openResultsProfile.putExtra("height", height);
            openResultsProfile.putExtra("gender", gender);
            openResultsProfile.putExtra("activityLevel", activityLevel);
            this.startActivity(openResultsProfile);
        }

    }
}
