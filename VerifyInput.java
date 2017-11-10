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
 *
 * @author 
 */
public class VerifyInput {
    
    private int[][] values;
    
    public VerifyInput() {
        this.values = new int[3][];
    }
    
    public int[][] getValues() {
        return this.values;
    }
    /**
     * 
     * @param fileName
     * @return 
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
    
    private boolean verifyFileHasExtention(String fileName) throws NoFileExtentionException {
        int i = fileName.lastIndexOf(".");
        if (i > 0) {
            //System.out.println("File has an extention");
            return true;
            }
        //return false;
        throw new NoFileExtentionException("Your file has no file extention");
    }
    
    private boolean verifyFileExtention(String fileName) throws InvalidFileExtentionException {
        int i = fileName.lastIndexOf(".");
        String fileExtension = fileName.substring(i + 1);
        String txt = "txt";
        
        if (txt.equals(fileExtension)) {
            return true;
            }
            //return false;
        throw new InvalidFileExtentionException("File must of txt format");
     }
    
    
    private boolean fileExists(String filepath) throws FileNotFoundException {
        //System.out.println("Checking if this file exists at " + filepath);
        if (new File(filepath).exists()) {
            return true;
        }
        throw new FileNotFoundException("There is no file at the specified path");
    }
    
    /**
     * Verifies that the player entry from the commmandline is legal
     * @param commandLineInput
     * @return boolean
     */
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
    
    private boolean lineIsLegal(String line) throws NumberFormatException, IllegalFileContentException{
        SpaceVerification sv = new SpaceVerification();
        sv.allTogether(line);
        String[] strings = upToComma(line);
        //System.out.println(Arrays.toString(strings));
        if (isIntegers(strings)) {
            //System.out.println(Arrays.toString(strings));
            placeIntoArray(strings);
            return true;
        }
        return false;
        
    }
    
    private String[] upToComma(String line) {
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
    
    
    private List<String> removeWhiteSpaces(List<String> clean){
        List<String> withoutWhiteSpaces = clean.stream().map(i -> i.replaceAll("\\s", "")).collect(Collectors.toList());
        return withoutWhiteSpaces;
        
    }
    
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
    
    private void placeIntoArray(String[] numbers) {
        int[] array = Arrays.stream(numbers).mapToInt(Integer::parseInt).toArray();
        //System.out.println(Arrays.toString(array));
        for (int i = 0; i < this.values.length; i++) {
            
            if (this.values[i] == null) {
                this.values[i] = array;
                
                return;
            }
        }
    }
}
