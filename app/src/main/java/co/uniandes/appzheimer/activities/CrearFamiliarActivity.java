package co.uniandes.appzheimer.activities;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;

import co.uniandes.appzheimer.R;
import co.uniandes.appzheimer.source.AppZheimer;
import co.uniandes.appzheimer.source.DBHelper;
import co.uniandes.appzheimer.source.Familiar;

public class CrearFamiliarActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    static final int REQUEST_IMAGE_CAPTURE = 1;
    private int indice;
    private String parentesco;
    private EditText nombre;
    private EditText apodo;
    private Spinner parentescos;
    private String rutaImagen;
    private Familiar f;
    //parentesco

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_familiar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        nombre = (EditText) findViewById(R.id.nombreFamiliarInput);
        apodo =(EditText) findViewById(R.id.apodoFamiliarInput);
        parentescos=(Spinner)findViewById(R.id.parentescoSpinner);
        parentescos.setOnItemSelectedListener(this);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,Familiar.parentescos);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        parentescos.setAdapter(dataAdapter);


        crearDialogo("Aviso", "Por favor ingrese sólo el nombre del familiar. Sin apellidos.");



        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            return;
        }
        indice = extras.getInt("indice");
        if(indice>-1){
            setTitle("Editar Información del Familiar");
            f= AppZheimer.darInstancia().getPaciente().getFamiliares().get(indice);
            nombre.setText(f.getNombre());
            apodo.setText(f.getApodo());
            parentescos.setSelection(Familiar.indiceParentesco(f.getParentesco()));
            findViewById(R.id.eli).setVisibility(View.VISIBLE);
        }

        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(nombre.getText().toString().equals("") || nombre.getText().toString().equals("")||parentescos.getSelectedItem()==null||parentesco==null){
                    crearDialogo("Error","Por favor, ingrese todos los campos");
                }else{
                    if(indice>-1){
                        if(!f.getNombre().equals(nombre.getText().toString()))
                            AppZheimer.darInstancia().getPaciente().getFamiliares().get(indice).setNombre(nombre.getText().toString());
                        if(!f.getApodo().equals(apodo.getText().toString()))
                            AppZheimer.darInstancia().getPaciente().getFamiliares().get(indice).setApodo(apodo.getText().toString());
                        if(!f.getParentesco().equals(parentesco))
                            AppZheimer.darInstancia().getPaciente().getFamiliares().get(indice).setParentesco(parentesco);
                        if(!f.getRutaImagen().equals(rutaImagen))
                            AppZheimer.darInstancia().getPaciente().getFamiliares().get(indice).setRutaImagen(rutaImagen);
                        DBHelper db = new DBHelper(getApplicationContext());
                        SQLiteDatabase datos = db.getWritableDatabase();
                        ContentValues valores = new ContentValues();
                        valores.put("nombrealias",nombre.getText().toString()+apodo.getText().toString());
                        valores.put("nombre",nombre.getText().toString());
                        valores.put("apodo",apodo.getText().toString());
                        valores.put("relacion",parentesco);
                        valores.put("rutaImagen",rutaImagen);
                        datos.update("FAMILIARES",valores,"nombrealias='"+nombre.getText().toString()+apodo.getText().toString()+"'",null);
                        datos.close();
                        listaFamiliar(view);
                    }else{
                        AppZheimer.darInstancia().agregarFamiliar(nombre.getText().toString(),apodo.getText().toString(),parentesco,"","");
                        DBHelper db = new DBHelper(getApplicationContext());
                        SQLiteDatabase datos = db.getWritableDatabase();
                        ContentValues valores = new ContentValues();
                        valores.put("nombrealias",nombre.getText().toString()+apodo.getText().toString());
                        valores.put("nombre",nombre.getText().toString());
                        valores.put("apodo",apodo.getText().toString());
                        valores.put("relacion",parentesco);
                        valores.put("rutaImagen",rutaImagen);
                        datos.insert("FAMILIARES",null,valores);
                        datos.close();
                        listaFamiliar(view);
                    }
                }
            }
        });

        final FloatingActionButton eli = (FloatingActionButton) findViewById(R.id.eli);
        eli.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                AppZheimer.darInstancia().getPaciente().eliminarFamiliar(indice);
                DBHelper db = new DBHelper(getApplicationContext());
                SQLiteDatabase datos = db.getWritableDatabase();
                datos.delete("FAMILIARES","nombrealias='"+nombre.getText().toString()+apodo.getText().toString()+"'",null);
                listaFamiliar(view);
            }
        });
    }

    public void listaFamiliar(View v){
        Intent intent = new Intent(this, ListaFamiliaresActivity.class);
        startActivity(intent);
    }

    public void crearDialogo(String titulo, String dialogo){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(dialogo);
        builder.setTitle(titulo);
        AlertDialog dialog = builder.create();
        dialog.show();
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        parentesco= parentescos.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void agregarFotoFamiliar(View v)
    {
        tomarFoto();
    }

    public void tomarFoto()
    {
        Intent tomarFotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(tomarFotoIntent.resolveActivity(getPackageManager())!=null)
        {
            startActivityForResult(tomarFotoIntent,REQUEST_IMAGE_CAPTURE);
        }
    }

    /**
     * Metodo para poder manipular la informacion de la foto
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if(requestCode==REQUEST_IMAGE_CAPTURE && resultCode==RESULT_OK)
        {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            ImageView imagen = (ImageView) findViewById(R.id.imagenFamiliarNuevo);
            imagen.setImageBitmap(imageBitmap);
            Uri tempUri = getImageUri(getApplicationContext(),imageBitmap);
            File finalFile = new File(getRealPathFromURI(tempUri));
            rutaImagen = finalFile.getAbsolutePath();
        }
    }

    public Uri getImageUri(Context inContext, Bitmap imagenP)
    {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        imagenP.compress(Bitmap.CompressFormat.JPEG,100,bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(),imagenP,"TituloImagen",null);
        return Uri.parse(path);
    }

    public String getRealPathFromURI(Uri URI)
    {
        Cursor cursor = getContentResolver().query(URI,null,null,null,null);
        cursor.moveToFirst();
        int indice = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
        return cursor.getString(indice);
    }


}
