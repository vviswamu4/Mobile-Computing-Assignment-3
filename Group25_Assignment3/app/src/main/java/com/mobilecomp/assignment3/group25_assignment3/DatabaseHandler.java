package com.mobilecomp.assignment3.group25_assignment3;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.IOException;

/**
 * Created by Vasudha on 3/23/2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Activity_Recognition.db";
    private static final String DATABASE_PATH = "/data/data/com.mobilecomp.assignment3.group25_assignment3/databases/";
    private static final String TABLE_NAME = "Activity_Table";
    private Context context;


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, 1);
        this.context = context;

        try{
            createDatabase();
        }
        catch(IOException e){

           e.printStackTrace();
        }
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


    public void createDatabase() throws IOException{

        Log.w(this.getClass().getSimpleName(),"create Database called");
        boolean dbExists = checkIfDbExists();
        if(!dbExists){
            createNewDatabase();
        }
        else{
            Log.w(this.getClass().getSimpleName(),"Database already exists");
        }

    }

    public boolean checkIfDbExists(){
        String db_name = DATABASE_PATH + DATABASE_NAME;
        try{
            File dbFile = new File(db_name);
            if(dbFile.exists()){
                Log.w(this.getClass().getSimpleName(),"Database file exists!");
                return true;
            }
        }
        catch(Exception e){
            Log.w(this.getClass().getSimpleName(),"Database file not present to open");
        }

        Log.w(this.getClass().getSimpleName(),"Database does not exist");
        return false;
    }

    public void createNewDatabase(){

        SQLiteDatabase sqLiteDatabase = null;
        String database = DATABASE_PATH + DATABASE_NAME;
        try {
            sqLiteDatabase = SQLiteDatabase.openOrCreateDatabase(database, null);
            Log.w(this.getClass().getSimpleName(), "Database exists");
            createTable();
        } catch (Exception e) {
            Log.w(this.getClass().getSimpleName(), "Database doesn't exist");
        }

    }

    public void createTable(){

        SQLiteDatabase db = getWritableDatabase();
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE IF NOT EXISTS ");
        sb.append(TABLE_NAME);
        sb.append("(ID INTEGER PRIMARY KEY AUTOINCREMENT, ");
        for(int i=1;i<=50; i++){

            sb.append("ACCEL_X");
            sb.append(i);
            sb.append(" REAL, ");

            sb.append("ACCEL_Y");
            sb.append(i);
            sb.append(" REAL, ");

            sb.append("ACCEL_Z");
            sb.append(i);
            sb.append(" REAL, ");

        }

        sb.append("ACTIVITY_LABEL TEXT );");
        String QUERY_STRING = sb.toString();
        db.execSQL(QUERY_STRING);
        Log.w("Table: ", "Creation successful");
        db.close();

    }




}
