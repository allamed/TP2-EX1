package com.example.activiteprincipale;

import static android.app.ProgressDialog.show;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ImageButton perso;
    private ImageButton launchActTel;
    private ImageButton launchActNav;
    private EditText phoneNumber;
    private EditText url;
    private String defaultUrl = "https://www.emi.ac.ma/";
    private int CALL_Perm=1;
    private EditText number1;
    private EditText number2;
    private boolean isUserLoggedIn=false;
    private static final int REQUEST_CODE = 1;
    private static final int check_REQUEST_CODE=1;
    private int somme;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        perso = (ImageButton) findViewById(R.id.activityPerso);
        launchActNav = (ImageButton) findViewById(R.id.navImage);
        launchActTel = (ImageButton) findViewById(R.id.telImage);
        phoneNumber = (EditText) findViewById(R.id.numberPhone);
        url = (EditText) findViewById(R.id.url);

        number1=(EditText) findViewById(R.id.editNumber1);
        number2=(EditText)findViewById(R.id.editNumber2);

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, CALL_Perm);

        perso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Intent persoActivityIntent = new Intent(getApplicationContext(), aCTIVITYpERSO.class);
                // startActivity(persoActivityIntent);
                Intent intent = new Intent("login.ACTION");
                startActivity(intent);
            }
        });

        Toast.makeText(this, "bit n3ayat", Toast.LENGTH_SHORT).show();
        Log.i("LIFECYCLE ", getLocalClassName() + " : ici onCreate");

        launchActTel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!phoneNumber.getText().toString().isEmpty()) {
                    if(isUserLoggedIn){
                        Intent phoneCallIntent = new Intent(Intent.ACTION_CALL);
                        phoneCallIntent.setData(Uri.parse("tel:" + phoneNumber));
                        startActivity(phoneCallIntent);
                    }
                    else{
                        Intent loginActivityIntent = new Intent(getApplicationContext(), LoginActivity.class);
                         startActivityForResult(loginActivityIntent, REQUEST_CODE);
                    }

                }
            }
        });

        launchActNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((!number1.getText().toString().isEmpty())&&(!number2.getText().toString().isEmpty())){
                    if(!isEgalSomme()) {
                        Intent checkActivityIntent = new Intent(getApplicationContext(), CheckActivity.class);
                        String number1Str = number1.getText().toString();
                        String number2Str = number2.getText().toString();

                        int number1Int = Integer.parseInt(number1Str);
                        int number2Int = Integer.parseInt(number2Str);
                        checkActivityIntent.putExtra("challengeNumber1", number1Int);
                        checkActivityIntent.putExtra("challengeNumber2", number2Int);

                        startActivityForResult(checkActivityIntent, check_REQUEST_CODE);
                    }
                    else{
                        if (url.getText().toString().equals("")) {
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(defaultUrl)));
                        } else {
                            String completeUrl = "https://" + url.getText().toString();
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(completeUrl)));
                        }
                    }
                }
                else {
                    Toast.makeText(MainActivity.this, "remplisez les champs necessaire", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK){
            boolean isUserLoggedIn = data.getExtras().getBoolean("isLoggedIn");
            int som=data.getExtras().getInt("somme");
            setUserLoggedIn(isUserLoggedIn);
            setSomme(som);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //check the permission type using the requestCode
        if (requestCode == CALL_Perm) {
        //the array is empty if not granted
            if (grantResults.length>0 && grantResults[0]== PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this, "prmission d'appel accordée", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("pendingPhoneCall", phoneNumber.getText().toString());
    }

    public EditText getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        String pendingPhoneCall = savedInstanceState.getString("pendingPhoneCall");
        phoneNumber.setText(pendingPhoneCall);
    }

    public void setUserLoggedIn(boolean userLoggedIn) {
        isUserLoggedIn = userLoggedIn;
    }
    public boolean isEgalSomme(){

        int num1=Integer.parseInt(number1.getText().toString());
        int num2=Integer.parseInt(number2.getText().toString());
        int som=num1+num2;
        if (som == somme){
            Toast.makeText(this, "check valide", Toast.LENGTH_SHORT).show();
        return true;}
        else {
            Toast.makeText(this, "check encore invalide.Faire le check", Toast.LENGTH_SHORT).show();
            return false;
        }



    }

    public void setSomme(int somme) {
        this.somme = somme;
    }


}