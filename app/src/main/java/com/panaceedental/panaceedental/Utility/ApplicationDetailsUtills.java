package com.panaceedental.panaceedental.Utility;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * Created by aristomichael on 16/09/17.
 */

public class ApplicationDetailsUtills {

    public static int getVersionCode(Context mContext)

    {
        int versionCode = 0;

        try {

            PackageInfo pInfo = mContext.getPackageManager().getPackageInfo(mContext.getPackageName(), 0);

            versionCode = pInfo.versionCode;

        } catch (PackageManager.NameNotFoundException e) {

            e.printStackTrace();
        }

        return versionCode;
    }

}
