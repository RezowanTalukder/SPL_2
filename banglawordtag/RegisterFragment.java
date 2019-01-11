package com.rez1.banglawordtag;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment {

    private EditText Name, UserName, UserPassword ;
    private Button BnRegister ;

    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        Name = view.findViewById(R.id.txt_name) ;
        UserName = view.findViewById(R.id.txt_user_name) ;
        UserPassword = view.findViewById(R.id.txt_user_pass) ;

        BnRegister = view.findViewById(R.id.register_bn) ;

        BnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return view ;
    }

    public void performRegistration(){
        String name = Name.getText().toString() ;
        String userName = UserName.getText().toString() ;
        String password = UserPassword.getText().toString() ;

        Call<User> call = MainActivity.apiInterface.performRegistration(name,userName,password) ;
        ((retrofit2.Call) call).enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                if(response.body().equals("ok")){
                    MainActivity.prefConfig.displayToast("Registration Success...");
                }

                else if(response.body().equals("exists")){
                    MainActivity.prefConfig.displayToast("User already exists...");

                }

                else if(response.body().equals("error")){
                    MainActivity.prefConfig.displayToast("Something went wrong...");

                }
            }

            @Override
            public void onFailure(retrofit2.Call call, Throwable t) {

            }
        });

        Name.setText("");
        UserPassword.setText("");
        UserName.setText("");
    }

}
