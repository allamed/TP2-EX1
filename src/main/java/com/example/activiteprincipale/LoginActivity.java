package com.example.activiteprincipale;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private Button login;
    private Button cancel;
    private EditText username;
    private EditText password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login=(Button) findViewById(R.id.login);
        cancel=(Button) findViewById(R.id.cancel);
        username=(EditText) findViewById(R.id.editTextUsername);
        password=(EditText) findViewById(R.id.editTextPassword);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(login()) {
                    Intent mainActivityIntent = new Intent(getApplicationContext(), MainActivity.class);
                    mainActivityIntent.putExtra("isLoggedIn", true);
                    setResult(Activity.RESULT_OK, mainActivityIntent);
                    finish();
                }
                else
                {
                    Toast.makeText(LoginActivity.this, "probleme d'authentification", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
    boolean login(){
        return (username.getText().toString().equals("TPandINFO") && password.getText().toString().equals("secret"));
    }
}
