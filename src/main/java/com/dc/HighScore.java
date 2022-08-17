package com.dc;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class HighScore implements Comparable<HighScore>
{
    private String playerName;
    private int moneyLeft;    

    public HighScore(String playerName, int moneyLeft)
    {
        this.playerName = playerName;
        this.moneyLeft = moneyLeft;
    }

    public String getPlayerName()
    {
        return playerName;
    }

    public Integer getMoneyLeft()
    {
        return moneyLeft;
    }
    
    private static String getHighscorePath() throws IOException
    {
        String path = new File(".").getCanonicalPath();
        path += "/hs.txt";

        return path;
    }

    public static ArrayList<HighScore> getHighscores() throws IOException
    {
        ArrayList<HighScore> highScores = new ArrayList<HighScore>();

        try {
            File highScoreFile = new File(getHighscorePath());
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
    
    public static void setHighScores(ArrayList<HighScore> highScores) throws IOException
    {
        Collections.sort(highScores, Collections.reverseOrder());

        FileWriter fileWriter = new FileWriter(getHighscorePath());
        
        for(int i = 0; i < highScores.size(); i++)
            fileWriter.write(highScores.get(i).getPlayerName() + "," + highScores.get(i).getMoneyLeft());

        fileWriter.close();
    }

    @Override
    public String toString()
    {
        return playerName + " - " + moneyLeft;
    }

    @Override
    public int compareTo(HighScore o) {
        return moneyLeft - o.getMoneyLeft();
    }

    
}
