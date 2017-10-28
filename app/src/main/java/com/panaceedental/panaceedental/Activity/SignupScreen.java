package com.panaceedental.panaceedental.Activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.panaceedental.panaceedental.Parcers.CommonParcer;
import com.panaceedental.panaceedental.R;
import com.panaceedental.panaceedental.Utility.APIClass;
import com.panaceedental.panaceedental.Utility.Const;
import com.panaceedental.panaceedental.Utility.MyResultReceiver;
import com.panaceedental.panaceedental.Utility.RequestType;
import com.panaceedental.panaceedental.Utility.Validate;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by aristomichael on 13/08/17.
 */

public class SignupScreen extends AppCompatActivity implements View.OnClickListener, MyResultReceiver.Receiver {

    EditText edName, edEmail, edPassword, edConfirmPassword, edMobile;
    LinearLayout llSignup;
    MyResultReceiver myResultReceiver;
    private ProgressDialog progress;

    public static Activity signUpContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.signup_screen);

        signUpContext = this;
        myResultReceiver = new MyResultReceiver(new Handler());
        myResultReceiver.setReceiver(this);

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

    private void signUp() {

        String sName = edName.getText().toString();
        String sEmail = edEmail.getText().toString();
        String sPassword = edPassword.getText().toString();
        String sConfirmPassword = edConfirmPassword.getText().toString();
        String sMobile = edMobile.getText().toString();

        if(Validate.isValidString(sName) && Validate.isValidString(sEmail) && Validate.isValidString(sPassword) && Validate.isValidString(sConfirmPassword)
                && Validate.isValidString(sMobile)) {

            Map<String, String> param = new HashMap<>();
            param.put(Const.ACTION, Const.SIGNUP_ACTION);
            param.put(Const.EMAIL, sEmail);
            param.put(Const.PASSWORD, sPassword);
            param.put(Const.MOBILE, sMobile);
            param.put(Const.NAME, sName);

            startDialog();
            APIClass apiClass = new APIClass(this, param, myResultReceiver, RequestType.SIGNUP_REQUEST, Const.mainURL);
            apiClass.makeJsonRequest();


//            Intent verifyPassword = new Intent(this, VerifyMobile.class);
//            startActivity(verifyPassword);
//            finish();
//            overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);
//            SplashLoginScreen.loginScreen.finish();


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

    @Override
    public void onReceiveResult(int resultCode, Bundle resultData) {

        if(resultData != null) {

            Gson gson = new Gson();
            CommonParcer commonParcer = gson.fromJson(resultData.getString(Const.RESPONSE), CommonParcer.class);


            switch (resultCode) {

                case RequestType.SIGNUP_REQUEST:

                    String status = commonParcer.getStatus();
                    String message = commonParcer.getMessage();
                    if(!status.isEmpty() && status.equals(getString(R.string.success))) {

                        Intent verifyPassword = new Intent(this, HomeActivity.class);
                        startActivity(verifyPassword);
                        overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);
                        finish();

                    }

                    Toast.makeText(signUpContext, message, Toast.LENGTH_SHORT).show();
                    hideDialog();

                    break;


            }
        } else {
            Toast.makeText(signUpContext, "error", Toast.LENGTH_SHORT).show();
            hideDialog();
        }

    }

    private void startDialog() {
        progress=new ProgressDialog(this);
        progress.setMessage("Loading...");
//        progress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
//        progress.setIndeterminate(true);
//        progress.setProgress(0);
        progress.show();

    }

    private void hideDialog() {
        if(progress != null) {
            progress.cancel();
        }
    }
}
