package com.dc;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.channels.ScatteringByteChannel;
import java.util.ArrayList;
import java.util.Scanner;

public class HighScore 
{
    private String playerName;
    private int moneyLeft;    

    public HighScore(String playerName, int moneyLeft)
    {
        this.playerName = playerName;
        this.moneyLeft = moneyLeft;
    }

    public static ArrayList<HighScore> getHighscores()
    {
        ArrayList<HighScore> highScores = new ArrayList<HighScore>();

        try {
            File highScoreFile = new File("hs.txt");
            Scanner scanner = new Scanner(highScoreFile);

            while(scanner.hasNextLine())
            {
                String data = scanner.nextLine();
                String[] splittedData = data.split(",");

                highScores.add(new HighScore(splittedData[0], Integer.parseInt(splittedData[1])));
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            return null;
        }
        
        return highScores;
    } 

    @Override
    public String toString()
    {
        return playerName + " - " + moneyLeft;
    }
}
