package Partido;

import Modelo.Partido;
import Modelo.Usuario;

public class NecesitamosJugadores implements PartidoState {

    @Override
    public void confirmar(Partido partido) {
        System.out.println("No se puede confirmar un partido sin jugadores suficientes.");
    }

    @Override
    public void iniciar(Partido partido) {
        System.out.println("No se puede iniciar un partido incompleto.");
    }

    @Override
    public void finalizar(Partido partido) {
        System.out.println("No se puede finalizar un partido que no comenzó.");
    }

    @Override
    public void cancelar(Partido partido) {
        partido.cambiarEstado(new Cancelado());
    }

    @Override
    public void agregarJugador(Partido partido, Usuario usuario) {
        partido.agregarJugador(usuario);
        System.out.println("Jugador agregado.");
        if (partido.getJugadores().size() >= partido.getJugadoresRequeridos()) {
            partido.cambiarEstado(new PartidoArmado());
        }
    }
}

