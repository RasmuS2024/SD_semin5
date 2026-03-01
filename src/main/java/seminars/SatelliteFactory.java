package seminars;

import seminars.domains.Satellite;

public abstract class SatelliteFactory {
    public abstract Satellite createSatellite(String name, double batteryLevel);
}
