/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecm1414_ca;

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class that holds methods to interact with the user to get the necessary information to start the game
 *
 * @author 660050748, 660049985
 */
public class PreGameSetup {
    
    private VerifyInput vf;
    private int[][] bagValues;
    private int playerEntry;
    
    /**
    * Constructor for the PreGameSetup class
    */
    public PreGameSetup() {
        vf = new VerifyInput();
        this.bagValues = new int[3][];
    }
    
    private void displayWelcomeMessage() {
        String first = "***************Welcome To the Pebble Game***************";
        
        printStarLine(first.length());
        System.out.print("\n");
        printStarLine(first.length());
        System.out.print("\n" + first + "\n");
        
        printStarLine(first.length());
        System.out.print("\n");
        printStarLine(first.length());
        System.out.print("\n");
    }
    
    private void printStarLine(int length) {
        for (int i = 0; i < length; i++){
            System.out.print("*");
        }
    }
    
    /**
    * Method gets the number of players and the specific files needed to initiate the PebbleGame.
    * Otherwise, the method just exits and the game ceases.
    */
    public void askInput() {
        Scanner scanner = new Scanner(System.in);
        displayWelcomeMessage();
        System.out.println("Press E to exit");
        boolean exit = false;
        while (!exit) {
            
            String next = null;
            next = getInfoOnNumOfPlayers(scanner);
            if (next.equals("E")) {
                exit = true;
                break;
            }
            next = getInfoOnPathForPebbles(scanner);
            
            if (next.equals("E")) {
                exit = true;
                break;
            } 
            //This here will get the int[][] from the VerifyInput object 
            this.bagValues = vf.getValues(); 
            
            break;
        }
        if (exit) {
            System.out.println("Program now exiting");
        }
        else {
            System.out.println("Game now starting");
            PebbleGame pg = new PebbleGame(this.playerEntry, this.bagValues);
            pg.startGame();
        }
        
    }    
    
    //Helper method that will return a String that will either be 'E' (to exit) or the path to get the file
    public String getInfoOnPathForPebbles(Scanner scanner) {
        String next = null;
        String[] counts = {"first", "second", "third"};
        for (int i = 0; i < 3; i++) {
            System.out.println("Please give the file location of the values for the " + counts[i] + " black bag");
            while (true) {
                next = scanner.nextLine();
                System.out.println("Next is " +  next);
                try {
                    if (next.equals("E") || validFile(next)) break;
                        } catch (Exception e) {
                            System.out.println(e);
                            }
                }
            if (next.equals("E")) break;
            }
        
        return next;    
    }
    //helper method that will return a String 'E' (to exit) or a number to determine the player amount    
    public String getInfoOnNumOfPlayers(Scanner scanner) {
        System.out.println("Please enter the number of players...");
        String next = "";
        while (true) {
            next = scanner.nextLine();
            if (validPlayerEntry(next) || next.equals("E")) break;
            System.out.println("Wrong input, please give a valid player entry");
                }
        return next;
        }    

    //Helper method to verify that amount of players inputted is greater than 1    
    public boolean validPlayerEntry(String entry) {
            try {
                int players = Integer.parseInt(entry);
                if (players < 1) {
                    return false;
                }
                this.playerEntry = players;
                this.vf.setPlayerNumber(players);
                return true;
            }
            catch (NumberFormatException e) {
                return false;
        
            }
        }

    //Helper method to determine if the path for the file is valid
    public boolean validFile(String filepath) throws IOException, NoFileExtentionException, InvalidFileExtentionException, IllegalFileContentException, FileNotFoundException {
            if (vf.verifyFileMeta(filepath) && vf.verifyFileContent(filepath)) {
                System.out.println("File content accepted");
                return true;
            }
            return false;
        }

    
}
