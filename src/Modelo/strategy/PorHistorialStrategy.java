package Modelo.strategy;

import Modelo.factoryPartido.IPartido;
import Modelo.Usuario;

public class PorHistorialStrategy implements MatchStrategy{
    @Override
    public boolean match(IPartido partido, Usuario usuario) {
        int partidosJugados = 0;
        if (partido != null && usuario != null) {
            for (IPartido jugado : usuario.getPartidosJugados()) {
                if (jugado.getDeporte().equals(partido.getDeporte())) {
                    partidosJugados++;
                }
            }
        }
        return partidosJugados >= 3;
    }
}
