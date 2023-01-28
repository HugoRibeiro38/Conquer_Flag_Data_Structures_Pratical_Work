package API;

import API.Interfaces.IGlobalSettings;

public class GlobalSettings implements IGlobalSettings {

    @Override
    public int calculateLevel(double experiencePoints, double x, double y) {
        return 0;
    }

    @Override
    public int calculateExperiencePoints(int level) {
        return 0;
    }
}
