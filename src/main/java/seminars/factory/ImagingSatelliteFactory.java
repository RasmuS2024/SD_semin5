package seminars.factory;

import seminars.domains.ImagingSatellite;
import seminars.domains.Satellite;

public class ImagingSatelliteFactory implements SatelliteFactory {
    private final double defaultResolution;

    public ImagingSatelliteFactory(double defaultResolution) {
        this.defaultResolution = defaultResolution;
    }

    @Override
    public Satellite createSatellite(String name, double batteryLevel) {
        return new ImagingSatellite(name, batteryLevel, defaultResolution);
    }

    @Override
    public Satellite createSatelliteWithParameter(String name, double batteryLevel, double parameter) {
        return new ImagingSatellite(name, batteryLevel, parameter);
    }
}