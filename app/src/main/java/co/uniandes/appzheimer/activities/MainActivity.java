package co.uniandes.appzheimer.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.Date;

import co.uniandes.appzheimer.R;
import co.uniandes.appzheimer.source.AppZheimer;
import co.uniandes.appzheimer.source.Paciente;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            InputStream archivo = openFileInput(CrearPerfilActivity.DATOSUSUARIO);
            if (archivo!=null)
            {
                InputStreamReader temp = new InputStreamReader(archivo);
                BufferedReader lector = new BufferedReader(temp);
                String linea = lector.readLine();
                if (!linea.equals("No hay sesion"))
                {
                    String nombre = linea;
                    String apodo = lector.readLine();
                    String fecha = lector.readLine();
                    SimpleDateFormat df = new SimpleDateFormat("dd/mm/yyyy");
                    Date fecha2 = df.parse(fecha);
                    AppZheimer.darInstancia().setPaciente(new Paciente(nombre,apodo,fecha2));
                    Intent intent = new Intent(this,MenuPrincipalActivity.class);
                    startActivity(intent);
                }
                lector.close();
                temp.close();
                archivo.close();
            }
        }
        catch (Exception e){
            if(false){
                Button btnCrearPerfil = (Button) findViewById(R.id.crearPerfilBtn);
                btnCrearPerfil.setVisibility(View.INVISIBLE);

            }else{
                Button btnMostrarMenu = (Button) findViewById(R.id.ingresarBtn);
                btnMostrarMenu.setVisibility(View.INVISIBLE);
            }
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
