package com.panaceedental.panaceedental.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.panaceedental.panaceedental.Parcers.CommonParcer;
import com.panaceedental.panaceedental.Parcers.ProductsListParser;
import com.panaceedental.panaceedental.R;
import com.panaceedental.panaceedental.Utility.APIClass;
import com.panaceedental.panaceedental.Utility.Const;
import com.panaceedental.panaceedental.Utility.MyResultReceiver;
import com.panaceedental.panaceedental.Utility.RequestType;
import com.panaceedental.panaceedental.Utility.SharedPref;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by aristomichael on 07/10/17.
 */

public class HomeFragment extends Fragment implements MyResultReceiver.Receiver {

    View view;
    RecyclerView rvProductList;
    ProgressDialog progress;
    MyResultReceiver myResultReceiver;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.home_fragment, container, false);


        myResultReceiver = new MyResultReceiver(new Handler());
        myResultReceiver.setReceiver(this);

        rvProductList = (RecyclerView) view.findViewById(R.id.rv_productlist);

        Map<String, String> param = new HashMap<>();
        param.put(Const.ACTION, Const.PRODUCTS_ACTION);

        APIClass apiClass = new APIClass(getActivity(), param, myResultReceiver, RequestType.PRODUCTSLIST_REQUEST, Const.mainURL);
        apiClass.makeJsonRequest();

        return view;
    }


    @Override
    public void onReceiveResult(int resultCode, Bundle resultData) {

        if(resultData != null) {

            Gson gson = new Gson();
            ProductsListParser productsListParser = gson.fromJson(resultData.getString(Const.RESPONSE), ProductsListParser.class);


            switch (resultCode) {

                case RequestType.PRODUCTSLIST_REQUEST:

                    String status = productsListParser.getStatus();
//                    String message = commonParcer.getMessage();
                    if(!status.isEmpty() && status.equals(getString(R.string.success))) {

                        ArrayList<ProductsListParser.Products> productsList = new ArrayList<>();
                        productsList.addAll(productsListParser.getProducts());

                    } else {

                    }

//                    Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
                    hideDialog();
                    break;


            }
        } else {
//            SharedPref.saveLoginDetails(SplashLoginScreen.this, "", "");
            Toast.makeText(getActivity(), getString(R.string.try_again_later), Toast.LENGTH_SHORT).show();
            hideDialog();
        }
    }

    private void startDialog() {
        progress=new ProgressDialog(getActivity());
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
