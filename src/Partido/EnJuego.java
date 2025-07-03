package Partido;

import Modelo.Partido;
import Modelo.Usuario;

public class EnJuego implements PartidoState {

    @Override
    public void confirmar(Partido partido) {
        System.out.println("Ya está en juego.");
    }

    @Override
    public void iniciar(Partido partido) {
        System.out.println("El partido ya ha comenzado.");
    }

    @Override
    public void finalizar(Partido partido) {
        partido.cambiarEstado(new Finalizado());
    }

    @Override
    public void cancelar(Partido partido) {
        System.out.println("No se puede cancelar un partido que está en juego.");
    }

    @Override
    public void agregarJugador(Partido partido, Usuario usuario) {
        System.out.println("No se pueden agregar jugadores durante el partido.");
    }
}
