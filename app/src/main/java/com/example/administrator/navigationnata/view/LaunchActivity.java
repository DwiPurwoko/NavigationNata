package com.example.administrator.navigationnata.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import com.example.administrator.navigationnata.MainActivity;

/**
 * Created by Administrator on 4/8/2016.
 */
public class LaunchActivity extends Activity {
    public static final String PREF_NAME = "pref" ;

    Context context;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharedPreferences = getSharedPreferences(PREF_NAME, 0);
        boolean state =  sharedPreferences.getBoolean("isLogin", false);

        if(state){
            // open main activity
            Intent intent = new Intent(LaunchActivity.this, MainActivity.class);
            startActivity(intent);
        }else {
            Intent intent = new Intent(LaunchActivity.this, LoginActivity.class);
            startActivity(intent);
        }
    }
    @Override
    public void onBackPressed()
    {
        // code here to show dialog
        super.onBackPressed();  // optional depending on your needs
        Intent intent = new Intent(LaunchActivity.this, LoginActivity.class);
        startActivity(intent);
    }
}
