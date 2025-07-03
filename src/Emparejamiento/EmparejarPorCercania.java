package Emparejamiento;

import java.util.List;

import Modelo.Partido;
import Modelo.Usuario;

public class EmparejarPorCercania implements EmparejamientoStrategy {

    @Override
    public Partido emparejar(List<Usuario> usuarios, Partido partido) {
        for (Usuario usuario : usuarios) {
            if (!partido.getJugadores().contains(usuario)) {
                boolean deporteCoincide = partido.getDeporte().equals(usuario.getDeporteFavorito());
                boolean ubicacionCoincide = partido.getUbicacion().equalsIgnoreCase("Caballito");
                boolean necesitaJugadores = partido.getJugadores().size() < partido.getJugadoresRequeridos();

                if (deporteCoincide && ubicacionCoincide && necesitaJugadores) {
                    partido.getEstado().agregarJugador(partido, usuario);
                    System.out.println("Emparejado por cercanía: " + usuario.getNombre());
                }
            }
        }
        return partido;
    }
}
