package Emparejamiento;

import java.util.List;

import Modelo.Partido;
import Modelo.Usuario;

public interface EmparejamientoStrategy {
    Partido emparejar(List<Usuario> usuarios, Partido partido);
}
