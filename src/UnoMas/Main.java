package UnoMas;

import java.util.*;

import Controlador.*;
import Emparejamiento.*;
import Modelo.*;
import Notificador.*;
import Partido.*;

public class Main {
    public static void main(String[] args) {

        // ----------- CREACIÓN DE DEPORTES -----------
        Deporte futbol = new Deporte("Fútbol");
        Deporte voley = new Deporte("Vóley");

        DeporteController deporteController = DeporteController.getInstancia();
        deporteController.registrarDeporte(futbol.getNombre());
        deporteController.registrarDeporte(voley.getNombre());
        deporteController.mostrarDeportes();

        // ----------- CREACIÓN DE USUARIOS -----------
        Usuario juan = new Usuario("Juan", "juan@mail.com", "1234", futbol, Nivel.INTERMEDIO);
        Usuario ana = new Usuario("Ana", "ana@mail.com", "abcd", futbol, Nivel.AVANZADO);
        Usuario pedro = new Usuario("Pedro", "pedro@mail.com", "pass", futbol, Nivel.PRINCIPIANTE);
        Usuario laura = new Usuario("Laura", "laura@mail.com", "clave", futbol, Nivel.INTERMEDIO);

        UsuarioController usuarioController = UsuarioController.getInstancia();
        usuarioController.registrarUsuario(juan.getNombre(), juan.getEmail(), juan.getContraseña(), juan.getDeporteFavorito(), juan.getNivel());
        usuarioController.registrarUsuario(ana.getNombre(), ana.getEmail(), ana.getContraseña(), ana.getDeporteFavorito(), ana.getNivel());
        usuarioController.registrarUsuario(pedro.getNombre(), pedro.getEmail(), pedro.getContraseña(), pedro.getDeporteFavorito(), pedro.getNivel());
        usuarioController.registrarUsuario(laura.getNombre(), laura.getEmail(), laura.getContraseña(), laura.getDeporteFavorito(), laura.getNivel());
        usuarioController.mostrarUsuarios();

        // ----------- CREACIÓN DE ESTRATEGIA DE EMPAREJAMIENTO -----------
        EmparejamientoStrategy estrategiaNivel = new EmparejarPorNivel(); // lógica simple implementada abajo

        // ----------- CREACIÓN DE PARTIDO -----------
        PartidoController partidoController = PartidoController.getInstancia();

        Partido partido = new Partido(
            futbol,
            3, // jugadores requeridos
            60,
            "Plaza central",
            new Date(System.currentTimeMillis() + 3600000), // en 1 hora
            new NecesitamosJugadores(),
            Nivel.INTERMEDIO,
            Nivel.AVANZADO,
            estrategiaNivel
        );

        // ----------- OBSERVER: NOTIFICADOR CON ESTRATEGIA EMAIL -----------
        AdapterNotificacionEmail adaptador = new AdapterJavaMail() {
            @Override
            public void notificarJavaMail(Notificacion notificacion) {
                System.out.println("EMAIL enviado a " + notificacion.getUsuario().getEmail() + ": " + notificacion.getMensaje());
            }
        };
        EstrategiaNotificacion estrategiaEmail = new Email(adaptador);
        Notificador notificador = new Notificador(estrategiaEmail);

        partido.agregar(notificador);

        // ----------- AGREGO JUGADORES Y SE CAMBIA DE ESTADO AUTOMÁTICAMENTE -----------
        partido.getEstado().agregarJugador(partido, juan);
        partido.getEstado().agregarJugador(partido, laura);
        partido.getEstado().agregarJugador(partido, ana); // esto debería activar transición a PartidoArmado y notificar

        // ----------- CONFIRMAR EL PARTIDO -----------
        partido.getEstado().confirmar(partido);

        // ----------- INICIAR Y FINALIZAR EL PARTIDO -----------
        partido.getEstado().iniciar(partido);
        partido.getEstado().finalizar(partido);

        // ----------- CAMBIO DE ESTRATEGIA -----------
        partidoController.cambiarEstrategia(new EmparejarPorHistorial(), partido);
        System.out.println("Se cambió la estrategia de emparejamiento a: EmparejarPorHistorial");

        // ----------- APLICACIÓN DE ESTRATEGIA ACTUAL -----------
        partido.emparejar(Arrays.asList(pedro, juan, laura, ana), partido);
    }
}
