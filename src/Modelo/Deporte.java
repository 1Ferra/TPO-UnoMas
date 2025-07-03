package Modelo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Deporte {
	private String nombre;
	
	public Deporte(String nombre) {
		this.nombre = nombre;
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	@Override
    public String toString() {
        return nombre;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Deporte)) return false;
        Deporte other = (Deporte) obj;
        return nombre != null && nombre.equalsIgnoreCase(other.nombre);
    }
}
