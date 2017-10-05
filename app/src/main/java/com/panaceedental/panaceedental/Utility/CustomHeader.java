package com.panaceedental.panaceedental.Utility;

import android.content.Context;

import java.util.HashMap;

/**
 * Created by aristomichael on 16/09/17.
 */

public class CustomHeader {

    public static HashMap<String, String> getHeader(Context mContext){

        HashMap<String, String> headers = new HashMap<String, String>();

        headers.put("Content-Type", "application/json");

        headers.put("Version_Code",String.valueOf(ApplicationDetailsUtills.getVersionCode(mContext)));

        return headers;
    }

}
