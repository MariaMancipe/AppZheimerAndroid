package co.uniandes.appzheimer.activities;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
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
import co.uniandes.appzheimer.source.DBHelper;
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
            findViewById(R.id.eli).setVisibility(View.VISIBLE);
            if(e.isAcompanhado())
                si.setChecked(true);
            else
                no.setChecked(true);
            findViewById(R.id.compartirEvento).setVisibility(View.VISIBLE);
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
                            DBHelper db = new DBHelper(getApplicationContext());
                            SQLiteDatabase datos = db.getWritableDatabase();
                            ContentValues valores = new ContentValues();
                            valores.put("nombre",nombre.getText().toString());
                            valores.put("hora",hora.getText().toString());
                            if (acompanhado)
                                valores.put("conPariente",1);
                            else
                                valores.put("conPariente",0);
                            datos.update("EVENTOS",valores,"nombre='"+nombre.getText().toString()+"'",null);
                            datos.close();
                            listaRutina(view);
                        } catch (ParseException e) {
                            crearDialogo("Error", "Existen problemas con la hora. Inténtelo de nuevo");
                        }


                    }else{
                        try {
                            SimpleDateFormat df = new SimpleDateFormat("hh:mm");
                            Date hr = df.parse(hora.getText().toString());
                            AppZheimer.darInstancia().agregarEvento(nombre.getText().toString(),"",hr,acompanhado);
                            DBHelper db = new DBHelper(getApplicationContext());
                            SQLiteDatabase datos = db.getWritableDatabase();
                            ContentValues valores = new ContentValues();
                            valores.put("nombre",nombre.getText().toString());
                            valores.put("hora",hora.getText().toString());
                            if (acompanhado)
                                valores.put("conPariente",1);
                            else
                                valores.put("conPariente",0);
                            datos.insert("EVENTOS",null,valores);
                            datos.close();
                            listaRutina(view);
                        } catch (ParseException e) {
                           crearDialogo("Error", "Existen problemas con la hora. Inténtelo de nuevo");
                        }

                    }

                }

            }
        });

        FloatingActionButton eli = (FloatingActionButton) findViewById(R.id.eli);
        eli.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                AppZheimer.darInstancia().getPaciente().eliminarEvento(indice);
                DBHelper db = new DBHelper(getApplicationContext());
                SQLiteDatabase datos = db.getWritableDatabase();
                ContentValues valores = new ContentValues();
                datos.delete("EVENTOS","nombre='"+nombre.getText().toString()+"'",null);
                listaRutina(view);
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

    public void enviarEvento(View v)
    {
        String Mensaje = "He creado un nuevo evento que se llama: " + nombre.getText().toString() + " y lo debo realizar a las " + hora.getText().toString();
        if (acompanhado)
            Mensaje += " y lo tengo que hacer acompaniado";
        else
            Mensaje += " y lo puedo hacer solo!";
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT,Mensaje);
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
    }


}
