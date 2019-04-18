package com.example.asus.automaticattendance;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class NewCource extends AppCompatActivity implements View.OnClickListener {

    DatabaseHelper databaseHelper;

    EditText editText;
    Button button;
    Intent intent;
    Spinner spinner1,spinner2;
    TranaferValues tranaferValues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_cource);
        setTitle("New Course");

        editText=findViewById(R.id.NewCourseNameEditTextId);
        spinner1=findViewById(R.id.spinerId1);
        spinner2=findViewById(R.id.spinerId2);
        button=findViewById(R.id.NewCourseButtonId);

        final String[] batchName=getResources().getStringArray(R.array.batch);
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<>(NewCource.this,R.layout.sample_view,R.id.textViewId,batchName);
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
        ArrayAdapter<String> arrayAdapter1=new ArrayAdapter<>(NewCource.this,R.layout.sample_view,R.id.textViewId,sectionName);
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

        databaseHelper=new DatabaseHelper(this);
        tranaferValues=new TranaferValues();
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String name=editText.getText().toString();

        if(name.equals("")){
            Toast.makeText(NewCource.this,"Please Fillup all the fields",Toast.LENGTH_SHORT).show();
        }
        else {


            long rowId=databaseHelper.insertNewCourceData(name,tranaferValues);
            if(rowId>0){
                Toast.makeText(getApplicationContext(),"Course Sucessfully Created",Toast.LENGTH_LONG ).show();
                intent=new Intent(NewCource.this,CourseList.class);
                //intent.putExtra("n",name);
                startActivity(intent);
            }else {
                Toast.makeText(getApplicationContext(),"Row incertation failed",Toast.LENGTH_SHORT ).show();
            }
        }
    }
}
