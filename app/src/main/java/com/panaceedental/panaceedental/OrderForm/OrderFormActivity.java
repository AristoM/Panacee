package com.panaceedental.panaceedental.OrderForm;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.panaceedental.panaceedental.R;

/**
 * Created by aristomichael on 09/10/17.
 */

public class OrderFormActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.order_form_page1);
    }
}
