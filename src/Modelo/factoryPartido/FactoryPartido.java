package Modelo.factoryPartido;

import Modelo.strategy.Nivel;

public class FactoryPartido {
	public static IPartido crearPartido(TipoPartido tipo, int duracion, String ubicacion, Nivel min, Nivel max) {
        IPartido partido = null;
        switch (tipo) {
            case FUTBOL5: partido = new PartidoFutbol5(); partido.setDuracion(duracion); partido.setUbicacion(ubicacion); partido.setNivelRequerido(min); partido.setNivelMaximo(max); break;
            case FUTBOL11: partido = new PartidoFutbol11();partido.setDuracion(duracion); partido.setUbicacion(ubicacion);  partido.setNivelRequerido(min); partido.setNivelMaximo(max);  break;
            case BASQUET: partido = new PartidoBasquet(); partido.setDuracion(duracion); partido.setUbicacion(ubicacion);  partido.setNivelRequerido(min); partido.setNivelMaximo(max); break;
            default: throw new RuntimeException("No existe el tipo de partido: " + tipo);
        }
        return partido;
    }
}
