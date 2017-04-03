/*
 * COPYRIGHT
 */
package lab1;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * RecordTracker
 *
 * TODO Class description
 *
 * @author Kyler Ross
 * @version $Revision$ ($Author$)
 */

public class Game
{

    /**
     * TODO Method description
     *
     * @param args
     */
    private int teamScore;
    private int otherScore;
    private String otherTeamName;
    private boolean homeGames;
    
    //default constructor
    public Game()
    {
        this.teamScore = 0;
        this.otherScore = 0;
        otherTeamName = "";
        homeGames = false;
    }
    
    //constructor
    public Game(int teamScore, int otherScore, String teamName, boolean homeGame)
    {
        this.teamScore = teamScore;
        this.otherScore = otherScore;
        otherTeamName = teamName;
        homeGames = homeGame;
    }

    //getters and setters
    public String getOtherTeamName()
    {
        return otherTeamName;
    }
    
    public void setOtherTeamName(String teamName)
    {
        otherTeamName = teamName;
    }
    public int getTeamScore()
    {
        return teamScore;
    }
    public void setTeamScore(int score)
    {
        teamScore = score;
    }
    public int getOtherScore()
    {
        return otherScore;
    }
    public void setOtherScore(int score)
    {
        otherScore = score;
    }
    public boolean getHomeGame()
    {
        return homeGames;
    }
    public void setHomeGame(boolean homeGame)
    {
        homeGames = homeGame;
    }
}
