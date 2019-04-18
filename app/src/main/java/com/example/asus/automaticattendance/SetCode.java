package com.example.asus.automaticattendance;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class SetCode extends AppCompatActivity implements View.OnClickListener{

    TextView textView;
    Button button;




    Timer timer;
    String genarated;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_code);
        setTitle("Genarate Code");

        textView=findViewById(R.id.showCodeId);
        button=findViewById(R.id.genarateButtonId);

        button.setOnClickListener(this);

        timer=new Timer();

    }

    @Override
    public void onClick(View v) {

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();

        Random rnd = new Random();
        int n = 100000 + rnd.nextInt(900000);
        genarated=Integer.toString(n);

        editor.putString("code", genarated);
        editor.apply();
        //tranaferValues2.setCode(genarated);
        textView.setText(genarated);
        Toast.makeText(SetCode.this, "Code Genarated Successfully", Toast.LENGTH_SHORT).show();

        timer.schedule(new TimerTask() {


            @Override
            public void run() {


                genarated="changed";
                change();


            }
            },60000
        );


    }
    void change(){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("code", genarated);
        //Toast.makeText(SetCode.this, "Code Successfully"+genarated, Toast.LENGTH_SHORT).show();
        editor.apply();

    }

}
