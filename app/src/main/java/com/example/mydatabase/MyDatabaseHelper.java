package com.example.mydatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    public static final String DB_NAME="uni.db";
    public static final String TBL_NAME="stu";

    public MyDatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TBL_NAME + "(Id INTEGER PRIMARY KEY AutoIncrement , name TEXT , LastName TEXT)");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + TBL_NAME);
        onCreate(db);
    }

    public MyDatabaseHelper(Context context) {
        super(context, DB_NAME, null, 1);
        SQLiteDatabase db=this.getWritableDatabase();
    }


    public boolean insertData(String n,String m){

        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues cv=new ContentValues();
        cv.put("name",n);
        cv.put("LastName",m);

        long result=db.insert(TBL_NAME,null,cv);

        if(result==-1)
            return false;
        else
            return true;
    }

    public boolean deleteData(String id){

        SQLiteDatabase db=this.getWritableDatabase();

        long result=db.delete(TBL_NAME,"Id=?",new String[] {id});

        if(result==0)
            return false;
        else
            return true;
    }

    public boolean updateData(String id,String n){

        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues cv=new ContentValues();
        cv.put("name",n);

        long result=db.update(TBL_NAME,cv,"Id=?",new String [] {id});

        if(result<1)
            return false;
        else
            return true;
    }

    public Cursor ShowallData(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor result=db.rawQuery("select * from " + TBL_NAME,null);
        return result;
    }



}
