package com.example.administrator.navigationnata.config;

import android.app.Application;
import android.app.ProgressDialog;
import android.content.Context;
import android.text.TextUtils;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Administrator on 3/31/2016.
 */
public class BaseApplication extends Application {
    private static final int TIMEOUT_MS = 25000; // 5second

    private RequestQueue requestQueue;
    private static BaseApplication instance;
    public static final String TAG = BaseApplication.class
            .getSimpleName();
    private ProgressDialog dialog;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
    public static synchronized BaseApplication getInstance() {
        return instance;
    }

    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(getApplicationContext());
        }
        return requestQueue;
    }

    public <T> void addToRequestQueue(Request<T> request, String tag) {
        //Utils.TRACE("BaseApplication", "addToRequestQueue : " + request.getUrl());
        request.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        // set retry policy
        request.setRetryPolicy(new DefaultRetryPolicy(
                TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        getRequestQueue().add(request);
    }

    public void cancelPendingRequest(Object tag) {
        if (requestQueue != null)
            requestQueue.cancelAll(tag);
    }


    public void startLoader(Context context){
        dialog = new ProgressDialog(context);
        dialog.setMessage("Loading..");
        dialog.setCancelable(false);
        dialog.setInverseBackgroundForced(false);
        dialog.show();
    }

    public void stopLoader(){
        if(dialog.isShowing()) {
            dialog.dismiss();
        }
    }

}
