package Modelo.estados;

import Modelo.factoryPartido.IPartido;
import Modelo.Usuario;

public interface PartidoState {
    String confirmar(IPartido partido);
    String iniciar(IPartido partido);
    String finalizar(IPartido partido);
    String cancelar(IPartido partido);
    String agregarJugador(IPartido partido, Usuario usuario);
}