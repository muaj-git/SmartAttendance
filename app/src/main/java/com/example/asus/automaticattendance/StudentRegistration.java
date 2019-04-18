package com.example.asus.automaticattendance;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class StudentRegistration extends AppCompatActivity implements View.OnClickListener {

    EditText editText1,editText2;
    DatabaseHelper databaseHelper;
    TranaferValues tranaferValues;
    Button button;
    Intent intent;
    Spinner spinner1,spinner2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_registration);
        setTitle("Student Registration");

        editText1=findViewById(R.id.StudentNameEditTextId);
        editText2=findViewById(R.id.StudentIDEditTextId);
        spinner1=findViewById(R.id.spinerId1);
        spinner2=findViewById(R.id.spinerId2);
        button=findViewById(R.id.StudentRegistrationButtonId);

        final String[] batchName=getResources().getStringArray(R.array.batch);
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<>(StudentRegistration.this,R.layout.sample_view,R.id.textViewId,batchName);
        spinner1.setAdapter(arrayAdapter);

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                tranaferValues.setBatch(batchName[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        final String[] sectionName=getResources().getStringArray(R.array.section);
        ArrayAdapter<String> arrayAdapter1=new ArrayAdapter<>(StudentRegistration.this,R.layout.sample_view,R.id.textViewId,sectionName);
        spinner2.setAdapter(arrayAdapter1);

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                tranaferValues.setSection(sectionName[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        button.setOnClickListener(this);
        databaseHelper=new DatabaseHelper(this);
        tranaferValues=new TranaferValues();

    }



    @Override
    public void onClick(View v) {
        String name=editText1.getText().toString();
        String id=editText2.getText().toString();

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("stdId", id);
        editor.putString("admin", "std");
        editor.apply();

        if(name.equals("") || id.equals("")){
            Toast.makeText(StudentRegistration.this,"Please Fillup all the fields",Toast.LENGTH_SHORT).show();
        }
        else {


            tranaferValues.setName(name);
            tranaferValues.setId(id);


            long rowId=databaseHelper.insertStudentData(tranaferValues);
            if(rowId>0){
                Toast.makeText(getApplicationContext(),"Wellcome",Toast.LENGTH_LONG ).show();
                intent=new Intent(StudentRegistration.this,CourseList.class);
                intent.putExtra("name","std");
                startActivity(intent);
            }else {
                Toast.makeText(getApplicationContext(),"Row incertation failed",Toast.LENGTH_SHORT ).show();
            }
        }
    }
}
