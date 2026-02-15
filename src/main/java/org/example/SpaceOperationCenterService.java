package org.example;

import org.springframework.stereotype.Service;

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

    }

    public void activateAllSatellites(String constellationName) {

    }

    public void showConstellationStatus(String constellationName) {
        System.out.println(constellationRepository.get(constellationName));
    }
}
