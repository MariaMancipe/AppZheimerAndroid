package co.uniandes.appzheimer.activities;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import co.uniandes.appzheimer.R;
import co.uniandes.appzheimer.source.AppZheimer;

public class ListaFamiliaresActivity extends AppCompatActivity {

    private int indice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_familiares);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                crearFamiliar(view);
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ListView list = (ListView)findViewById(R.id.familiaresList);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, AppZheimer.darInstancia().getPaciente().getNombreFamiliares());
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                indice = position;
                String item = (String) parent.getItemAtPosition(position);
                editarFamiliar(view);

            }

        });
    }

    public void crearFamiliar(View v){
        Intent intent = new Intent(this, CrearFamiliarActivity.class);
        intent.putExtra("indice", -1);
        startActivity(intent);
    }

    public void editarFamiliar(View v){
        Intent intent = new Intent(this, CrearFamiliarActivity.class);
        intent.putExtra("indice", indice);
        startActivity(intent);
    }

}
