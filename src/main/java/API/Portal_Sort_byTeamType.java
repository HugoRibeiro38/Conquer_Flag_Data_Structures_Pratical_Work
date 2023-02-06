package API;

import API.Interfaces.IPortal;

public class Portal_Sort_byTeamType extends Portal implements Comparable<IPortal> {
  public Portal_Sort_byTeamType(String name, float latitute, float longitude) {
    super(name, latitute, longitude);
  }

  //Sort by TeamType
  @Override
  public int compareTo(IPortal o) {
    return this.getStatus().compareTo(o.getStatus());
  }
}

