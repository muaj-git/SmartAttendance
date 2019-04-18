package com.example.asus.automaticattendance;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class TeacherMarks extends AppCompatActivity {

    TextView textView;
    Intent intent;
    TableLayout tableLayout;
    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_marks);
        setTitle("Attendance");

        textView=findViewById(R.id.TableHeaderId);
        Bundle bundle=getIntent().getExtras();
        if(bundle!=null) {


            String string= bundle.getString("name");
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("course", string);
            editor.apply();

            textView.setText(string);
        }
        databaseHelper=new DatabaseHelper(this);
        tableLayout=findViewById(R.id.mytable);
        databaseHelper.showTableLayout(tableLayout);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) { // manu
        MenuInflater menuInflater=getMenuInflater();

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String admin = preferences.getString("admin", "");

        if(admin.equals("tec")) {
            menuInflater.inflate(R.menu.menu_teacher_marks_layout, menu);
        }
        else {
            menuInflater.inflate(R.menu.menu_student_marks_layout, menu);
        }

       /* Bundle bundle=getIntent().getExtras();
        if(bundle!=null) {


            String string = bundle.getString("nm");

            if(string.equals("tec")) {
                menuInflater.inflate(R.menu.menu_teacher_marks_layout, menu);
            }
            else {
                menuInflater.inflate(R.menu.menu_student_marks_layout, menu);
            }
        }*/

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.aboutId){
            Toast.makeText(TeacherMarks.this,"About",Toast.LENGTH_SHORT).show();
        }
        if(item.getItemId()==R.id.setCodeId){
            intent=new Intent(TeacherMarks.this,SetCode.class);
            Bundle bundle=getIntent().getExtras();
            if(bundle!=null) {


                String string= bundle.getString("name");
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("codeCourse", string);
                editor.apply();

                textView.setText(string);
            }
           // intent.putExtra("name",string);
            startActivity(intent);
        }
        if(item.getItemId()==R.id.enterCodeId){
            intent=new Intent(TeacherMarks.this,EnterCode.class);

            // intent.putExtra("name",string);
            startActivity(intent);
        }
        if(item.getItemId()==R.id.addStudentId){
            intent=new Intent(TeacherMarks.this,AddStudent.class);

            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }


   public  void showTableLayout1(){

        int rows = 10;
        int colums  = 4;
        //tableLayout.setStretchAllColumns(true);
       // tableLayout.bringToFront();

        for(int i = 0; i < rows; i++){

            TableRow tr =  new TableRow(this);
            for(int j = 0; j < colums; j++)
            {
                TextView txtGeneric = new TextView(this);
                txtGeneric.setTextSize(18);

                txtGeneric.setText("Name"+" " );
                tr.addView(txtGeneric);
                /*txtGeneric.setHeight(30); txtGeneric.setWidth(50);   txtGeneric.setTextColor(Color.BLUE);*/
            }
            tableLayout.addView(tr);
        }
    }
}
