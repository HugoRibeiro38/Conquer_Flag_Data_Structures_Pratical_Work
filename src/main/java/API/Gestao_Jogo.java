package API;

import API.Graph.PathWithWeight;
import API.Interfaces.ILocalType;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.so.Collections.Arrays.ArrayList;
import com.so.Collections.Arrays.ArrayUnorderedList;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Gestao_Jogo {

  //DO a menu for all private methods
  public static void menu(){
    int option;
    do {
      System.out.println("Choose an option:\n" +
          " 1.Shortest Path 2.Shortest Path having to pass through\n" +
          " 3.Export all Possible Paths 0.Back");
      Scanner sc = new Scanner(System.in);
      option = sc.nextInt();
      switch (option) {
        case 1:
          CaminhoMaisCurto();
          break;
        case 2:
          CaminhoMaisCurto_tendoquepassarpor();
          break;
        case 3:
          exportCaminhos();
          break;
        case 0:
          break;
        default:
          System.out.println("Invalid option");
      }
    } while (option != 0);
  }



  private static void CaminhoMaisCurto() {
    for (ILocalType place : SimulatePlay.graph.getPlaces()) {
      System.out.println(place);
    }


    Scanner sc = new Scanner(System.in);
    System.out.println("Insira o ID do primeiro local");
    int id1 = sc.nextInt();
    System.out.println("Insira o ID do segundo local");
    int id2 = sc.nextInt();

    if (SimulatePlay.graph.getVertex(id1) == null || SimulatePlay.graph.getVertex(id2) == null) {
      System.out.println("Um dos locais não existe");
      return;
    }

    ArrayList<ILocalType> path = SimulatePlay.graph.djistkra(SimulatePlay.graph.getVertex(id1), SimulatePlay.graph.getVertex(id2));
    if (path.isEmpty()) {
      System.out.println("Não existe caminho entre os dois locais");
      return;
    }

    System.out.println("Caminho mais curto: " + SimulatePlay.graph.djistkra(SimulatePlay.graph.getVertex(id1), SimulatePlay.graph.getVertex(id2)));
  }

  private static void CaminhoMaisCurto_tendoquepassarpor() {
    for (ILocalType place : SimulatePlay.graph.getPlaces()) {
      System.out.println(place);
    }
    Scanner sc = new Scanner(System.in);
    System.out.println("Insira o ID do primeiro local");
    int id1 = sc.nextInt();
    System.out.println("Insira o ID do segundo local");
    int id2 = sc.nextInt();

    int option = 0;
    ArrayUnorderedList<ILocalType> places = new ArrayUnorderedList<>();
    do {
      System.out.println("Insira os Ids dos locais que tem que passar pelo caminho (-1 para terminar)");
      option = sc.nextInt();
      if (SimulatePlay.graph.getVertex(option) == null) {
        System.out.println("Local não existe");
        return;
      } else {
        if (option != -1) {
          places.addToRear(SimulatePlay.graph.getVertex(option));
        }
        break;
      }
    } while (option != -1);

    PathWithWeight path = SimulatePlay.graph.findShortestPath_WithPoints(places, SimulatePlay.graph.getVertex(id1), SimulatePlay.graph.getVertex(id2));
    if (path == null) {
      System.out.println("Não existe caminho entre os dois locais");
      return;
    }
    System.out.println(path);
  }

  //Export into a json file the list of shortest paths between all places
  private static void exportCaminhos() {
    JsonArray array = new JsonArray();
    for (PathWithWeight path : SimulatePlay.graph.findAllPaths()) {
      array.add(path.toJson());
    }
    JsonObject object = new JsonObject();
    object.add("PossiblePaths", array);
    try {
      FileWriter file = new FileWriter("CaminhoMaisCurto.json");
      file.write(object.toString());
      file.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
