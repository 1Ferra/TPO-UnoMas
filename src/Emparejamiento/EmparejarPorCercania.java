package Emparejamiento;

import java.util.List;

import Modelo.Partido;
import Modelo.Usuario;

public class EmparejarPorCercania implements EmparejamientoStrategy {

    @Override
    public Partido emparejar(List<Usuario> usuarios, Partido partido) {
        for (Usuario usuario : usuarios) {
            if (!partido.getJugadores().contains(usuario)) {
                boolean faltaGente = partido.getJugadores().size() < partido.getJugadoresRequeridos();
                boolean deporteCoincide = partido.getDeporte().equals(usuario.getDeporteFavorito());
                boolean ubicacionCoincide = partido.getUbicacion().equalsIgnoreCase("Plaza central");

                if (faltaGente && deporteCoincide && ubicacionCoincide) {
                    partido.agregarJugador(usuario);
                    System.out.println("Emparejado por cercanía: " + usuario.getNombre());
                    break;
                }
            }
        }

        return partido;
    }
}
