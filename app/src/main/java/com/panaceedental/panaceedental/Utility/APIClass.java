package com.panaceedental.panaceedental.Utility;

import android.content.Context;
import android.os.Bundle;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.panaceedental.panaceedental.R;

import org.json.JSONObject;

import java.util.Map;

/**
 * Created by aristomichael on 13/08/17.
 */

public class APIClass {

    Map<String, String> params;
    MyResultReceiver myResultReceiver;
    int reqType;
    Context mContext;

    // Tag used to cancel the request
    String tag_json_obj = "json_obj_req";

    public APIClass(Context mContext, Map<String, String> params, MyResultReceiver myResultReceiver, int reqType) {
        this.params = params;
        this.myResultReceiver = myResultReceiver;
        this.reqType = reqType;
        this.mContext = mContext;
    }


    public void makeJsonRequest() {

//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
//                (Const.mainURL, new JSONObject(params), new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//
//                        String result = "";
//                        try {
//                            result = response.getString("error");
//                        }catch (Exception e) {
//                            e.printStackTrace();
//                        }
//
//                        Bundle bundle = new Bundle();
//                        bundle.putString(mContext.getString(R.string.result), result);
//                        myResultReceiver.send(reqType, bundle);
//
//                    }
//                }, new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//
//                    }
//                });
//
//        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
//                Const.MY_SOCKET_TIMEOUT_MS,
//                Const.DEFAULT_MAX_RETRIES,
//                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//
//        if (AppController.getInstance() != null) {
//            AppController.getInstance().addToRequestQueue(jsonObjectRequest, tag_json_obj);
//        }



        JsonObjectRequest jsonObjReq = new JsonObjectRequest(
                Const.mainURL, new JSONObject(params),
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        try {

                            Bundle bundle = new Bundle();
                            bundle.putString("Response", response.toString());
                            myResultReceiver.send(reqType, bundle);

                        } catch (OutOfMemoryError e) {
                            myResultReceiver.send(0, null);
                        } catch (Exception e) {
                            myResultReceiver.send(0, null);
                        }

                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                int statusCode = 0;
                if (error.networkResponse != null) {
                    statusCode = error.networkResponse.statusCode;
                }
                VolleyLog.d("@@@1 Error " + statusCode + " " + error.getMessage());

                myResultReceiver.send(0, null);

                if (error instanceof TimeoutError || error instanceof NoConnectionError) {
//                    Toast.makeText(mContext, "Network timeout",
//                            Toast.LENGTH_LONG).show();
                } else if (error instanceof AuthFailureError) {
//                    Toast.makeText(mContext, "AuthFailureError",
//                            Toast.LENGTH_LONG).show();
                } else if (error instanceof ServerError) {
//                    Toast.makeText(mContext, "Server error",
//                            Toast.LENGTH_LONG).show();
                } else if (error instanceof NetworkError) {
//                    Toast.makeText(mContext, "network_error",
//                            Toast.LENGTH_LONG).show();
                }
            }

        }) {

            /**
             * Passing some request headers
             * */
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
            /*    HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                return headers;*/

                return CustomHeader.getHeader(mContext);

            }

        };

        jsonObjReq.setRetryPolicy(new DefaultRetryPolicy(
                Const.MY_SOCKET_TIMEOUT_MS,
                Const.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        // Adding request to request queue
        if (AppController.getInstance() != null) {
            AppController.getInstance().addToRequestQueue(jsonObjReq,
                    tag_json_obj);
        }




    }

}
