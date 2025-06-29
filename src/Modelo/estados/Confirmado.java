package Modelo.estados;

import java.time.LocalDateTime;

import Modelo.factoryPartido.IPartido;
import Modelo.Usuario;

public class Confirmado implements PartidoState {
	@Override
    public String agregarJugador(IPartido partido, Usuario jugador) {
        return "El partido ya está confirmado. No se pueden agregar jugadores.";
    }

    @Override
    public String confirmar(IPartido partido) {
        return "El partido ya fue confirmado.";
    }

    @Override
    public String iniciar(IPartido partido) {
        if (LocalDateTime.now().isAfter(partido.getHorario())) {
            partido.cambiarEstado(new EnJuego());
            return "Partido Iniciado";
        } else {
            return "Todavia no es el horario de incio de partido.";
        }
    }

    @Override
    public String finalizar(IPartido partido) {
        return "El partido aún no ha empezado.";
    }

    @Override
    public String cancelar(IPartido partido) {
        partido.cambiarEstado(new Cancelado());
        return "Partido cancelado.";
    }
    
    @Override
    public String toString() {
        return "Confirmado";
    }
}