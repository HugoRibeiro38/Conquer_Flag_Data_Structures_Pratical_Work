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

    public Portal(String name, float latitude, float longitude ) {
        super(latitude, longitude);
        this.gameSettings= new GameSettings(DEFAULT_ENERGY,null);
        this.name = name;
        super.localType = LocalType.NEUTRAL;
        this.status = TeamType.NEUTRAL;
    }



   /* public Portal(String name, float latitude, float longitude, int ID, int energy, String localType, String status) {
        super(name, latitude, longitude, ID, energy, localType);
        StatusCheck(status);
    }
    */

    /*
    private void StatusCheck(String status) {
        switch (status) {
            case "Neutral":
                this.status = "Neutral";
                this.OwnPlayer = null;
                System.out.println("This Local is empty");
                break;

            case "Owned":
                this.status = "Owned";
                System.out.println("This Local dominated by" + OwnPlayer.getTeam() + "conquered by" + OwnPlayer.getName());
        }
    }
     */

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

  //TODO: Refactor string Method
  private class GameSettings extends AGameSettings {
    private OwnerShip ownerShip;


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
