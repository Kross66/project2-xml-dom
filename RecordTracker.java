/*
 * COPYRIGHT
 */
package lab1;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * RecordTracker
 *
 * This class keeps track of games by creating a game object and putting them all into an ArrayList
 *
 * @author Kyler Ross
 * @version 1.0 (Kyler Ross)
 */
public class RecordTracker
{
    //Contains choice options
    public static void main( String[] args )
    {
        // Main Process of Application
        Scanner reader = new Scanner(System.in); 
        
        boolean keepGoing = true;
        //Arraylists of game objects
        ArrayList<Game> games = new ArrayList<Game>();
        
        //choice for option
        int choice = 0;
        while (keepGoing)
        {
            choice = 0;
            System.out.println( "Choose an action and enter number" );
            System.out.println( " (1) Add a new game " );
            System.out.println( " (2) Edit an existing game " );
            System.out.println( " (3) Display all games " );
            System.out.println( " (4) Display stats for entire season " );
            System.out.println( " (5) Save file " );
            System.out.println( " (6) Quit " );
            choice = chooseChoice();
            if (choice == 6)
            {
                keepGoing = false;
            }
            
            games = chooseOption(choice, games);
        }
        
        System.out.println( "Done." );
        //Or logging
        reader.close();
        
    }

    //Takes input and creates choice
    public static int chooseChoice()
    {
        Scanner reader = new Scanner(System.in);
        int returnVal = 0;
        returnVal = reader.nextInt();
        return returnVal;
    }
    
    //Creates an ArrayList to store the Game objects
    public static ArrayList<Game> chooseOption(int choice, ArrayList<Game> array)
    {
        
        //Add game
        if (choice == 1)
        {
            array.add(addGame());
        }
        //Choose game to edit, then edit
        else if (choice == 2)
        {
            Scanner scan = new Scanner(System.in);
            for (int j = 0; j < array.size(); j++)
            {
                System.out.print( "Game against[" + j + "]: [" + array.get(j).getOtherTeamName() + "] " );
            }
            System.out.println( "" );
            System.out.println( "What game do you wish to edit? : " );
            editGame(array, scan.nextInt());
        }
        
        //Print results of season while using ArrayList
        else if (choice == 3)
        {
            int countDown = array.size() - 1;
            while (countDown != -1)
            {
                System.out.println( "The game against [ " + array.get(countDown).getOtherTeamName() +
                        " ] had a final score of [ " + array.get(countDown).getTeamScore() + " ] to [ " 
                        + array.get(countDown).getOtherScore() + " ] and was at home [ " 
                        + array.get(countDown).getHomeGame() + " ]" );
                countDown -= 1;
            }
        }
        
        //Display stats for season through ArrayList
        else if (choice == 4)
        {
            displayStats(array);
        }
        
        //Print arraylist to file
        else if (choice == 5)
        {
            try
            {
                buffered(array);
            }
            catch( IOException e )
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        
        //Do nothing
        else if (choice == 6)
        {
            //quit
        }
        
        //If invalid input, print then re-ask options
        else
        {
            System.out.println("Invalid number entered, please enter a number from 1-6");
        }
        
        //return array
        return array;
    }
    
    //Add game by taking inputs from user and creating object with parameters    
    private static Game addGame()
    {
        // TODO Auto-generated method stub
        Scanner scan = new Scanner(System.in); 
        System.out.println( "Enter home score: " );
        int gscore = scan.nextInt();
        System.out.println( "Enter opponents name: ");
        String teamName = scan.next();
        System.out.println( "Enter opponents score: " );
        int escore = scan.nextInt();
        System.out.println( "Was it a home game? (True/False) : " );
        boolean home = scan.nextBoolean();
        
        Game newGame = new Game();
        newGame.setHomeGame( home );
        newGame.setOtherScore( escore );
        newGame.setOtherTeamName( teamName );
        newGame.setTeamScore( gscore );
        // Log here
        return newGame;
    }
    
    //Edit game by taking array and specific game to edit, then reprompt questions
    private static void editGame(ArrayList<Game> array, int gameNumber)
    {
        // TODO Auto-generated method stub
        Scanner scanner = new Scanner(System.in);
        int game = gameNumber - 1;
        System.out.println( "Game to edit : " + game );
        System.out.println( "The old home score for the game is: [ " + array.get(game).getTeamScore() + " ]" );
        System.out.println( "What should the new score be? : " );
        array.get(game).setTeamScore( scanner.nextInt() );
        System.out.println( "The old opponent score for the game is: [ " + array.get(game).getOtherScore() + " ]" );
        System.out.println( "What should the new score be? : " );
        array.get(game).setOtherScore( scanner.nextInt() );
        System.out.println( "The old game was at home [ " + array.get(game).getHomeGame() + " ]" );
        System.out.println( "Should the old game be at home? (True/False) : " );
        array.get(game).setHomeGame( scanner.nextBoolean() );
    }
    
    //Display stats by taking array and iterating through
    private static void displayStats(ArrayList<Game> array)
    {
        int countDown = array.size() - 1; //Base 0 array
        float totalNumberOfGames = array.size();
        float numberOfHomeGames = 0;
        float numberOfWonHomeGames = 0;
        float numberOfWonGames = 0;
        
        //First object created is in position 0;
        while (countDown != -1)
        {
            //Adds home games
            if (array.get( countDown ).getHomeGame() == true)
            {
                numberOfHomeGames += 1;
                if (array.get( countDown ).getTeamScore() > array.get( countDown ).getOtherScore() == true)
                {
                    numberOfWonHomeGames += 1;
                    numberOfWonGames += 1;
                }
            }
            
            //Adds other games
            else if (array.get( countDown ).getHomeGame()== false)
            {
                if (array.get( countDown ).getTeamScore() > array.get( countDown ).getOtherScore() == true)
                {
                    numberOfWonGames += 1;
                }                
            }
            countDown -= 1;
        }
        
        //Print results
        System.out.println( "Number of won games: " + numberOfWonGames );
        System.out.println( "Overall Winning percentage :[" + ((numberOfWonGames / totalNumberOfGames) * 100) 
                + "%]" );
        if (numberOfHomeGames != 0)
        {
            System.out.println( "Home Winning percentage :[" + ((numberOfWonHomeGames / numberOfHomeGames) * 100)
                    + "%]");
        }
        else
        {
            System.out.println("Home game win percentage: [N/A]");
        }
        System.out.println( "Total number of games :[" + totalNumberOfGames + "]");
        System.out.println( "Number of home games :[" + numberOfHomeGames + "]" );
    }
    
    //Print results to file
    private static void buffered(ArrayList<Game> array) throws IOException
    {
        try (DataOutputStream output = new DataOutputStream
                (new BufferedOutputStream(new FileOutputStream("gameRecords.dat")));)
        {

            int countDown = array.size() - 1;
            while (countDown != -1)
            {
                 String values = "[" + array.get(countDown).getOtherTeamName() +
                        "==" + array.get(countDown).getTeamScore() + " ]:[ " 
                        + array.get(countDown).getOtherScore() + " ]:[ " 
                        + array.get(countDown).getHomeGame() + " ]";
                 output.write( (values + "\n").getBytes() );
                countDown -= 1;
            }
            output.close();
        }
    }
}
