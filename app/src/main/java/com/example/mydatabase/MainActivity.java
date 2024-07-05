package com.example.mydatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    MyDatabaseHelper mydb;
    EditText fn,Ln,edtid;
    String mes="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mydb=new MyDatabaseHelper(this);
    }

    public void onBtnSaveClick(View v) {

        fn = findViewById(R.id.fname);
        Ln = findViewById(R.id.lname);

        String f = fn.getText().toString();
        String l = Ln.getText().toString();

        if(f.length()<1){
            fn.setHint("No Enter LastName...");
            return;
        }
        if(l.length()<1){
            Ln.setHint("No Enter LastName...");
            return;
        }

        boolean a= mydb.insertData(f,l);
        if(a==true)
            mes = "Yes save ...";
 else
       mes = "No save ..!";


        Toast.makeText(MainActivity.this,mes,Toast.LENGTH_LONG).show();
    }

    public void onBtnDeleteClick(View v){
        edtid=findViewById(R.id.setID);

        String e=edtid.getText().toString();

        if(e.length()<1){
            edtid.setHint("No Enter ID...!");
            return;
        }

        boolean b=mydb.deleteData(e);
        if(b==true)
            mes = "Deleted...";
else
        mes = "Not Deleted";

        Toast.makeText(MainActivity.this,mes,Toast.LENGTH_LONG).show();
    }

    public void onbtnNextPageClick(View view) {
        Intent i =new Intent(MainActivity.this,NewPageActivity.class);
        startActivity(i);

    }
}