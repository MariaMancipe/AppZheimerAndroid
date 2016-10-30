package co.uniandes.appzheimer.activities;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import co.uniandes.appzheimer.R;
import co.uniandes.appzheimer.source.AppZheimer;
import co.uniandes.appzheimer.source.Evento;
import co.uniandes.appzheimer.source.Paciente;

public class CrearEventoActivity extends AppCompatActivity {

    private boolean acompanhado;
    private EditText nombre;
    private EditText hora;
    private RadioButton si;
    private RadioButton no;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_evento);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        acompanhado = false;

        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            return;
        }

        nombre = (EditText) findViewById(R.id.nombreEventoInput);
        hora = (EditText) findViewById(R.id.horaEventoInput);
        si = (RadioButton) findViewById(R.id.si_acompanhado);
        no =(RadioButton) findViewById(R.id.no_acompanhado);

        final int indice = extras.getInt("indice");
        if (indice>-1) {
            setTitle("Editar Evento de la Rutina");
            Evento e = AppZheimer.darInstancia().getPaciente().getRutina().get(indice);
            nombre.setText(e.getNombre());
            if(e.isAcompanhado())
                si.setChecked(true);
            else
                no.setChecked(true);
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(nombre.getText().toString().equals("") || hora.getText().toString().equals("") || !(si.isChecked()||no.isChecked())){
                    crearDialogo("Error","Por favor, ingrese todos los campos");
                }else{
                    if(indice>-1){
                        try {
                            SimpleDateFormat df = new SimpleDateFormat("hh:mm");
                            Date hr = df.parse(hora.getText().toString());
                            AppZheimer.darInstancia().getPaciente().getRutina().get(indice).setNombre(nombre.getText().toString());
                            AppZheimer.darInstancia().getPaciente().getRutina().get(indice).setRutaImagen("");
                            AppZheimer.darInstancia().getPaciente().getRutina().get(indice).setHora(hr);
                            AppZheimer.darInstancia().getPaciente().getRutina().get(indice).setAcompanhado(acompanhado);
                            listaRutina(view);
                        } catch (ParseException e) {
                            crearDialogo("Error", "Existen problemas con la hora. Inténtelo de nuevo");
                        }


                    }else{
                        try {
                            SimpleDateFormat df = new SimpleDateFormat("hh:mm");
                            Date hr = df.parse(hora.getText().toString());
                            AppZheimer.darInstancia().agregarEvento(nombre.getText().toString(),"",hr,acompanhado);
                            listaRutina(view);
                        } catch (ParseException e) {
                           crearDialogo("Error", "Existen problemas con la hora. Inténtelo de nuevo");
                        }

                    }

                }

            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.si_acompanhado:
                if (checked)
                    acompanhado=true;
                    break;
            case R.id.no_acompanhado:
                if (checked)
                    acompanhado=false;
                    break;
        }
    }
    public void crearDialogo(String titulo, String dialogo){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(dialogo);
        builder.setTitle(titulo);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void listaRutina(View v){
        Intent intent = new Intent(this,ListaRutinaActivity.class);
        startActivity(intent);
    }

}
