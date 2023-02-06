package API.Graph;

import API.Interfaces.ILocalType;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.so.Collections.ListADT;

public class PathWithWeight implements Comparable<PathWithWeight> {
  @SerializedName("path")
  @Expose
  ListADT<ILocalType> path;
  @SerializedName("weight")
  @Expose
  int weight;

  public PathWithWeight(ListADT<ILocalType> path, int weight) {
    this.path = path;
    this.weight = weight;
  }

  public ListADT<ILocalType> getPath() {
    return this.path;
  }

  public int getWeight() {
    return this.weight;
  }

  public String toString() {
    return "PathWithWeight{path=" + this.path + ", weight=" + this.weight + '}';
  }

  /**
   * Compares this object with the specified object for order.  Returns a
   * negative integer, zero, or a positive integer as this object is less
   * than, equal to, or greater than the specified object.
   *
   * <p>The implementor must ensure
   * {@code sgn(x.compareTo(y)) == -sgn(y.compareTo(x))}
   * for all {@code x} and {@code y}.  (This
   * implies that {@code x.compareTo(y)} must throw an exception iff
   * {@code y.compareTo(x)} throws an exception.)
   *
   * <p>The implementor must also ensure that the relation is transitive:
   * {@code (x.compareTo(y) > 0 && y.compareTo(z) > 0)} implies
   * {@code x.compareTo(z) > 0}.
   *
   * <p>Finally, the implementor must ensure that {@code x.compareTo(y)==0}
   * implies that {@code sgn(x.compareTo(z)) == sgn(y.compareTo(z))}, for
   * all {@code z}.
   *
   * <p>It is strongly recommended, but <i>not</i> strictly required that
   * {@code (x.compareTo(y)==0) == (x.equals(y))}.  Generally speaking, any
   * class that implements the {@code Comparable} interface and violates
   * this condition should clearly indicate this fact.  The recommended
   * language is "Note: this class has a natural ordering that is
   * inconsistent with equals."
   *
   * <p>In the foregoing description, the notation
   * {@code sgn(}<i>expression</i>{@code )} designates the mathematical
   * <i>signum</i> function, which is defined to return one of {@code -1},
   * {@code 0}, or {@code 1} according to whether the value of
   * <i>expression</i> is negative, zero, or positive, respectively.
   *
   * @param o the object to be compared.
   * @return a negative integer, zero, or a positive integer as this object
   * is less than, equal to, or greater than the specified object.
   * @throws NullPointerException if the specified object is null
   * @throws ClassCastException   if the specified object's type prevents it
   *                              from being compared to this object.
   */
  @Override
  public int compareTo(PathWithWeight o) {
    return Integer.compare(this.weight, o.weight);
  }

  public JsonObject toJson() {
    Gson gson = new Gson();
    JsonObject jsonObject = new JsonObject();
    JsonArray pathArray = new JsonArray();
    for (ILocalType localType : path) {
      JsonElement jsonElement = gson.toJsonTree(localType);
      pathArray.add(jsonElement);
    }
    jsonObject.add("path", pathArray);
    jsonObject.addProperty("weight", weight);
    return jsonObject;
  }
}
