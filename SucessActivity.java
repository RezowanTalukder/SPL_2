package com.rez1.first;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class SucessActivity extends AppCompatActivity {


    private SharedPrefrenceConfig prefrenceConfig ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sucess);

        prefrenceConfig = new SharedPrefrenceConfig(getApplicationContext()) ;

    }

    public void userLogout(View view) {

        prefrenceConfig.writeLoginStatus(false);
        startActivity(new Intent(this,MainActivity.class));

    }
}
