package API.Interfaces;

import API.Local;

public interface IGlobalSettings {

  static int calculateLevel(double experiencePoints, double x, double y) {
    return (int) Math.pow(experiencePoints / x, 1 / y);
  }

  static int calculateExperiencePoints(int level, double x, double y) {
    return (int) Math.pow(level / x, y);
  }


  static int calculateEdgeWeight(ICoordinate start, ICoordinate end) {
    double x = Math.pow((end.getLongitude() - start.getLongitude()), 2);
    double y = Math.pow(end.getLatitude() - start.getLatitude(), 2);
    return (int) Math.sqrt(x + y);
  }

}
