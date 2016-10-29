package co.uniandes.appzheimer.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import co.uniandes.appzheimer.R;

public class MenuPrincipalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void editarRutina(View v){
        Intent intent= new Intent(this, ListaRutinaActivity.class);
        startActivity(intent);
    }

    public void reconocerFamiliares(View v){
        Intent intent = new Intent(this, ReconocerFamiliarActivity.class);
        startActivity(intent);
    }
}
