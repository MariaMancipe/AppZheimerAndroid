package co.uniandes.appzheimer.source;

import java.util.Date;

/**
 * Created by Q551 on 18/09/2016.
 */
public class Evento {

    private String nombre;
    private String rutaImagen;
    private Date hora;
    private boolean acompanhado;

    public Evento(String nombre, String rutaImagen, Date hora, boolean acompanhado) {
        this.nombre = nombre;
        this.rutaImagen = rutaImagen;
        this.hora = hora;
        this.acompanhado = acompanhado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRutaImagen() {
        return rutaImagen;
    }

    public void setRutaImagen(String rutaImagen) {
        this.rutaImagen = rutaImagen;
    }

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }

    public boolean isAcompanhado() {
        return acompanhado;
    }

    public void setAcompanhado(boolean acompanhado) {
        this.acompanhado = acompanhado;
    }

    public static long convertirHorasSegundos(String hora){
        return (long)((Integer.parseInt(hora.substring(0,2)))*360 + (Integer.parseInt(hora.substring(2)))*60);
    }

    public static String convertirSegundosHoras(long segundos){
        String horas= ((int)((segundos/60)/60)+(int)((segundos/60)%60)+ "");
        return horas.length()>3?horas: "0"+horas;
    }
}
