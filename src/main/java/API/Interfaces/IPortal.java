package API.Interfaces;

import API.Player;

public interface IPortal extends ILocalType {
    String getStatus();

    Player getPlayer();

    String getName();

    void setName(String name);
}
