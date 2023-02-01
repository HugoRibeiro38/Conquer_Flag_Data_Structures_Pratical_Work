package API;

import API.Interfaces.IPlayer;
import com.so.Collections.Arrays.ArrayUnorderedList;
import com.so.Collections.ListADT;

//TODO:Make Missing Menu that allows players to "play"
public class SimulatePlay {
  static ListADT<IPlayer> players = new ArrayUnorderedList<>();
  static int time = 0;

  public static int getTime() {
    return time;
  }

  public void incrementTime() {
    time++;
  }
}
