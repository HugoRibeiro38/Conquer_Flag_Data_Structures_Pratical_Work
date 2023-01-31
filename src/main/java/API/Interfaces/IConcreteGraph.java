package API.Interfaces;

import com.so.Collections.Arrays.ArrayList;
import com.so.Collections.Arrays.ArrayUnorderedList;
import com.so.Collections.Map.HashMap;

public interface IConcreteGraph {
  void addRoute(int start, int end);

  void removeRoute(int start, int end);

  void removePlace(int id);

  HashMap<Integer, ArrayUnorderedList<Integer>> getRoutes();

  void addPlace(ILocalType place);

  ArrayList<ILocalType> djistkra(ILocalType start, ILocalType end);

  ArrayList<ILocalType> findPaths(ArrayList<ILocalType> toPass, ILocalType start, ILocalType end);

}
