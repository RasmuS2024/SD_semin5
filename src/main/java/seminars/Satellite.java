package seminars;

import lombok.Getter;

public abstract class Satellite implements EnergySystem.EnergyListener {
    @Getter
    protected String name;
    protected SatelliteState state;
    @Getter
    protected EnergySystem energy;

    public Satellite(String name, double batteryLevel) {
        this.name = name;
        this.state = new SatelliteState();
        this.energy = new EnergySystem(batteryLevel);
        this.energy.setListener(this);

        System.out.println("Создан спутник: " + name + " (заряд: " + energy.batteryLevelToPercent() + "%)");
    }

    @Override
    public void onLowBattery() {
        System.out.println("НИЗКИЙ ЗАРЯД! " + name + " деактивируется...");
        deActivate();
    }

    public void activate() {
        state.activate(this);
    }

    public void deActivate() {
        state.deActivate(this);
    }

    protected abstract void performMission();

    public EnergySystem getEnergySystem() {
        return energy;
    }
}

