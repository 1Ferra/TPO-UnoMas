package Modelo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Emparejamiento.EmparejamientoStrategy;
import Notificador.IObserver;
import Notificador.Observado;
import Partido.NecesitamosJugadores;
import Partido.PartidoState;

public class Partido extends Observado {

    private Deporte deporte;
    private int jugadoresRequeridos;
    private int duracion;
    private String ubicacion;
    private Date fechaHora;
    private PartidoState estado;
    private Nivel nivelMinimo;
    private Nivel nivelMaximo;
    private List<Usuario> jugadores;
    private EmparejamientoStrategy estrategia;
    
    public Partido(Deporte deporte, int jugadoresRequeridos, int duracion, String ubicacion, Date fechaHora, Nivel nivelMinimo, Nivel nivelMaximo, EmparejamientoStrategy estrategia) {
    	super();
		this.deporte = deporte;
		this.jugadoresRequeridos = jugadoresRequeridos;
		this.duracion = duracion;
		this.ubicacion = ubicacion;
		this.fechaHora = fechaHora;
		this.estado = new NecesitamosJugadores();
		this.nivelMinimo = nivelMinimo;
		this.nivelMaximo = nivelMaximo;
		this.estrategia = estrategia;
        jugadores = new ArrayList<>();
    }
    
    public Partido(Deporte deporte, int jugadoresRequeridos, int duracion, String ubicacion, Date fechaHora, PartidoState estado, Nivel nivelMinimo, Nivel nivelMaximo, List<Usuario> jugadores, EmparejamientoStrategy estrategia) {
		super();
		this.deporte = deporte;
		this.jugadoresRequeridos = jugadoresRequeridos;
		this.duracion = duracion;
		this.ubicacion = ubicacion;
		this.fechaHora = fechaHora;
		this.estado = estado;
		this.nivelMinimo = nivelMinimo;
		this.nivelMaximo = nivelMaximo;
		this.jugadores = jugadores;
		this.estrategia = estrategia;
	}

	// Observer pattern
    public void agregar(IObserver observer) {
        observadores.add(observer);
    }

    public void eliminar(IObserver observer) {
    	observadores.remove(observer);
    }

    @Override
    public void notificar() {
        for (IObserver obs : observadores) {
            obs.serNotificadoPor(this);
        }
    }

    public void agregarJugador(Usuario jugador) {
        estado.agregarJugador(this, jugador);
        notificar();
    }
    
    public void cambiarEstado(PartidoState estado) {
        this.estado = estado;
        notificar();
    }

    public void setEstrategia(EmparejamientoStrategy estrategia) {
        this.estrategia = estrategia;
    }

    public Partido emparejar(List<Usuario> usuarios, Partido partido) {
        if (estrategia != null) {
            return estrategia.emparejar(usuarios, partido);
        }
        return null;
    }

	public Deporte getDeporte() {
		return deporte;
	}

	public void setDeporte(Deporte deporte) {
		this.deporte = deporte;
	}

	public int getJugadoresRequeridos() {
		return jugadoresRequeridos;
	}

	public void setJugadoresRequeridos(int jugadoresRequeridos) {
		this.jugadoresRequeridos = jugadoresRequeridos;
	}

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public Date getFechaHora() {
		return fechaHora;
	}

	public void setFechaHora(Date fechaHora) {
		this.fechaHora = fechaHora;
	}

	public PartidoState getEstado() {
		return estado;
	}

	public void setEstado(PartidoState estado) {
		this.estado = estado;
	}

	public Nivel getNivelMinimo() {
		return nivelMinimo;
	}

	public void setNivelMinimo(Nivel nivelMinimo) {
		this.nivelMinimo = nivelMinimo;
	}

	public Nivel getNivelMaximo() {
		return nivelMaximo;
	}

	public void setNivelMaximo(Nivel nivelMaximo) {
		this.nivelMaximo = nivelMaximo;
	}

	public List<Usuario> getJugadores() {
		return jugadores;
	}

	public void setJugadores(List<Usuario> jugadores) {
		this.jugadores = jugadores;
	}

	public List<IObserver> getObservers() {
		return observadores;
	}

	public void setObservers(List<IObserver> observers) {
		this.observadores = observers;
	}

	public EmparejamientoStrategy getEstrategia() {
		return estrategia;
	}
}
