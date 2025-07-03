package Notificador;

public class AdapterJavaMail implements AdapterNotificacionEmail {

	@Override
	public void notificarJavaMail(Notificacion notificacion) {
		System.out.println("EMAIL enviado a " + notificacion.getUsuario().getEmail() + ": " + notificacion.getMensaje());
	}
}
