package API;

import API.Enums.LocalType;
import API.Interfaces.IPortal;

public class Portal extends Local implements IPortal {

    private String name;
    private String status;
    private Player OwnPlayer;

    public Portal(String name, float latitude, float longitude, int ID, int energy, String localType) {
        super(latitude, longitude, energy);
        this.name = name;
        PortalTypeCheck(localType);
        this.status = "Neutral";
        this.OwnPlayer = null;
    }



   /* public Portal(String name, float latitude, float longitude, int ID, int energy, String localType, String status) {
        super(name, latitude, longitude, ID, energy, localType);
        StatusCheck(status);
    }
    */

    private void PortalTypeCheck(String localType) {
        switch (localType){
            case "Portal":
                super.localType = LocalType.PORTAL;
        }
    }

    /*
    private void StatusCheck(String status) {
        switch (status) {
            case "Neutral":
                this.status = "Neutral";
                this.OwnPlayer = null;
                System.out.println("This Local is empty");
                break;

            case "Owned":
                this.status = "Owned";
                System.out.println("This Local dominated by" + OwnPlayer.getTeam() + "conquered by" + OwnPlayer.getName());
        }
    }
     */

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public LocalType getType() {
        return super.localType;
    }

    @Override
    public float getLatitude() {
        return super.latitude;
    }

    @Override
    public float getLongitude() {
        return super.longitude;
    }

    @Override
    public String getStatus() {
        return this.status;
    }

    @Override
    public Player getPlayer() {
        return this.OwnPlayer;
    }
}
