package com.rez1.first;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private SharedPrefrenceConfig prefrenceConfig ;
    private EditText UserName, UserPassword ;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prefrenceConfig = new SharedPrefrenceConfig(getApplicationContext()) ;

        UserName = findViewById(R.id.user_name) ;
        UserPassword = findViewById(R.id.user_password) ;


        if(prefrenceConfig.readLoginStatus()){
            startActivity(new Intent(this, SucessActivity.class));
            finish();

        }


    }

    public void LoginUser(View view) {
        String username = UserName.getText().toString() ;
        String userpassword = UserPassword.getText().toString() ;




        if(username.equals(getResources().getString(R.string.user_name))  &&
        userpassword.equals(getResources().getString(R.string.user_password))){

            startActivity(new Intent(this, SucessActivity.class));
            prefrenceConfig.writeLoginStatus(true);
            finish();


        }
        else{
            Toast.makeText(this,"Login Failed... Try Again",Toast.LENGTH_SHORT).show();

            UserPassword.setText("");
            UserName.setText("");

        }

    }
}
