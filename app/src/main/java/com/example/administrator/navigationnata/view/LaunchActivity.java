package com.example.administrator.navigationnata.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;

import com.example.administrator.navigationnata.MainActivity;

/**
 * Created by Administrator on 4/8/2016.
 */
public class LaunchActivity extends Activity {
    public static final String PREF_NAME = "pref" ;

    private static int splashInterval = 1000;

    Context context;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                sharedPreferences = getSharedPreferences(PREF_NAME, 0);
                boolean state =  sharedPreferences.getBoolean("isLogin", false);
                // TODO Auto-generated method stub
                if(state){
                    // open main activity
                    Intent intent = new Intent(LaunchActivity.this, MainActivity.class);
                    startActivity(intent);
                }else {
                    Intent intent = new Intent(LaunchActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
                //jeda selesai Splashscreen
                this.finish();
            }

            private void finish() {
                // TODO Auto-generated method stub

            }
        }, splashInterval);


    }
   /* @Override
        public void onBackPressed()
        {
            // code here to show dialog
            super.onBackPressed();  // optional depending on your needs
            Intent intent = new Intent(LaunchActivity.this, LoginActivity.class);
            startActivity(intent);
    }*/
}
