package Modelo.adapter;
import Modelo.factoryPartido.*;
import Modelo.Usuario;

public class AdapterFireBase implements AdapterNotificadorPush {

	@Override
	public void notificarFireBase(IPartido partido) {
		for(Usuario jugador: partido.getJugadores()) {
			System.out.println("Notifiacion cambio de estado " + partido.getEstado().toString() + ", enviada por FireBase a usuario: " + jugador.getNombre());
		}
		
	}

}
