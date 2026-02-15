package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Main {
    private static final int CHAR_REPEAT_COUNT = 50;  //количество знаков - для разделителей в терминале

    public static void main(String[] args) {
        System.out.println("ЗАПУСК СИСТЕМЫ УПРАВЛЕНИЯ СПУТНИКОВОЙ ГРУППИРОВКОЙ"
                + " \uD83C\uDF0D \uD83D\uDCE1 \uD83D\uDEF0\uFE0F \uD83D\uDEF0\uFE0F \uD83D\uDEF0\uFE0F");
        printSeparator();

        ConfigurableApplicationContext context = SpringApplication.run(Main.class, args);

        ConstellationRepository constellationRepository = context.getBean(ConstellationRepository.class);
        SpaceOperationCenterService spaceOperationCenterService = context.getBean(SpaceOperationCenterService.class);

        System.out.println();
        System.out.println("СОЗДАНИЕ СПЕЦИАЛИЗИРОВАННЫХ СПУТНИКОВ: \uD83D\uDEF0\uFE0F");
        printSeparator();

        CommunicationSatellite commSat1 = new CommunicationSatellite("Связь-1", 0.85, 500);
        CommunicationSatellite commSat2 = new CommunicationSatellite("Связь-2", 0.75, 1000);

        ImagingSatellite imgSat1 = new ImagingSatellite("ДЗЗ-1", 0.92, 2.5);
        ImagingSatellite imgSat2 = new ImagingSatellite("ДЗЗ-2", 0.45, 1.0);
        ImagingSatellite imgSat3 = new ImagingSatellite("ДЗЗ-3", 0.15, 0.5);

        printSeparator();
        spaceOperationCenterService.createAndSaveConstellation("Орбита-1");
        printSeparator();

        spaceOperationCenterService.createAndSaveConstellation("Орбита-2");
        printSeparator();

        System.out.println("ФОРМИРОВАНИЕ ГРУППИРОВОК \uD83D\uDEF0\uFE0F \uD83D\uDEF0\uFE0F \uD83D\uDEF0\uFE0F");
        printSeparator();

        spaceOperationCenterService.addSatelliteToConstellation("Орбита-1", commSat1);
        spaceOperationCenterService.addSatelliteToConstellation("Орбита-1", imgSat1);
        spaceOperationCenterService.addSatelliteToConstellation("Орбита-1", imgSat2);

        spaceOperationCenterService.addSatelliteToConstellation("Орбита-2", commSat2);
        spaceOperationCenterService.addSatelliteToConstellation("Орбита-2", imgSat3);

        printSeparator();
        spaceOperationCenterService.showConstellationStatus("Орбита-1");
        printSeparator();

        spaceOperationCenterService.showConstellationStatus("Орбита-2");
        printSeparator();


        System.out.println("АКТИВАЦИЯ СПУТНИКОВ \uD83D\uDCE1 \uD83D\uDEF0\uFE0F");

        spaceOperationCenterService.activateAllSatellites("Орбита-1");
        printSeparator();

        spaceOperationCenterService.showConstellationStatus("Орбита-1");
        printSeparator();

        spaceOperationCenterService.activateAllSatellites("Орбита-2");
        printSeparator();

        spaceOperationCenterService.showConstellationStatus("Орбита-2");
        printSeparator();

        spaceOperationCenterService.executeConstellationMission("Орбита-1");
        printSeparator();

        spaceOperationCenterService.executeConstellationMission("Орбита-2");
        printSeparator();

        spaceOperationCenterService.printConstellationRepositoryToConsole();
        printSeparator();
    }

    private static void printSeparator() {
        System.out.println("-".repeat(CHAR_REPEAT_COUNT));
    }
}