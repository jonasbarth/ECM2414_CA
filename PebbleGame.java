/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ECM2414_CA;

import ECM2414_CA.listenerEvent.PlayerEvent;
import ECM2414_CA.listenerEvent.PlayerListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;



/**
 *
 * @author 
 */
public class PebbleGame implements PlayerListener {
    
    private Player[] players;
    private BlackBag[] blackBags;
    private WhiteBag[] whiteBags;
    private int turn;
    private boolean gameOver;
    private Player winner;
    
    
    public PebbleGame(Player[] players, BlackBag[] blackBags, WhiteBag[] whiteBags) {
        this.players = new Player[players.length];
        this.players = players;
        
        this.blackBags = new BlackBag[blackBags.length];
        this.blackBags = blackBags;
        
        this.whiteBags = new WhiteBag[whiteBags.length];
        this.whiteBags = whiteBags;
        
        this.turn = 0;
        this.gameOver = false;
    }

    
    public void incrementTurn() {
        this.turn++;
    }
    
    //public void onPlayerAnnouncedWin()
    
    //public void onPlayerFoundBagEmpty()
    
    
    public void startGame() {
        
    }
    
    /**
     * 
     * @param fileName
     * @return 
     */
    public boolean verifyFileMeta(String fileName) {
        if (verifyFileHasExtention(fileName)) {
            if (verifyFileExtention(fileName))  {
                return true;
            }
        
        }
        return false;
    }
    
    private boolean verifyFileHasExtention(String fileName) {
        int i = fileName.lastIndexOf(".");
        if (i > 0) {
            return true;
            }
        return false;
    }
    
    private boolean verifyFileExtention(String fileName) {
        int i = fileName.lastIndexOf(".");
        String fileExtension = fileName.substring(i + 1);
        String txt = "txt";
        
        if (txt.equals(fileExtension)) {
            return true;
            }
            return false;
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
    
    public boolean verifyFileContent(String filepath) throws IOException {
        ReadFile rf = new ReadFile(filepath);
        String line = rf.openFile();
        
        if (lineIsLegal(line)) {
            return true;
        }
        return false;
    }
    
    public boolean lineIsLegal(String line){
        String[] strings = upToComma(line);
        if (isIntegers(strings)) {
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
    
    private boolean isIntegers(String[] listOfNumbers) throws NumberFormatException {
        int[] weights = new int[listOfNumbers.length];
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

    @Override
    public void onPlayerAnnouncedWinEvent(PlayerEvent e) {
        //announce win to all other players
    }

    @Override
    public void onPlayerFoundEmptyBagEvent(PlayerEvent e) {
        //refill the players bag
    }

    @Override
    public void onPlayerHasDrawnEvent(PlayerEvent e) {
        //
    }

    @Override
    public void onPlayerNeedsNewBagEvent(PlayerEvent e) {
        //give player new bag
    }
    
}
