package Partido;

import Modelo.Partido;
import Modelo.Usuario;

public class Cancelado implements PartidoState {

    @Override
    public void confirmar(Partido partido) {
        System.out.println("El partido fue cancelado.");
    }

    @Override
    public void iniciar(Partido partido) {
        System.out.println("No se puede iniciar un partido cancelado.");
    }

    @Override
    public void finalizar(Partido partido) {
        System.out.println("El partido fue cancelado.");
    }

    @Override
    public void cancelar(Partido partido) {
        System.out.println("Ya está cancelado.");
    }

    @Override
    public void agregarJugador(Partido partido, Usuario usuario) {
        System.out.println("No se pueden agregar jugadores a un partido cancelado.");
    }
}
