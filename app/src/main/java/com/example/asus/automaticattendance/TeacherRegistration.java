package com.example.asus.automaticattendance;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class TeacherRegistration extends AppCompatActivity implements View.OnClickListener {

    DatabaseHelper databaseHelper;
    TranaferValues tranaferValues;
    EditText editText;
    Button button;
    Intent intent;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_registration);
        setTitle("Teacher Registration");

        editText=findViewById(R.id.TeacherNameEditTextId);
        spinner=findViewById(R.id.spinerId);
        button=findViewById(R.id.TeacherRegistrationButtonId);

        databaseHelper=new DatabaseHelper(this);
        tranaferValues=new TranaferValues();
        button.setOnClickListener(this);

        final String[] depertmentName=getResources().getStringArray(R.array.depertment);
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<>(TeacherRegistration.this,R.layout.sample_view,R.id.textViewId,depertmentName);
        spinner.setAdapter(arrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                tranaferValues.setDept(depertmentName[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


   
}
