package Controlador;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Emparejamiento.EmparejamientoStrategy;
import Modelo.Deporte;
import Modelo.Nivel;
import Modelo.Partido;
import Modelo.Usuario;

public class PartidoController {

    private List<Partido> partidos;
    private static PartidoController instancia;

    public void crearPartido(Deporte deporte,
                             int jugadoresRequeridos,
                             int duracion,
                             String ubicacion,
                             Date fechaHora,
                             Nivel nivelMinimo,
                             Nivel nivelMaximo,
                             EmparejamientoStrategy estrategia) {
    	Partido nuevoPartido = new Partido(deporte, jugadoresRequeridos, duracion, ubicacion, fechaHora, nivelMinimo, nivelMaximo, estrategia);

		partidos.add(nuevoPartido);
    }
    
    public void crearPartido(Partido partido) {
    	partidos.add(partido);
    }
    
    public void eliminarPartido(Partido partido) {
        partidos.remove(partido);
    }

    public void mostrarPartidos() {
        System.out.println(partidos);
    }

    public void notificar(Partido partido) {
        partido.notificar();
    }

    public void cambiarEstrategia(EmparejamientoStrategy estrategia, Partido partido) {
        partido.setEstrategia(estrategia);
    }

    public void agregarJugador(Partido partido, Usuario usuario) {
        partido.agregarJugador(usuario);
    }
    
    public List<Partido> buscarPartidosDisponibles(Deporte deporte, String ubicacion) {
        List<Partido> disponibles = new ArrayList<>();
        
        for (Partido p : partidos) {
            boolean coincideDeporte = p.getDeporte().equals(deporte);
            boolean coincideUbicacion = p.getUbicacion().equalsIgnoreCase(ubicacion);
            boolean necesitaJugadores = p.getJugadores().size() < p.getJugadoresRequeridos();

            if (coincideDeporte && coincideUbicacion && necesitaJugadores) {
                disponibles.add(p);
            }
        }

        return disponibles;
    }
    
    public List<Partido> getPartidos() {
        return partidos;
    }

    public static PartidoController getInstancia() {
        if (instancia == null) {
            instancia = new PartidoController();
        }
        return instancia;
    }
    
    private PartidoController() {
		super();
    	this.partidos = new ArrayList<>();
	}
}
