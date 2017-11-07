/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ECM2414_CA;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author 
 */
public class VerifyInput {
    
    /**
     * 
     * @param fileName
     * @return 
     */
    public static boolean verifyFileMeta(String fileName) throws NoFileExtentionException, InvalidFileExtentionException {
        if (verifyFileHasExtention(fileName)) {
            if (verifyFileExtention(fileName))  {
                return true;
            }
        
        }
        return false;
    }
    
    private static boolean verifyFileHasExtention(String fileName) throws NoFileExtentionException {
        int i = fileName.lastIndexOf(".");
        if (i > 0) {
            return true;
            }
        //return false;
        throw new NoFileExtentionException("Your file has no file extention");
    }
    
    private static boolean verifyFileExtention(String fileName) throws InvalidFileExtentionException {
        int i = fileName.lastIndexOf(".");
        String fileExtension = fileName.substring(i + 1);
        String txt = "txt";
        
        if (txt.equals(fileExtension)) {
            return true;
            }
            //return false;
        throw new InvalidFileExtentionException("File must of txt format");
     }
    
    
    /**
     * Verifies that the player entry from the commmandline is legal
     * @param commandLineInput
     * @return boolean
     */
    private static boolean verifyPlayerNumber(String commandLineInput) {
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
    
    public static boolean verifyFileContent(String filepath) throws IOException, IllegalFileContentException {
        ReadFile rf = new ReadFile(filepath);
        String line = rf.openFile();
        
        if (lineIsLegal(line)) {
            return true;
        }
        //return false;
        throw new IllegalFileContentException("The file content is illegal, make sure the file consists of a comma separated list of integers");
    }
    
    private static boolean lineIsLegal(String line){
        String[] strings = upToComma(line);
        if (isIntegers(strings)) {
            return true;
        }
        return false;
        
    }
    
    private static String[] upToComma(String line) {
        char comma = ',';
        List<String> weights = new ArrayList<String>();
        String itemToAdd = "";
        for(int i = 0; i < line.length(); i++){
            if(line.charAt(i) == comma){
                weights.add(itemToAdd);
                itemToAdd = "";
            }
            else{
                itemToAdd += line.charAt(i);
            }
        }
        List<String> clean = removeWhiteSpaces(weights);
        String[] toReturn = new String[clean.size()];
        toReturn = clean.toArray(toReturn);
        return toReturn;
    }
    
    
    private static List<String> removeWhiteSpaces(List<String> clean){
        List<String> withoutWhiteSpaces = clean.stream().map(i -> i.replaceAll("\\s", "")).collect(Collectors.toList());
        return withoutWhiteSpaces;
        
    }
    
    private static boolean isIntegers(String[] listOfNumbers) throws NumberFormatException {
        
        for(int i = 0; i < listOfNumbers.length;i++){
            try {
                Integer.parseInt(listOfNumbers[i]);
            }
            catch (NumberFormatException e) {
                return false;
            }
        }
        return true;
    }
}
