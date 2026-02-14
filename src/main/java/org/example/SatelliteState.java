package org.example;

public class SatelliteState {
    protected boolean isActive;

    public SatelliteState() {
        this.isActive = false;
    }

    public boolean activate(Satellite satellite) {
        EnergySystem energy = satellite.getEnergySystem();

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

}
