package Modelo.estados;

import Modelo.factoryPartido.IPartido;
import Modelo.Usuario;

public class Finalizado implements PartidoState {
	@Override
    public String agregarJugador(IPartido partido, Usuario jugador) {
        return "El partido ya ha finalizado.";
    }

    @Override
    public String confirmar(IPartido partido) {
        return "El partido ya ha finalizado.";
    }

    @Override
    public String iniciar(IPartido partido) {
        return "El partido ya ha finalizado.";
    }

    @Override
    public String finalizar(IPartido partido) {
        return "El partido ya ha finalizado.";
    }

    @Override
    public String cancelar(IPartido partido) {
        return "No se puede cancelar. Partido finalizado.";
    }
    
    @Override
    public String toString() {
        return "Finalizado";
    }
}