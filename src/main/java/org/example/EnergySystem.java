package org.example;

public class EnergySystem {
    protected double batteryLevel;
    private static final double LOW_BATTERY_THRESHOLD = 0.15;

    private static final double MAX_BATTERY = 1.0;

    private static final double MIN_BATTERY = 0.0;

    private EnergyListener listener;

    public interface EnergyListener {
        void onLowBattery();
    }

    public void setListener(EnergyListener listener) {
        this.listener = listener;
    }

    public EnergySystem (double batteryLevel) {
        this.batteryLevel = Math.max(MIN_BATTERY, Math.min(MAX_BATTERY, batteryLevel));
    }

    public double getBatteryLevel() {
        return batteryLevel;
    }

    public void consume(double amount) {
        if (amount <= 0) return;

        double oldLevel = batteryLevel;

        batteryLevel = Math.max(0.0, batteryLevel - amount);

        if (oldLevel > LOW_BATTERY_THRESHOLD && batteryLevel <= LOW_BATTERY_THRESHOLD) {
            listener.onLowBattery();
        }
    }

    public double getMinimumBatteryLevel() {
        return LOW_BATTERY_THRESHOLD;
    }

    public int batteryLevelToPercent() {
        return (int)(batteryLevel * 100);
    }

    @Override
    public String toString() {
        return "EnergySystem{batteryLevel=" + batteryLevelToPercent() + "%}";
    }
}
