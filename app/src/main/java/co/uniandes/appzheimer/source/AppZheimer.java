package co.uniandes.appzheimer.source;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Q551 on 17/09/2016.
 */
public class AppZheimer implements Serializable {

    private static AppZheimer instancia;

    private Paciente paciente;

    private Familiar cuidador;

    public static AppZheimer darInstancia(){
        if(instancia == null){
            instancia = new AppZheimer();
        }
        return instancia;
    }

    public AppZheimer(){

    }

    public void agregarFamiliar(String nombre, String parentesco, String apodo, String rutaImagen, String rutaSonido){
        paciente.addFamiliar(nombre, parentesco,apodo,rutaImagen, rutaSonido);
    }

    public void agregarEvento(String nombre, String rutaImagen, Date hora, boolean acompanhado){
        paciente.addEvento(nombre,rutaImagen,hora,acompanhado);
    }

    public Familiar getCuidador() {
        return cuidador;
    }

    public void setCuidador(Familiar cuidador) {
        this.cuidador = cuidador;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
}
