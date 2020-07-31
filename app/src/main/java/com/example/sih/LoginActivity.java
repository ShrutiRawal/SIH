package com.example.sih;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Locale;

public class LoginActivity extends AppCompatActivity {

    private Button btnLogin,btnChngelang,login,register,btnRegister;
    private ImageView logo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLocale();
        setContentView(R.layout.activity_login);
        initialiseWidgets();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLoginPopup();
            }
        });
        btnChngelang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showChangeLanguage();
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRegistrationPopup();
            }
        });
    }

    private void showChangeLanguage() {
        final String[] languages = {"English","हिन्दी"};
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(LoginActivity.this);
        mBuilder.setTitle("SELECT LANGUAGE");
        mBuilder.setSingleChoiceItems(languages, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                 if(i==0) {
                     setAppLocale("en");
                     recreate();
                 } else//{
                     if(i==1){
                         setAppLocale("hi-rIN");
                         recreate();
                     }
                // }
                 dialog.dismiss();
            }
        });
        AlertDialog mDialog = mBuilder.create();
        mDialog.show();
    }


    private void setAppLocale(String en) {
        /*Locale locale = new Locale(en);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,getBaseContext().getResources().getDisplayMetrics());
        SharedPreferences.Editor editor = getSharedPreferences("Settings",MODE_PRIVATE).edit();
        editor.putString("My_Lang",en);
        editor.apply();*/
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.JELLY_BEAN_MR1){
            conf.setLocale(new Locale(en));
        }else{
            conf.locale = new Locale(en);
        }
        res.updateConfiguration(conf,dm);
    }

    public void loadLocale(){
        SharedPreferences prefs = getSharedPreferences("Settings", Activity.MODE_PRIVATE);
        String language = prefs.getString("My_Lang","");
        setAppLocale(language);
    }

    private void showLoginPopup() {
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(LoginActivity.this);
        LayoutInflater inflater = getLayoutInflater();
        View customview = inflater.inflate(R.layout.popup_user_login,null);
        mBuilder.setView(customview);
        AlertDialog mDialog = mBuilder.create();
        mDialog.show();
        final EditText phone = customview.findViewById(R.id.phone);
        final EditText password = customview.findViewById(R.id.password);
        TextView forgotPwd = customview.findViewById(R.id.forgotPwd);
        login = customview.findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
                startActivity(intent);
            }
        });
    }

    private  void showRegistrationPopup(){
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(LoginActivity.this);
        LayoutInflater inflater = getLayoutInflater();
        View customview = inflater.inflate(R.layout.popup_user_reg,null);
        mBuilder.setView(customview);
        AlertDialog mDialog = mBuilder.create();
        mDialog.show();
        final EditText name = customview.findViewById(R.id.name);
        final EditText phone = customview.findViewById(R.id.phone);
        final EditText password = customview.findViewById(R.id.password);
        register = customview.findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initialiseWidgets() {
        btnLogin = findViewById(R.id.login_button);
        btnChngelang = findViewById(R.id.changeLanguage);
        logo = findViewById(R.id.mainLogo);
        btnRegister = (Button)findViewById(R.id.register_button);

    }
}
