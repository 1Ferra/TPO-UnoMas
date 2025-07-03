package Emparejamiento;

import java.util.List;

import Modelo.Partido;
import Modelo.Usuario;

public class EmparejarPorHistorial implements EmparejamientoStrategy {

    @Override
    public Partido emparejar(List<Usuario> usuarios, Partido partido) {
        usuarios.stream()
            .filter(u -> !partido.getJugadores().contains(u))
            .sorted((u1, u2) -> Integer.compare(
                u2.contarPartidosPorDeporte(partido.getDeporte()),
                u1.contarPartidosPorDeporte(partido.getDeporte())
            ))
            .limit(partido.getJugadoresRequeridos() - partido.getJugadores().size())
            .forEach(u -> {
                partido.agregarJugador(u);
                System.out.println("Emparejado por historial: " + u.getNombre());
            });

        return partido;
    }
}
