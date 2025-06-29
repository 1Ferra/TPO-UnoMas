package Modelo.adapter;

import Modelo.factoryPartido.IPartido;
import Modelo.Usuario;

public class AdapterJavaMail implements AdapterNotificadorEmail{

	@Override
	public void notificarJavaMail(IPartido partido) {
		for(Usuario jugador: partido.getJugadores()) {
			System.out.println("Notificacion cambio de estado " + partido.getEstado() + ", por JavaMail a la casilla: " + jugador.getEmail());
		}	
	}
}
