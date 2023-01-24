package API;

public class Connector extends Local {

    /**
     * Guardar num array os jogadores que ja carregaram no connector para inibilos de carregar por 5 min a cada vez que carregam em um connector.
     */
    public Connector(float latitude, float longitude, int energy) {
        super(latitude, longitude, energy);
    }
}
