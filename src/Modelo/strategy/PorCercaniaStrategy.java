package Modelo.strategy;

import Modelo.factoryPartido.IPartido;
import Modelo.Usuario;

public class PorCercaniaStrategy implements MatchStrategy{
    @Override
    public boolean match(IPartido partido, Usuario usuario) {
        return partido.getUbicacion().equalsIgnoreCase(usuario.getUbicacion());
    }
}
