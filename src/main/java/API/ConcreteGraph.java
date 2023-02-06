package API;

import API.Graph.PathWithWeight;
import API.Interfaces.IConcreteGraph;
import API.Interfaces.IGlobalSettings;
import API.Interfaces.ILocalType;
import API.Interfaces.IPortal;
import com.so.Collections.Arrays.ArrayList;
import com.so.Collections.Arrays.ArrayOrderedList;
import com.so.Collections.Arrays.ArrayUnorderedList;
import com.so.Collections.Arrays.SortingAndSearching;
import com.so.Collections.Graphs.WGraph;
import com.so.Collections.ListADT;
import com.so.Collections.Lists.DoubleUnorderedList;
import com.so.Collections.Map.HashMap;
import com.so.Collections.Queues.LinkedQueue;
import com.so.Collections.Queues.QueueADT;

import java.util.Iterator;


public class ConcreteGraph extends WGraph<ILocalType> implements IConcreteGraph {


  private final HashMap<Integer, ArrayUnorderedList<Integer>> routes;

  public ConcreteGraph() {
    super();
    routes = new HashMap<>();
  }

  /**
   * Adds a route "edge" to the arraylist routes.
   *
   * @param start the start vertex
   * @param end   the end vertex
   * @return
   */
  @Override
  public boolean addRoute(int start, int end) {
    if (start == end || super.isEmpty()) return false;

    // Dont allow to Portals to be connected to themselves
    if (getVertex(start) instanceof IPortal && getVertex(end) instanceof IPortal) {
      return false;
    }

    int startIndex = super.getIndex(getVertex(start));
    int endIndex = super.getIndex(getVertex(end));
    if ((startIndex != -1 && endIndex != -1) && super.adjMatrix[startIndex][endIndex] == Integer.MAX_VALUE) {
      super.addEdge(startIndex, endIndex, IGlobalSettings.calculateEdgeWeight(getVertex(start).getCoordinates(), getVertex(end).getCoordinates()));
      if (!routes.isEmpty() && routes.containsKey(start)) {
        routes.get(start).addToRear(end);
      } else {
        ArrayUnorderedList<Integer> list = new ArrayUnorderedList<>();
        list.addToRear(end);
        routes.put(start, list);
      }
      return true;
    }

    return false;
  }


  /**
   * Removes a route "edge" from the arraylist routes.
   * First value of the route is the key of the hashmap.
   * Second value of the route is the value to be added to the arraylist.
   *
   * @param start the start vertex
   * @param end   the end vertex
   * @return
   */
  @Override
  public boolean removeRoute(int start, int end) {
    if (start == end || super.isEmpty()) return false;

    int startIndex = super.getIndex(getVertex(start));
    int endIndex = super.getIndex(getVertex(end));

    if (startIndex == -1 || endIndex == -1) return false;

    QueueADT<Integer> indices = new LinkedQueue<>() {{
      enqueue(startIndex);
      enqueue(endIndex);
    }};
    QueueADT<Integer> keys = new LinkedQueue<>() {{
      enqueue(start);
      enqueue(end);
    }};

    while (!indices.isEmpty()) {
      int index = indices.dequeue();
      int key = keys.dequeue();

      if (super.adjMatrix[index][index == startIndex ? endIndex : startIndex] != Integer.MAX_VALUE) {
        if (routes.containsKey(key)) {
          ArrayList<Integer> list = routes.get(key);
          list.remove(key == start ? end : start);
          if (list.isEmpty()) {
            routes.remove(key);
          }
          super.removeEdge(index, index == startIndex ? endIndex : startIndex);
        }
      }
    }
    return true;
  }


  /**
   * Based on the ID of the ILocalType, returns the index of the vertex in the graph.
   *
   * @param id the ID of the ILocalType
   * @return Object representing the ILocalType
   */
  public ILocalType getVertex(int id) {
    VerticesIterator iterator = new VerticesIterator(vertices);
    while (iterator.hasNext()) {
      ILocalType vertex = (ILocalType) iterator.next();
      if (vertex.getID() == id) return vertex;
    }
    return null;
  }

  @Override
  public void addPlace(ILocalType place) {
    super.addVertex(place);
  }

  /**
   * Removes a vertex from the graph.
   *
   * @param id ID of the place to be removed.
   */
  @Override
  public void removePlace(int id) {
    ILocalType vertex = getVertex(id);
    if (vertex != null) {
      super.removeVertex(vertex);
    }
  }


  @Override
  public ArrayList<ILocalType> djistkra(ILocalType start, ILocalType end) {
    return super.djisktra(start, end);
  }


  private ArrayList<PathWithWeight> findPaths(ArrayList<ILocalType> toPass, ILocalType start, ILocalType end) {
    ArrayOrderedList<PathWithWeight> validPaths = new ArrayOrderedList();
    Iterator var6 = this.findAllPathsWithWeight(start, end).iterator();

    while (var6.hasNext()) {
      PathWithWeight pathWithWeight = (PathWithWeight) var6.next();
      ArrayUnorderedList<ILocalType> path = (ArrayUnorderedList) pathWithWeight.getPath();
      boolean isValid = true;
      Iterator var10 = toPass.iterator();

      while (var10.hasNext()) {
        ILocalType typeToPass = (ILocalType) var10.next();
        if (!path.contains(typeToPass)) {
          isValid = false;
          break;
        }
      }

      if (isValid) {
        validPaths.add(pathWithWeight);
      }
    }

    return validPaths;
  }

  public PathWithWeight findShortestPath_WithPoints(ArrayList<ILocalType> toPass, ILocalType start, ILocalType end) {
    return (PathWithWeight) this.findPaths(toPass, start, end).first();
  }

  private ListADT<PathWithWeight> findAllPathsWithWeight(ILocalType startVertex, ILocalType endVertex) {
    int startIndex = this.getIndex(startVertex);
    int endIndex = this.getIndex(endVertex);
    boolean[] isVisited = new boolean[this.numVertices];
    ArrayList<ILocalType> currentPath = new ArrayUnorderedList();
    ListADT<PathWithWeight> allPaths = new ArrayUnorderedList();
    this.findAllPathsWithWeight(startIndex, endIndex, isVisited, currentPath, allPaths, 0);

    PathWithWeight[] paths = new PathWithWeight[allPaths.size()];
    int i = 0;
    for (int size = allPaths.size(); i < size; ++i) {
      paths[i] = allPaths.removeFirst();
    }
    SortingAndSearching.mergeSort(paths, 0, paths.length - 1);
    for (PathWithWeight path : paths)
      ((ArrayUnorderedList) allPaths).addToRear(path);

    return allPaths;
  }

  ILocalType getVertex_Pos(int pos) {
    int i = 0;

    for (VerticesIterator it = new VerticesIterator(this.vertices); it.hasNext(); ++i) {
      ILocalType localType = (ILocalType) it.next();
      if (pos == i) {
        return localType;
      }
    }

    return null;
  }

  private void findAllPathsWithWeight(int currentIndex, int endIndex, boolean[] isVisited, ListADT<ILocalType> currentPath, ListADT<PathWithWeight> allPaths, int weight) {
    // System.out.println("INDEX" + currentIndex + " VERTEX" + this.getVertex_Pos(currentIndex));
    ((ArrayUnorderedList) currentPath).addToRear(this.getVertex_Pos(currentIndex));
    isVisited[currentIndex] = true;
    if (currentIndex == endIndex) {
      ArrayUnorderedList<ILocalType> copyPath = new ArrayUnorderedList();
      Iterator<ILocalType> it = currentPath.iterator();

      while (it.hasNext()) {
        copyPath.addToRear((ILocalType) it.next());
      }

      ((ArrayUnorderedList) allPaths).addToRear(new PathWithWeight(copyPath, weight));
    } else {
      for (int i = 0; i < this.numVertices; ++i) {
        if (this.adjMatrix[currentIndex][i] != Integer.MAX_VALUE && !isVisited[i]) {
          this.findAllPathsWithWeight(i, endIndex, isVisited, currentPath, allPaths, weight + this.adjMatrix[currentIndex][i]);
        }
      }
    }

    currentPath.removeLast();
    isVisited[currentIndex] = false;
  }


  private static class VerticesIterator<T> implements Iterator<T> {
    private final T[] vertices;
    private int currentIndex = 0;

    public VerticesIterator(T[] vertices) {
      this.vertices = vertices;
    }

    @Override
    public boolean hasNext() {
      return currentIndex < vertices.length && vertices[currentIndex] != null;
    }

    @Override
    public T next() {
      return vertices[currentIndex++];
    }
  }

  public HashMap<Integer, ArrayUnorderedList<Integer>> getRoutes() {
    return routes;
  }

  public ArrayList<ILocalType> getPlaces() {
    ArrayUnorderedList<ILocalType> places = new ArrayUnorderedList<>();

    VerticesIterator iterator = new VerticesIterator(vertices);
    while (iterator.hasNext()) {
      ILocalType vertex = (ILocalType) iterator.next();
      places.addToRear(vertex);
    }
    return places;
  }

  public ListADT<PathWithWeight> findAllPaths() {
    DoubleUnorderedList<PathWithWeight> allPathsForAllPlaces = new DoubleUnorderedList<>();
    for (int i = 0; i < this.numVertices; i++) {
      for (int j = 0; j < this.numVertices; j++) {
        if (i != j) {
          allPathsForAllPlaces.addToRear(this.findAllPathsWithWeight(this.getVertex_Pos(i), this.getVertex_Pos(j)).first());
        }
      }
    }
    return allPathsForAllPlaces;
  }

  public void displayPlaces(ILocalType vertex) {
    int index = this.getIndex(vertex);
    System.out.println("Places to visit from " + vertex.getID() + ":");
    for (int i = 0; i < this.numVertices; i++) {
      if (this.adjMatrix[index][i] != Integer.MAX_VALUE) {
        System.out.println(this.getVertex_Pos(i));
      }
    }
  }

}


