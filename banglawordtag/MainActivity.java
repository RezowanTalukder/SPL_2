 package com.rez1.banglawordtag;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements LogInFragment.OnLoginFormActivityListener {

    public static PrefConfig prefConfig ;
    public static ApiInterface apiInterface ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prefConfig = new PrefConfig(this) ;
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class) ;

        if(findViewById(R.id.fragment_container)!=null){
            if(savedInstanceState!=null){
                return;
            }

            if(prefConfig.readLoginStatus()){
                getSupportFragmentManager().beginTransaction().add(R.id.fragment_container,new WordFragment()).commit() ;
            }
            else {
                getSupportFragmentManager().beginTransaction().add(R.id.fragment_container,new LogInFragment()).commit() ;
            }
        }
    }

    @Override
    public void performRegister() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new RegisterFragment()).addToBackStack(null).commit() ;
    }

    @Override
    public void performLogin(String name) {
        prefConfig.writeName(name);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new WordFragment()).commit();
    }
}
