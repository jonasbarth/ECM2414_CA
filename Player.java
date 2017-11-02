/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ECM2414_CA;

import ECM2414_CA.listenerEvent.GameEvent;
import ECM2414_CA.listenerEvent.GameListener;
import java.util.ArrayList;
import java.util.Arrays;



/**
 *
 * @author Jonas
 */
public class Player implements Runnable, GameListener {
    
    private BlackBag currentBag;
    private ArrayList pebbles;
    
    private String name;
    
    private boolean continuePlaying = false;
    private int totalWeight;
    private WriteToFile file;
    private boolean newTurn = false;
    
    

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
                
                if (this.hasWinningHand()) {
                    this.fireAnnounceWinEvent();
                    
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
        
        }   else {//tell the pebblegame the bag is empty
            this.fireFoundEmptyBagEvent();
            }    
    }
    
    /**
     * Service method to calculate the current weight of all pebbles
     * held in the player's hand.
     */
    private void calculateTotalWeight(){
        int totalValue = 0;
        for(Pebble pebble: this.getHand()) {
            totalValue += pebble.getValue();
        }
        this.totalWeight = totalValue;
    }
    
    /**
     * Method to return the total value of all pebbles currently
     * held in the player's hand
     * @return int 
     */
    public int getTotalWeight(){
        this.calculateTotalWeight();
        return this.totalWeight;
    }
    
    private boolean hasWinningHand() {
        if (this.getTotalWeight() == 100) {
            return true;
        }
        return false;
    }
    
    /**
     * Method to discard a chosen pebble into the partner bag of the 
     * bag from which the last pebble was drawn
     */
    public void discardPebble(){
        Bag partnerBag = this.currentBag.getPartnerBag();
        Pebble toDiscard = this.choosePebble();
        partnerBag.addPebble(toDiscard);
        this.pebbles.remove(toDiscard);
        
        this.discardedMessage(partnerBag, toDiscard.getValue());
        
    }
    
    /**
     * Method to set the file path of the file to which output of
     * this player is written
     * @param filePath 
     */
    public void setFile(String filePath) {
        filePath += this.name + "_output.txt";
        this.file = new WriteToFile(filePath);
    }
    
    /**
     * Method to write a message about which value was discarded into which bag
     * @param bag
     * @param value 
     */
    public void discardedMessage(Bag bag, int value) {
        String output = this.name + " has discarded a " + value + " into bag " + bag.getName();
        this.writeToFile(output);
    }
    
    /**
     * Method to write a message about which value was drawn from which bag
     * @param bag
     * @param value 
     */
    public void drawnMessage(Bag bag, int value) {
        String output = this.name + " has drawn a " + value + " from bag " + bag.getName();
        this.writeToFile(output);
    }
    
    /**
     * Method writing a message detailing the current values held by the player
     */
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

    @Override
    public void onOtherPlayerAnnouncedWinEvent(GameEvent e) {
        this.continuePlaying = false;
    }

    @Override
    public void onTurnChangeEvent(GameEvent e) {
       this.newTurn = true;
    }
    
    public void fireAnnounceWinEvent() {
        
    }
    
    public void fireFoundEmptyBagEvent() {
        
    }
    
    public void fireHasDrawnEvent() {
        
    }
    
    public void fireNeedNewBagEvent() {
        
    }
}

