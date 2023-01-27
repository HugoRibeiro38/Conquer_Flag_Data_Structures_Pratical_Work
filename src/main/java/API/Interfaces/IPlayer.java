package API.Interfaces;

import API.Enums.TeamType;

public interface IPlayer {

    String getName();

    void setName(String name);

    TeamType getTeam();

    void setTeam(TeamType team);

    int getLevel();

    void setLevel(int level);

    int getExperiencePoints();

    void setExperiencePoints(int experiencePoints);

    int getCurrentEnergy();

    void setCurrentEnergy(int currentEnergy);
}
