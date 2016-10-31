package co.uniandes.appzheimer.source;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Q551 on 17/09/2016.
 */
public class Paciente implements Serializable {

    private final static String RUTA_RUTINA = "";

    private final static  String RUTA_FAMILIARES ="";

    private List<Evento> rutina;

    private List<String> nombreEventos;

    private List<String> nombreFamiliares;

    private String nombre;

    private Date fechaNacimiento;

    private String apodo;

    private List<Familiar> familiares;


    public Paciente(String nombre, String apodo, Date fechaNacimiento){
        this.nombre=nombre;
        this.apodo=apodo;
        this.fechaNacimiento=fechaNacimiento;
        familiares = new ArrayList<Familiar>();
        nombreEventos = new ArrayList<String>();
        nombreFamiliares = new ArrayList<String>();
        rutina = new ArrayList<Evento>();
    }

    public void addFamiliar(String nombre, String parentesco, String apodo, String rutaImagen, String rutaSonido){
        familiares.add(new Familiar(nombre, apodo,parentesco,rutaImagen, rutaSonido));
        nombreFamiliares.add(nombre);
    }

    public void addEvento(String nombre, String rutaImagen, Date hora, boolean acompanhado){
        rutina.add(new Evento(nombre,rutaImagen,hora,acompanhado));
        nombreEventos.add(nombre);
    }

    public List<Evento> getRutina() {
        return rutina;
    }

    public void setRutina(List<Evento> rutina) {
        this.rutina = rutina;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getApodo() {
        return apodo;
    }

    public void setApodo(String apodo) {
        this.apodo = apodo;
    }

    public List<Familiar> getFamiliares() {
        return familiares;
    }

    public List<String> getNombreEventos() {
        return nombreEventos;
    }

    public List<String> getNombreFamiliares() {
        return nombreFamiliares;
    }

    public Evento buscarRutina(String nombreP)
    {
        for (int i=0;i<rutina.size();i++)
        {
            Evento actual = rutina.get(i);
            if (actual.getNombre().equalsIgnoreCase(nombreP))
                return actual;
        }
        return null;
    }

    public void eliminarEvento(int indice)
    {
        rutina.remove(indice);
        nombreEventos.remove(indice);
    }

    public void eliminarFamiliar(int indice)
    {
        nombreFamiliares.remove(indice);
        familiares.remove(indice);
    }
}
