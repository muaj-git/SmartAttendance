package com.example.asus.automaticattendance;

import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

public class CourseList extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    Intent intent;
    ListView listView;
    TranaferValues tranaferValues;


    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_list);
        setTitle("Home");


        databaseHelper=new DatabaseHelper(this);

        listView=findViewById(R.id.listId);

          final String[] courseNames=databaseHelper.generate();
           // Log.d("msgeeeeeeeeeeeeeeeeeee",Names[0]);

       // final String[] courseNames=new String[]{"CT CSE35(D) MR X"};



           arrayAdapter = new ArrayAdapter<String>(CourseList.this, R.layout.sample_course_view, R.id.textViewId, courseNames);
           listView.setAdapter(arrayAdapter);




          listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                   String string = courseNames[position];

                    intent=new Intent(CourseList.this,TeacherMarks.class);



                    Bundle bundle=getIntent().getExtras();
                    if(bundle!=null) {


                        String str = bundle.getString("name");

                        if (str.equals("tec")) {
                            intent.putExtra("nm","tec");
                        }
                        else {
                            intent.putExtra("nm","std");
                        }
                    }
                    intent.putExtra("name",string);
                    startActivity(intent);
                }
            });

   }

    @Override
    public void onBackPressed() { //alertDialog

        AlertDialog.Builder alertDialogBuilder;
        alertDialogBuilder=new AlertDialog.Builder(CourseList.this);
        alertDialogBuilder.setIcon(R.drawable.question_mark_icon);
        alertDialogBuilder.setTitle(R.string.alert_title);
        alertDialogBuilder.setMessage(R.string.alert_message);
        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alertDialog=alertDialogBuilder.create();
        alertDialog.show();
    }





        @Override
        public boolean onCreateOptionsMenu (Menu menu){ // manu
            MenuInflater menuInflater = getMenuInflater();
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
            String admin = preferences.getString("admin", "");
            if(admin.equals("tec")) {
                menuInflater.inflate(R.menu.menu_techer_layout, menu);
            }
            else {
                menuInflater.inflate(R.menu.menu_student_layout, menu);
            }
           /* SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
            SearchView searchView = (SearchView) menu.findItem(R.id.menu_searchId).getActionView();
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
            searchView.setIconifiedByDefault(false); // Do not iconify the widget; expand it by default

            //return true;
            Bundle bundle=getIntent().getExtras();
            if(bundle!=null) {


                String string = bundle.getString("name");

                if(string.equals("tec")) {
                    menuInflater.inflate(R.menu.menu_techer_layout, menu);
                }
                else {
                    menuInflater.inflate(R.menu.menu_student_layout, menu);
                }
            }*/
            return super.onCreateOptionsMenu(menu);
        }

        @Override
        public boolean onOptionsItemSelected (MenuItem item){
            if (item.getItemId() == R.id.aboutId) {
                intent = new Intent(CourseList.this, About.class);

                startActivity(intent);
            }

            if (item.getItemId() == R.id.createId) {
                intent = new Intent(CourseList.this, NewCource.class);

                startActivity(intent);
            }
            return super.onOptionsItemSelected(item);
        }



}
