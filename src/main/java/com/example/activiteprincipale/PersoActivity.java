package com.example.activiteprincipale;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PersoActivity extends AppCompatActivity {
    private Button quitterPerso;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activityp_erso);
        quitterPerso=(Button) findViewById(R.id.ClosePerso);
        quitterPerso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}