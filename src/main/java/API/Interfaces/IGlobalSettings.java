package API.Interfaces;

public interface IGlobalSettings {

  static int calculateLevel(double experiencePoints,double x,double y) {
    return (int) Math.pow(experiencePoints / x, 1/y);
  }

  static int calculateExperiencePoints(int level,double x,double y){
    return (int) Math.pow(level/x, y);
  }

}
