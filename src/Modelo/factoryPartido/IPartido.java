package Modelo.factoryPartido;

import java.time.LocalDateTime;
import java.util.List;

import Modelo.estados.PartidoState;
import Modelo.strategy.Nivel;
import Modelo.Usuario;
import Modelo.observer.*;

public interface IPartido {
	String agregarJugador(Usuario usuario);
    void cambiarEstado(PartidoState nuevoEstado);
    void notificar();
    void agregarNotificacion(IObservador observador);
    void eliminarNotificacion(IObservador observador);
    PartidoState getEstado();
    List<Usuario> getJugadores();
    int getJugadoresRequeridos();
    LocalDateTime getHorario();
	Nivel getNivelRequerido();
	String getUbicacion();
	String getDeporte();
    void setID(int id);
    int getID();
    void setDuracion(int duracion);
    void setUbicacion(String ubicacion);
    void setNivelRequerido(Nivel nivel);
    void setNivelMaximo(Nivel nivel);

    }


