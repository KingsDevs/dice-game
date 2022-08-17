package com.dc;

public class Bet 
{
    private int playerBet;
    private String underover;
    private int numberRolled;
    private Boolean won;
    
    public Bet(int playerBet, String underover, int numberRolled, Boolean won)
    {
        this.playerBet = playerBet;
        this.underover = underover;
        this. numberRolled = numberRolled;
        this.won = won;
    }

    @Override
    public String toString()
    {
        String betOutcome = new String();

        if(won)
            betOutcome = "Won";
        else
            betOutcome = "Lost";

        return "Player Bet: " + playerBet + " - " + underover + " Number Rolled: " + numberRolled + " - " + betOutcome;
    } 
}
