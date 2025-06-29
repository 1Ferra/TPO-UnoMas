package Modelo.observer;

import Modelo.adapter.AdapterFireBase;
import Modelo.adapter.AdapterNotificadorPush;
import Modelo.factoryPartido.PartidoBase;

public class NotificadorPush implements IObservador {

private AdapterNotificadorPush adapter;
	
	public NotificadorPush() {
		this.adapter = new AdapterFireBase();
	}

	@Override
	public void Notificar(PartidoBase partido) {
		adapter.notificarFireBase(partido);		
	}
}
