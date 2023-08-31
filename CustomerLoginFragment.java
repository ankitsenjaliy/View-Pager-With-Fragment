package com.example.hotelmanagementsystem.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.hotelmanagementsystem.Activity.ForgotPasswordActivity;
import com.example.hotelmanagementsystem.Activity.NavigationActivity;
import com.example.hotelmanagementsystem.Activity.RegistrationActivity;
import com.example.hotelmanagementsystem.Model.LoginModel;
import com.example.hotelmanagementsystem.R;
import com.example.hotelmanagementsystem.Api.APIInterface;
import com.example.hotelmanagementsystem.Api.RetrofitApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CustomerLoginFragment extends Fragment {

    EditText email_id, password;
    Button login, cancel;
    TextView new_registration,forgot_password;
    boolean password_visible;

    APIInterface api;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.customer_login_fragment, container, false);

        email_id = view.findViewById(R.id.email_id);
        password = view.findViewById(R.id.password);
        login = view.findViewById(R.id.login);
        cancel = view.findViewById(R.id.cancel);
        new_registration = view.findViewById(R.id.new_registration);
        forgot_password = view.findViewById(R.id.forgot_password);


        new_registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(view.getContext(), RegistrationActivity.class);
                startActivity(intent);
            }
        });

        forgot_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(view.getContext(), ForgotPasswordActivity.class);
                startActivity(intent);
            }
        });

        password.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {

                final int Right = 2;

                if (event.getAction() == MotionEvent.ACTION_UP) {

                    if (event.getRawX() >= password.getRight() - password.getCompoundDrawables()[Right].getBounds().width()) {

                        int selection = password.getSelectionEnd();

                        if (password_visible) {
                            //set drawable image here
                            password.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.eye_visibility_off, 0);
//                            for hide password
                            password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            password_visible = false;
                        } else {
                            //set drawable image here
                            password.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.eye_visibility_on, 0);
//                            for show password
                            password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            password_visible = true;
                        }
                        password.setSelection(selection);
                        return true;
                    }

                }
                return false;
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String emailid = email_id.getText().toString();
                String pass = password.getText().toString();

                if (emailid.equals("")) {

                    Toast.makeText(getContext(), "Please Enter Your Email Id", Toast.LENGTH_SHORT).show();

                }
                else if(!emailid.contains(".com")||!emailid.contains("@")) {

                    Toast.makeText(getContext(), "Please Enter Valid Email Id", Toast.LENGTH_SHORT).show();

                } else if (pass.equals("")) {

                    Toast.makeText(getContext(), "Please Enter Your Password", Toast.LENGTH_SHORT).show();

                } else {

                    getLoginData(emailid, pass);
                }
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(view.getContext(), CustomerLoginFragment.class);
                System.exit(0);
            }
        });

        return view;
    }

    private void getLoginData(String email, String password) {

        api = RetrofitApi.getClient(getContext()).create(APIInterface.class);
        Call<LoginModel> loginModelCall = api.GetLogin(email, password);
        loginModelCall.enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {

                if (response.body().gethotelLoginModel().get(0).getSuccess().equals("1")) {

                    Toast.makeText(getContext(), "Login SuccessFully", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(getContext(), NavigationActivity.class);
                    startActivity(intent);

                } else {

                    Toast.makeText(getContext(), "Please Valid Email Id And Password", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<LoginModel> call, Throwable t) {

                Toast.makeText(getContext(), "Not Valid Email Id And Password", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
