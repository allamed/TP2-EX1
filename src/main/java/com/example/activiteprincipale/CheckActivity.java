package com.example.activiteprincipale;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CheckActivity extends AppCompatActivity {
    private Button check;
    private Button cancel;
    private TextView number1;
    private TextView number2;
    private EditText somme;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);

        check=(Button) findViewById(R.id.check);
        cancel=(Button) findViewById(R.id.cancelCheck);
        number1=(TextView) findViewById(R.id.textViewNumber1);
        number2=(TextView) findViewById(R.id.textViewNumber2);
        somme=(EditText) findViewById(R.id.editSomme);

        int challengeNumber1 = getIntent().getExtras().getInt("challengeNumber1");
        int challengeNumber2 = getIntent().getExtras().getInt("challengeNumber2");
        number1.setText(String.valueOf(challengeNumber1));
        number2.setText(String.valueOf(challengeNumber2));

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Toast.makeText(CheckActivity.this, "opération annullée", Toast.LENGTH_SHORT).show();
            }
        });

        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!somme.getText().toString().isEmpty()){
                    Intent mainActivityIntent = new Intent(getApplicationContext(), MainActivity.class);
                    mainActivityIntent.putExtra("somme", Integer.parseInt(somme.getText().toString()));
                    setResult(Activity.RESULT_OK, mainActivityIntent);

                    finish();

                }
            }
        });

    }



}