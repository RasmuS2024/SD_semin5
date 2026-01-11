package org.example;

public class Main {
    public static void main(String[] args) {
        System.out.println("ЗАПУСК СИСТЕМЫ УПРАВЛЕНИЯ СПУТНИКОВОЙ ГРУППИРОВКОЙ\n");
        System.out.println("=".repeat(70));

        ImagingSatellite satellite = new ImagingSatellite("ДЗЗ-1", 1.0, 0.5);

        System.out.println("1. Создан спутник: " + satellite.getName());

    }
}