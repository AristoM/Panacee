package com.panaceedental.panaceedental.Utility;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by aristomichael on 06/10/17.
 */

public class SharedPref {

    public static void saveLoginDetails(Context context, String userName, String password) {

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(Const.SP_USERNAME, userName);
        editor.putString(Const.SP_PASSWORD, password);
        editor.apply();

    }

    public static String getUserName(Context context) {

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        return sp.getString(Const.SP_USERNAME, "");

    }

    public static String getPassword(Context context) {

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        return sp.getString(Const.SP_PASSWORD, "");

    }

    public static void clearLoginDetails(Context context) {

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        sp.edit().remove(Const.SP_USERNAME).commit();
        sp.edit().remove(Const.SP_PASSWORD).commit();


    }

}
