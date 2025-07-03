package Partido;

import Modelo.Partido;
import Modelo.Usuario;

public interface PartidoState {
    void confirmar(Partido partido);
    void iniciar(Partido partido);
    void finalizar(Partido partido);
    void cancelar(Partido partido);
    void agregarJugador(Partido partido, Usuario usuario);
}

