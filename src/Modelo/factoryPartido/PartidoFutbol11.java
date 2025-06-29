package Modelo.factoryPartido;

import java.time.LocalDateTime;

public class PartidoFutbol11 extends PartidoBase {
    public PartidoFutbol11() {
        this.deporte = "Futbol11";
        this.jugadoresRequeridos = 22;
        this.horario = LocalDateTime.now().plusDays(1);
    }
}