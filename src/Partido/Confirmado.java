package Partido;

import Modelo.Partido;
import Modelo.Usuario;

public class Confirmado implements PartidoState {

    @Override
    public void confirmar(Partido partido) {
        System.out.println("El partido ya está confirmado.");
    }

    @Override
    public void iniciar(Partido partido) {
        partido.cambiarEstado(new EnJuego());
    }

    @Override
    public void finalizar(Partido partido) {
        System.out.println("Debe iniciarse antes de finalizar.");
    }

    @Override
    public void cancelar(Partido partido) {
        partido.cambiarEstado(new Cancelado());
    }

    @Override
    public void agregarJugador(Partido partido, Usuario usuario) {
        System.out.println("No se pueden agregar jugadores a un partido confirmado.");
    }
}
