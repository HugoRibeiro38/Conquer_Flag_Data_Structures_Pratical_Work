package Program;

import API.*;
import API.Interfaces.IConnector;
import API.Interfaces.ILocalType;
import API.Interfaces.IPlayer;
import API.Interfaces.IPortal;
import com.so.Collections.Arrays.ArrayUnorderedList;

import java.util.Scanner;

public class Main {
  public static void main(String[] args) {

    Player player = new Player("Hugo", "GIANTS");
    //System.out.println(player.getCurrentEnergy());


    Player player1 = new Player("João", "SPARKS");

    ArrayUnorderedList<IPlayer> players = new ArrayUnorderedList<>() {{
      addToRear(player);
      addToRear(player1);
    }};

    IPortal portal = new Portal("F", 4, 4);

    System.out.println(portal.getID());

    IPortal portal1 = new Portal("G", 5, 5);


    System.out.println(portal1.getStatus());
    IConnector connector = new Connector(1, 1);
    IConnector connector1 = new Connector(2, 10);
    System.out.println(connector.toString());

    ConcreteGraph graph = new ConcreteGraph();


    //System.out.println("ROUTE: "+graph.getRoutes().toString());
    //System.out.println(route.size());
    //graph.removeRoute(route);
    //System.out.println("ROUTE: "+graph.getRoutes().toString());
    graph.addPlace(portal);
    graph.addPlace(portal1);
    graph.addPlace(connector);
    graph.addPlace(connector1);
    graph.addRoute(1, 2);
    graph.addRoute(3, 1);
    graph.addRoute(2, 3);
    graph.addRoute(4, 1);
    graph.addRoute(4, 2);
    //System.out.println(graph.toString());
    //graph.removeRoute(1, 3);
    System.out.println(graph.toString());
    //graph.removePlace(1);
    //System.out.println(graph.toString());
    // System.out.println(graph.djistkra(portal, portal1));
    //Manage_Players.addPlayer("Hugo", "Giants");
    //Manage_Players.addPlayer("João", "Sparks");
    //Manage_Players.removePlayer();
    ArrayUnorderedList<ILocalType> topass = new ArrayUnorderedList<>() {{
      addToRear(portal1);
    }};
    //System.out.println(graph.findAllPaths());
    //System.out.println("TOPASS "+graph.findShortestPath_WithPoints(topass, portal, portal1));

    //Manage_Local.menu();
    //System.out.println(SimulatePlay.graph.getRoutes().size());
    //  Manage_Players.menu();

    //SimulatePlay.graph = graph;
    //SimulatePlay.players = players;

    /*
     *
     * MENU PARA SER USADO
     * */

    //Menu para Manage_Game,Manage_Players,Manage_Local,Manage_Routes
    int option;
    Scanner sc = new Scanner(System.in);
    do {
      System.out.println("Choose an option:\n" +
          " 1.Manage_Game 2.Manage_Players 3.Manage_Local 4.Manage_Routes 5.Simulate Play 6.Load File  7.Export File 0.STOP");
      option = sc.nextInt();
      switch (option) {
        case 1:
          Manage_Game.menu();
          break;
        case 2:
          Manage_Players.menu();
          break;
        case 3:
          Manage_Local.menu();
          break;
        case 4:
          Manage_Routes.menu();
          break;
        case 5:
          SimulatePlay jogar = new SimulatePlay();
          jogar.play();
          break;
        case 6:
          SimulatePlay loadFile = new SimulatePlay();
          loadFile.loadFile();
          break;
        case 7:
          SimulatePlay saveFile = new SimulatePlay();
          saveFile.saveFile();
          break;
        case 0:
          break;
        default:
          System.out.println("Invalid option");
          break;
      }
    } while (option != 0);


  }
}
