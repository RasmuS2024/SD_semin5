package org.example;

import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class SpaceOperationCenterService {
    protected ConstellationRepository constellationRepository;

    public SpaceOperationCenterService(ConstellationRepository constellationRepository) {
        this.constellationRepository = constellationRepository;
    }

    public void createAndSaveConstellation(String name) {
        this.constellationRepository.add(new SatelliteConstellation(name));
        System.out.println("Создана спутниковая группировка: " + name);
        System.out.println("Сохранена группировка: " + name);
    }

    public void addSatelliteToConstellation(String constellationName, Satellite satellite) {
        SatelliteConstellation constellation = this.constellationRepository.get(constellationName);
        constellation.addSatellite(satellite);
    }

    public void executeConstellationMission(String constellationName) {
        SatelliteConstellation constellation = this.constellationRepository.get(constellationName);
        constellation.executeAllMissions();
    }

    public void activateAllSatellites(String constellationName) {
        SatelliteConstellation constellation = this.constellationRepository.get(constellationName);
        constellation.activteAllSatellites();
    }

    public void showConstellationStatus(String constellationName) {
        System.out.println("СТАТУС ГРУППИРОВКИ: " + constellationName);
        System.out.println(constellationRepository.get(constellationName));
    }
/*
    @Override
    public String toString() {
        return constellationRepository.constellations.stream()
                .map(Satellite::toString)
                .collect(Collectors.joining(",\n", "[", "]"));
    }*/
}
