/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ECM2414_CA;

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jonas
 */
public class PreGameSetup {
    
    private VerifyInput vf;
    private int[][] bagValues;
    private int playerEntry;
    
    public PreGameSetup() {
        vf = new VerifyInput();
        this.bagValues = new int[3][];
    }
    
    public void askInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Press E to exit");
        boolean exit = false;
        while (!exit) {
            
            System.out.println("Please enter the number of players...");
            String next = "";
            while (true) {
                next = scanner.nextLine();
                
                if (validPlayerEntry(next) || next.equals("E")) {
                    break;
                }
                System.out.println("Wrong input, please give a valid player entry");
            }
            
            if (next.equals("E")) {
                exit = true;
                //System.out.println("You pressed E");
                break;
            }
            
            String[] counts = {"first", "second", "third"};
            for (int i = 0; i < 3; i++) {
                System.out.println("Please give the file location of the values for the " + counts[i] + " black bag");
                
                while (true) {
                    //System.out.println("Please give the file location of the values for the " + counts[i] + " black bag");
                    next = scanner.nextLine();
                    //System.out.println(next);
                    try {
                        if (validFile(next) || next.equals("E")) {
                            
                            break;
                        }
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }
                
                if (next.equals("E")) {
                    exit = true;
                    break;
                }
            }
            this.bagValues = vf.getValues();
            
            
            break;
        }
        if (exit) {
            System.out.println("Program now exiting");
        }
        else {
            System.out.println("Game now starting");
            PebbleGame pg = new PebbleGame(this.playerEntry, this.bagValues);
            Thread thread = new Thread(pg);
            thread.start();
        }
        
    }
        
    
    
    
    
    public boolean validPlayerEntry(String entry) {
        try {
            int players = Integer.parseInt(entry);
            if (players < 1) {
                return false;
            }
            this.playerEntry = players;
            return true;
        }
        catch (NumberFormatException e) {
            return false;
        
        }
    }
    
    public boolean validFile(String filepath) throws IOException, NoFileExtentionException, InvalidFileExtentionException, IllegalFileContentException, FileNotFoundException {
        //System.out.println("Validfile " + filepath);
        
        
        if (vf.verifyFileMeta(filepath) && vf.verifyFileContent(filepath)) {
            System.out.println("File content accepted");
            return true;
        }
        return false;
    }
    
    
    
    
    
    public static void main(String[] args) {
        PreGameSetup ask = new PreGameSetup();
        
        ask.askInput();
    }
}
