package Modelo.estados;

import Modelo.factoryPartido.IPartido;
import Modelo.Usuario;

public class NecesitamosJugadores implements PartidoState {
	
	public String agregarJugador(IPartido partido, Usuario jugador) {
    partido.getJugadores().add(jugador);

    String mensaje = "Jugador agregado. Total: " + partido.getJugadores().size();

    if (partido.getJugadores().size() == partido.getJugadoresRequeridos()) {
        partido.cambiarEstado(new PartidoArmado());
        mensaje += "\nPartido armado. Estado actualizado.";
    }

    return mensaje;
}

    @Override
    public String confirmar(IPartido partido) {
        return "Aún no hay jugadores suficientes para confirmar.";
    }

    @Override
    public String iniciar(IPartido partido) {
       return "No se puede iniciar. Partido no armado.";
    }

    @Override
    public String finalizar(IPartido partido) {
        return "El partido aún no ha comenzado.";
    }

    @Override
    public String cancelar(IPartido partido) {
        partido.cambiarEstado(new Cancelado());
        return "Partido cancelado.";
    }
    
    @Override
    public String toString() {
        return "Necesitamos Jugadores";
    }
}