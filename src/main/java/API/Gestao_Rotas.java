package API;

import com.google.gson.*;
import com.so.Collections.Arrays.ArrayUnorderedList;
import com.so.Collections.Map.HashMap;

import java.io.*;
import java.util.Scanner;

public class Gestao_Rotas {
  //Menu que permite ao utilizador criar e remover rotas
  public static void menu() {
    int opcao = 0;
    Scanner sc = new Scanner(System.in);
    do {
      System.out.println("1 - Criar Rota");
      System.out.println("2 - Remover Rota");
      System.out.println("3 - Exportar Rotas");
      System.out.println("0 - Voltar");
      opcao = sc.nextInt();
      switch (opcao) {
        case 1:
          criarRota();
          break;
        case 2:
          removerRota();
          break;
        case 3:
          exportarRotas();
          break;
        case 0:
          break;
        default:
          System.out.println("Opção inválida");
      }
    } while (opcao != 0);
  }


  //Função que permite ao utilizador criar uma rota
  public static void criarRota() {
    Scanner sc = new Scanner(System.in);
    System.out.println("Insira o ID do primeiro local");
    int id1 = sc.nextInt();
    System.out.println("Insira o ID do segundo local");
    int id2 = sc.nextInt();
    if (SimulatePlay.graph.addRoute(id1, id2)) {
      System.out.println("Rota criada com sucesso");
    } else {
      System.out.println("Não foi possível criar a rota");
    }
  }

  //Função que permite ao utilizador remover uma rota
  public static void removerRota() {
    Scanner sc = new Scanner(System.in);
    System.out.println("Insira o ID do primeiro local");
    int id1 = sc.nextInt();
    System.out.println("Insira o ID do segundo local");
    int id2 = sc.nextInt();
    if (SimulatePlay.graph.removeRoute(id1, id2)) {
      System.out.println("Rota removida com sucesso");
    } else {
      System.out.println("Não foi possível remover a rota");
    }
  }

  //Função que permite ao utilizador exportar as rotas para um ficheiro
  public static void exportarRotas() {
    Gson gson = new Gson();
    HashMap<Integer, ArrayUnorderedList<Integer>> routes = SimulatePlay.graph.getRoutes();
    String json = "{\"routes\":[";

    for (Integer from : routes.keySet()) {
      ArrayUnorderedList<Integer> toNodes = routes.get(from);
      for (Integer to : toNodes) {
        json += "{\"from\":" + from + ",\"to\":" + to + "},";
      }
    }

    json = json.substring(0, json.length() - 1) + "]}";

    try {
      JsonParser parser = new JsonParser();
      JsonObject playersJson = null;

      File file = new File("game.json");
      if (file.exists()) {
        JsonElement jsonElement = parser.parse(new FileReader("game.json"));
        playersJson = jsonElement.getAsJsonObject();
      } else {
        playersJson = new JsonObject();
      }

      JsonElement routesElement = parser.parse(json);
      playersJson.add("routes", routesElement.getAsJsonObject().get("routes"));

      FileWriter fileWriter = new FileWriter("game.json");
      fileWriter.write(playersJson.toString());
      fileWriter.close();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }


  public static void importarRotas() {
    if (SimulatePlay.graph.isEmpty()) {
      System.out.println("Não existem locais para criar rotas");
      System.out.println("Por favor crie/importe locais primeiro");
      return;
    }

    Gson gson = new Gson();
    JsonObject jsonData = new JsonObject();

    try {
      JsonParser parser = new JsonParser();
      JsonObject playersJson = parser.parse(new FileReader("game.json")).getAsJsonObject();

      JsonArray routes = playersJson.getAsJsonArray("routes");
      for (JsonElement route : routes) {
        JsonObject routeObject = route.getAsJsonObject();
        int from = routeObject.get("from").getAsInt();
        int to = routeObject.get("to").getAsInt();
        SimulatePlay.graph.addRoute(from, to);
      }

    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    }
  }


}
