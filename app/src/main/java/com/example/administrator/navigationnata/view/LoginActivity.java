package com.example.administrator.navigationnata.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.administrator.navigationnata.MainActivity;
import com.example.administrator.navigationnata.R;
import com.example.administrator.navigationnata.config.BaseApplication;
import com.example.administrator.navigationnata.config.WebService;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 4/5/2016.
 */
public class LoginActivity extends AppCompatActivity {
    public static final String PREF_NAME = "pref" ;

    public static final String KEY_PASSWORD="password";
    public static final String KEY_EMAIL="email";

    Context context;
    SharedPreferences sharedPreferences;

    private EditText txtEmail;
    private EditText txtPassword;
    private Button btnLogin,btnLinkToRegister;

    private TextView btnLinkToForgotPassword;

    private String email;
    private String password;

    private Toolbar toolbar;

    private boolean loggedIn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
     //   getSupportActionBar().setDisplayHomeAsUpEnabled(true);
       // getSupportActionBar().setDisplayShowHomeEnabled(true);
     //   getSupportActionBar().setTitle("Login");

        txtEmail = (EditText) findViewById(R.id.txtEmail);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLinkToRegister = (Button) findViewById(R.id.btnLinkToRegister);
        btnLinkToForgotPassword = (TextView) findViewById(R.id.btnLinkToForgotPassword);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = txtEmail.getText().toString().trim();
                password = txtPassword.getText().toString().trim();

                // Check for empty data in the form
                if (!email.isEmpty() && !password.isEmpty()) {
                    // login user
                    userLogin();
                } else {
                    // Prompt user to enter credentials
                    Toast.makeText(getApplicationContext(),
                            "Invalid username or password", Toast.LENGTH_LONG)
                            .show();
                }
            }
        });

        btnLinkToRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),
                        RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnLinkToForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),
                        ForgotPasswordActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

   /* @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences = getSharedPreferences(PREF_NAME,0);
        loggedIn = sharedPreferences.getBoolean("isLogin", false);

        if(loggedIn){
            Log.d(PREF_NAME,"isLogin");
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            intent.putExtra(KEY_EMAIL, email);
            startActivity(intent);
        }
    }*/


    private void userLogin (){
        email = txtEmail.getText().toString().trim();
        password = txtPassword.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, WebService.getLogin(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("login", "response login " + response.toString());
                    //    BaseApplication.getInstance().startLoader(LoginActivity.this);
                        if(response.toString().contains("0")){

                            Toast.makeText(LoginActivity.this,
                                    "Invalid username or password", Toast.LENGTH_LONG)
                                    .show();
                        }else{
                    //        BaseApplication.getInstance().stopLoader();
                            SharedPreferences sharedPreferences = LoginActivity.this.
                                    getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putBoolean("isLogin", true);
                            editor.putString(KEY_EMAIL, email);
                            editor.commit();

                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            intent.putExtra(KEY_EMAIL, email);
                            startActivity(intent);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        BaseApplication.getInstance().stopLoader();
                       // Toast.makeText(LoginActivity.this,error.toString(),Toast.LENGTH_LONG ).show();
                        NetworkResponse networkResponse = error.networkResponse;
                        if (networkResponse != null) {
                            Log.d("Volley", "Error. HTTP Status Code:" + networkResponse.statusCode);
                        }
                        if (error instanceof TimeoutError) {
                            Log.d("Volley", "TimeoutError");
                        } else if (error instanceof NoConnectionError) {
                            Log.d("Volley", "NoConnectionError");
                        } else if (error instanceof AuthFailureError) {
                            Log.d("Volley", "AuthFailureError");
                        } else if (error instanceof ServerError) {
                            Log.d("Volley", "ServerError");
                        } else if (error instanceof NetworkError) {
                            Log.d("Volley", "NetworkError");
                        } else if (error instanceof ParseError) {
                            Log.d("Volley", "ParseError");
                        }
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<String,String>();
                map.put(KEY_EMAIL,email);
                map.put(KEY_PASSWORD,password);
                return map;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    @Override
    public void onBackPressed() {
        // Disable going back to the MainActivity
        moveTaskToBack(true);
    }

}
