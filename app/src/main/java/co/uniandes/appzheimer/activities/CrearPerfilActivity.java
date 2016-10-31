package co.uniandes.appzheimer.activities;


import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import co.uniandes.appzheimer.R;
import co.uniandes.appzheimer.source.AppZheimer;
import co.uniandes.appzheimer.source.Paciente;

public class CrearPerfilActivity extends AppCompatActivity {

    public static final String DATOSUSUARIO = "appzheimerDatos.txt";

    private int year,month,day;

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
                TextView fechaNacimiento = (TextView) findViewById(R.id.fechaNacimientoInput);
                if(nombre.getText().equals("") || nombre.getText().toString().equals("") || fechaNacimiento.getText().toString().equals("")){
                    crearDialogo("Error","Por favor, llenar todos los campos");
                }else{
                    try {
                        SimpleDateFormat df = new SimpleDateFormat("dd/mm/yyyy");
                        Date fecha = df.parse(fechaNacimiento.getText().toString());
                        AppZheimer.darInstancia().setPaciente(new Paciente(nombre.getText().toString(), apodo.getText().toString(),fecha));
                        OutputStreamWriter impresora = new OutputStreamWriter(openFileOutput(DATOSUSUARIO,0));
                        String lineaNueva = System.getProperty("line.separator");
                        impresora.write(nombre.getText().toString());
                        impresora.write(lineaNueva);
                        impresora.write(apodo.getText().toString());
                        impresora.write(lineaNueva);
                        impresora.write(fechaNacimiento.getText().toString());
                        impresora.close();
                        listarRutina(view);
                    } catch (ParseException e) {
                        crearDialogo("Error","Hay problemas con la fecha, inténtelo de nuevo");
                    }
                    catch(java.io.IOException e)
                    {

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

    public void showDatePickerDialog(View v)
    {
        showDialog(999);
        Toast.makeText(getApplicationContext(),"ca",Toast.LENGTH_SHORT).show();
    }

    /**
     * Metodo que crear el date picker
     */
    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener(){
        @Override
        public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3)
        {
            showDate(arg1,arg2+1,arg3);
        }
    };

    /**
     * Metodo que configura el textview con la fecha actual
     * @param pYear el anio de la fecha
     * @param pMonth el mes de la fecha
     * @param pDay el dia de la fecha
     */
    private void showDate(int pYear, int pMonth, int pDay)
    {
        TextView fecha =(TextView) findViewById(R.id.fechaNacimientoInput);
        fecha.setText(new StringBuilder().append(pDay).append("/").append(pMonth).append("/").append(pYear));
    }

    /**
     * Metodo que crea el dialogo con el calendario
     * @param id
     * @return
     */
    @Override
    protected Dialog onCreateDialog(int id)
    {
        if(id==999)
        {
            return new DatePickerDialog(this,myDateListener,year,month,day);
        }
        return null;
    }

}
