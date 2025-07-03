package Modelo;

public class Usuario {

    private String nombre;
    private String email;
    private String contraseña;
    private Deporte deporteFavorito;
    private Nivel nivel;

    public Usuario(String nombre, String email, String contraseña, Deporte deporteFavorito, Nivel nivel) {
        this.nombre = nombre;
        this.email = email;
        this.contraseña = contraseña;
        this.deporteFavorito = deporteFavorito;
        this.nivel = nivel;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public Deporte getDeporteFavorito() {
        return deporteFavorito;
    }

    public void setDeporteFavorito(Deporte deporteFavorito) {
        this.deporteFavorito = deporteFavorito;
    }

    public Nivel getNivel() {
        return nivel;
    }

    public void setNivel(Nivel nivel) {
        this.nivel = nivel;
    }

    @Override
    public String toString() {
        return "Usuario{" +
               "nombre='" + nombre + '\'' +
               ", email='" + email + '\'' +
               ", deporteFavorito=" + deporteFavorito +
               ", nivel=" + nivel +
               '}';
    }
}
