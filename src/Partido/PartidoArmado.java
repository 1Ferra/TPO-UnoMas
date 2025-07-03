package Partido;

import Modelo.Partido;
import Modelo.Usuario;

public class PartidoArmado implements PartidoState {

    @Override
    public void confirmar(Partido partido) {
        partido.cambiarEstado(new Confirmado());
    }

    @Override
    public void iniciar(Partido partido) {
        System.out.println("Debe confirmarse antes de iniciar.");
    }

    @Override
    public void finalizar(Partido partido) {
        System.out.println("No se puede finalizar antes de jugar.");
    }

    @Override
    public void cancelar(Partido partido) {
        partido.cambiarEstado(new Cancelado());
    }

    @Override
    public void agregarJugador(Partido partido, Usuario usuario) {
        System.out.println("Partido ya armado, no se pueden agregar más jugadores.");
    }
}
