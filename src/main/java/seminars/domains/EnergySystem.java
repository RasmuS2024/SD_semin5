package seminars.domains;

import lombok.Data;

@Data
public class EnergySystem {

    protected double batteryLevel;

    private static final double LOW_BATTERY_THRESHOLD = 0.15;

    private static final double MAX_BATTERY = 1.0;

    private static final double MIN_BATTERY = 0.0;

    public double getLowBatteryThreshold() {
        return LOW_BATTERY_THRESHOLD;
    }

    public EnergySystem (double batteryLevel) {
        this.batteryLevel = Math.max(MIN_BATTERY, Math.min(MAX_BATTERY, batteryLevel));
    }

    public boolean consume(double amount) {
        if (amount <= 0 || batteryLevel <= MIN_BATTERY) {
            return false;
        }

        batteryLevel = Math.max(MIN_BATTERY, batteryLevel - amount);
        return true;
    }
    
    public boolean hasSufficientPower() {
        return batteryLevel > LOW_BATTERY_THRESHOLD;
    }

    public int batteryLevelToPercent() {
        return (int)(batteryLevel * 100);
    }

}
