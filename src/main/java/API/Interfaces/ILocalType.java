package API.Interfaces;

import API.Enums.LocalType;

public interface ILocalType {
    LocalType getType();

    float getLatitude();

    float getLongitude();

    int getID();

    void setID(int ID);
}
