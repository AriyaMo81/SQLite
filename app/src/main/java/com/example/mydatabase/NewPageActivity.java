package com.example.mydatabase;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class NewPageActivity extends AppCompatActivity {

    MyDatabaseHelper mydb;
    EditText etid,etname;
    String mes="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_page);

        mydb=new MyDatabaseHelper(this);
    }

    public void onBtnUpdateClick(View view) {
        etid=findViewById(R.id.etIdForUpdate);
        etname=findViewById(R.id.etNameForUpdate);

        String strid=etid.getText().toString();
        String strname=etname.getText().toString();

        if(strname.length()>0 && strid.length()>0){

            boolean f=mydb.updateData(strid,strname);
            if(f==true)
                mes = "Updated....";
            else
               mes = "no Updated...!";

            Toast.makeText(NewPageActivity.this,mes,Toast.LENGTH_LONG).show();
        }

 else {
            etid.setHint("Please Enter Id...");
           etname.setHint("Please Enter New id....");
            return;
        }

        }

    public void onBtnBackClick(View view) {
        Intent i=new Intent(NewPageActivity.this,MainActivity.class);
        startActivity(i);
    }

    public void onBtnShowAllClick(View view) {

        Cursor res=mydb.ShowallData();

        if(res.getCount()==0){
            Toast.makeText(NewPageActivity.this,"emptytable...!",Toast.LENGTH_LONG).show();
        }

        StringBuffer data=new StringBuffer();

        while (res.moveToNext()){
            data.append("ID:" + res.getString(0)+ "\n");
            data.append("name:" + res.getString(1) + "\n");
            data.append("Lastname" + res.getString(2) + "\n");
        }

        AlertDialog.Builder alert=new AlertDialog.Builder(this);
        alert.setCancelable(true);
        alert.setTitle("show data");
        alert.setMessage(data.toString());
        alert.show();

    }
}