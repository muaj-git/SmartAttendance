package com.example.asus.automaticattendance;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

public class DatabaseHelper extends SQLiteOpenHelper {



    private static final String DATABASE_NAME="attendanceManager.db";
    private static final int VERSON_NUMBER=13;

    private static final String TABLE1="student_registration";
    private static final String TABLE2="teacher_registration";
    private static final String TABLE3="cource";
    private static final String TABLE4="add_student";
    private static final String TABLE5="marks";

    private static final String ID="Id";
    private static final String NAME="Name";

    private static final String COURSE_PK="course_fk_id";
    private static final String STUDENT_PK="student_fk_id";
    private static final String STUDENT_ID="student_id";
    private static final String TEACHER_PK="teacher_fk_id";
    private static final String CODE="code";

    private static final String BATCH="Batch";
    private static final String SECTION="Section";
   // private static final String PASSWARD="Password";

    //private static final String ID2="Fk_Id";
    private static final String DEPERTMENT="Depertment";
    private static final String ATTENDANCE="Attendance";
    private static final String MARKS="Marks";


    private static final String CREATE_TABLE1="CREATE TABLE "+TABLE1+"("+ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+STUDENT_ID+" TEXT,"+NAME+" VARCHAR(255) NOT NULL,"+BATCH+" TEXT,"+SECTION+" TEXT )";
    private static final String CREATE_TABLE2="CREATE TABLE "+TABLE2+"("+ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+NAME+" VARCHAR(255),"+DEPERTMENT+" TEXT )";
    private static final String CREATE_TABLE3="CREATE TABLE "+TABLE3+"("+ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+NAME+" VARCHAR(255),"+BATCH+" TEXT,"+SECTION+" TEXT,"+TEACHER_PK+" TEXT)";
    private static final String CREATE_TABLE4="CREATE TABLE "+TABLE4+"("+ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+STUDENT_PK+" TEXT,"+COURSE_PK+" TEXT,"+ATTENDANCE+" INTEGER,"+CODE+" INTEGER)";

    private static final String CREATE_TABLE5="CREATE TABLE "+TABLE5+"("+ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+NAME+" VARCHAR(255),"+ATTENDANCE+" INTEGER ,"+MARKS+" INTEGER )";


    // private static final  String CREATE_TABLE2="CREATE TABLE "+TABLE_NAME2+"("+PK+" INTEGER PRIMARY KEY AUTOINCREMENT,"+ID2+" INTEGER NOT NULL,"+USER_NAME2+" TEXT NOT NULL,"+PASSWARD2+" TEXT NOT NULL," + "FOREIGN KEY (" + ID2 + ") REFERENCES " + TABLE_NAME + "(" + ID + ") )";


    private Context context;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSON_NUMBER);
        this.context=context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
            try{
                db.execSQL(CREATE_TABLE1);
                db.execSQL(CREATE_TABLE2);
                db.execSQL(CREATE_TABLE3);
                db.execSQL(CREATE_TABLE4);
                db.execSQL(CREATE_TABLE5);
               // Toast.makeText(context,"OnCreate is called",Toast.LENGTH_LONG).show();
            }catch (Exception e){
                Log.d("ex: ","e");
                Toast.makeText(context,"Exception: "+e,Toast.LENGTH_LONG).show();
            }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try{
            db.execSQL("DROP TABLE IF EXISTS "+TABLE1);
            db.execSQL("DROP TABLE IF EXISTS "+TABLE2);
            db.execSQL("DROP TABLE IF EXISTS "+TABLE3);
            db.execSQL("DROP TABLE IF EXISTS "+TABLE4);
            db.execSQL("DROP TABLE IF EXISTS "+TABLE5);
            onCreate(db);

           // Toast.makeText(context,"OnUpgrade is called",Toast.LENGTH_LONG).show();
        }catch (Exception e){
            Toast.makeText(context,"Exception: "+e,Toast.LENGTH_LONG).show();
        }
    }
    public long insertStudentData(TranaferValues tranaferValues){

        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();

        contentValues.put(NAME,tranaferValues.getName());
        contentValues.put(STUDENT_ID,tranaferValues.getId());
        contentValues.put(BATCH,tranaferValues.getBatch());
        contentValues.put(SECTION,tranaferValues.getSection());



        //contentValues.put(USER_NAME,tranaferValues.getUsername());
        //contentValues.put(PASSWARD,tranaferValues.getPassword());

        long rowId=sqLiteDatabase.insert(TABLE1,null,contentValues);



        return rowId;
    }
    public long insertTeacherData(TranaferValues tranaferValues){

        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(NAME,tranaferValues.getTecName());
        contentValues.put(DEPERTMENT,tranaferValues.getDept());

        //contentValues.put(USER_NAME,tranaferValues.getUsername());
        //contentValues.put(PASSWARD,tranaferValues.getPassword());

        long rowId=sqLiteDatabase.insert(TABLE2,null,contentValues);

        return rowId;
    }



    long rowId;
    public long insertAddStudentData(String name){

        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        String course = preferences.getString("course", "");
        //String code = preferences.getString("code", "");

        Cursor cursor=sqLiteDatabase.rawQuery("SELECT * FROM "+TABLE1,null);

        if(cursor.getCount()==0){

        }else {
            while (cursor.moveToNext()){
                String id=cursor.getString(1);


                if(id.equals(name)){

                    Cursor cursor1=sqLiteDatabase.rawQuery("SELECT * FROM "+TABLE4,null);

                    if(cursor1.getCount()==0){
                        SQLiteDatabase sqLiteDatabase1=this.getWritableDatabase();
                        ContentValues contentValues=new ContentValues();
                        contentValues.put(STUDENT_PK,name);
                        contentValues.put(COURSE_PK, course);


                        rowId=sqLiteDatabase1.insert(TABLE4,null,contentValues);
                        return rowId;

                    }else {

                        while (cursor1.moveToNext()){
                            String id1=cursor1.getString(1);
                            String crs=cursor1.getString(2);


                            if(id1.equals(name)&&crs.equals(course)){

                                return rowId;
                            }


                        }


                                SQLiteDatabase sqLiteDatabase1=this.getWritableDatabase();
                                ContentValues contentValues=new ContentValues();
                                contentValues.put(STUDENT_PK,name);
                                contentValues.put(COURSE_PK, course);


                                rowId=sqLiteDatabase1.insert(TABLE4,null,contentValues);
                                return rowId;


                    }


                }

            }
        }
        return rowId;
    }
    public boolean update(){
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        String course = preferences.getString("course", "");
        String code = preferences.getString("code", "");
        String stdId = preferences.getString("stdId", "");
        String codeCourse = preferences.getString("codeCourse", "");

        Cursor cursor=sqLiteDatabase.rawQuery("SELECT * FROM "+TABLE4,null);
        int ck=0;
        if(cursor.getCount()==0){
            return false;
        }else {
            while (cursor.moveToNext()){
                String id1=cursor.getString(1);
                String id2=cursor.getString(2);



                if(id1.equals(stdId) &&id2.equals(codeCourse)){
                    SQLiteDatabase sqLiteDatabase1=this.getWritableDatabase();
                    ContentValues contentValues=new ContentValues();
                    String id=cursor.getString(0);
                    int id3=cursor.getInt(3);
                    String id4=cursor.getString(4);
                    contentValues.put(ID,id);
                    contentValues.put(STUDENT_PK,id1);
                    contentValues.put(COURSE_PK, id2);
                    if(id3<0) {
                        id3 = 1;
                    }
                    else if(code.equals(id4)){
                        return false;
                    }
                    else {
                        id3++;
                    }
                    contentValues.put(ATTENDANCE, id3);

                    contentValues.put(CODE, code);
                    sqLiteDatabase1.update(TABLE4,contentValues,ID+" = ?",new String[]{id});

                    ck=1;
                    break;

                }


            }
        }
        if(ck==0)
            return false;
        return true;


    }

    public long insertNewCourceData(String name,TranaferValues tranaferValues){

        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        String tecId = preferences.getString("tecId", "");

        ContentValues contentValues=new ContentValues();
        contentValues.put(NAME,name);
        contentValues.put(BATCH,tranaferValues.getBatch());
        contentValues.put(SECTION,tranaferValues.getSection());
        contentValues.put(TEACHER_PK,tecId);

        //contentValues.put(USER_NAME,tranaferValues.getUsername());
        //contentValues.put(PASSWARD,tranaferValues.getPassword());

        long rowId=sqLiteDatabase.insert(TABLE3,null,contentValues);

        return rowId;
    }

    public String[] generate(){



        //String [] str = {"jjj","jjjj"};
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        String admin = preferences.getString("admin", "");
        String tecId = preferences.getString("tecId", "");
        String stdId = preferences.getString("stdId", "");
        //String codeCourse = preferences.getString("codeCourse", "");

        String[] name = new String[]{""};
        int i = 0;

        if(admin.equals("tec")) {
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE3, null);
            if (cursor.getCount() == 0) {
                Toast.makeText(context, "No Data Found", Toast.LENGTH_SHORT).show();

            } else {
                while (cursor.moveToNext()) {

                    String id = cursor.getString(4);
                    if (tecId.equals(id))
                        name[i++] = cursor.getString(1)+" "+cursor.getString(2)+"("+cursor.getString(3)+")\n"+cursor.getString(4);
                       // Toast.makeText(context,"Teacher",Toast.LENGTH_LONG).show();


                }
            }
        }
        else {

            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE4, null);
            if (cursor.getCount() == 0) {
                Toast.makeText(context, "No Data Found", Toast.LENGTH_SHORT).show();

            } else {
                while (cursor.moveToNext()) {

                    String id = cursor.getString(1);
                    if (stdId.equals(id)) {

                       /* String course = cursor.getString(2);
                        Cursor cursor1 = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE3, null);
                        if (cursor1.getCount() == 0) {
                            Toast.makeText(context, "No Data Found", Toast.LENGTH_SHORT).show();

                        } else {
                            while (cursor1.moveToNext()) {

                                String crs = cursor1.getString(1);
                                if (course.equals(crs))
                                    name[i++] = cursor1.getString(1)+" "+cursor1.getString(2)+"("+cursor1.getString(3)+") "+cursor1.getString(4);*/
                                    name[i++] = cursor.getString(2);
                                    //Toast.makeText(context,"Student",Toast.LENGTH_LONG).show();


                            //}
                      //  }
                    }


                }
            }
        }

        return name;

    }
    public  void showTableLayout(TableLayout tableLayout){

        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        String course = preferences.getString("course", "");
        String code = preferences.getString("code", "");
        String stdId = preferences.getString("stdId", "");
        String codeCourse = preferences.getString("codeCourse", "");
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT * FROM "+TABLE4,null);



        if(cursor.getCount()==0){
            Toast.makeText(context,"No Data Found",Toast.LENGTH_SHORT).show();

        }else {
            while (cursor.moveToNext()){

                TableRow tr =  new TableRow(context);


                String str=cursor.getString(2);
               if(course.equals(str)) {

                    TextView txtGeneric1 = new TextView(context);

                    txtGeneric1.setTextSize(18);
                    txtGeneric1.setPadding(4,1,2,0);



                    txtGeneric1.setText(cursor.getString(1));
                    tr.addView(txtGeneric1);

                    TextView txtGeneric2 = new TextView(context);
                    txtGeneric2.setPadding(4,1,2,0);
                    txtGeneric2.setTextSize(18);
                    Cursor cursor1=sqLiteDatabase.rawQuery("SELECT * FROM "+TABLE1,null);
                    while (cursor1.moveToNext()){

                       String id=cursor.getString(1);
                       String id1=cursor1.getString(1);
                       if(id1.equals(id))
                       {
                           txtGeneric2.setText(cursor1.getString(2));
                           break;
                       }



                    }

                    tr.addView(txtGeneric2);

                    TextView txtGeneric3 = new TextView(context);
                    txtGeneric3.setTextSize(18);
                    txtGeneric3.setPadding(30,1,0,0);
                    txtGeneric3.setText(cursor.getString(3));

                    tr.addView(txtGeneric3);


                }
                tableLayout.addView(tr);

            }
        }



    }
}
