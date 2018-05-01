package com.example.feliz.sitiosgo.Modulos.SplashScreen.Ctrls;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.feliz.sitiosgo.Modulos.Account.Ctrls.SGLogin;
import com.example.feliz.sitiosgo.Modulos.Account.Ctrls.SGLoginF;
import com.example.feliz.sitiosgo.R;

public class SGSplashScreen extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sgsplash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent loginIntent = new Intent(SGSplashScreen.this, SGLogin.class);
                //Intent loginIntent = new Intent(SGSplashScreen.this, SGLogin.class);
                startActivity(loginIntent);
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
