package Modelo.strategy;

import Modelo.factoryPartido.IPartido;
import Modelo.Usuario;

public interface MatchStrategy {
    boolean match(IPartido partido, Usuario usuario);
}