package com.panaceedental.panaceedental.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.panaceedental.panaceedental.R;
import com.panaceedental.panaceedental.Utility.Validate;

/**
 * Created by aristomichael on 13/08/17.
 */

public class SignupScreen extends AppCompatActivity implements View.OnClickListener{

    EditText edName, edEmail, edPassword, edConfirmPassword, edMobile;
    LinearLayout llSignup;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.signup_screen);

        edName = (EditText) findViewById(R.id.ed_name);
        edEmail = (EditText) findViewById(R.id.ed_email);
        edPassword = (EditText) findViewById(R.id.ed_password);
        edConfirmPassword = (EditText) findViewById(R.id.ed_confirm_password);
        edMobile = (EditText) findViewById(R.id.ed_mobile);
        llSignup = (LinearLayout) findViewById(R.id.ll_signup);

        llSignup.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.ll_signup:


                signUp();

                break;
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out);
    }

    void signUp() {

        String sName = edName.getText().toString();
        String sEmail = edEmail.getText().toString();
        String sPassword = edPassword.getText().toString();
        String sConfirmPassword = edConfirmPassword.getText().toString();
        String sMobile = edMobile.getText().toString();

        if(Validate.isValidString(sName) && Validate.isValidString(sEmail) && Validate.isValidString(sPassword) && Validate.isValidString(sConfirmPassword)
                && Validate.isValidString(sMobile)) {

            Intent verifyPassword = new Intent(this, VerifyMobile.class);
            startActivity(verifyPassword);
            finish();
            overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);
            SplashLoginScreen.loginScreen.finish();


        } else {

            if(!Validate.isValidString(sName)) {
                edName.requestFocus();
                edName.setError(getString(R.string.fill_mandatory));
                return;
            }

            if(!Validate.isValidString(sEmail)) {
                edEmail.requestFocus();
                edEmail.setError(getString(R.string.fill_mandatory));
                return;
            }

            if(!Validate.isValidString(sPassword)) {
                edPassword.requestFocus();
                edPassword.setError(getString(R.string.fill_mandatory));
                return;
            }

            if(!Validate.isValidString(sConfirmPassword)) {
                edConfirmPassword.requestFocus();
                edConfirmPassword.setError(getString(R.string.fill_mandatory));
                return;
            }

            if(!Validate.isValidString(sMobile)) {
                edMobile.requestFocus();
                edMobile.setError(getString(R.string.fill_mandatory));
                return;
            }

        }



    }
}
