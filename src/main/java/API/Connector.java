package API;

import com.so.Collections.Map.HashMap;

public class Connector extends Local {
  private static final int DEFAULT_SIZE = 20;
  private static final int DEFAULT_COOLDOWN_TIMER = 5;
  public HashMap<Player, Integer> players = new HashMap<>(DEFAULT_SIZE);
  int cooldownTimer;


  /**
   * Guardar num array os jogadores que ja carregaram no connector para inibilos de carregar por 5 min a cada vez que carregam em um connector.
   */
  public void reload_energy(Player player){
    if(players.containsKey(player)){
      if(SimulatePlay.getCurrentTime() - players.get(player)>= getCoolDownTimer()){
        players.put(player, DEFAULT_COOLDOWN_TIMER);
        energy += 100;
      }
  }



  /**
   * Construtor de um Connector, sem cooldown timer.
   * É predefinido o valor de cooldownTimer para 5.
   *
   * @param latitude Latitude do Connector
   * @param longitude Longitude do Connector
   * @param energy Energia do Connector
   */
  public Connector(float latitude, float longitude, int energy) {
    super(latitude, longitude, energy);
    this.cooldownTimer=DEFAULT_COOLDOWN_TIMER;
  }

  /**
   * Construtor de um Connector, com cooldown timer.
   * O Valor do cooldownTimer é definido pelo utilizador.
   *
   * @param latitude Latitude do Connector
   * @param longitude Longitude do Connector
   * @param energy Energia do Connector
   * @param cooldownTimer Tempo de espera para o jogador poder carregar no Connector novamente
   */
  public Connector(float latitude, float longitude, int energy, int cooldownTimer) {
    super(latitude, longitude, energy);
    this.cooldownTimer = cooldownTimer;
  }

}
