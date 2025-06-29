package Modelo.estados;

import Modelo.factoryPartido.IPartido;
import Modelo.Usuario;

public class EnJuego implements PartidoState {
	@Override
    public String agregarJugador(IPartido partido, Usuario jugador) {
        return "El partido ya está en curso. No se pueden agregar jugadores.";
    }

    @Override
    public String confirmar(IPartido partido) {
        return "El partido ya está en curso.";
    }

    @Override
    public String iniciar(IPartido partido) {
        return "El partido ya está en curso.";
    }

    @Override
    public String finalizar(IPartido partido) {
        partido.cambiarEstado(new Finalizado());
        return "Partido finalizado.";
    }

    @Override
    public String cancelar(IPartido partido) {
        return "No se puede cancelar un partido en curso.";
    }
    
    @Override
    public String toString() {
        return "En Juego";
    }
}
