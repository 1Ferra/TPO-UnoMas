package Modelo.observer;

import Modelo.adapter.AdapterJavaMail;
import Modelo.adapter.AdapterNotificadorEmail;
import Modelo.factoryPartido.PartidoBase;

public class NotificadorEmail implements IObservador {

	private AdapterNotificadorEmail adapter;
	
	public NotificadorEmail() {
		this.adapter = new AdapterJavaMail();
	}
	
	@Override
	public void Notificar(PartidoBase partido) {
		adapter.notificarJavaMail(partido);		
	}

}
