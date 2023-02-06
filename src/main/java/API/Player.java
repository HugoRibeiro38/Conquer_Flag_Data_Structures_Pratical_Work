package API;

import API.Enums.TeamType;
import API.Interfaces.IGlobalSettings;
import API.Interfaces.IPlayer;

public class Player implements IPlayer {

  private String name;
  private TeamType team;
  private int level;
  private int experiencePoints;
  private int currentEnergy;
  private int DEFAULT_ENERGY = 100;
  public int currentLocation = -1;
  private int capturedPortals = 0;


  public Player(String name, String team) {
    this.name = name;
    this.team = TeamType.valueOf(team);
    this.level = 0;
    this.experiencePoints = 0;
    this.currentEnergy = DEFAULT_ENERGY;
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

  public int getCapturedPortals() {
    return capturedPortals;
  }

  public void setCapturedPortals(int capturedPortals) {
    this.capturedPortals = capturedPortals;
  }


  //DO compareTo method that compares the level of the player, the Team and number of portals conquisted by one player
  @Override
  public int compareTo(IPlayer player) {
    if (!team.equals(player.getTeam())) {
      return team.compareTo(player.getTeam());
    }
    if (level != player.getLevel()) {
      return Integer.compare(level, player.getLevel());
    }
    return Integer.compare(capturedPortals, player.getCapturedPortals());
  }


  @Override
  public String toString() {
    return "Player{" +
        "name='" + name + '\'' +
        ", team=" + team +
        ", level=" + level +
        ", experiencePoints=" + experiencePoints +
        ", currentEnergy=" + currentEnergy +
        ", MaxEnergy=" + DEFAULT_ENERGY +
        ", currentLocation=" + currentLocation +
        ", capturedPortals=" + capturedPortals +
        '}';
  }
}
