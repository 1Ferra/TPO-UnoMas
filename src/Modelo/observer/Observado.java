package Modelo.observer;

import java.util.ArrayList;
import java.util.List;
import Modelo.factoryPartido.PartidoBase;

public class Observado {
	private List<IObservador> observadores = new ArrayList<>();
	
	public void SuscribirObservador(IObservador observador) {
		observadores.add(observador);
	}
	
	public void EliminarObservador(IObservador observador) {
		observadores.remove(observador);
	}
	
	public void NotificarObservador(PartidoBase partidoBase) {
		for(IObservador observador: observadores) {
			observador.Notificar(partidoBase);
		}
	}
}
