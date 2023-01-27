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
     * Construtor com um ID único para cada instância de Player
     */
    public Local(float latitude, float longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.ID = nextID++;
        
    }

    /*
    private void ConvertLocalType(String localType) {
        switch (localType) {
            case "Connector":
                this.localType = LocalType.CONNECTOR;
                break;
            case "Portal":
                this.localType = LocalType.PORTAL;
        }
    }

     */

    @Override
    public int getID() {
        return this.ID;
    }

    @Override
    public void setID(int ID) {
        this.ID = ID;
    }

    @Override
    public LocalType getType() {
        return this.localType;
    }

    @Override
    public float getLatitude() {
        return this.latitude;
    }

    @Override
    public float getLongitude() {
        return this.longitude;
    }
}
