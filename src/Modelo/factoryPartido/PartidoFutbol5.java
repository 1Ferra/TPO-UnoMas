package Modelo.factoryPartido;

import java.time.LocalDateTime;

public class PartidoFutbol5 extends PartidoBase {
    public PartidoFutbol5() {
        this.deporte = "Futbol5";
        this.jugadoresRequeridos = 10;
        this.horario = LocalDateTime.now().plusDays(1);
    }
}