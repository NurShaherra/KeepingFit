package com.example.a15031777.keepingfittt;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class BmiActivity extends AppCompatActivity {
    EditText etInputH;
    EditText etInputW;
    Button btnCalc;
    TextView tvResult;
    TextView tvDate;
    TextView tvDiff;
    Button btnclear;

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        String date1 = prefs.getString("date", " ");
        String bmi1 = prefs.getString("bmi", " ");

        tvResult.setText(bmi1);
        tvDate.setText(date1);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnclear = (Button) findViewById(R.id.buttonClear);
        etInputH = (EditText) findViewById(R.id.editTextHeight);
        etInputW = (EditText) findViewById(R.id.editTextWeight);
        btnCalc = (Button) findViewById(R.id.buttonBMI);
        tvResult = (TextView) findViewById(R.id.tvBmi);
        tvDate = (TextView) findViewById(R.id.tvDate);
        tvDiff = (TextView) findViewById(R.id.tvDiff);

        Intent intentReceived = getIntent();
        String title = intentReceived.getStringExtra("title");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(title);

        btnCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar now = Calendar.getInstance();

                String datetime = now.get(Calendar.DAY_OF_MONTH)+ "/" +
                        (now.get(Calendar.MONTH)+1)+ "/" +
                        (now.get(Calendar.YEAR)) + "/" +
                        (now.get(Calendar.HOUR_OF_DAY))+ "/" +
                        (now.get(Calendar.MINUTE));
                tvDate.setText(datetime);

                String strheight = etInputH.getText().toString();
                String strweight = etInputW.getText().toString();

                if(strheight.length() == 0) {
                    Toast.makeText(getApplicationContext(), "Height is required", Toast.LENGTH_SHORT).show();
                }

                float weight  = Float.parseFloat(strweight);
                float height = Float.parseFloat(strheight);
                float bmi = weight / (height * height);
                String b = String.format("%.02f", bmi);

                if(bmi < 18.5 ){
                    tvResult.setText( b + "\n You are Underweight" );
                } else if (bmi <= 24.9 ){
                    tvResult.setText( b + "\n You are Healthy" );
                } else if (bmi <= 29.9){
                    tvResult.setText( b + "\n You are Overweight" );
                } else if (bmi > 30) {
                    tvResult.setText( b + "\n You are Obese" );
                }

                String result = tvResult.getText().toString();
//                if (b < result) {
//
//                }

                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(BmiActivity.this);
                SharedPreferences.Editor edit = prefs.edit();

                edit.putString("bmi", result);
                edit.putString("date", datetime);

                edit.commit();

            }
        });

        btnclear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(BmiActivity.this);
                SharedPreferences.Editor editor = prefs.edit();
                editor.clear();
                editor.commit();

                tvResult.setText("");
                tvDate.setText("");

            }
        });


    }
}
