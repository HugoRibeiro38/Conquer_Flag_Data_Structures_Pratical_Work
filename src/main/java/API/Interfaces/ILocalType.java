package API.Interfaces;

import API.Enums.LocalType;

public interface ILocalType {
  LocalType getType();

  ICoordinate getCoordinates();

  int getID();

  void setID(int ID);
}
