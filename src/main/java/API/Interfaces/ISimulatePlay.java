package API.Interfaces;

/**
 * Interface que representa a Simulação do jogo.
 */
public interface ISimulatePlay {
    /**
     * Incrementa o tempo da simulação.
     */
    void incrementTime();

    /**
     * Inicia a simulação de jogo.
     */
    void play();
}
