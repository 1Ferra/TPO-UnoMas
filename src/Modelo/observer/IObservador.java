package Modelo.observer;

import Modelo.factoryPartido.PartidoBase;

public interface IObservador {
	void Notificar(PartidoBase partidoBase);
}
