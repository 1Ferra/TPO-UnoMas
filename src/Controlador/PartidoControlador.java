package Controlador;

import Modelo.factoryPartido.*;
import Modelo.strategy.*;
import Modelo.estados.*;
import Modelo.Usuario;

import Vista.*;


import java.util.*;

public class PartidoControlador {

    private Map<Integer, IPartido> partidos = new HashMap<>();
    private int idFutbol = getIdPartidoActual();
    public List<IPartido> partidosDisponibles = new ArrayList<>();

    public Map<Integer, IPartido> getPartidos() {
        return partidos;
    }


    public String crearPartido(Usuario usuario, TipoPartido tipo, int duracion, String ubicacion, Nivel min, Nivel max) {
        IPartido nuevoPartido = FactoryPartido.crearPartido(tipo, duracion, ubicacion, min, max);
        nuevoPartido.setDuracion(duracion);
        nuevoPartido.setUbicacion(ubicacion);
        int id = ++idFutbol;
        partidos.put(id, nuevoPartido);
        partidosDisponibles.add(nuevoPartido);
        nuevoPartido.setID(id);
        return "Se creó el partido con ID: " + id;

    }
    
    public void agregarPartido(IPartido partido) {
        partidos.put(partido.getID(), partido);
    }

    public void agregarJugador(int idPartido, Usuario jugador) {
        IPartido partido = partidos.get(idPartido);
        if (partido != null) {
            String mensaje = partido.agregarJugador(jugador);
            System.out.println("Partido " + idPartido + ": " + mensaje);
        }
    }

    public void confirmarPartido(int idPartido) {
        IPartido partido = partidos.get(idPartido);
        if (partido != null) {
            PartidoState nuevoEstado = new Confirmado();
            partido.cambiarEstado(nuevoEstado);
            System.out.println("Partido " + idPartido + ": " + "confirmado");
        }
    }

    public void iniciarPartido(int idPartido) {
        IPartido partido = partidos.get(idPartido);
        if (partido != null) {
            PartidoState nuevoEstado = new EnJuego();
            partido.cambiarEstado(nuevoEstado);
            System.out.println("Partido " + idPartido + ": " + "iniciado");
        }
    }

    public void finalizarPartido(int idPartido) {
        IPartido partido = partidos.get(idPartido);
        if (partido != null) {
            PartidoState nuevoEstado = new Finalizado();
            for (Usuario usuario : partido.getJugadores()) {
                usuario.agregarPartidoJugado(partido);
            }
            partido.cambiarEstado(nuevoEstado);
            System.out.println("Partido " + idPartido + ": " + "finalizado");
        }
    }

    public void mostrarEstado(int idPartido) {
        IPartido partido = partidos.get(idPartido);
        if (partido != null) {
            System.out.println("Estado actual del partido " + idPartido + ": " + partido.getEstado());
        }
    }
  

    public int getIdPartidoActual() {
        return idFutbol;
            }

}