package org.example;

public abstract class Satellite {
    protected String name;
    protected boolean isActive;
    protected double batteryLevel;
    private static final double MINIMUM_BATTERY_LEVEL = 0.05;

    public Satellite(String name, double batteryLevel) {
        this.name = name;
        this.batteryLevel = batteryLevel;
        this.isActive = false;
        System.out.println("Создан спутник: " + name + " (заряд: " + batteryLevelToPercent() + "%)");
    }

    public boolean activate() {
        if (batteryLevel > MINIMUM_BATTERY_LEVEL && !isActive) {
            isActive = true;
            System.out.println("Спутник " + name + " активирован");
            return true;
        }
        System.out.println(name + ": Ошибка активации (заряд: " + batteryLevelToPercent() + "%)");
        return false;
    }

    public void deActivate() {
        if (isActive) {
            isActive = false;
            System.out.println("Спутник " + name + " деактивирован");
        }
    }

    protected abstract void performMission();

    public String getName() { return name; }

    public double getBatteryLevel() { return batteryLevel; }

    public boolean isActive() { return isActive; }

    public void consumeBattery(double amount) {
        if (amount > 0) {
            batteryLevel = Math.max(0.0, batteryLevel - amount);
            if (batteryLevel <= MINIMUM_BATTERY_LEVEL && isActive) {
                System.out.println("НИЗКИЙ ЗАРЯД! " + name + " деактивируется...");
                deActivate();
            }
        }
    }

    public int batteryLevelToPercent() {
        return (int)(batteryLevel * 100);
    }

}

