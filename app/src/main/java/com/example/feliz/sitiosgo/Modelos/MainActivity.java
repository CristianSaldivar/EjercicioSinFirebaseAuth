package com.example.feliz.sitiosgo.Modelos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.feliz.sitiosgo.Modulos.Account.Ctrls.SGLogin;
import com.example.feliz.sitiosgo.Modulos.Account.Ctrls.SGLoginF;
import com.example.feliz.sitiosgo.Modulos.Account.Ctrls.SGLoginG;
import com.example.feliz.sitiosgo.R;
import com.facebook.AccessToken;
import com.facebook.login.LoginManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(AccessToken.getCurrentAccessToken() == null){
            goLoginScreen();
        }
    }

    //Método para cerrar sesión
    public void logout(View view) {
        //FirebaseAuth.getInstance().signOut();
        LoginManager.getInstance().logOut();
        goLoginScreen();
    }

    private void goLoginScreen() {
        Intent intent = new Intent(MainActivity.this, SGLogin.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

}
