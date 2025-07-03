package Notificador;

import java.util.ArrayList;
import java.util.List;

public abstract class Observado {

    protected List<IObserver> observadores;

    public Observado() {
        this.observadores = new ArrayList<>();
    }

    public void agregar(IObserver observer) {
        observadores.add(observer);
    }

    public void eliminar(IObserver observer) {
        observadores.remove(observer);
    }

    public void notificar() {
        for (IObserver o : observadores) {
            o.serNotificadoPor(this);
        }
    }
}

