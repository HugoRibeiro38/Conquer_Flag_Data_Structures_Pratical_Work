package API;

import API.Abstractions.AGameSettings;
import API.Enums.LocalType;
import API.Enums.TeamType;
import API.Interfaces.IPortal;

public class Portal extends Local implements IPortal {
  private String name;
  private TeamType status;
  //private Player OwnPlayer;
  private final static int DEFAULT_ENERGY = 0;
  private AGameSettings gameSettings;

  private final int MAX_ENERGY = 400; /* Visto que um connector tem energia de (100 - 300) */
  private int MAX_energy;

  public Portal(String name, float latitude, float longitude) {
    super(latitude, longitude);
    this.gameSettings = new GameSettings(DEFAULT_ENERGY, null);
    this.name = name;
    super.localType = LocalType.PORTAL;
    this.status = TeamType.NEUTRAL;
    this.MAX_energy = MAX_ENERGY;
  }

  // Make method that allows a player to capture a portal with TeamType.Neutral,
  // and if so the player needs to have atleast 25% of the maximum energy of the portal


  public boolean conquerPortal(Player player) {
    //TODO: Remove this if statement, and make it so that the player can capture a portal with TeamType.Neutral in the menu class
    if (this.status != TeamType.NEUTRAL) {
      return false;
    }


    // Get the maximum energy of the portal
    //TODO: Make missing method (getMaxEnergy)
    int portal_maxEnergy = this.getMAX_energy();
    int player_currentEnergy = player.getCurrentEnergy();
    /*
     * This checks if the energy is less or equal to current energy of the player
     * and energy is greater or equal to 25% of the maximum energy of the portal
     * if any part is false then, it returns ;
     */

    //TODO: Increment player score after capturing a Neutral portal
    if (player_currentEnergy >= (portal_maxEnergy * 0.25)) {
      player.setCurrentEnergy(player_currentEnergy - (int) (portal_maxEnergy * 0.25));
      //Energia do portal
      this.gameSettings.setEnergy(this.gameSettings.getEnergy() + (int) (portal_maxEnergy * 0.25));
      this.status = player.getTeam();
      GameSettings gameSettings = (GameSettings) this.gameSettings;
      gameSettings.getOwnerShip().setPlayer(player);
      return true;
    }

    return false;
  }

  /*
  Dar uma vista de olhos no codigo para ver se esta tudo bem
   */
  public boolean conquerEnemyPortal(Player player) {
    //TODO: Remove this if statement, and make it so that the player can capture a portal with !=TeamType  and TeamType!=NEUTRAL in the menu class
    if (this.status.equals(player.getTeam()) || this.status.equals(TeamType.NEUTRAL)) {
      return false;
    }

    if (this.gameSettings.getEnergy() > player.getCurrentEnergy()) {
      //TODO: Make custom Exception
      throw new IllegalArgumentException("Player does not have enough energy to capture this portal");
    }

//Energia da Torre 70
// Energia do player 70

    //0
    this.gameSettings.setEnergy(this.gameSettings.getEnergy() - player.getCurrentEnergy());
    //0
    int remainingEnergy = Math.abs(this.gameSettings.getEnergy());

    //Check if the portal has no energy left
    if (gameSettings.getEnergy() < 0) {
      this.gameSettings.setEnergy(0);
    }
    //0
    player.setCurrentEnergy(remainingEnergy);

    //TODO: Increment player score after capturing an Enemy portal
    //OwnerShip to null
    GameSettings gameSettings = (GameSettings) this.gameSettings;
    gameSettings.getOwnerShip().setPlayer(null);
    this.status = TeamType.NEUTRAL;
    System.out.println("Player " + player.getName() + "de-captured the portal " + this.name);

    //Return true if Portal has been captured, false if not
    return conquerPortal(player);
  }

  //Make method that allows a player make a portal stronger by adding its current energy to the portal
  public boolean addEnergy(Player player, int energy) {

    //Not the player's team portal
    if (this.status != player.getTeam()) {
      throw new IllegalArgumentException("Portal does not belong to the player's team: " + player.getTeam());
    }

    int player_currentEnergy = player.getCurrentEnergy();
    int portal_maxEnergy = this.getMAX_energy();
    int portal_currentEnergy = this.gameSettings.getEnergy();
    //Check if the energy more than the player has, if so throw an exception
    if (player_currentEnergy < energy) {
      throw new IllegalArgumentException("Energy is more than the player has");
    }

    //Check if the energy is more than the portal can hold, if so just add the energy that the portal can hold
    if (portal_currentEnergy + energy > portal_maxEnergy) {
      int energyToAdd = portal_maxEnergy - portal_currentEnergy;
      this.gameSettings.setEnergy(this.gameSettings.getEnergy() + energyToAdd);
      player.setCurrentEnergy(player_currentEnergy - energyToAdd);
      return true;
    } else {
      this.gameSettings.setEnergy(this.gameSettings.getEnergy() + energy);
      player.setCurrentEnergy(player_currentEnergy - energy);
      return true;
    }
    //TODO: Increment player score after adding energy to a portal

    //player.setExperiencePoints(player.getExperiencePoints() + GlobalSettings.addPortalEnergyPoints);
    return false;
  }


  public int getMAX_energy() {
    return MAX_energy;
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public void setName(String name) {
    this.name = name;
  }

  @Override
  public LocalType getType() {
    return super.localType;
  }

  @Override
  public float getLatitude() {
    return super.latitude;
  }

  @Override
  public float getLongitude() {
    return super.longitude;
  }

  @Override
  public TeamType getStatus() {
    return this.status;
  }

  @Override
  public Player getPlayer() {
    GameSettings gameSettings1 = (GameSettings) this.gameSettings;
    return gameSettings1.ownerShip.getPlayer();
  }

  // TODO: Refactor string Method
// GameSettings energy is the current energy of the portal
  private class GameSettings extends AGameSettings {

    private OwnerShip ownerShip;

    public OwnerShip getOwnerShip() {
      return ownerShip;
    }

    public GameSettings(int energy, Player player) {
      super(energy);
      this.ownerShip = new OwnerShip(player);
    }

    private class OwnerShip {
      private Player player;

      public OwnerShip(Player player) {
        this.player = player;
      }

      public Player getPlayer() {
        return player;
      }

      public void setPlayer(Player player) {
        this.player = player;
      }

      @Override
      public String toString() {
        return "OwnerShip{" +
            "player=" + player +
            '}';
      }
    }

    @Override
    public String toString() {
      return "{" +
          super.toString() + "," +
          "ownerShip=" + ownerShip.toString() +
          '}';
    }

  }

  @Override
  public String toString() {
    return "Portal{" +
        "name='" + name + '\'' +
        ", status='" + status + '\'' +
        ", OwnPlayer=" + getPlayer() +
        ", gameSettings=" + gameSettings +
        ", latitude=" + latitude +
        ", longitude=" + longitude +
        ", ID=" + ID +
        ", localType=" + localType +
        '}';
  }
}
