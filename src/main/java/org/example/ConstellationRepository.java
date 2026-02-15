package org.example;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

@Service
public class ConstellationRepository {
    private final Map<String, SatelliteConstellation> constellations;

    public ConstellationRepository() {
        this.constellations = new HashMap<>();
    }

    public void add(SatelliteConstellation constellation) {
        constellations.put(constellation.getConstellationName(), constellation);
    }

    public SatelliteConstellation get(String name) {
        return constellations.get(name);
    }

    public void update(String name, SatelliteConstellation updatedConstellation) {
        if (constellations.containsKey(name)) {
            constellations.put(name, updatedConstellation);
        }
    }

    public void delete(String name) {
        constellations.remove(name);
    }

    public List<SatelliteConstellation> getAll() {
        return new ArrayList<>(constellations.values());
    }
}