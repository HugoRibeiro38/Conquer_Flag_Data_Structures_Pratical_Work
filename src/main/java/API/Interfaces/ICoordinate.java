package API.Interfaces;

/**
 * Interface que representa as coordenadas geográficas de um local.
 */
public interface ICoordinate {
  /**
   * Retorna a latitude.
   *
   * @return a latitude
   */
  float getLatitude();

  /**
   * Retorna a longitude.
   *
   * @return a longitude
   */
  float getLongitude();
}
