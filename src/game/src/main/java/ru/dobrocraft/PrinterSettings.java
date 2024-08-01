package ru.dobrocraft;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import lombok.Getter;

@Getter
public class PrinterSettings {
    private final char enemyChar;
    private final char playerChar;
    private final char wallChar;
    private final char goalChar;
    private final char emptyChar;
    private final String enemyColor;
    private final String playerColor;
    private final String wallColor;
    private final String goalColor;
    private final String emptyColor;

    private Properties properties;

    public PrinterSettings() {
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
        this.emptyChar = properties.getProperty("empty.char", " ").charAt(0);
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
