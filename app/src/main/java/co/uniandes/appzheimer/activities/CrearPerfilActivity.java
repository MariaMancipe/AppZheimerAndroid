package co.uniandes.appzheimer.activities;


import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import co.uniandes.appzheimer.R;
import co.uniandes.appzheimer.source.AppZheimer;
import co.uniandes.appzheimer.source.Paciente;

public class CrearPerfilActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_perfil);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText nombre = (EditText) findViewById(R.id.nombrePacienteInput);
                EditText apodo = (EditText) findViewById(R.id.apodoPacienteInput);
                EditText fechaNacimiento = (EditText) findViewById(R.id.fechaNacimientoInput);
                if(nombre.getText().equals("") || nombre.getText().toString().equals("") || fechaNacimiento.getText().toString().equals("")){
                    crearDialogo("Error","Por favor, llenar todos los campos");
                }else{
                    try {
                        SimpleDateFormat df = new SimpleDateFormat("dd/mm/yyyy");
                        Date fecha = df.parse(fechaNacimiento.getText().toString());
                        AppZheimer.darInstancia().setPaciente(new Paciente(nombre.getText().toString(), apodo.getText().toString(),fecha));
                        listarRutina(view);
                    } catch (ParseException e) {
                        crearDialogo("Error","Hay problemas con la fecha, inténtelo de nuevo");
                    }

                }

            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void crearDialogo(String titulo, String dialogo){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(dialogo);
        builder.setTitle(titulo);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void listarRutina(View v){
        Intent intent = new Intent(this, ListaRutinaActivity.class);
        startActivity(intent);
    }

}
