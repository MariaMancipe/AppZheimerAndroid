package co.uniandes.appzheimer.source;

import java.io.Serializable;

/**
 * Created by Q551 on 17/09/2016.
 */
public class Familiar implements Serializable {

    public final static String[] parentescos = {"Hijo","Hija","Nieto","Nieta","Amigo","Amiga","Hermano","Hermana","Sobrina","Sobrino"};

    private String nombre;

    private String apodo;

    private String parentesco;

    private String rutaImagen;

    private String rutaSonido;

    public Familiar(String nombre, String apodo, String parentesco, String rutaImagen, String rutaSonido){
        this.nombre = nombre;
        this.apodo=apodo;
        this.parentesco=parentesco;
        this.rutaImagen = rutaImagen;
        this.rutaSonido = rutaSonido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApodo() {
        return apodo;
    }

    public void setApodo(String apodo) {
        this.apodo = apodo;
    }

    public String getParentesco() {
        return parentesco;
    }

    public void setParentesco(String parentesco) {
        this.parentesco = parentesco;
    }

    public String getRutaImagen() {
        return rutaImagen;
    }

    public void setRutaImagen(String rutaImagen) {
        this.rutaImagen = rutaImagen;
    }

    public String getRutaSonido() {
        return rutaSonido;
    }

    public void setRutaSonido(String rutaSonido) {
        this.rutaSonido = rutaSonido;
    }

    public static int indiceParentesco(String p){
        for (int i=0;i<parentescos.length;i++){
            if(parentescos[i].equals(p))
                return i;
        }
        return -1;
    }

}
