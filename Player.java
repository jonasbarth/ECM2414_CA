/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ECM2414_CA;

import java.util.ArrayList;
import java.util.Arrays;



/**
 *
 * @author Jonas
 */
public class Player implements Runnable {
    
    private BlackBag currentBag;
    private ArrayList pebbles;
    private String name;
    private boolean hasWon = false;
    private boolean continuePlaying = false;
    private int totalWeight;
    private WriteToFile file;
    private boolean newTurn =false;
    

    public Player(String name, BlackBag bagChosenByGame) {
        this.name = name;
        this.currentBag = bagChosenByGame;
        this.pebbles = new ArrayList();
    }
    
    //31/10/2017 better to have interaction only between two types of Objects
    //in a real game, the bag will be a passive object
    @Override
    public void run() {
        //draw ten pebbles
        for (int i = 0; i < 10; i++) {
            this.drawPebble(this.currentBag);
        }
        //fire an event that we have drawn 10 pebbles to start the game
        while (continuePlaying) {
            //here the player listens if some other player has won
            if (newTurn){
                //here the player should be listening if there is a new turn 
                
                if (hasWon) {
                
                    
                // fire event to tell pebblegame
                }
                else {
                    this.discardPebble();
                    //fire event to tell game that you need a new current bag
                    this.drawPebble(currentBag);
                }
                
                
 
            } 
        }
    }
    
    public void setNewBag(BlackBag bag) {
        this.currentBag = bag;
    }
    
    /**
     * Method that adds a pebble from a BlackBag object at random
     * to the player's list of pebbles held
     * 
     * @param bag BlackBag object that holds pebbles
     */
    public void drawPebble(BlackBag bag){
        if (bag.isEmpty() == false) {
        Pebble rand = bag.getRandomPebble();
        this.pebbles.add(rand);
        this.drawnMessage(bag, rand.getValue());
        
    }   else {//fire the event here
            
        }    
    }
    
    private void calculateTotalWeight(){
        int totalValue = 0;
        for(Pebble pebble: this.getHand()) {
            totalValue += pebble.getValue();
        }
        this.totalWeight = totalValue;
    }
    
    public int getTotalWeight(){
        this.calculateTotalWeight();
        return this.totalWeight;
    }
    
    public void discardPebble(){
        Bag partnerBag = this.currentBag.getPartnerBag();
        Pebble toDiscard = this.choosePebble();
        partnerBag.addPebble(toDiscard);
        this.pebbles.remove(toDiscard);
        
        this.discardedMessage(partnerBag, toDiscard.getValue());
        
    }
    
    public void setFile(String filePath) {
        filePath += this.name + "_output.txt";
        this.file = new WriteToFile(filePath);
    }
    
    public void discardedMessage(Bag bag, int value) {
        String output = this.name + " has discarded a " + value + " into bag " + bag.getName();
        this.writeToFile(output);
    }
    
    public void drawnMessage(Bag bag, int value) {
        String output = this.name + " has drawn a " + value + " from bag " + bag.getName();
        this.writeToFile(output);
    }
    
    public void handMessage() {
        String output = this.name + " hand is " + Arrays.toString(getHandAsInt());
        this.writeToFile(output);
    }
    
    
    public void writeToFile(String output) {
        this.file.writeLineToFile(output);
        
    }
    
    private int[] getHandAsInt() {
        Pebble[] pebbles = this.getHand();
        int[] integers = new int[pebbles.length];
        
        for (int i = 0; i < pebbles.length; i++) {
            integers[i] = pebbles[i].getValue();
        }
        
        return integers;
    }
    
    private Pebble choosePebble(){
        int totalPoints = this.getTotalWeight();
        if (totalPoints > 100)
            return this.choosePebbleOver();
        else
            return this.choosePebbleUnder();
            }
    
    private Pebble choosePebbleOver(){
       Pebble[] temp = this.getHand();
       Pebble toDiscard = temp[0];
       for(Pebble pebble: temp){
           if (pebble.getValue() > toDiscard.getValue())
               toDiscard = pebble;
       }
       return toDiscard;
    }
    
    private Pebble choosePebbleUnder(){
       Pebble[] temp = this.getHand();
       Pebble toDiscard = temp[0];
       for(Pebble pebble: temp){
           if (pebble.getValue() < toDiscard.getValue())
               toDiscard = pebble;
        }
       return toDiscard;
    }
    
    private Pebble[] getHand(){
        Object[] tempArray = this.pebbles.toArray();
        Pebble[] toUse = new Pebble[tempArray.length];
        for(int i = 0; i < tempArray.length;i++){
            toUse[i] = (Pebble) tempArray[i];
        }
        return toUse;
    }
    
}

