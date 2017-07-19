package com.example.a15031777.keepingfittt;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class diet_planning extends AppCompatActivity {

    EditText etBfast;
    EditText etLunch;
    EditText etDinner;
    Button btnSave;
    Button btnClear;
    Button btnBack;

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        String strBfast = prefs.getString("bfast", " ");
        String strLunch = prefs.getString("lunch", " ");
        String strDinner = prefs.getString("dinner", " ");

        etBfast.setText(strBfast);
        etLunch.setText(strLunch);
        etDinner.setText(strDinner);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet_planning);



        etBfast = (EditText) findViewById(R.id.etBfast);
        etLunch = (EditText) findViewById(R.id.etLunch);
        etDinner = (EditText) findViewById(R.id.etDinner);
        btnSave = (Button) findViewById(R.id.button2);
        btnClear = (Button) findViewById(R.id.button3);
        btnBack = (Button) findViewById(R.id.btnback);

        Intent intentReceived = getIntent();
        String title = intentReceived.getStringExtra("title");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(title);



        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String strBfast = etBfast.getText().toString();
                String strLunch = etLunch.getText().toString();
                String strDinner = etDinner.getText().toString();

                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(diet_planning.this);
                SharedPreferences.Editor edit = prefs.edit();



                edit.putString("bfast", strBfast);
                edit.putString("lunch", strLunch);
                edit.putString("dinner", strDinner);
                edit.commit();

                Toast.makeText(diet_planning.this, "Successfully saved!", Toast.LENGTH_SHORT).show();

            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(diet_planning.this);
                SharedPreferences.Editor editor = prefs.edit();
                editor.clear();
                editor.commit();

                etBfast.setText("");
                etLunch.setText("");
                etDinner.setText("");



            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), MainActivity.class );

                startActivity(intent);
            }
        });








    }
}
