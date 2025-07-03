package Partido;

import Modelo.Partido;
import Modelo.Usuario;

public class Finalizado implements PartidoState {

    @Override
    public void confirmar(Partido partido) {
        System.out.println("No se puede confirmar un partido que ya finalizó.");
    }

    @Override
    public void iniciar(Partido partido) {
        System.out.println("No se puede iniciar un partido que ya finalizó.");
    }

    @Override
    public void finalizar(Partido partido) {
        System.out.println("El partido ya está finalizado.");
    }

    @Override
    public void cancelar(Partido partido) {
        System.out.println("No se puede cancelar un partido que ya finalizó.");
    }

    @Override
    public void agregarJugador(Partido partido, Usuario usuario) {
        System.out.println("No se pueden agregar jugadores a un partido finalizado.");
    }
}
