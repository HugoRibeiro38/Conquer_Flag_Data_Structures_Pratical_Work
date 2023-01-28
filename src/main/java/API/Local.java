package API;

import API.Enums.LocalType;
import API.Interfaces.ILocalType;

public class Local implements ILocalType {
    protected float latitude;
    protected float longitude;
    protected static int nextID = 1;
    protected int ID;
    protected LocalType localType;

    /**
     * Construtor para uma instância do tipo Local, onde o ID é predefinido e incremental pelo IDE.
     *
     *
     * @param latitude Latitude de um local
     * @param longitude Longitude de um local
     */
    public Local(float latitude, float longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.ID = nextID++;
    }


    /**
     *Getter para um ID de um local.
     *
     * @return ID do local.
     */
    @Override
    public int getID() {
        return this.ID;
    }

    /**
     * setter para o ID do local.
     *
     * @param ID
     */
    @Override
    public void setID(int ID) {
        this.ID = ID;
    }

    /**
     *Getter para o tipo de local.
     *
     * @return tipo de local.
     */
    @Override
    public LocalType getType() {
        return this.localType;
    }

    /**
     *Getter para a latitude de um local.
     *
     * @return latitude do local.
     */
    @Override
    public float getLatitude() {
        return this.latitude;
    }

    /**
     *Getter para a Longitude de um Local
     *
     * @return longitude de um local.
     */
    @Override
    public float getLongitude() {
        return this.longitude;
    }
}
