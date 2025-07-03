package Emparejamiento;

import java.util.List;

import Modelo.Partido;
import Modelo.Usuario;

public class EmparejarPorNivel implements EmparejamientoStrategy {
    @Override
    public Partido emparejar(List<Usuario> usuarios, Partido partido) {
        for (Usuario u : usuarios) {
            if (u.getNivel().ordinal() >= partido.getNivelMinimo().ordinal()
                && u.getNivel().ordinal() <= partido.getNivelMaximo().ordinal()
                && partido.getJugadores().size() < partido.getJugadoresRequeridos()) {
                partido.agregarJugador(u);
                System.out.println("Emparejado por nivel: " + u.getNombre());
            }
        }
        return partido;
    }
}
