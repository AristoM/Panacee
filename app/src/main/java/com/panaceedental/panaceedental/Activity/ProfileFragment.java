package com.panaceedental.panaceedental.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.panaceedental.panaceedental.R;
import com.panaceedental.panaceedental.Utility.SharedPref;

/**
 * Created by aristomichael on 07/10/17.
 */

public class ProfileFragment extends Fragment{

    View view;
    TextView bt_logout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.profile_fragment, container, false);
        bt_logout = view.findViewById(R.id.bt_logout);

        bt_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPref.clearLoginDetails(getActivity());

                Intent homescreen = new Intent(getActivity(), SplashLoginScreen.class);
                startActivity(homescreen);
                getActivity().overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);
                getActivity().finish();


            }
        });

        return view;
    }
}
