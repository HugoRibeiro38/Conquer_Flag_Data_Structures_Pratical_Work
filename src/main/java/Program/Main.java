package Program;

import API.ConcreteGraph;
import API.Connector;
import API.Gestao_Players;
import API.Interfaces.IConcreteGraph;
import API.Interfaces.IConnector;
import API.Interfaces.IPortal;
import API.Portal;

public class Main {
  public static void main(String[] args) {

    //Player player = new Player("Hugo", "Giants");
    //System.out.println(player.getCurrentEnergy());


    //Player player1 = new Player("João", "Sparks");

    IPortal portal = new Portal("F", 4, 4);

    System.out.println(portal.getID());

    IPortal portal1 = new Portal("G", 5, 5);


    System.out.println(portal1.getStatus());
    IConnector connector = new Connector(1, 1);
    System.out.println(connector.toString());

    IConcreteGraph graph = new ConcreteGraph();


    //System.out.println("ROUTE: "+graph.getRoutes().toString());
    //System.out.println(route.size());
    //graph.removeRoute(route);
    //System.out.println("ROUTE: "+graph.getRoutes().toString());
    graph.addPlace(portal);
    graph.addPlace(portal1);
    graph.addPlace(connector);
    graph.addRoute(1, 2);
    graph.addRoute(3, 1);
    graph.addRoute(2, 3);
    //System.out.println(graph.toString());
    //graph.removeRoute(1, 3);
    System.out.println(graph.toString());
    //graph.removePlace(1);
    //System.out.println(graph.toString());
    System.out.println(graph.djistkra(portal,portal1));
    //Gestao_Players.addPlayer("Hugo", "Giants");
    //Gestao_Players.addPlayer("João", "Sparks");
    //Gestao_Players.removePlayer();

  }
}
