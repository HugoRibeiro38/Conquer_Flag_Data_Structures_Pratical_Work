package API;

import API.Enums.TeamType;
import API.Interfaces.IPlayer;
import com.so.Collections.Arrays.ArrayUnorderedList;
import com.so.Collections.Arrays.SortingAndSearching;
import com.so.Collections.ListADT;

import java.util.Iterator;
import java.util.Scanner;

public class Gestao_Players {


  public static void addPlayer(String name, String team) {
    IPlayer player = new Player(name, team);
    ArrayUnorderedList<IPlayer> temp_players = (ArrayUnorderedList<IPlayer>) SimulatePlay.players;
    temp_players.addToRear(player);
  }

//Make menu that goes through all the players and prints their names and teams, and then asks for the name of the player to be removed and removes it from the list.

  private static String goTroughPlayers(ListADT<IPlayer> temp_players, String textShow) {

    for (IPlayer player : temp_players) {
      System.out.println(player.toString());
    }
    System.out.println(textShow);
    Scanner sc = new Scanner(System.in);
    return sc.nextLine();

  }

  //Make menu that allows the user to update value of a player

  private static void updatePlayer(ListADT<IPlayer> temp_players) {
    String name = goTroughPlayers(temp_players, "Choose the player to update");
    IPlayer player = removePlayer(name);
    if (player == null) return;

    System.out.println("Name:" + player.getName() + ".Team" + player.getTeam());
    System.out.println("Choose the value to update: 1.Name 2.Team");
    Scanner sc = new Scanner(System.in);
    int option = sc.nextInt();
    switch (option) {
      case 1:
        System.out.println("Insert the new name");
        String new_name = sc.next();
        player.setName(new_name);
        break;
      case 2:
        System.out.println("Insert the new team");
        for (TeamType team : TeamType.values()) {
          System.out.println(team);
        }
        String team = sc.next();
        player.setTeam(TeamType.valueOf(team));
        break;
      default:
        System.out.println("Invalid option");
    }
    ((ArrayUnorderedList<IPlayer>) (temp_players)).addToRear(player);
  }

  public static IPlayer removePlayer(String name) {
    ArrayUnorderedList<IPlayer> temp_players = (ArrayUnorderedList<IPlayer>) SimulatePlay.players;
    IPlayer[] players = new IPlayer[temp_players.size()];
    int i = 0;
    for (IPlayer player : temp_players) {
      players[i++] = player;
    }

    Iterator<IPlayer> it = temp_players.iterator();
    while (it.hasNext() && new SortingAndSearching().binarySearch(players, 0, temp_players.size(), name)) {
      IPlayer player = it.next();
      if (name.equals(player.getName())) {
        it.remove();
        return player;
      }
    }
    return null;
  }

// Make menus with the methods above


  //CHANGE TO DO WHILE MENU
  public static void menu() {
    System.out.println("1.Add player 2.Remove player 3.Update player 4.Exit");
    Scanner sc = new Scanner(System.in);
    int option = sc.nextInt();
    switch (option) {
      case 1:
        System.out.println("Insert the name of the player");
        String name = sc.next();
        System.out.println("Insert the team of the player");
        for (TeamType team : TeamType.values()) {
          System.out.println(team+" ");
        }
        String team = sc.next();
        addPlayer(name, team);
        break;
      case 2:
        String name1 = goTroughPlayers(SimulatePlay.players, "Choose the player to remove");
        removePlayer(name1);
        break;
      case 3:
        updatePlayer(SimulatePlay.players);
        break;
      case 4:
        return;
      default:
        System.out.println("Invalid option");
    }
    menu();
  }


}
