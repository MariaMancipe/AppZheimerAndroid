package co.uniandes.appzheimer.source;

/**
 * Created by Santiago on 10/30/2016.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper
{
    /**
     * Constante que muestra la version de la base de datos
     */
    private static final int DATA_BASE_VERSION = 1;

    /**
     * El nombre de la base de datos
     */
    private static  final String DATA_BASE_NAME = "AppZheimer.db";

    /**
     * Metodo constructor de la base de datos
     * @param context
     */
    public DBHelper(Context context)
    {
        super(context,DATA_BASE_NAME,null,DATA_BASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db)
    {
        String createTableFamiliares = "CREATE TABLE FAMILIARES (nombrealias TEXT PRIMARY KEY, nombre TEXT, apodo TEXT, relacion TEXT, rutaImagen TEXT)";
        String createTableEventos = "CREATE TABLE EVENTOS (nombre TEXT PRIMARY KEY, hora TEXT, conPariente BIT)";
        db.execSQL(createTableFamiliares);
        db.execSQL(createTableEventos);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXIST FAMILIARES");
        db.execSQL("DROP TABLE IF EXIST EVENTOS");
        onCreate(db);
    }
}

