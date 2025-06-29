package Modelo.strategy;

public class MatchStrategyFactory {
    public static MatchStrategy crearEstrategy(String tipo){
        return switch (tipo.toLowerCase()){
            case "nivel" -> new PorNivelStrategy();
            case "historial" -> new PorHistorialStrategy();
            case "cercania" -> new PorCercaniaStrategy();
            default -> throw new IllegalArgumentException("No se ha encontrado el tipo de strategy");
        };


    }
}