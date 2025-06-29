package Vista;

import javax.swing.*;
import java.awt.*;
import java.awt.List;
import java.util.*;
import Modelo.*;
import Controlador.*;
import Modelo.strategy.*;
import Modelo.factoryPartido.*;
import Modelo.estados.*;

public class MenuConsola extends JFrame {

    private PartidoControlador controlador;
    private Map<String, Usuario> usuarios;
    private int idPartidoCreado = -1;

    public MenuConsola(PartidoControlador controlador, Map<String, Usuario> usuarios) {
        this.controlador = controlador;
        this.usuarios = usuarios;
        setTitle("Menú de Partidos");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton btnRegistrar = new JButton("Registrar usuario");
        JButton btnCrear = new JButton("Crear partido");
        JButton btnAgregar = new JButton("Agregar jugador");
        JButton btnEstado = new JButton("Cambiar estado");
        JButton btnMostrar = new JButton("Mostrar estado");
        JButton btnEstrategia = new JButton("Buscar con estrategia");
        JButton btnSalir = new JButton("Salir");

        panel.add(btnRegistrar);
        btnRegistrar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnRegistrar.setMaximumSize(new Dimension(200, 30));
        panel.add(btnCrear);
        btnCrear.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnCrear.setMaximumSize(new Dimension(200, 30));
        panel.add(btnAgregar);
        btnAgregar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnAgregar.setMaximumSize(new Dimension(200, 30));
        panel.add(btnEstado);
        btnEstado.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnEstado.setMaximumSize(new Dimension(200, 30));
        panel.add(btnMostrar);
        btnMostrar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnMostrar.setMaximumSize(new Dimension(200, 30));
        panel.add(btnEstrategia);
        btnEstrategia.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnEstrategia.setMaximumSize(new Dimension(200, 30));
        panel.add(btnSalir);
        btnSalir.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnSalir.setMaximumSize(new Dimension(200, 30));

        add(panel);

        // Listeners
        btnRegistrar.addActionListener(e -> registrarUsuario());
        btnCrear.addActionListener(e -> crearPartido());
        btnAgregar.addActionListener(e -> agregarJugador());
        btnEstado.addActionListener(e -> cambiarEstado());
        btnMostrar.addActionListener(e -> mostrarEstado());
        btnEstrategia.addActionListener(e -> aplicarEstrategia());
        btnSalir.addActionListener(e -> System.exit(0));

        setVisible(true);
    }

    private void registrarUsuario() {
        String nombre = JOptionPane.showInputDialog("Nombre:");
        String email = JOptionPane.showInputDialog("Email:");
        String pass = JOptionPane.showInputDialog("Contraseña:");
        String ubicacion = JOptionPane.showInputDialog("Ubicación:");


        String deporte = JOptionPane.showInputDialog("Deporte preferido (FUTBOL5, FUTBOL11, BASQUET):");

        Usuario usuario;
        String[] niveles = {"NINGUNO","PRINCIPIANTE", "INTERMEDIO", "AVANZADO"};
        String nivelStr = (String) JOptionPane.showInputDialog(null, "Nivel:", "Seleccionar Nivel",
                JOptionPane.QUESTION_MESSAGE, null, niveles, niveles[0]);
        if (nivelStr=="NINGUNO") {
            if (deporte == null || deporte.isEmpty()) {
                usuario = new Usuario(nombre, email, pass, ubicacion);
            } else {
                usuario = new Usuario(nombre, email, pass, ubicacion, deporte);
            }
        }
        else {
            Nivel nivel = Nivel.valueOf(nivelStr);
            if (deporte == null || deporte.isEmpty()) {
                usuario = new Usuario(nombre, email, pass, ubicacion, nivel);
            } else {
                usuario = new Usuario(nombre, email, pass, ubicacion, nivel, deporte);
            }
        }
        usuarios.put(email, usuario);
        JOptionPane.showMessageDialog(this, "Usuario registrado correctamente.");

        
    }private void crearPartido() {
        String email = JOptionPane.showInputDialog("Email del creador:");
        Usuario creador = usuarios.get(email);
        if (creador == null) {
            JOptionPane.showMessageDialog(this, "Usuario no encontrado.");
            return;
        }

        String[] tipos = {"FUTBOL5", "FUTBOL11", "BASQUET"};
        String tipoStr = (String) JOptionPane.showInputDialog(null, "Tipo de partido:", "Seleccionar tipo",
                JOptionPane.QUESTION_MESSAGE, null, tipos, tipos[0]);
        if (tipoStr == null) return;

        TipoPartido tipo = TipoPartido.valueOf(tipoStr);

        // Duración
        int duracion = 0;
        try {
            String duracionStr = JOptionPane.showInputDialog("Duración del partido (en minutos):");
            if (duracionStr == null) return;
            duracion = Integer.parseInt(duracionStr);
            if (duracion <= 0) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Duración inválida. Debe ser un número positivo.");
            return;
        }

        // Ubicación
        String ubicacion = JOptionPane.showInputDialog("Ubicación del partido:");
        if (ubicacion == null || ubicacion.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ubicación no puede estar vacía.");
            return;
        }

        // Nivel mínimo
        Nivel[] niveles = Nivel.values();
        Nivel nivelMin = (Nivel) JOptionPane.showInputDialog(null, "Nivel mínimo (opcional):", "Seleccionar nivel mínimo",
                JOptionPane.QUESTION_MESSAGE, null, niveles, null);

        // Nivel máximo
        Nivel nivelMax = (Nivel) JOptionPane.showInputDialog(null, "Nivel máximo (opcional):", "Seleccionar nivel máximo",
                JOptionPane.QUESTION_MESSAGE, null, niveles, null);

        // Validar que nivelMin <= nivelMax (si ambos están definidos)
        if (nivelMin != null && nivelMax != null) {
            if (nivelMin.ordinal() > nivelMax.ordinal()) {
                JOptionPane.showMessageDialog(this, "Nivel mínimo no puede ser mayor que el nivel máximo.");
                return;
            }
        }

        // Llamar al controlador con todos los datos
        String mensaje = controlador.crearPartido(creador, tipo, duracion, ubicacion, nivelMin, nivelMax);
        idPartidoCreado = controlador.getIdPartidoActual();
        JOptionPane.showMessageDialog(this, mensaje);
    }
    private void agregarJugador() {
        String email = JOptionPane.showInputDialog("Email del jugador:");
        Usuario jugador = usuarios.get(email);
        if (jugador == null) {
            JOptionPane.showMessageDialog(this, "Usuario no encontrado.");
            return;
        }

        IPartido partidoSeleccionado = seleccionarPartido();
        if (partidoSeleccionado == null) return;

        controlador.agregarJugador(partidoSeleccionado.getID(), jugador);
        JOptionPane.showMessageDialog(this, "Jugador agregado al partido.");
    }

    private void cambiarEstado() {
        IPartido partidoSeleccionado = seleccionarPartido();
        if (partidoSeleccionado == null) return;

        String[] opciones = {"Confirmar", "Iniciar", "Finalizar"};
        String opcion = (String) JOptionPane.showInputDialog(null, "Seleccione acción:",
                "Cambiar estado", JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);

        if (opcion == null) return;

        switch (opcion) {
            case "Confirmar":
                controlador.confirmarPartido(partidoSeleccionado.getID());
                break;
            case "Iniciar":
                controlador.iniciarPartido(partidoSeleccionado.getID());
                break;
            case "Finalizar":
                controlador.finalizarPartido(partidoSeleccionado.getID());
                break;
        }

        JOptionPane.showMessageDialog(this, "Estado actualizado.");
    }


    private void mostrarEstado() {
        IPartido partidoSeleccionado = seleccionarPartido();
        if (partidoSeleccionado == null) return;

        controlador.mostrarEstado(partidoSeleccionado.getID());
    }

    private IPartido seleccionarPartido() {
        Map<Integer, IPartido> partidosMap = controlador.getPartidos();

        if (partidosMap.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay partidos disponibles.");
            return null;
        }

        // Crear una lista de descripciones
        java.util.List<String> descripciones = new ArrayList<>();
        Map<String, Integer> descripcionAId = new HashMap<>();

        for (Map.Entry<Integer, IPartido> entry : partidosMap.entrySet()) {
            int id = entry.getKey();
            IPartido partido = entry.getValue();
            String descripcion = "ID: " + id + " - " + partido.getDeporte();
            descripciones.add(descripcion);
            descripcionAId.put(descripcion, id);
        }

        String seleccion = (String) JOptionPane.showInputDialog(
                null,
                "Seleccione un partido:",
                "Partidos Disponibles",
                JOptionPane.QUESTION_MESSAGE,
                null,
                descripciones.toArray(),
                descripciones.get(0)
        );

        if (seleccion == null) return null;

        int idSeleccionado = descripcionAId.get(seleccion);
        return partidosMap.get(idSeleccionado);
    }

    private void aplicarEstrategia() {
        String email = JOptionPane.showInputDialog("Email del usuario:");
        Usuario usuario = usuarios.get(email);
        if (usuario == null) {
            JOptionPane.showMessageDialog(this, "Usuario no encontrado.");
            return;
        }

        String[] estrategias = {"Por Nivel", "Por Cercanía", "Por Historial"};
        String seleccion = (String) JOptionPane.showInputDialog(null, "Estrategia:",
                "Seleccionar Estrategia", JOptionPane.QUESTION_MESSAGE, null, estrategias, estrategias[0]);

        if (seleccion == null) return;

        switch (seleccion) {
            case "Por Nivel":
                usuario.setEstrategia("nivel");
                break;
            case "Por Cercanía":
                usuario.setEstrategia("cercania");
                break;
            case "Por Historial":
                usuario.setEstrategia("historial");
                break;
        }

        usuario.buscarPartido(controlador.partidosDisponibles);
        JOptionPane.showMessageDialog(this, "Estrategia aplicada.");
    }

    public static void main(String[] args) {
    	Map<String, Usuario> usuarios = new HashMap<>();
    	PartidoControlador controlador = new PartidoControlador();
    	
    	// Poblamos la DB
        Usuario usuario1 = new Usuario("Juan", "juan@mail.com", "1234", "Palermo", Nivel.INTERMEDIO, "FUTBOL5");
        Usuario usuario2 = new Usuario("Maria", "maria@mail.com", "abcd", "Recoleta", Nivel.AVANZADO, "BASQUET");
        Usuario usuario3 = new Usuario("Lucas", "lucas@mail.com", "pass", "Caballito", Nivel.PRINCIPIANTE);
        Usuario usuario4 = new Usuario("Tomas", "tomas@mail.com", "pass", "Caballito", Nivel.PRINCIPIANTE);
        Usuario usuario5 = new Usuario("Ignacio", "ignacio@mail.com", "pass", "Caballito", Nivel.INTERMEDIO, "FUTBOL5");
        Usuario usuario6 = new Usuario("Valentin", "valen@mail.com", "pass", "San Telmo", Nivel.INTERMEDIO, "FUTBOL11");
        Usuario usuario7 = new Usuario("Roberto", "rober@mail.com", "pass", "Villa Urquiza", Nivel.INTERMEDIO);
        Usuario usuario8 = new Usuario("Lara", "lari@mail.com", "pass", "Caballito", Nivel.AVANZADO, "BASQUET");
        Usuario usuario9 = new Usuario("Mauricio", "mauri@mail.com", "123", "Caballito", Nivel.INTERMEDIO, "FUTBOL5");
        Usuario usuario10 = new Usuario("Luis", "lucho@mail.com", "abcd", "Caballito", Nivel.PRINCIPIANTE, "FUTBOL11");
        
        usuarios.put(usuario1.getEmail(), usuario1);
        usuarios.put(usuario2.getEmail(), usuario2);
        usuarios.put(usuario3.getEmail(), usuario3);
        usuarios.put(usuario4.getEmail(), usuario4);
        usuarios.put(usuario5.getEmail(), usuario5);
        usuarios.put(usuario6.getEmail(), usuario6);
        usuarios.put(usuario7.getEmail(), usuario7);
        usuarios.put(usuario8.getEmail(), usuario8);
        usuarios.put(usuario9.getEmail(), usuario9);
        usuarios.put(usuario10.getEmail(), usuario10);
        

        // CREACIÓN DE PARTIDO
        IPartido partidoFutbol = usuario1.crearPartido(TipoPartido.FUTBOL5, 60, "Caballito", Nivel.PRINCIPIANTE, Nivel.AVANZADO);

        // 4. ESTADO DE PARTIDO – AGREGAR JUGADORES
        partidoFutbol.agregarJugador(usuario1);
        partidoFutbol.agregarJugador(usuario2);
        partidoFutbol.agregarJugador(usuario3);
        partidoFutbol.agregarJugador(usuario4); 
        partidoFutbol.agregarJugador(usuario5); 
        partidoFutbol.agregarJugador(usuario6); 
        partidoFutbol.agregarJugador(usuario7); 
        partidoFutbol.agregarJugador(usuario8); 
        partidoFutbol.agregarJugador(usuario9); 
        partidoFutbol.agregarJugador(usuario10); //Cambia de estado x cantidad de jugadores completa
        
        controlador.agregarPartido(partidoFutbol);

        // CAMBIO MANUAL DE ESTADO
        partidoFutbol.cambiarEstado(new Confirmado()); //Confirmo
        partidoFutbol.cambiarEstado(new EnJuego()); //Empieza el partido
        partidoFutbol.cambiarEstado(new Finalizado()); //Finaliza el partido

        // ESTRATEGIA DE EMPAREJAMIENTO
        ArrayList<IPartido> partidosDisponibles = new ArrayList<>();
        partidosDisponibles.add(partidoFutbol);

        usuario2.setEstrategia(new PorNivelStrategy());
        usuario2.buscarPartido(partidosDisponibles);

        usuario6.setEstrategia(new PorCercaniaStrategy());
        usuario6.buscarPartido(partidosDisponibles);

        usuario10.setEstrategia(new PorHistorialStrategy());
        usuario10.buscarPartido(partidosDisponibles);
        
        // Inicializamos la UI
        SwingUtilities.invokeLater(() -> new MenuConsola(controlador, usuarios));
    }
}