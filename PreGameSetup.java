/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ECM2414_CA;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jonas
 */
public class AskForInput {
    
    public void askInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Press E to exit");
        boolean exit = false;
        while (!exit) {
            
            System.out.println("Please enter the number of players...");
            String next = "";
            while (true) {
                next = scanner.next();
                
                if (validPlayerEntry(next) || next.equals("E")) {
                    break;
                }
                System.out.println("Wrong input, please give a valid player entry");
            }
            
            if (next.equals("E")) {
                System.out.println("You pressed E");
                break;
            }
            
            String[] counts = {"first", "second", "third"};
            for (int i = 0; i < 3; i++) {
                //System.out.println("Please give the file location of the values for the " + counts[i] + " black bag");
                
                while (true) {
                    System.out.println("Please give the file location of the values for the " + counts[i] + " black bag");
                    next = scanner.next();

                    try {
                        if (validFile(next) || next.equals("E")) {
                            
                            break;
                        }
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }
                
                if (next.equals("E")) {
                    break;
                }
            }
               
            exit = true;
        }
        System.out.println("Program now exiting");
    }
        
    
    
    
    
    public static boolean validPlayerEntry(String entry) {
        try {
            int players = Integer.parseInt(entry);
            if (players < 2) {
                return false;
            }
            return true;
        }
        catch (NumberFormatException e) {
            return false;
        }
    }
    
    public static boolean validFile(String filepath) throws NoFileExtentionException, InvalidFileExtentionException, IllegalFileContentException {
        try {
            if (VerifyInput.verifyFileMeta(filepath) && VerifyInput.verifyFileContent(filepath)) {
                return true;
            }
        } catch (IOException e) {
            return false;
        }
        return false;
    }
    
    public static void main(String[] args) {
        AskForInput ask = new AskForInput();
        
        ask.askInput();
    }
}
