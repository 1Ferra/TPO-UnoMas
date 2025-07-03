package Notificador;

import Modelo.Partido;
import Modelo.Usuario;

public class Notificador implements IObserver {

    private Notificacion notificacion;
    private EstrategiaNotificacion estrategiaNotificacion;
    private Partido partido;

    public Notificador(EstrategiaNotificacion estrategia) {
        this.estrategiaNotificacion = estrategia;
    }

    public void notificar(Notificacion notificacion) {
        if (estrategiaNotificacion != null) {
            estrategiaNotificacion.notificar(notificacion);
        } else {
            System.out.println("Estrategia de notificación no configurada.");
        }
    }

    public void setEstrategia(EstrategiaNotificacion estrategia) {
        this.estrategiaNotificacion = estrategia;
    }

    @Override
    public void serNotificadoPor(Observado observable) {
        if (observable instanceof Partido) {
            this.partido = (Partido) observable;
            
            String estadoActual = partido.getEstado().getClass().getSimpleName();
            String mensaje = "El partido cambió de estado a: " + estadoActual;
            
            for (Usuario u : partido.getJugadores()) {
                Notificacion n = new Notificacion(mensaje, u);
                notificar(n);
            }
        }
    }
}
