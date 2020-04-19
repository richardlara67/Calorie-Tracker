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
    Spinner spnActivityLevel;
    Button submitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        userName = (EditText) findViewById(R.id.txtEditName);
        userAge = (EditText) findViewById(R.id.txtEditAge);
        userWeight = (EditText) findViewById(R.id.txtEditWeight);
        userHeight = (EditText) findViewById(R.id.txtEditHeight);
        spnGender = (Spinner) findViewById(R.id.spnGender);
        spnActivityLevel = (Spinner) findViewById(R.id.spnActivityLevel);

        submitBtn = (Button) findViewById(R.id.submitBtn);
        submitBtn.setOnClickListener(this);

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
        String name, gender, age, weight, height, activityLevel;

        name = userName.getText().toString();
        age = userAge.getText().toString();
        weight = userWeight.getText().toString();
        height = userHeight.getText().toString();
        gender = spnGender.getSelectedItem().toString();
        activityLevel = spnActivityLevel.getSelectedItem().toString();

        //pass info to ProfileResults activity
        Intent profileResults = new Intent(Profile.this, ProfileResultsActivity.class);
        profileResults.putExtra("name", name);
        profileResults.putExtra("age", age);
        profileResults.putExtra("weight", weight);
        profileResults.putExtra("height", height);
        profileResults.putExtra("gender", gender);
        profileResults.putExtra("activityLevel", activityLevel);
        displayToast(name);
        this.startActivity(profileResults);
    }

    public void displayToast(String profileName) {
        Toast.makeText(this, "Welcome " + profileName + "!", Toast.LENGTH_LONG).show();
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
