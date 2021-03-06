package co.uniandes.appzheimer.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import co.uniandes.appzheimer.R;
import co.uniandes.appzheimer.source.AppZheimer;

public class ListaRutinaActivity extends AppCompatActivity {

    private int indice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_rutina);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                crearEvento(view);
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ListView list = (ListView)findViewById(R.id.rutinaList);
        ArrayAdapter<String>  adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, AppZheimer.darInstancia().getPaciente().getNombreEventos());
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                indice = position;
                String item = (String) parent.getItemAtPosition(position);
                editarEvento(view);

            }

        });

    }

    /**
     * Metodo para crear el menu
     * @param menu el menu que se va a crear
     * @return true
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_options,menu);
        return  true;
    }

    /**
     * Metodo para que los elementos del menu funciones
     * @param item el item que se selecciono
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();
        if (id==R.id.menuPrincipal)
        {
            Intent intent = new Intent(this,MenuPrincipalActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    public void crearEvento(View v){
        Intent intent = new Intent(this, CrearEventoActivity.class);
        intent.putExtra("indice",-1);
        startActivity(intent);
    }

    public void editarEvento(View v){
        Intent intent = new Intent(this, CrearEventoActivity.class);
        intent.putExtra("indice",indice);
        startActivity(intent);
    }
}
