package Notificador;

public interface IObserver {
	public void notificar(Notificacion notificacion);
	public void serNotificadoPor(Observado observado);
}
