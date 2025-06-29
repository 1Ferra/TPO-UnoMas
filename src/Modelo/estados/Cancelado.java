package Modelo.estados;

import Modelo.factoryPartido.IPartido;
import Modelo.Usuario;

public class Cancelado implements PartidoState {
    @Override
    public String agregarJugador(IPartido partido, Usuario jugador) {
        return "El partido fue cancelado. No se pueden agregar jugadores.";
    }

    @Override
    public String confirmar(IPartido partido) {
        return "El partido fue cancelado.";
    }

    @Override
    public String iniciar(IPartido partido) {
        return "El partido fue cancelado.";
    }

    @Override
    public String finalizar(IPartido partido) {
        return "El partido fue cancelado.";
    }

    @Override
    public String cancelar(IPartido partido) {
        return "Ya está cancelado.";
    }
    
    @Override
    public String toString() {
        return "Cancelado";
    }
}