package Program;

import API.Enums.LocalType;
import API.Interfaces.IConnector;
import API.Interfaces.IPortal;
import API.Player;
import API.Portal;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        Player player = new Player("Hugo", "Giants");

        Player player1 = new Player("João", "Sparks");

        IPortal Portal = new Portal("London", 524.2f, 465346589, 1, 20, "Connector");

    }
}
