package com.example.asus.automaticattendance;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button button1,button2;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1=findViewById(R.id.RegisterStudentId);
        button2=findViewById(R.id.RegisterTeacherId);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.RegisterStudentId){
            intent=new Intent(MainActivity.this,StudentRegistration.class);

            startActivity(intent);
        }
        if(v.getId()==R.id.RegisterTeacherId){
            intent=new Intent(MainActivity.this,TeacherRegistration.class);

            startActivity(intent);
        }
    }
}
