package com.example.caloriecounter;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Profile extends AppCompatActivity implements View.OnClickListener {

    EditText userName;
    EditText userAge;
    EditText userWeight;
    EditText userHeight;
    Spinner spnGender;
    Button submitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //create variables for XML TextViews
        userName = (EditText) findViewById(R.id.txtEditName);
        userAge = (EditText) findViewById(R.id.txtEditAge);
        userWeight = (EditText) findViewById(R.id.txtEditWeight);
        userHeight = (EditText) findViewById(R.id.txtEditHeight);
        spnGender = (Spinner) findViewById(R.id.spnGender);

        submitBtn = (Button) findViewById(R.id.submitBtn);
        submitBtn.setOnClickListener(this);

        FloatingActionButton fab = findViewById(R.id.fab);
    }

    @Override
    public void onClick(View v) {
        String name, gender, age, weight, height;

        //get user input
        name = userName.getText().toString();
        age = userAge.getText().toString().trim();
        weight = userWeight.getText().toString().trim();
        height = userHeight.getText().toString().trim();
        gender = spnGender.getSelectedItem().toString();

        //validate user input is filled out
        if(!name.isEmpty() && !age.isEmpty() && !weight.isEmpty() && !height.isEmpty() && !gender.equals("Gender")) {
            //pass info to ExerciseLevelActivity activity
            Intent openExerciseLevel = new Intent(Profile.this, ExerciseLevelActivity.class);
            openExerciseLevel.putExtra("name", name);
            openExerciseLevel.putExtra("age", age);
            openExerciseLevel.putExtra("weight", weight);
            openExerciseLevel.putExtra("height", height);
            openExerciseLevel.putExtra("gender", gender);
            displayToast(name);
            this.startActivity(openExerciseLevel);
        }
        else {
            Toast.makeText(this, "Fields cannot be empty!", Toast.LENGTH_SHORT).show();
        }
    }

    public void displayToast(String profileName) {
        Toast.makeText(this, "Welcome " + profileName + "!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch(item.getItemId()) {
            case R.id.mnuMain:
                startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                return true;
            case R.id.mnuExit:
                finish();
            default:
                return super.onOptionsItemSelected(item);
        }
    }



}
