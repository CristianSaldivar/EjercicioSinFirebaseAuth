package com.example.feliz.sitiosgo.Modulos.Account.Ctrls;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.feliz.sitiosgo.Modelos.MainActivity;
import com.example.feliz.sitiosgo.R;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;

public class SGLoginG extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    //intermediario para validar el inicio de sesion con google
    private GoogleApiClient googleApiClient;

    //Boton de google en el layout, inicializarlo
    private SignInButton signInButton;

    //Constante
    public static final int SIGN_IN_CODE = 777;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sglogin_g);

        //Inicio de sesion con google
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                //Obtener un token
                //.requestIdToken(getString(R.string.default_web_client_id))
                //metodo para obtener el correo del usuario autenticado
                .requestEmail()
                .build();

        ///Inicializarlo
        googleApiClient = new GoogleApiClient.Builder(this)
                //gestiona el ciclo de vida de GoogleApiClient con la Activity
                //Activity y quien se encargara de escuchar si algo sale mal
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        signInButton = (SignInButton) findViewById(R.id.signInButton);
        //signInButton = (SignInButton) findViewById(R.id.login_button);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
                startActivityForResult(intent, SIGN_IN_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == SIGN_IN_CODE){
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    private void handleSignInResult(GoogleSignInResult result) {
        if(result.isSuccess()){
            goMainScreen();
            //metodo encargado de la autenticacion con Firebase
            //firebaseAuthWithGoogle(result.getSignInAccount());
            //goMainScreen();
        } else {
            Toast.makeText(this, "Problemas al iniciar sesion", Toast.LENGTH_SHORT).show();
        }
    }

    private void goMainScreen() {
        Intent intent = new Intent(SGLoginG.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
