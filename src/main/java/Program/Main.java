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
    //Gestao_Players.addPlayer("Hugo", "Giants");
    //Gestao_Players.addPlayer("João", "Sparks");
    //Gestao_Players.removePlayer();
    ArrayUnorderedList<ILocalType> topass = new ArrayUnorderedList<>() {{
      addToRear(portal1);
    }};
    //System.out.println(graph.findAllPaths());
    //System.out.println("TOPASS "+graph.findShortestPath_WithPoints(topass, portal, portal1));

    //Gestao_Local.menu();
    //System.out.println(SimulatePlay.graph.getRoutes().size());
    //  Gestao_Players.menu();

    SimulatePlay.graph = graph;
    SimulatePlay.players = players;

    /*
     *
     * MENU PARA SER USADO
     * */

    //Menu para Gestao_Jogo,Gestao_Players,Gestao_Local,Gestao_Rotas
    int option;
    Scanner sc = new Scanner(System.in);
    do {
      System.out.println("Choose an option:\n" +
          " 1.Gestao_Jogo 2.Gestao_Players 3.Gestao_Local 4.Gestao_Rotas 5.Simulate Play 0.STOP");
      option = sc.nextInt();
      switch (option) {
        case 1:
          Gestao_Jogo.menu();
          break;
        case 2:
          Gestao_Players.menu();
          break;
        case 3:
          Gestao_Local.menu();
          break;
        case 4:
          Gestao_Rotas.menu();
          break;
        case 5:
          SimulatePlay jogar = new SimulatePlay();
          jogar.play();
        case 0:
          break;
        default:
          System.out.println("Invalid option");
          break;
      }
    } while (option != 0);


  }
}
