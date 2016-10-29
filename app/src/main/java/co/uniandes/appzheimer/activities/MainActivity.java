package co.uniandes.appzheimer.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import co.uniandes.appzheimer.R;
import co.uniandes.appzheimer.source.AppZheimer;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(false){
            Button btnCrearPerfil = (Button) findViewById(R.id.crearPerfilBtn);
            btnCrearPerfil.setVisibility(View.INVISIBLE);

        }else{
            Button btnMostrarMenu = (Button) findViewById(R.id.ingresarBtn);
            btnMostrarMenu.setVisibility(View.INVISIBLE);
        }
    }

    public void crearPerfil(View v){
        Intent intent = new Intent(this, CrearPerfilActivity.class);
        startActivity(intent);
    }

    public void mostrarMenuPrincipal(View v){
        Intent intent=new Intent(this, MenuPrincipalActivity.class);
        startActivity(intent);
    }

    public void salir(){

    }
}
