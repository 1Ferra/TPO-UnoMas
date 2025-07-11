package Modelo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Controlador.PartidoController;
import Emparejamiento.EmparejamientoStrategy;

public class Usuario {

    private String nombre;
    private String email;
    private String contraseña;
    private Deporte deporteFavorito;
    private Nivel nivel;
    private List<Partido> historial;

    public Usuario(String nombre, String email, String contraseña, Deporte deporteFavorito, Nivel nivel) {
        this.nombre = nombre;
        this.email = email;
        this.contraseña = contraseña;
        this.deporteFavorito = deporteFavorito;
        this.nivel = nivel;
        this.historial = new ArrayList<>();
    }
    
    public Partido crearPartido(Deporte deporte,
            int jugadoresRequeridos,
            int duracion,
            String ubicacion,
            Date fechaHora,
            Nivel nivelMinimo,
            Nivel nivelMaximo,
            EmparejamientoStrategy estrategia) {
    	
		Partido partido = new Partido(
			deporte,
			jugadoresRequeridos,
			duracion,
			ubicacion,
			fechaHora,
			nivelMinimo,
			nivelMaximo,
			estrategia
		);
		
		partido.agregarJugador(this);
		this.agregarAlHistorial(partido);
		PartidoController.getInstancia().crearPartido(partido);
		return partido;
	}
    
    public void agregarAlHistorial(Partido partido) {
        historial.add(partido);
    }

    public int contarPartidosPorDeporte(Deporte deporte) {
        int count = 0;
        for (Partido p : historial) {
            if (p.getDeporte().equals(deporte)) {
                count++;
            }
        }
        return count;
    }
    
    public List<Partido> buscarPartidosDisponibles(Deporte deporte, String ubicacion) {
        PartidoController controller = PartidoController.getInstancia();
        return controller.buscarPartidosDisponibles(deporte, ubicacion);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public Deporte getDeporteFavorito() {
        return deporteFavorito;
    }

    public void setDeporteFavorito(Deporte deporteFavorito) {
        this.deporteFavorito = deporteFavorito;
    }

    public Nivel getNivel() {
        return nivel;
    }

    public void setNivel(Nivel nivel) {
        this.nivel = nivel;
    }
    
    public List<Partido> getHistorial() {
    	return historial;
    }
    
    @Override
    public String toString() {
        return "Usuario{" +
               "nombre='" + nombre + '\'' +
               ", email='" + email + '\'' +
               ", deporteFavorito=" + deporteFavorito +
               ", nivel=" + nivel +
               '}';
    }
}
