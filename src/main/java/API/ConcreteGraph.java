package API;

import API.Interfaces.ILocalType;
import API.Interfaces.IPortal;
import com.so.Collections.Arrays.ArrayList;
import com.so.Collections.Arrays.ArrayUnorderedList;
import com.so.Collections.Graphs.Graph;
import com.so.Collections.Map.HashMap;
import com.so.Collections.Queues.LinkedQueue;
import com.so.Collections.Queues.QueueADT;

import java.util.Iterator;


public class ConcreteGraph extends Graph<ILocalType> {

  public HashMap<Integer, ArrayUnorderedList<Integer>> getRoutes() {
    return routes;
  }

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
   */
  public void addRoute(int start, int end) {
    if (start == end || super.isEmpty()) return;

    // Dont allow to Portals to be connected to themselves
    if (getVertex(start) instanceof IPortal && getVertex(end) instanceof IPortal) {
      return;
    }

    int startIndex = super.getIndex(getVertex(start));
    int endIndex = super.getIndex(getVertex(end));
    if (!super.adjMatrix[startIndex][endIndex]) {
      super.addEdge(startIndex, endIndex);
      if (!routes.isEmpty() && routes.containsKey(start)) {
        routes.get(start).addToRear(end);
      } else {
        ArrayUnorderedList<Integer> list = new ArrayUnorderedList<>();
        list.addToRear(end);
        routes.put(start, list);
      }
    }


  }


  /**
   * Removes a route "edge" from the arraylist routes.
   * First value of the route is the key of the hashmap.
   * Second value of the route is the value to be added to the arraylist.
   *
   * @param start the start vertex
   * @param end   the end vertex
   */
  public void removeRoute(int start, int end) {
    if (start == end || super.isEmpty()) return;

    int startIndex = super.getIndex(getVertex(start));
    int endIndex = super.getIndex(getVertex(end));

    if (startIndex == -1 || endIndex == -1) return;

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

      if (super.adjMatrix[index][index == startIndex ? endIndex : startIndex]) {
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
  }


  /**
   * Inserts a vertex to the graph.
   *
   * @param vertex vertex to be inserted.
   */
  public void insertVertex(ILocalType vertex) {
    super.addVertex(vertex);
  }

  private ILocalType getVertex(int id) {
    VerticesIterator iterator = new VerticesIterator(vertices);
    while (iterator.hasNext()) {
      ILocalType vertex = (ILocalType) iterator.next();
      if (vertex.getID() == id) return vertex;
    }
    return null;
  }

  public void removePlace(int id) {
    ILocalType vertex = getVertex(id);
    if (vertex != null) {
      super.removeVertex(vertex);
      routes.remove(id);
    }
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


}


