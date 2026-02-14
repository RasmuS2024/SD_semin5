package org.example;

public class Main {
    private static final int CHAR_REPEAT_COUNT = 50;  //количество знаков тире - для рисования полосок в терминале

    public static void main(String[] args) {
        System.out.println("ЗАПУСК СИСТЕМЫ УПРАВЛЕНИЯ СПУТНИКОВОЙ ГРУППИРОВКОЙ");
        System.out.println("=".repeat(CHAR_REPEAT_COUNT) + ">>>");

        CommunicationSatellite commSat1 = new CommunicationSatellite("Связь-1", 0.85, 500);
        CommunicationSatellite commSat2 = new CommunicationSatellite("Связь-2", 0.22, 1000);

        ImagingSatellite imgSat1 = new ImagingSatellite("ДЗЗ-1", 0.92, 2.5);
        ImagingSatellite imgSat2 = new ImagingSatellite("ДЗЗ-2", 0.35, 1.0);
        ImagingSatellite imgSat3 = new ImagingSatellite("ДЗЗ-3", 0.15, 0.5);

        System.out.println("-".repeat(CHAR_REPEAT_COUNT));
        SatelliteConstellation satConstellation = new SatelliteConstellation("RU Basic");
        System.out.println("-".repeat(CHAR_REPEAT_COUNT));

        System.out.println("ФОРМИРОВАНИЕ ГРУППИРОВКИ");
        System.out.println("-".repeat(CHAR_REPEAT_COUNT));
        satConstellation.addSatellite(commSat1);
        satConstellation.addSatellite(commSat2);
        satConstellation.addSatellite(imgSat1);
        satConstellation.addSatellite(imgSat2);
        satConstellation.addSatellite(imgSat3);
        System.out.println("-".repeat(CHAR_REPEAT_COUNT));
        System.out.println(satConstellation);
        System.out.println("-".repeat(CHAR_REPEAT_COUNT));

        System.out.println("АКТИВАЦИЯ СПУТНИКОВ");

        commSat1.activate();
        commSat2.activate();
        imgSat1.activate();
        imgSat2.activate();
        imgSat3.activate();

        System.out.println("-".repeat(CHAR_REPEAT_COUNT));
        satConstellation.executeAllMissions();
        System.out.println("-".repeat(CHAR_REPEAT_COUNT));

        System.out.println("Запуск проверки деактивации при израсходовании батареи");
        commSat2.performMission();
        imgSat2.performMission();
        imgSat2.performMission();
        imgSat2.performMission();
        System.out.println("-".repeat(CHAR_REPEAT_COUNT));

        System.out.println(satConstellation);

    }
}