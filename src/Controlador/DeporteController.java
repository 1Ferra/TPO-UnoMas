package Controlador;

import java.util.ArrayList;
import java.util.List;

import Modelo.Deporte;

public class DeporteController {

    private List<Deporte> deportes;
    private static DeporteController instancia;

    private DeporteController() {
        deportes = new ArrayList<>();
    }

    public void registrarDeporte(String nombre) {
        Deporte nuevo = new Deporte(nombre);
        deportes.add(nuevo);
    }

    public void eliminarDeporte(Deporte deporte) {
        deportes.remove(deporte);
    }

    public void mostrarDeportes() {
        for (Deporte d : deportes) {
            System.out.println(d);
        }
    }

    public static DeporteController getInstancia() {
        if (instancia == null) {
            instancia = new DeporteController();
        }
        return instancia;
    }
}

