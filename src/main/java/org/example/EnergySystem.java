package org.example;

public class EnergySystem {
    protected double batteryLevel;
    private static final double MINIMUM_BATTERY_LEVEL = 0.15;
    private EnergyListener listener;

    public interface EnergyListener {
        void onLowBattery();
    }

    public void setListener(EnergyListener listener) {
        this.listener = listener;
    }

    public EnergySystem (double batteryLevel) {
        this.batteryLevel = batteryLevel;
    }

    public double getBatteryLevel() {
        return batteryLevel;
    }

    public void consume(double amount) {
        if (amount <= 0) return;

        double oldLevel = batteryLevel;

        batteryLevel = Math.max(0.0, batteryLevel - amount);

        if (oldLevel > MINIMUM_BATTERY_LEVEL && batteryLevel <= MINIMUM_BATTERY_LEVEL) {
            listener.onLowBattery();
        }
    }

    public double getMinimumBatteryLevel() {
        return MINIMUM_BATTERY_LEVEL;
    }

    public int batteryLevelToPercent() {
        return (int)(batteryLevel * 100);
    }
}
