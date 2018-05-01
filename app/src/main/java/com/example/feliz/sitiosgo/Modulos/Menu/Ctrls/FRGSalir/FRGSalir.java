package com.example.feliz.sitiosgo.Modulos.Menu.Ctrls.FRGSalir;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.feliz.sitiosgo.Modulos.Menu.Ctrls.SGMenuPrincipal;
import com.example.feliz.sitiosgo.R;

public class FRGSalir extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_frgperfil, container, false);

        //Intent intentSalir = new Intent(FRGSalir.this, SGMenuPrincipal.class);
        //Open next activity
        //startActivity(intentSalir);
        //Close this activity
        //System.exit(0);

    }


}
