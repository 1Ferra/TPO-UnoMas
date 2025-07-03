package Emparejamiento;

import java.util.List;

import Modelo.Partido;
import Modelo.Usuario;

public class EmparejarPorNivel implements EmparejamientoStrategy {
	
	@Override
    public Partido emparejar(List<Usuario> usuarios, Partido partido) {
        return partido;
    }
}
