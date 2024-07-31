package ru.dobrocraft.game;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import lombok.Getter;


public class GameSettings {
    @Getter
    private final char enemyChar;
    @Getter
    private final char playerChar;
    @Getter
    private final char wallChar;
    @Getter
    private final char goalChar;
    @Getter
    private final char emptyChar;
    @Getter
    private final String enemyColor;
    @Getter
    private final String playerColor;
    @Getter
    private final String wallColor;
    @Getter
    private final String goalColor;
    @Getter
    private final String emptyColor;

    private Properties properties;

    public GameSettings() {
        this.properties = new Properties();
        try {
            loadSettings();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.enemyChar = properties.getProperty("enemy.char").charAt(0);
        this.playerChar = properties.getProperty("player.char").charAt(0);
        this.wallChar = properties.getProperty("wall.char").charAt(0);
        this.goalChar = properties.getProperty("goal.char").charAt(0);
        this.emptyChar = properties.getProperty("empty.char"," ").charAt(0);
        this.enemyColor = properties.getProperty("enemy.color");
        this.emptyColor = properties.getProperty("empty.color");
        this.goalColor = properties.getProperty("goal.color");
        this.playerColor = properties.getProperty("player.color");
        this.wallColor = properties.getProperty("wall.color");
    }


    private void loadSettings() throws IOException {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("application-production.properties")) {
            this.properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
