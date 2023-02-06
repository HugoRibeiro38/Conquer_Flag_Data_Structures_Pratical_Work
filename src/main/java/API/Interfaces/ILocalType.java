package API.Interfaces;

import API.Enums.LocalType;

/**
 * Interface que representa o tipo de local.
 */
public interface ILocalType {

  /**
   * Retorna o tipo de local.
   *
   * @return o tipo de local
   */
  LocalType getType();

  /**
   * Retorna as coordenadas do local.
   *
   * @return as coordenadas do local
   */
  ICoordinate getCoordinates();

  /**
   * Retorna o ID do local.
   *
   * @return o ID do local
   */
  int getID();

  /**
   * Define o ID do local.
   *
   * @param ID o ID a ser definido
   */
  void setID(int ID);

  void menu(IPlayer player);
}
