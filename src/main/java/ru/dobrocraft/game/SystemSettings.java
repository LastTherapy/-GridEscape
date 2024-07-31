package ru.dobrocraft.game;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class SystemSettings {
    public char enemyChar;
    public char playerChar;
    public char wallChar;
    public char goalChar;
    public char emptyChar;
    public char enemyColor;
    public char playerColor;
    public char wallColor;
    public char goalColor;
    public char emptyColor;

    private Properties properties;

    public SystemSettings() {
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
        this.emptyChar = properties.getProperty("empty.char").charAt(0);
        this.enemyColor = properties.getProperty("enemy.color").charAt(0);
        this.playerColor = properties.getProperty("player.color").charAt(0);
        this.wallColor = properties.getProperty("wall.color").charAt(0);
        this.goalColor = properties.getProperty("goal.color").charAt(0);
        this.emptyColor = properties.getProperty("empty.color").charAt(0);
    }


    private void loadSettings() throws IOException {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("application-production.properties")) {
            this.properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
