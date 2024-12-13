package ru.dobrocraft;

import com.beust.jcommander.Parameter;
public class GameConfig {
    @Parameter(names = "--enemiesCount", description = "Количество врагов")
    public static int enemiesCount = 1;

    @Parameter(names = "--size", description = "Размер карты")
    public static int fieldSize = 30;

    @Parameter(names = "--wallsCount", description = "Количество стен")
    public static int wallsCount = 45;

    @Parameter(names = "--profile", description = "Режим отладки")
    public static String profile = "production";

    public int getEnemiesCount() {
        return enemiesCount;
    }

    public int getFieldSize() {
        return fieldSize;
    }

    public String getProfile() {
        return profile;
    }

    public int getWallsCount() {
        return wallsCount;
    }
}
