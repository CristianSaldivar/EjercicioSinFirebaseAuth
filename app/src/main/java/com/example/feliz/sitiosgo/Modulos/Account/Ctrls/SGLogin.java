package com.example.feliz.sitiosgo.Modulos.Account.Ctrls;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.feliz.sitiosgo.Modelos.MainActivity;
import com.example.feliz.sitiosgo.Modulos.Menu.Ctrls.SGMenuPrincipal;
import com.example.feliz.sitiosgo.R;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.Arrays;


public class SGLogin extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    //Se instancián en el método onCreate()
    private LoginButton loginButton;
    private CallbackManager callbackManager;

    //ProgressBar
    private ProgressBar progressBar;

    //Todo esto que sigue es de Google
    //intermediario para validar el inicio de sesion con google
    private GoogleApiClient googleApiClient;

    //Boton de google en el layout, inicializarlo
    private SignInButton signInButton;

    //Constante
    public static final int SIGN_IN_CODE = 777;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sglogin);

        callbackManager = CallbackManager.Factory.create();

        loginButton = (LoginButton) findViewById(R.id.loginButton);
        //Pedir permiso al ususario de obtener su correo desde facebook
        loginButton.setReadPermissions(Arrays.asList("email"));
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                //Respuesta Exitosa
                goMainScreen();
                //handleFacebookAccessToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                //Se mostrará mensaje si la operación se cancela
                Toast.makeText(getApplicationContext(), R.string.cancel_login, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException error) {
                //Se mostrará mensaje en caso de error
                Toast.makeText(getApplicationContext(), R.string.error_login, Toast.LENGTH_SHORT).show();
            }
        });

        //Todo esto es de Google
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
            callbackManager.onActivityResult(requestCode, resultCode, data);

            if(requestCode == SIGN_IN_CODE){
                GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
                handleSignInResult(result);
            }
        }

    //Todo, este metodo es de google
    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    /*@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == SIGN_IN_CODE){
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }*/

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

    /*private void goMainScreen() {
        android.content.Intent intent = new Intent(SGLogin.this, MainActivity.class);
        intent.addFlags(android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP | android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK | android.content.Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }*/

    private void goMainScreen() {
        //Intent intent = new Intent(SGLogin.this, MainActivity.class);
        Intent intent = new Intent(SGLogin.this, SGMenuPrincipal.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
