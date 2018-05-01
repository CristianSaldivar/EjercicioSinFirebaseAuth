package com.example.feliz.sitiosgo.Modulos.Menu.Ctrls;

/*import android.app.FragmentManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.app.Fragment;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.feliz.sitiosgo.Modulos.Menu.Ctrls.FRGContacto.FRGContacto;
import com.example.feliz.sitiosgo.R;*/

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;


import com.example.feliz.sitiosgo.Modelos.MainActivity;
import com.example.feliz.sitiosgo.Modulos.Account.Ctrls.SGLogin;
import com.example.feliz.sitiosgo.Modulos.Menu.Ctrls.FRGContacto.FRGContacto;
import com.example.feliz.sitiosgo.Modulos.Menu.Ctrls.FRGMapa.FRGMapa;
import com.example.feliz.sitiosgo.Modulos.Menu.Ctrls.FRGPerfil.FRGPerfil;
import com.example.feliz.sitiosgo.Modulos.Menu.Ctrls.FRGSalir.FRGSalir;
import com.example.feliz.sitiosgo.Modulos.Menu.Ctrls.FRGSitios.FRGSitios;
import com.example.feliz.sitiosgo.R;
import com.facebook.login.LoginManager;

import static android.R.attr.id;

public class SGMenuPrincipal extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sgmenu_principal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.sgmenu_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        FragmentManager fragmentManager = getSupportFragmentManager();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_contacto) {
            fragmentManager.beginTransaction().replace(R.id.contenedor, new FRGContacto()).commit();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        FragmentManager fragmentManager = getSupportFragmentManager();
        int id = item.getItemId();

        if (id == R.id.nav_perfil) {
            fragmentManager.beginTransaction().replace(R.id.contenedor, new FRGPerfil()).commit();
        } else if (id == R.id.nav_sitios) {
            fragmentManager.beginTransaction().replace(R.id.contenedor, new FRGSitios()).commit();
        } else if (id == R.id.nav_mapa) {
            fragmentManager.beginTransaction().replace(R.id.contenedor, new FRGMapa()).commit();
        } else if (id == R.id.nav_contacto) {
            fragmentManager.beginTransaction().replace(R.id.contenedor, new FRGContacto()).commit();
        } else if (id == R.id.nav_salir) {
            fragmentManager.beginTransaction().replace(R.id.contenedor, new FRGSalir()).commit();
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //Método para cerrar sesión
    public void logout(View view) {
        //FirebaseAuth.getInstance().signOut();
        LoginManager.getInstance().logOut();
        goLoginScreen();
    }

    private void goLoginScreen() {
        Intent intent = new Intent(SGMenuPrincipal.this, SGLogin.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

}
