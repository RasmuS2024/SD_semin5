package seminars.factory;

import seminars.domains.CommunicationSatellite;
import seminars.domains.Satellite;

public class CommunicationSatelliteFactory implements SatelliteFactory {
    private final double defaultBandwidth;

    public CommunicationSatelliteFactory(double defaultBandwidth) {
        this.defaultBandwidth = defaultBandwidth;
    }

    @Override
    public Satellite createSatellite(String name, double batteryLevel) {
        return new CommunicationSatellite(name, batteryLevel, defaultBandwidth);
    }

    @Override
    public Satellite createSatelliteWithParameter(String name, double batteryLevel, double parameter) {
        return new CommunicationSatellite(name, batteryLevel, parameter);
    }
}