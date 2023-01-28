package Portal;

import API.Enums.LocalType;
import API.Enums.TeamType;
import API.Interfaces.IPortal;
import API.Portal;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PortalTest {

    /**
     *
     */
    @Test
    public void PortalTestT01() {
        IPortal portal = new Portal("Felgueiras", 44.5f, 44.7f);
        assertEquals(TeamType.NEUTRAL, portal.getStatus());
        assertEquals(null, portal.getPlayer());
        assertEquals("Felgueiras", portal.getName());
        assertEquals(LocalType.PORTAL, portal.getType());
        assertEquals(44.5f, portal.getLatitude());
        assertEquals(44.7f, portal.getLongitude()); // XD
    }
}
