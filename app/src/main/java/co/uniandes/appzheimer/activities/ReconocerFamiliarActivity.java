package co.uniandes.appzheimer.activities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;

import co.uniandes.appzheimer.R;
import co.uniandes.appzheimer.source.AppZheimer;

public class ReconocerFamiliarActivity extends AppCompatActivity {

    private int posicion;
    private ImageButton imagen1;
    private ImageButton imagen2;
    private ImageButton imagen3;
    private ImageButton imagen4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reconocer_familiar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        imagen1=(ImageButton) findViewById(R.id.imagen1);
        imagen2=(ImageButton) findViewById(R.id.imagen2);
        imagen3=(ImageButton) findViewById(R.id.imagen3);
        imagen4=(ImageButton) findViewById(R.id.imagen4);

        if(AppZheimer.darInstancia().getPaciente().getFamiliares().isEmpty()){

        }else{

            int i = 0+(int)(Math.random()*((AppZheimer.darInstancia().getPaciente().getFamiliares().size()-0)+1));
            int j = 0+(int)(Math.random()*((AppZheimer.darInstancia().getPaciente().getFamiliares().size()-0)+1));
            int k = 0+(int)(Math.random()*((AppZheimer.darInstancia().getPaciente().getFamiliares().size()-0)+1));
            int l = 0+(int)(Math.random()*((AppZheimer.darInstancia().getPaciente().getFamiliares().size()-0)+1));
            Bitmap bmp = BitmapFactory.decodeFile(AppZheimer.darInstancia().getPaciente().getFamiliares().get(i).getRutaImagen());
            imagen1.setImageBitmap(bmp);
            bmp = BitmapFactory.decodeFile(AppZheimer.darInstancia().getPaciente().getFamiliares().get(j).getRutaImagen());
            imagen2.setImageBitmap(bmp);
            bmp = BitmapFactory.decodeFile(AppZheimer.darInstancia().getPaciente().getFamiliares().get(k).getRutaImagen());
            imagen3.setImageBitmap(bmp);
            bmp = BitmapFactory.decodeFile(AppZheimer.darInstancia().getPaciente().getFamiliares().get(l).getRutaImagen());
            imagen4.setImageBitmap(bmp);
            MediaPlayer mediaPlayer = new MediaPlayer();
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

            if(posicion == 0){

                //reproducir mensaje 1
            }else if(posicion==-1){
                //reproducir mensaje 4
            }else{
                //reproducir mensaje 3
            }

        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
