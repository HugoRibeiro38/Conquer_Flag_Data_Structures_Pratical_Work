package API;

import API.Abstractions.AGameSettings;
import API.Enums.LocalType;
import API.Enums.TeamType;
import API.Interfaces.IConnector;
import API.Interfaces.ILocalType;
import API.Interfaces.IPortal;
import com.google.gson.*;
import com.so.Collections.Arrays.SortingAndSearching;

import java.io.*;
import java.util.Scanner;

public class Gestao_Local {

  //Make menu that allows the user to add a new local, edit and remove a local
  public static void menu() {
    int option;
    do {
      System.out.println("Choose an option:\n" +
          " 1.Add 2.Edit 3.Remove 4.List 5.Export 6.Import 0.Back");
      Scanner sc = new Scanner(System.in);
      option = sc.nextInt();
      switch (option) {
        case 1:
          addLocal();
          break;
        case 2:
          editLocal();
          break;
        case 3:
          removeLocal();
          break;
        case 4:
          listLocals();
          break;
        case 5:
          exportLocals();
          break;
        case 6:
          importLocals();
          break;
        case 0:
          break;
        default:
          System.out.println("Invalid option");
      }
    } while (option != 0);
  }


  //Make menu that allows the user to add a new local
  private static void addLocal() {

    //Longitudinal and latitudinal coordinates

    Scanner sc = new Scanner(System.in);
    System.out.println("Insert the type of the local");
    for (LocalType type : LocalType.values()) {
      System.out.println(type.ordinal() + " " + type);
    }
    int type = sc.nextInt();
    System.out.println("Longitudinal and latitudinal coordinates");
    System.out.println("Longitude");
    float longitude = sc.nextFloat();
    System.out.println("Latitude");
    float latitude = sc.nextFloat();

    //Only add local if the integer is in range
    if (type < 0 || type > LocalType.values().length) {
      System.out.println("Invalid type");
      return;
    }

    switch (LocalType.values()[type]) {
      case CONNECTOR:
        System.out.println("Insert the energy of the connector");
        int energy = sc.nextInt();
        System.out.println("Insert the cooldown timer of the connector");
        int cooldownTimer = sc.nextInt();
        SimulatePlay.graph.addPlace(new Connector(latitude, longitude, energy, cooldownTimer));
        break;
      case PORTAL:
        System.out.println("Insert the name of the portal");
        String name = sc.next();
        SimulatePlay.graph.addPlace(new Portal(name, latitude, longitude));
        break;
      default:
        System.out.println("Invalid type");
    }
  }

  //Make menu that allows the user to edit a local
  private static void editLocal() {
    for (ILocalType place : SimulatePlay.graph.getPlaces()) {
      System.out.println(place.toString());
    }
    System.out.println("Insert the index of the local to edit");
    Scanner sc = new Scanner(System.in);
    int id = sc.nextInt();
    ILocalType place = SimulatePlay.graph.getVertex(id);
    if (place == null) {
      System.out.println("Invalid id");
      return;
    }

    switch (place.getType()) {
      case CONNECTOR:
        System.out.println("Insert the energy of the connector");
        int energy = sc.nextInt();
        System.out.println("Insert the cooldown timer of the connector");
        int cooldownTimer = sc.nextInt();
        ((Connector) place).getGameSettings().setEnergy(energy);
        ((Connector) place).getGameSettings().setCooldownTimer(cooldownTimer);
        break;
      case PORTAL:
        System.out.println("Insert the name of the portal");
        String name = sc.next();
        ((Portal) place).setName(name);
        break;
      default:
        System.out.println("Invalid type");
    }
  }


  //Make menu that allows the user to remove a local
  private static void removeLocal() {
    for (ILocalType place : SimulatePlay.graph.getPlaces()) {
      System.out.println(place.toString());
    }
    System.out.println("Insert the index of the local to remove");
    Scanner sc = new Scanner(System.in);
    int id = sc.nextInt();
    SimulatePlay.graph.removePlace(id);
  }

  //Make a menu that list all the locals,via certain criteria
  public static void listLocals() {
    //Add a option that allows the user to list Captures Portal
    System.out.println("Choose an option: 1.List all 2.List by type 3.List Portal by name 4.List Captured Portals");
    Scanner sc = new Scanner(System.in);
    int option = sc.nextInt();
    switch (option) {
      case 1:
        for (ILocalType place : SimulatePlay.graph.getPlaces()) {
          System.out.println(place.toString());
        }
        break;
      case 2:
        System.out.println("Insert the type of the local");
        for (LocalType type : LocalType.values()) {
          System.out.println(type.ordinal() + " " + type);
        }
        int type = sc.nextInt();
        if (type < 0 || type > LocalType.values().length) {
          System.out.println("Invalid type");
          return;
        }
        for (ILocalType place : SimulatePlay.graph.getPlaces()) {
          if (place.getType() == LocalType.values()[type]) {
            System.out.println(place.toString());
          }
        }
        break;
      case 3:
        System.out.println("Insert the name of the local");
        String name = sc.next();
        for (ILocalType place : SimulatePlay.graph.getPlaces()) {
          if (place instanceof IPortal && ((IPortal) place).getName().equals(name)) {
            System.out.println(place.toString());
          }
        }
        break;

      case 4:
        Portal_Sort_byTeamType[] capturedPortals = new Portal_Sort_byTeamType[SimulatePlay.graph.getPlaces().size()];
        int i = 0;
        for (ILocalType place : SimulatePlay.graph.getPlaces()) {
          if (place instanceof IPortal && ((IPortal) place).getStatus() != TeamType.NEUTRAL) {
            capturedPortals[i++] = (Portal_Sort_byTeamType) place;
          }
        }
        SortingAndSearching.insertionSort(capturedPortals);
        for (Portal_Sort_byTeamType place : capturedPortals) {
          if (place != null) {
            System.out.println(place);
          }
        }

        break;

      default:
        System.out.println("Invalid option");
    }
  }

  private static void exportLocals() {

    if (SimulatePlay.graph.isEmpty()) {
      System.out.println("There are no locals to export");
      return;
    }

    Gson gson = new Gson();

    String json = "{\"locals\":[";
    for (ILocalType place : SimulatePlay.graph.getPlaces()) {
      json += gson.toJson(place) + ",";
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

      JsonElement localElement = parser.parse(json);
      playersJson.add("locals", localElement.getAsJsonObject().get("locals"));

      FileWriter fileWriter = new FileWriter("game.json");
      fileWriter.write(playersJson.toString());
      fileWriter.close();
      System.out.println("Locals exported");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  protected static void importLocals() {

    if (!SimulatePlay.graph.isEmpty()) {
      System.out.println("There are already locals in the program");
      return;
    }

    Gson gson = new GsonBuilder()
        .registerTypeAdapter(AGameSettings.class, new GameSettingsDeserializer())
        .create();

    try {
      JsonParser parser = new JsonParser();
      JsonObject playersJson = parser.parse(new FileReader("game.json")).getAsJsonObject();

      JsonArray locals = playersJson.getAsJsonArray("locals");
      for (JsonElement route : locals) {
        JsonObject routeObject = route.getAsJsonObject();
        String type = routeObject.get("localType").getAsString();

        switch (type) {
          case "PORTAL":
            IPortal portal = gson.fromJson(routeObject, Portal.class);
            SimulatePlay.graph.addPlace(portal);
            break;
          case "CONNECTOR":
            IConnector connector = gson.fromJson(routeObject, Connector.class);
            SimulatePlay.graph.addPlace(connector);
            break;
          default:
            System.out.println("Invalid type");
        }
      }
      System.out.println("Locals imported " + SimulatePlay.graph.getPlaces().size());
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    }
  }


}
