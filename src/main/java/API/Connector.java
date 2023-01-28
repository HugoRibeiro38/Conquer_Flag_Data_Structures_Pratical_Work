package API;

import API.Abstractions.AGameSettings;
import API.Enums.LocalType;
import API.Interfaces.IConnector;
import com.so.Collections.Map.HashMap;

public class Connector extends Local implements IConnector {
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

 /* public void reload_energy(Player player) {
    if (!players.containsKey(player) || SimulatePlay.getCurrentTime() - players.get(player) >= getCoolDownTimer()) {
      players.put(player, SimulatePlay.getCurrentTime());
      //Check if energy to be reloaded is less than this.energy, if so reload player energy with energy to be reloaded, else reload player energy with this.energy
      int energyToBeReloaded = player.getDefaultEnergy() - player.getCurrentEnergy();
      player.setEnergy(energyToBeReloaded < this.gameSettings.getEnergy() ? player.getCurrentEnergy() + energyToBeReloaded : this.gameSettings.getEnergy());
    }
  }*/


  /**
   * Construtor de um Connector, sem cooldown timer.
   * É predefinido o valor de cooldownTimer para 5 e a energia de um connector para um valor aleatório entre (100 - 300).
   * Ao instanciar um connector, fica guardado no LocalType que é um CONNECTOR
   *
   *
   * @param latitude  Latitude do Connector
   * @param longitude Longitude do Connector
   */
  public Connector(float latitude, float longitude) {
    super(latitude, longitude);
    super.localType = LocalType.CONNECTOR;
    this.gameSettings = new GameSettings(DEFAULT_COOLDOWN_TIMER, DEFAULT_ENERGY);
  }

  /**
   * Construtor de um Connector, com cooldown timer.
   * O Valor do cooldownTimer e energy do connector é definido pelo utilizador.
   *
   * @param latitude      Latitude do Connector
   * @param longitude     Longitude do Connector
   * @param energy        Energia do Connector
   * @param cooldownTimer Tempo de espera para o jogador poder carregar no Connector novamente
   */
  public Connector(float latitude, float longitude, int energy, int cooldownTimer) {
    super(latitude, longitude);
    this.gameSettings = new GameSettings(cooldownTimer, energy);
    super.localType = LocalType.CONNECTOR;
  }

  public int getCoolDownTimer() {
    GameSettings gameSettings = (GameSettings) this.gameSettings;
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

    public int getCooldownTimer() {
      return this.cooldownTimer;
    }

    @Override
    public String toString() {
      return "GameSettings{" +
          super.toString() + ", " +
          "cooldownTimer=" + cooldownTimer +
          '}';
    }
  }

  @Override
  public String toString() {
    return "Connector{" +
        "gameSettings=" + gameSettings +
        ", latitude=" + latitude +
        ", longitude=" + longitude +
        ", ID=" + ID +
        ", localType=" + localType +
        '}';
  }
}
