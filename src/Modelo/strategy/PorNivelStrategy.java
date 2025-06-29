package Modelo.strategy;

import Modelo.factoryPartido.IPartido;
import Modelo.Usuario;

public class PorNivelStrategy implements MatchStrategy{
    @Override
    public boolean match(IPartido partido, Usuario usuario) {
       Nivel nivelNecesario = partido.getNivelRequerido();
       return nivelNecesario == null || nivelNecesario == usuario.getNivel();
    }
}
