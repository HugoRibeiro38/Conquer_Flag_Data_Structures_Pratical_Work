package API;

import API.Interfaces.ILocalType;
import API.Interfaces.IPlayer;
import com.so.Collections.Arrays.ArrayUnorderedList;
import com.so.Collections.ListADT;
import com.so.Collections.Lists.LinkedList;
import com.so.Collections.Map.HashMap;

import java.util.Iterator;
import java.util.Scanner;

//TODO:Make Missing Menu that allows players to "play"
public class SimulatePlay {
  public static ListADT<IPlayer> players = new ArrayUnorderedList<>();
  public static ConcreteGraph graph = new ConcreteGraph();
  static HashMap<IPlayer, ILocalType> playerLocation = new HashMap<>();
  static int time = 0;

  public static int getTime() {
    return time;
  }

  public void incrementTime() {
    time++;
  }


  //Make a menu that allows players to "play"
  public void play() {

    if (players.isEmpty()) {
      System.out.println("No players");
      return;
    }
    if (graph.isEmpty()) {
      System.out.println("No places");
      return;
    }

    if (!players.isEmpty() && playerLocation.isEmpty()) {
      generateRandomLocations();
    }

    Iterator<IPlayer> playerIterator = players.iterator();
    int option;
    do {
      System.out.println("Choose an option:\n" +
          " 1.Play 0.STOP");
      Scanner sc = new Scanner(System.in);
      option = sc.nextInt();
      if (option == 0) {
        break;
      }
      IPlayer currentPlayer = cyclePlayers(playerIterator);

      ILocalType currentPlace = (ILocalType) playerLocation.get(currentPlayer);

      System.out.println("Current Player: " + currentPlayer.getName());
      System.out.println("Current Place: " + currentPlace.getID());

      //Make a menu with options move to a place, or stay in the current place and do something
      System.out.println("Choose an option:\n" +
          " 1.Move 2.Stay");
      option = sc.nextInt();
      switch (option) {
        case 1:
          LinkedList<ILocalType> places_move = (LinkedList<ILocalType>) graph.displayPlaces(currentPlace);
          System.out.println("ID of the place you want to move to:");
          int id = sc.nextInt();


          ILocalType newPlace = graph.getVertex(id);
          if (newPlace == null) {
            System.out.println("Invalid ID");
            break;
          } else if (!places_move.contains(newPlace)) {
            System.out.println("You can't move to that place");
            break;
          }
          System.out.println("Moving to " + newPlace.getID());
          playerLocation.put(currentPlayer, newPlace);
          incrementTime();
          break;
        case 2:
          currentPlace.menu(currentPlayer);
          incrementTime();
          break;
        default:
          System.out.println("Invalid option");
          break;
      }


    } while (true);

  }

  //Generate a random location for each player
  public static void generateRandomLocations() {
    if (!playerLocation.isEmpty()) {
      return;
    }
    for (IPlayer player : players) {
      //Generate a random int between 0 and the number of vertices
      int random = (int) (Math.random() * graph.size());

      playerLocation.put(player, ((ConcreteGraph) graph).getVertex_Pos(random));
    }
  }


  private IPlayer cyclePlayers(Iterator<IPlayer> playerIterator) {
    if (!playerIterator.hasNext()) {
      playerIterator = players.iterator();
    }
    return playerIterator.next();
  }


}
