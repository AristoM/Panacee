package com.panaceedental.panaceedental.OrderForm;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.panaceedental.panaceedental.R;

/**
 * Created by aristomichael on 09/10/17.
 */

public class OrderFormPage2 extends Fragment {

    View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.order_form_page2, container, false);

        return view;
    }

}
