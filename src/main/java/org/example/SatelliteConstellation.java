package org.example;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SatelliteConstellation {
    private final String constellationName;
    private final List<Satellite> satellites = new ArrayList<>();

    public SatelliteConstellation() {
        this.constellationName = "";
    }

    public SatelliteConstellation(String constellationName) {
        this.constellationName = constellationName;
    }

    public void addSatellite(Satellite satellite) {
        if (satellite != null && !satellites.contains(satellite)) {
            satellites.add(satellite);
            System.out.println(satellite.getName() + " добавлен в группировку \"" + constellationName + "\"");
        }
    }

    public void executeAllMissions() {
        System.out.println("ВЫПОЛНЕНИЕ МИССИЙ ГРУППИРОВКИ " + constellationName.toUpperCase());
        System.out.println("=".repeat(50));

        for (Satellite satellite : satellites) {
            satellite.performMission();
        }
    }

    public List <Satellite> getSatellites() { return satellites; }

    public String getConstellationName() { return constellationName; }

    @Override
    public String toString() {
        return satellites.stream()
                .map(Satellite::toString)
                .collect(Collectors.joining(",\n", "[", "]"));
    }

}
