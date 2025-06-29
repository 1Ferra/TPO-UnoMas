package Modelo.estados;

import Modelo.factoryPartido.IPartido;
import Modelo.Usuario;

public class PartidoArmado implements PartidoState {
	@Override
    public String agregarJugador(IPartido partido, Usuario jugador) {
        return "El partido ya está armado. No se pueden agregar más jugadores.";
        
    }

    @Override
    public String confirmar(IPartido partido) {
        partido.cambiarEstado(new Confirmado());
        return "Todos los jugadores confirmaron. Partido confirmado.";
    }

    @Override
    public String iniciar(IPartido partido) {
        return "El partido no ha empezado todavia.";
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
        return "Partido Armado";
    }
}