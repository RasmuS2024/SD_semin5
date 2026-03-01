package seminars.domains;

import lombok.Data;

@Data
public abstract class Satellite {
    protected String name;
    protected SatelliteState state;
    protected EnergySystem energy;

    public Satellite(String name, double batteryLevel) {
        this.name = name;
        this.state = new SatelliteState();
        this.energy = new EnergySystem(batteryLevel);

        System.out.println("Создан спутник: " + name + " (заряд: " + energy.batteryLevelToPercent() + "%)");
    }

    public boolean activate() {
        if (state.activate(energy.hasSufficientPower())) {
            System.out.println(name + ": Активация успешна");
        }

        System.out.println(name + ": Ошибка активации (заряд: " + (getEnergy().batteryLevelToPercent() + "%)"));
        return false;
    }

    public void deActivate() {
        if (state.isActive()) {
            state.deActivate();
        }

        System.out.println(name + ": Деактивирован");
    }

    public abstract void performMission();

}

