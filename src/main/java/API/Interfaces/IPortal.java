package API.Interfaces;

import API.Enums.TeamType;
import API.Player;

public interface IPortal extends ILocalType {
    TeamType getStatus();

    Player getPlayer();

    String getName();

    void setName(String name);
}
