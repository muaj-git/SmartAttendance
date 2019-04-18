package com.example.asus.automaticattendance;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddStudent extends AppCompatActivity implements View.OnClickListener {

    DatabaseHelper databaseHelper;
    EditText editText;
    Button button;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);
        setTitle("Add Student");

        editText = findViewById(R.id.addEditTextId);
        button = findViewById(R.id.addButtonId);
        databaseHelper = new DatabaseHelper(this);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        String name = editText.getText().toString();

        if (name.equals("")) {
            Toast.makeText(AddStudent.this, "Please Fillup all the fields", Toast.LENGTH_SHORT).show();
        } else {


            long rowId = databaseHelper.insertAddStudentData(name);
            if (rowId > 0) {
                Toast.makeText(getApplicationContext(), "This Id Succesfully Addeded", Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(getApplicationContext(), "This Id is not valid or Alrady Added", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
