package com.panaceedental.panaceedental.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.panaceedental.panaceedental.R;

/**
 * Created by aristomichael on 14/08/17.
 */

public class VerifyMobile extends Activity implements View.OnClickListener{

    TextView tvSkip;
    Button btSignIn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.verify_mobile);

        tvSkip = findViewById(R.id.tv_skip);
        btSignIn = findViewById(R.id.bt_signin);

        tvSkip.setOnClickListener(this);
        btSignIn.setOnClickListener(this);


    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.tv_skip:

                Intent homeScreen = new Intent(VerifyMobile.this, HomeActivity.class);
                startActivity(homeScreen);
                overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);
                finish();

                break;
        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent homeScreen = new Intent(VerifyMobile.this, HomeActivity.class);
        startActivity(homeScreen);
        overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);
        finish();

    }
}
