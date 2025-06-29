package Modelo.factoryPartido;

import java.time.LocalDateTime;

public class PartidoBasquet extends PartidoBase {
    public PartidoBasquet() {
        this.deporte = "Basquet";
        this.jugadoresRequeridos = 10;
        this.horario = LocalDateTime.now().plusDays(1);
    }
}
