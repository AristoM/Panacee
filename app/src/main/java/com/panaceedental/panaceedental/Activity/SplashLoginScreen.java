package com.panaceedental.panaceedental.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
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
import com.panaceedental.panaceedental.Utility.Utility;
import com.panaceedental.panaceedental.Utility.Validate;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by aristomichael on 12/08/17.
 */

public class SplashLoginScreen extends Activity implements View.OnClickListener, MyResultReceiver.Receiver{

    LinearLayout  llSignup;
    EditText edMobile, edPassword;
    Button btSignin;

    MyResultReceiver myResultReceiver;

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

        llSignup.setOnClickListener(this);
        btSignin.setOnClickListener(this);

        loginScreen = this;


        myResultReceiver = new MyResultReceiver(new Handler());

        myResultReceiver.setReceiver(this);

//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//
//                Intent homescreen = new Intent(SplashLoginScreen.this, HomeActivity.class);
//                startActivity(homescreen);
//                finish();
//
//            }
//        }, 1000);


    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.bt_signin:

                signinSuccess();

                break;
            case R.id.ll_signup:

                Intent signupintent = new Intent(SplashLoginScreen.this, SignupScreen.class);
                startActivity(signupintent);
                overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);
//                finish();

                break;
        }
    }

    void signinSuccess() {

        Utility.hideKeyboard(this);

        String sMobile = edMobile.getText().toString();
        String sPassword = edPassword.getText().toString();


        if(Validate.isValidString(sMobile)) {
            if(Validate.isValidString(sPassword)) {

                Intent homescreen = new Intent(SplashLoginScreen.this, HomeActivity.class);
                startActivity(homescreen);
                overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);
                finish();

//                Map<String, String> param = new HashMap<>();
//                param.put(Const.ACTION, Const.LOGIN_ACTION);
//                param.put(Const.EMAIL, sMobile);
//                param.put(Const.PASSWORD, sPassword );
//
//                APIClass apiClass = new APIClass(this, param, myResultReceiver, RequestType.LOGIN_REQUEST);
//                apiClass.makeJsonRequest();


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
            CommonParcer commonParcer = gson.fromJson(resultData.getString("result"), CommonParcer.class);


            switch (resultCode) {

                case RequestType.LOGIN_REQUEST:

                    String error = commonParcer.getError();
                    Toast.makeText(loginScreen, error, Toast.LENGTH_SHORT).show();

                    break;


            }
        } else {
            Toast.makeText(loginScreen, "error", Toast.LENGTH_SHORT).show();
        }

    }

    public void finishActivity(){
        SplashLoginScreen.this.finish();
    }
}
