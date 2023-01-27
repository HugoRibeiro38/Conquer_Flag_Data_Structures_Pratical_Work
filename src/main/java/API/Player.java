package API;

import API.Enums.TeamType;
import API.Interfaces.IPlayer;
import API.Exceptions.IllegalArgumentException;

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

    public int getLevel() {
        return this.level;
    }

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
