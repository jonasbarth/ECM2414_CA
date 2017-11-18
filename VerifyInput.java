/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ECM2414_CA;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class that holds methods to verify we are taking the correct input from the text or CSV files
 *
 * @author 660050748, 660049985
 */
public class VerifyInput {
    
    private int[][] values;
    private int playerNumber;
    
    /**
    *
    * Constructor for the VerifyInput Class
    */
    public VerifyInput() {
        this.values = new int[3][];
    }
    
    /**
    * Method to get the values held in the 'values' field 
    */
    public int[][] getValues() {
        return this.values;
    }
    
    /**
    * Method to set the number of players that there will be in the game
    * 
    * @param number will be the number of players the user requests
    */
    public void setPlayerNumber(int number) {
        this.playerNumber = number;
    }
    /**
     * Method determines if the given path for a file is valid 
     *
     * @param fileName a string containing the path for the required file
     * @return boolean True if the file is a .txt or .csv and if it also exists at the given path
     */
    public boolean verifyFileMeta(String fileName) throws FileNotFoundException, NoFileExtentionException, InvalidFileExtentionException {
        //System.out.println("verifyFileMeta: " + fileName);
        if (fileExists(fileName)) {
            if (verifyFileHasExtention(fileName)) {
                if (verifyFileExtention(fileName))  {
                    return true;
                }
            }
        }
        
        return false;
    }
    
    //helper method to determine if the file contains a "." to show that it can have .csv or .txt
    //hence, it will be able to read in as a file
    private boolean verifyFileHasExtention(String fileName) throws NoFileExtentionException {
        int i = fileName.lastIndexOf(".");
        if (i > 0) {
            return true;
            }
        throw new NoFileExtentionException("Your file has no file extention");
    }
    
    //helper method to check if the file is of a type .txt or .csv
    private boolean verifyFileExtention(String fileName) throws InvalidFileExtentionException {
        int i = fileName.lastIndexOf(".");
        String fileExtension = fileName.substring(i + 1);
        String txt = "txt";
        String csv = "csv";
        
        if (fileExtension.equals(txt) || fileExtension.equals(csv)) {
            return true;
            }
        throw new InvalidFileExtentionException("File must of txt format");
     }
    
    //helper method to determine if a file exists in the given path
    private boolean fileExists(String filepath) throws FileNotFoundException {
        //System.out.println("Checking if this file exists at " + filepath);
        if (new File(filepath).exists()) {
            return true;
        }
        throw new FileNotFoundException("There is no file at the specified path");
    }
    
    //Helper method to check that the user has inputted an integer greater than 1
    //for the amount of players
    private boolean verifyPlayerNumber(String commandLineInput) {
        try {
            int playerNumber = Integer.parseInt(commandLineInput);
            if (playerNumber > 1) {
                return true;
            }
            //integer has a value below two
            return false;
        }
        catch (NumberFormatException e) {
            //argument not a valid integer
            return false;
        }     
        
    }
    
    
    
    public boolean verifyFileContent(String filepath) throws IOException, IllegalFileContentException {
        ReadFile rf = new ReadFile(filepath);
        String line = rf.openFile();
        
        if (lineIsLegal(line)) {
            
            //System.out.println("The file content is legal");
            return true;
        }
        //return false;
        throw new IllegalFileContentException("The file content is illegal, make sure the file consists of a comma separated list of integers");
    }
    
    //helper method to determine if the String is a list of integers seperated by commas
    private boolean lineIsLegal(String line) throws NumberFormatException, IllegalFileContentException{
        SpaceVerification sv = new SpaceVerification();
        sv.allTogether(line);
        String[] strings = upToComma(line);
        if (isIntegers(strings)) {
            //if all of the values in 'strings' are valid integers we proceed
            placeIntoArray(strings);
            return true;
        }
        return false;
        
    }
    
    //helper method to get
    private String[] upToComma(String line) {
        char comma = ',';
        List<String> weights = new ArrayList<String>();
        String itemToAdd = "";
        for(int i = 0; i < line.length(); i++){
            //we loop through the String and we begin concatenating the number
            //if it is more than 1 digit. When a comma is found, we add that number
            //to 'weights' and reset the string itemToAdd to get the next number
            if(line.charAt(i) == comma){
                weights.add(itemToAdd);
                itemToAdd = "";
            }
            else{
                itemToAdd += line.charAt(i);
            }
        }
        weights.add(itemToAdd);
        List<String> clean = removeWhiteSpaces(weights);
        String[] toReturn = new String[clean.size()];
        toReturn = clean.toArray(toReturn);
        return toReturn;
    }
    
    //helper method removes any spaces that might be in the string 
    private List<String> removeWhiteSpaces(List<String> clean){
        List<String> withoutWhiteSpaces = clean.stream().map(i -> i.replaceAll("\\s", "")).collect(Collectors.toList());
        return withoutWhiteSpaces;
        
    }
    
    //helper method that verifies that all of the String objects in the array are integers and that they are not 0 or
    //any negative values. True is returned only if all of those conditions are met
    private boolean isIntegers(String[] listOfNumbers) throws NumberFormatException, IllegalFileContentException {
        if (listOfNumbers.length == 0) {
            return false;
        }
        for(int i = 0; i < listOfNumbers.length;i++){
            try {
                
                int value = Integer.parseInt(listOfNumbers[i]);
                if (value < 1){
                    throw new IllegalFileContentException("You have a negative value in your file, please only give positive values.");
                }
            }
            catch (NumberFormatException e) {
                return false;
            }
        }
        return true;
    }
    
    //helper method uses the information in 'numbers' to update its (the verifyInput object)
    //field 'values' if conditions in verifyPlayerPebbleRelation() hold
    private void placeIntoArray(String[] numbers) throws IllegalFileContentException {
        int[] array = Arrays.stream(numbers).mapToInt(Integer::parseInt).toArray();
        //System.out.println(Arrays.toString(array));
        if (verifyPlayerPebbleRelation(array)) {
           for (int i = 0; i < this.values.length; i++) {
            
                if (this.values[i] == null) {
                    this.values[i] = array;

                    return;
                }
            } 
        }
        
    }
    
    //helper method to determine whether the amount of pebbles equals or supercedes the amount of players*11
    //also verifies that the there are not more than 15*amount of players number of pebbles in the bag
    private boolean verifyPlayerPebbleRelation(int[] pebbleValues) throws IllegalFileContentException {
        if (pebbleValues.length >= this.playerNumber * 11 && pebbleValues.length <= this.playerNumber * 15) {
            return true;
        }
        throw new IllegalFileContentException("The bag needs to contain at least 11 times the amount of players of pebbles and a maximum of 15 times the amount of players of pebbles.");
    }
}
