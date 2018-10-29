package com.example.rishabh.tiffinrecord;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    Spinner time;
DatePicker datePicker;
    Database mydb;
    EditText editDate;
    TextView editTotal;
    Button buttonAdd,viewAllButton,total;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mydb=new Database(this);


        editDate= (EditText) findViewById(R.id.editText_Time);

        editTotal= (TextView) findViewById(R.id.totaltiffin);
        datePicker= (DatePicker) findViewById(R.id.datePicker);

        time= (Spinner) findViewById(R.id.spinner);

        buttonAdd = (Button) findViewById(R.id.button_add);
        viewAllButton= (Button) findViewById(R.id.buttonshow);

        total= (Button) findViewById(R.id.tiffin_count);
        Adddata();
        viewAll();
        showAll();

    }




    public void Adddata(    ) {
        buttonAdd.setOnClickListener(


                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String date;


                        String year= String.valueOf(datePicker.getYear());
                      String month= String.valueOf(datePicker.getMonth());

                       String dday = String.valueOf(datePicker.getDayOfMonth());

                        boolean isInserted= mydb.insertData(editDate.getText().toString(),String.valueOf(time.getSelectedItem()));

                        if (isInserted==true){
                            Toast.makeText(MainActivity.this,"Data Inserted"+dday+month+year,Toast.LENGTH_LONG).show();}
                        else{Toast.makeText(MainActivity.this,"Data not Inserted",Toast.LENGTH_LONG).show();}
                    }
                }
        );
    }
    public void viewAll (     ){
        viewAllButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res=mydb.getAllData();
                        if (res.getCount()==0) {//show message
                            showMessage("Error","Nothing found");
                            return;   }
                        StringBuffer buffer=new StringBuffer();
                        while (res.moveToNext()){
                            buffer.append("Id :"+res.getString(0)+"\n");

                            buffer.append("Date :"+res.getString(1)+"\n");

                            buffer.append("Time:"+res.getString(2)+"\n\n");

                        }

                        showMessage("DATA",buffer.toString());
                    }
                }
        );
    }

    public void showAll (     ){

             total.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res=mydb.getAllData();
                        if (res.getCount()==0) {//show message
                            showMessage("NO RECORD","0");
                            return;   }
 int c=0;
                         while (res.moveToNext())
                         {
 c++;
                         }

editTotal.setText("NUMBER OF TIFFINS ARE-"+c);
                        Toast.makeText(MainActivity.this,"NUMBER OF TIFFINS ARE-"+c,Toast.LENGTH_LONG).show();
                    }
                }
        );
    }








    public void showMessage(String title,String message){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);

        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

}
