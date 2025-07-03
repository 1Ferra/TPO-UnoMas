package Emparejamiento;

import java.util.List;

import Modelo.Partido;
import Modelo.Usuario;

public class EmparejarPorNivel implements EmparejamientoStrategy {

    @Override
    public Partido emparejar(List<Usuario> usuarios, Partido partido) {
        for (Usuario u : usuarios) {
            if (!partido.getJugadores().contains(u)) {
                boolean cumpleNivel = u.getNivel().ordinal() >= partido.getNivelMinimo().ordinal()
                                   && u.getNivel().ordinal() <= partido.getNivelMaximo().ordinal();

                if (cumpleNivel && partido.getJugadores().size() < partido.getJugadoresRequeridos()) {
                    partido.getEstado().agregarJugador(partido, u);
                    System.out.println("Emparejado por nivel: " + u.getNombre());
                }
            }
        }
        return partido;
    }
}
