package Modelo;

import java.util.ArrayList;
import java.util.List;

import Modelo.strategy.*;
import Modelo.factoryPartido.*;
import Modelo.observer.*;



public class Usuario {
    private String nombre;
    private String email;
    private String password;
    private String deporteFavorito;
    private String ubicacion;
    private Nivel nivel;
    private MatchStrategy estrategia;
    private List<IPartido> partidosJugados = new ArrayList<IPartido>();


    public Usuario(String nombre, String email, String password,String ubicacion, String deporteFavorito) {
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.ubicacion = ubicacion;
        this.deporteFavorito = deporteFavorito;
    }
    public Usuario(String nombre, String email, String password,String ubicacion) {
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.ubicacion = ubicacion;
    }

    public Usuario(String nombre, String email, String password,String ubicacion, Nivel nivel,String deporteFavorito ){
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.ubicacion = ubicacion;
        this.nivel = nivel;
        this.deporteFavorito = deporteFavorito;
    }
    public Usuario(String nombre, String email, String password,String ubicacion, Nivel nivel) {
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.ubicacion = ubicacion;
        this.nivel = nivel;
    }

    public void setNivel(Nivel nivel) {
        this.nivel = nivel;
    }

    public void setDeporteFavorito(String deporteFavorito) {
        this.deporteFavorito = deporteFavorito;
    }

    public void setEstrategia(String tipo) {
        this.estrategia = MatchStrategyFactory.crearEstrategy(tipo);
    }
    
    public void setEstrategia(MatchStrategy estrategia) {
        this.estrategia = estrategia;
    }

    public Nivel getNivel(){
        return nivel;
    }
    public String getUbicacion(){
        return this.ubicacion;
    }

    public IPartido crearPartido(TipoPartido tipo, int duracion, String ubicacion, Nivel min, Nivel max) {
        IPartido partido = FactoryPartido.crearPartido(tipo, duracion, ubicacion, min, max);
        partido.agregarNotificacion(new NotificadorEmail());
        partido.agregarNotificacion(new NotificadorPush());
        System.out.println("Partido de " + tipo + "creado por " + this.nombre);
        return partido;
    }

    public void buscarPartido(List<IPartido> partidoDisponibles){
        for(IPartido partido:partidoDisponibles){
            if(estrategia != null && estrategia.match(partido, this)){
                System.out.println("Encontre: "+ partido.getDeporte() );
            }
        }
    }
	public String getEmail() {
		return email;
	}
	public String getNombre() {
		return nombre;
	}
    public void agregarPartidoJugado(IPartido partido){
        this.partidosJugados.add(partido);
    }
     public List<IPartido> getPartidosJugados(){
        return this.partidosJugados;
    }
}