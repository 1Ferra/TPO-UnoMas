package UnoMas;

import java.util.*;

import Controlador.*;
import Emparejamiento.*;
import Modelo.*;
import Notificador.*;
import Partido.*;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        System.out.println("=== CREACIÓN DE DEPORTES ===");
        Deporte futbol = new Deporte("Fútbol");
        Deporte padel = new Deporte("Pádel");
        Deporte handball = new Deporte("Handball");

        DeporteController deporteController = DeporteController.getInstancia();
        deporteController.registrarDeporte(futbol.getNombre());
        deporteController.registrarDeporte(padel.getNombre());
        deporteController.registrarDeporte(handball.getNombre());
        deporteController.mostrarDeportes();

        System.out.println("\n=== CREACIÓN DE USUARIOS ===");
        UsuarioController usuarioController = UsuarioController.getInstancia();

        Nivel[] niveles = Nivel.values();
        String[] nombres = {"Juan", "Ana", "Pedro", "Laura", "Diego", "Sofia", "Tomas", "Camila", "Luis", "Flor"};
        List<Usuario> usuarios = new ArrayList<>();

        for (int i = 0; i < nombres.length; i++) {
            Usuario u = new Usuario(
                nombres[i],
                nombres[i].toLowerCase() + "@mail.com",
                "1234",
                futbol,
                niveles[i % niveles.length]
            );
            usuarioController.registrarUsuario(u.getNombre(), u.getEmail(), u.getContraseña(), u.getDeporteFavorito(), u.getNivel());
            usuarios.add(u);
        }

        usuarioController.mostrarUsuarios();

        System.out.println("\n=== CREACIÓN DE PARTIDO ===");
        Date mañana = new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000);

        EmparejamientoStrategy estrategiaInicial = new EmparejarPorNivel();
        Partido partido = usuarios.get(0).crearPartido(
            futbol,
            10,
            90,
            "Caballito",
            mañana,
            Nivel.PRINCIPIANTE,
            Nivel.AVANZADO,
            estrategiaInicial
        );

        // Agregar notificador por mail
        AdapterNotificacionEmail adapter = new AdapterJavaMail();
        
        Notificador notificador = new Notificador(new Email(adapter));
        partido.agregar(notificador);

        PartidoController partidoController = PartidoController.getInstancia();
        partidoController.crearPartido(partido);
        
        System.out.println("Partido creado en Caballito con 10 jugadores necesarios\n");

        // ================================
        // Emparejar por nivel
        // ================================
        System.out.println("=== EMPAREJAMIENTO POR NIVEL ===");

        List<Usuario> disponibles = new ArrayList<>(usuarios);
        disponibles.remove(0); // el creador ya fue agregado

        List<Usuario> primeraMitad = disponibles.subList(0, 4); // 4 usuarios

        partido.emparejar(primeraMitad, partido);

        System.out.println("Jugadores actuales: " + partido.getJugadores().size());

        // ================================
        // Emparejar por ubicacion
        // ================================
        System.out.println("\n=== CAMBIO DE ESTRATEGIA A CERCANÍA ===");
        partido.setEstrategia(new EmparejarPorCercania());

        List<Usuario> segundaMitad = disponibles.subList(4, disponibles.size()); // 5 usuarios restantes
        partido.emparejar(segundaMitad, partido);

        System.out.println("Jugadores actuales: " + partido.getJugadores().size());

        // ================================
        // Confirmar y cambiar estados
        // ================================
        System.out.println("\n=== TRANSICIÓN DE ESTADOS ===");

        System.out.println("Estado actual: " + partido.getEstado().getClass().getSimpleName());

        partido.getEstado().confirmar(partido);
        System.out.println("Estado actual: " + partido.getEstado().getClass().getSimpleName());

        partido.getEstado().iniciar(partido);
        System.out.println("Estado actual: " + partido.getEstado().getClass().getSimpleName());

        partido.getEstado().finalizar(partido);
        System.out.println("Estado actual: " + partido.getEstado().getClass().getSimpleName());
    }
}
