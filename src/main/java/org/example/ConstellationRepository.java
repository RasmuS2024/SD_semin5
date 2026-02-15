package org.example;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConstellationRepository {
    private final Map<String, SatelliteConstellation> constellations;

    public ConstellationRepository() {
        this.constellations = new HashMap<>();
    }

    public void add(SatelliteConstellation constellation) {
        constellations.put(constellation.getConstellationName(), constellation);
        System.out.println("Сохранена группировка: " + constellation.getConstellationName());
    }

    public SatelliteConstellation getConstellation(String name) {
        SatelliteConstellation constellation = constellations.get(name);
        if (constellation == null) {
            throw new RuntimeException("Группировка не найдена: " + name);
        }
        return constellation;
    }

    public void updateConstellation(String name, SatelliteConstellation updatedConstellation) {
        if (constellations.containsKey(name)) {
            constellations.put(name, updatedConstellation);
        }
        System.out.println("Обновлена группировка: " + name);
    }

    public void deleteConstellation(String name) {
        constellations.remove(name);
        System.out.println("Удалена группировка: " + name);
    }

    public Map<String, SatelliteConstellation> getAllConstellations() {
        return new HashMap<>(constellations);
    }

    public boolean containsConstellation(String name) {
        return constellations.containsKey(name);
    }

    @Override
    public String toString() {
        if (constellations.isEmpty()) {
            return "{}";
        }

        return constellations.entrySet().stream()
                .map(entry -> entry.getKey() + "=" + entry.getValue())
                .collect(Collectors.joining(",\n\n", "{\n", "\n}"));
    }
}