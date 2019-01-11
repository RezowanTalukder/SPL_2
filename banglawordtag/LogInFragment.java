package com.rez1.banglawordtag;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.security.PrivateKey;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LogInFragment extends Fragment {

    private TextView RegText ;
    private EditText UserName, UserPassword ;
    private Button LoginBn ;

    OnLoginFormActivityListener loginFormActivityListener ;

    public interface OnLoginFormActivityListener{
        public void performRegister() ;
        public void performLogin(String name) ;
    }

    public LogInFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_log_in, container, false);
        RegText = view.findViewById(R.id.register_txt);
        UserName = view.findViewById(R.id.user_name) ;
        UserPassword = view.findViewById(R.id.user_pass) ;
        LoginBn = view.findViewById(R.id.login_bn) ;

        LoginBn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performlogin();
            }
        });


        RegText.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                loginFormActivityListener.performRegister();
            }
        });

        return view ;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity = (Activity)context ;
        loginFormActivityListener = (OnLoginFormActivityListener) activity ;
    }

    private void performlogin(){
        String username = UserName.getText().toString() ;
        String password = UserPassword.getText().toString() ;

        Call<User> call = MainActivity.apiInterface.performUserLogin(username,password) ;
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                if(response.body().getResponse().equals("ok")){
                    MainActivity.prefConfig.writeLoginStatus(true);
                    loginFormActivityListener.performLogin(response.body().getName());
                }

                else if(response.body().getResponse().equals("fail")){
                    MainActivity.prefConfig.displayToast("Login Failed...");
                }

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });

        UserName.setText("") ;
        UserPassword.setText("");
    }
}
