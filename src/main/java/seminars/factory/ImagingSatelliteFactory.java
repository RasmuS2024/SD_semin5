package seminars.factory;

import org.springframework.stereotype.Component;
import seminars.domains.ImagingSatellite;
import seminars.domains.Satellite;

@Component
public class ImagingSatelliteFactory implements SatelliteFactory {

    private static final double DEFAULT_RESOLUTION = 100.0;

    @Override
    public Satellite createSatellite(String name, double batteryLevel) {
        return new ImagingSatellite(name, batteryLevel, DEFAULT_RESOLUTION);
    }

    @Override
    public Satellite createSatelliteWithParameter(String name, double batteryLevel, double parameter) {
        return new ImagingSatellite(name, batteryLevel, parameter);
    }
}