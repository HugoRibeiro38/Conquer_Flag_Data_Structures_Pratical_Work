package API;

import API.Enums.TeamType;
import API.Exceptions.IllegalArgumentException;
import API.Interfaces.IGlobalSettings;
import API.Interfaces.IPlayer;

public class Player implements IPlayer {

  private String name;
  private TeamType team;
  private int level;
  private int experiencePoints;
  private int currentEnergy;
  private int DEFAULT_ENERGY = 100;

  public Player(String name, String team) {
    this.name = name;
    ConvertTeam(team);
    this.level = 0;
    this.experiencePoints = 0;
    this.currentEnergy = DEFAULT_ENERGY;
  }

  private void ConvertTeam(String team) {
    switch (team) {
      case "Giants":
        this.team = TeamType.GIANTS;
        break;

      case "Sparks":
        this.team = TeamType.SPARKS;
        break;
      default:
        throw new IllegalArgumentException("Invalid team:" + team);
    }
  }

  private int calculateDefaultEnergy() {
    return DEFAULT_ENERGY + (int) (DEFAULT_ENERGY * 0.05 * this.level);
  }

  public int getDefaultEnergy() {
    return calculateDefaultEnergy();
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public TeamType getTeam() {
    return this.team;
  }

  public void setTeam(TeamType team) {
    this.team = team;
  }

  private int checkLevel() {

    if (this.experiencePoints >= IGlobalSettings.calculateExperiencePoints(
        this.level + 1, GlobalSettings.x, GlobalSettings.y)) {
      this.level++;

    }
    return this.level;
  }

  public int getLevel() {
    return checkLevel();
  }

  //Creio que não seja necessário
  public void setLevel(int level) {
    this.level = level;
  }

  public int getExperiencePoints() {
    return experiencePoints;
  }

  public void setExperiencePoints(int experiencePoints) {
    this.experiencePoints = experiencePoints;
  }

  public int getCurrentEnergy() {
    return currentEnergy;
  }

  public void setCurrentEnergy(int currentEnergy) {
    this.currentEnergy = currentEnergy;
  }
}
