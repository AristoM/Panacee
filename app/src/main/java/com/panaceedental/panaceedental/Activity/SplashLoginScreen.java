package com.panaceedental.panaceedental.Activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.panaceedental.panaceedental.Parcers.CommonParcer;
import com.panaceedental.panaceedental.R;
import com.panaceedental.panaceedental.Utility.APIClass;
import com.panaceedental.panaceedental.Utility.Const;
import com.panaceedental.panaceedental.Utility.MyResultReceiver;
import com.panaceedental.panaceedental.Utility.RequestType;
import com.panaceedental.panaceedental.Utility.SharedPref;
import com.panaceedental.panaceedental.Utility.Utility;
import com.panaceedental.panaceedental.Utility.Validate;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by aristomichael on 12/08/17.
 */

public class SplashLoginScreen extends Activity implements View.OnClickListener, MyResultReceiver.Receiver {

    LinearLayout  llSignup, llSignin;
    EditText edMobile, edPassword;
    Button btSignin;
    ImageView ivLogo;
    MyResultReceiver myResultReceiver;
    ProgressDialog progress;

    public static Activity loginScreen;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.splash_login_screen);

        llSignup = findViewById(R.id.ll_signup);
        edMobile = findViewById(R.id.ed_mobile);
        edPassword = findViewById(R.id.ed_password);
        btSignin = findViewById(R.id.bt_signin);
        ivLogo = findViewById(R.id.iv_logo);
        llSignin = findViewById(R.id.ll_signin);

        llSignup.setOnClickListener(this);
        btSignin.setOnClickListener(this);

        loginScreen = this;


        myResultReceiver = new MyResultReceiver(new Handler());
        myResultReceiver.setReceiver(this);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                String username = SharedPref.getUserName(SplashLoginScreen.this);
                String password = SharedPref.getPassword(SplashLoginScreen.this);

                if(Validate.isValidString(username)) {

                    formLoginData(username, password);

//                    Intent homescreen = new Intent(SplashLoginScreen.this, HomeActivity.class);
//                    startActivity(homescreen);
//                    overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);
//                    finish();
                } else {
                    llSignin.setVisibility(View.VISIBLE);
                    llSignup.setVisibility(View.VISIBLE);
                    ivLogo.setVisibility(View.GONE);
                }

//                signinSuccess(username, password);

            }
        }, 3000);


    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.bt_signin:

                String sMobile = edMobile.getText().toString();
                String sPassword = edPassword.getText().toString();

                signinProcess(sMobile, sPassword);

                break;
            case R.id.ll_signup:

                Intent signupintent = new Intent(SplashLoginScreen.this, SignupScreen.class);
                startActivity(signupintent);
                overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);
//                finish();

                break;
        }
    }

    void signinProcess(String sMobile, String sPassword) {

        Utility.hideKeyboard(this);


        if(Validate.isValidString(sMobile)) {
            if(Validate.isValidString(sPassword)) {

                // Save login details in shared pref
                SharedPref.saveLoginDetails(SplashLoginScreen.this, sMobile, sPassword);

//                Intent homescreen = new Intent(SplashLoginScreen.this, HomeActivity.class);
//                startActivity(homescreen);
//                overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);
//                finish();

                formLoginData(sMobile, sPassword);

                startDialog();

            } else {

                edPassword.requestFocus();
                edPassword.setError(getString(R.string.fill_password));
            }
        } else {
            edMobile.requestFocus();
            edMobile.setError(getString(R.string.fill_mobile));
        }

    }

    @Override
    public void onReceiveResult(int resultCode, Bundle resultData) {

        if(resultData != null) {

            Gson gson = new Gson();
            CommonParcer commonParcer = gson.fromJson(resultData.getString(Const.RESPONSE), CommonParcer.class);


            switch (resultCode) {

                case RequestType.LOGIN_REQUEST:

                    String status = commonParcer.getStatus();
                    String message = commonParcer.getMessage();
                    if(!status.isEmpty() && status.equals(getString(R.string.success))) {

                        Intent homescreen = new Intent(SplashLoginScreen.this, HomeActivity.class);
                        startActivity(homescreen);
                        overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);
                        finish();

                    } else {
                        SharedPref.saveLoginDetails(SplashLoginScreen.this, "", "");
                    }

                    Toast.makeText(loginScreen, message, Toast.LENGTH_SHORT).show();
                    hideDialog();
                    break;


            }
        } else {
//            SharedPref.saveLoginDetails(SplashLoginScreen.this, "", "");
            Toast.makeText(loginScreen, getString(R.string.try_again_later), Toast.LENGTH_SHORT).show();
            hideDialog();
        }

    }

    public void finishActivity(){
        SplashLoginScreen.this.finish();
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

    private void formLoginData(String sMobile, String sPassword) {

        Map<String, String> param = new HashMap<>();
        param.put(Const.ACTION, Const.LOGIN_ACTION);
        param.put(Const.EMAIL, sMobile);
        param.put(Const.PASSWORD, sPassword );

        APIClass apiClass = new APIClass(this, param, myResultReceiver, RequestType.LOGIN_REQUEST, Const.mainURL);
        apiClass.makeJsonRequest();

    }

}
