package Controlador;

import java.util.ArrayList;
import java.util.List;

import Modelo.Deporte;
import Modelo.Nivel;
import Modelo.Usuario;

public class UsuarioController {

    private List<Usuario> usuarios;
    private static UsuarioController instancia;

    public void registrarUsuario(String nombre, String email, String contraseña, Deporte deporteFavorito, Nivel nivel) {
        Usuario nuevoUsuario = new Usuario(nombre, email, contraseña, deporteFavorito, nivel);
        usuarios.add(nuevoUsuario);
    }

    public void mostrarUsuarios() {
        for (Usuario u : usuarios) {
            System.out.println(u);
        }
    }

    public void eliminarUsuario(Usuario usuario) {
        usuarios.remove(usuario);
    }

    public static UsuarioController getInstancia() {
        if (instancia == null) {
            instancia = new UsuarioController();
        }
        return instancia;
    }
    
    private UsuarioController() {
        usuarios = new ArrayList<>();
    }
}
