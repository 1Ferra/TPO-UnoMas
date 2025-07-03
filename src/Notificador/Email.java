package Notificador;

public class Email implements EstrategiaNotificacion {

    private AdapterNotificacionEmail adapter;

    public Email(AdapterNotificacionEmail adapter) {
        this.adapter = adapter;
    }

    @Override
    public void notificar(Notificacion notificacion) {
        adapter.notificarJavaMail(notificacion);
    }
}

