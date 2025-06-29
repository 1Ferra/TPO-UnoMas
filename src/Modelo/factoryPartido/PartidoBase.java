package Modelo.factoryPartido;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import Modelo.estados.NecesitamosJugadores;
import Modelo.estados.PartidoState;
import Modelo.observer.IObservador;
import Modelo.observer.Observado;
import Modelo.strategy.Nivel;
import Modelo.Usuario;

public class PartidoBase implements IPartido{
	protected String deporte;
	protected int jugadoresRequeridos;
	protected String ubicacion;
	protected int Duracion;
    protected int ID;
    protected Nivel nivelMaximo;
	protected Nivel nivelRequerido;
	protected LocalDateTime horario;
	protected List<Usuario> jugadores = new ArrayList<>();
	protected PartidoState estado;
    protected Observado observado;

    public PartidoBase() {
        this.jugadoresRequeridos = 10;
        this.horario = LocalDateTime.now().plusDays(1);
        this.estado = new NecesitamosJugadores();
        this.jugadores = new ArrayList<>();
        this.observado = new Observado();
        this.ID = 0;
    }
    
    @Override
    public String agregarJugador(Usuario usuario) {
        return estado.agregarJugador(this, usuario);
    }


    public void setID(int id){
        this.ID= id;
    }

    public int getID(){
        return this.ID;
    }

    @Override
    public void cambiarEstado(PartidoState nuevoEstado) {
        this.estado = nuevoEstado;
        this.notificar();
    }

    @Override
    public void notificar() {
        observado.NotificarObservador(this);
    }

    @Override
    public void agregarNotificacion(IObservador observador) {
        observado.SuscribirObservador(observador);
    }

    @Override
    public void eliminarNotificacion(IObservador observador) {
        observado.EliminarObservador(observador);
    }

    @Override
    public PartidoState getEstado() {
        return estado;
    }
    
    @Override
    public List<Usuario> getJugadores() {
        return jugadores;
    }
    
    @Override
    public int getJugadoresRequeridos() {
        return jugadoresRequeridos;
    }

    @Override
    public LocalDateTime getHorario() {
        return horario;
    }

	@Override
	public Nivel getNivelRequerido() {
		return nivelRequerido;
	}

	@Override
	public String getUbicacion() {
		return ubicacion;
	}

	@Override
	public String getDeporte() {
		return deporte;
	}

    @Override
    public void setDuracion(int duracion) {
        this.Duracion=duracion;
    }
    public void setUbicacion(String ubi) {
        this.ubicacion=ubi;
    }
    public void setNivelRequerido(Nivel nivel) {
        this.nivelRequerido=nivel;
    }
    public void setNivelMaximo(Nivel nivel) {
        this.nivelMaximo=nivel;
    }

}



