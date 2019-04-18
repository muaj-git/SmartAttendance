package com.example.asus.automaticattendance;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EnterCode extends AppCompatActivity implements View.OnClickListener {

    EditText editText;
    Button button;
    TranaferValues tranaferValues;
    DatabaseHelper databaseHelper;
    Intent intent;


    SetCode setCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_code);
        setTitle("Enter Code");

        editText=findViewById(R.id.enterEditTextId);
        button=findViewById(R.id.enterButtonId);

        button.setOnClickListener(this);
       // setCode=new SetCode();
        //tranaferValues=new TranaferValues();
        databaseHelper=new DatabaseHelper(this);

    }

    @Override
    public void onClick(View v) {
              SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
            String string=editText.getText().toString();
            String name = preferences.getString("code", "");

            if(string.equals(name)){
                if(databaseHelper.update()==true) {
                    Toast.makeText(EnterCode.this, "Congratulations, Your Attendance is Counted", Toast.LENGTH_SHORT).show();
                    intent=new Intent(EnterCode.this,TeacherMarks.class);
                    //intent.putExtra("n",name);
                    startActivity(intent);
                }

                else
                    Toast.makeText(EnterCode.this, "Please Enter the Correct Code", Toast.LENGTH_SHORT).show();

            }
           else {
               Toast.makeText(EnterCode.this, "Please Enter the Correct Code", Toast.LENGTH_SHORT).show();
           }
    }
}
