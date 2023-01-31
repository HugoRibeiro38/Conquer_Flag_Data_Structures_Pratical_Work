package Program;

import API.ConcreteGraph;
import API.Connector;
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

    ConcreteGraph graph = new ConcreteGraph();


    //System.out.println("ROUTE: "+graph.getRoutes().toString());
    //System.out.println(route.size());
    //graph.removeRoute(route);
    //System.out.println("ROUTE: "+graph.getRoutes().toString());
    graph.insertVertex(portal);
    graph.insertVertex(portal1);
    graph.insertVertex(connector);
    graph.addRoute(1, 2);
    graph.addRoute(3, 1);
    graph.addRoute(2, 3);
    graph.removeRoute(1, 3);
    //System.out.println(graph.toString());
    graph.removePlace(1);
    System.out.println(graph.toString());

  }
}
