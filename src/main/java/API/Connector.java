package API;

import API.Abstractions.AGameSettings;
import com.so.Collections.Map.HashMap;

public class Connector extends Local {
  private static final int DEFAULT_SIZE = 20;
  private static final int DEFAULT_COOLDOWN_TIMER = 5;

  private static final int maxEnergy = 300;
  private static final int minEnergy = 100;
  private static final int DEFAULT_ENERGY = (int) (Math.random() * (maxEnergy - minEnergy + 1)) + minEnergy;
  private final AGameSettings gameSettings;

  public HashMap<Player, Integer> players = new HashMap<>(DEFAULT_SIZE);


  /**
   * Guardar num HashMap os jogadores que ja carregaram no connector para inibilos de carregar energia antes do CoolDown_Timer.
   */
  /*
  public void reload_energy(Player player) {
    if (players.containsKey(player)) {
      if (SimulatePlay.getCurrentTime() - players.get(player) >= getCoolDownTimer()) {
        players.put(player, SimulatePlay.getCurrentTime());
        //Ternary operator, check if energy to be reloaded is less than this.energy, if so reload player energy with energy to be reloaded, else reload player energy with this.energy
        player.setEnergy(this.energy);

      }
    } else {
      players.put(player, SimulatePlay.getCurrentTime());
      player.setEnergy(this.energy);
    }
  }
*/

  /**
   * Construtor de um Connector, sem cooldown timer.
   * É predefinido o valor de cooldownTimer para 5.
   *
   * @param latitude  Latitude do Connector
   * @param longitude Longitude do Connector
   * @param energy    Energia do Connector
   */
  public Connector(float latitude, float longitude, int energy) {
    super(latitude, longitude);
    this.gameSettings = new GameSettings(DEFAULT_COOLDOWN_TIMER, energy);
  }

  /**
   * Construtor de um Connector, com cooldown timer.
   * O Valor do cooldownTimer é definido pelo utilizador.
   *
   * @param latitude      Latitude do Connector
   * @param longitude     Longitude do Connector
   * @param energy        Energia do Connector
   * @param cooldownTimer Tempo de espera para o jogador poder carregar no Connector novamente
   */
  public Connector(float latitude, float longitude, int energy, int cooldownTimer) {
    super(latitude, longitude);
    this.gameSettings = new GameSettings(cooldownTimer, energy);
  }

  public int getCoolDownTimer() {
    return gameSettings.getCooldownTimer();
  }

  private class GameSettings extends AGameSettings {
    private final int cooldownTimer;

    public GameSettings(int cooldownTimer, int energy) {
      super(energy);
      this.cooldownTimer = cooldownTimer;
    }


    @Override
    public int getEnergy() {
      return super.getEnergy();
    }
    @Override
    public int getCooldownTimer() {
      return this.cooldownTimer;
    }



  }

}
