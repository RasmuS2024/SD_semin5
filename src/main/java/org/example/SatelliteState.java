package org.example;

import java.util.stream.Collectors;

public class SatelliteState {
    protected boolean isActive;

    public SatelliteState() {
        this.isActive = false;
    }

    public boolean activate(Satellite satellite) {
        EnergySystem energy = satellite.getEnergySystem();

        if (isActive) {
            System.out.println("✅ " + satellite.getName() + ": уже активен (заряд: " + energy.batteryLevelToPercent() + "%)");
            return true;
        }

        if (energy.getBatteryLevel() > energy.getMinimumBatteryLevel() && !isActive) {
            isActive = true;
            System.out.println("✅ " + satellite.getName() + ": Активация успешна");
            return true;
        }

        System.out.println("❌ " + satellite.getName() + ": Ошибка активации (заряд: " + energy.batteryLevelToPercent() + "%)");
        return false;
    }

    public boolean isActive() {
        return isActive;
    }

    public void deActivate(Satellite satellite) {
        if (isActive) {
            isActive = false;
            System.out.println("Спутник " + satellite.getName() + " деактивирован");
        }
    }

    @Override
    public String toString() {
        return "SatelliteState{isActive=" +
                isActive + ", statusMessage='" +
                (isActive ? "Активен" : "Не активирован") + "'}";
    }

}
