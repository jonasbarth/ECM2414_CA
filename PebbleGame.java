/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecm1414_ca;

import ecm1414_ca.listenerEvent.GameEvent;
import ecm1414_ca.listenerEvent.GameListener;
import ecm1414_ca.listenerEvent.PlayerEvent;
import ecm1414_ca.listenerEvent.PlayerListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;



/**
 * Class holds methods to initialise the Pebble game simulation
 * 
 * @author 660050748, 660049985
 */
public class PebbleGame implements PlayerListener, GameManager {
    
    private Playing[] players;
    private volatile BlackBag[] blackBags;
    private volatile WhiteBag[] whiteBags;
    private int turn;
    private volatile boolean gameOver;
    private Playing winner;
    private ArrayList<GameListener> gameListeners;
    private HashMap playerDraw;
    
    /**
    * Constructor for PebbleGame class with 3 arguments
    *
    * @param players an array of Players that will participate in the game
    * @param blackBags an array of BlackBags that will participate in the game 
    * @param whiteBags an array of WhiteBags that will participate in the game
    */
    public PebbleGame(Playing[] players, BlackBag[] blackBags, WhiteBag[] whiteBags) {
        this.players = new Player[players.length];
        this.players = players;
        
        this.blackBags = new BlackBag[blackBags.length];
        this.blackBags = blackBags;
        
        this.whiteBags = new WhiteBag[whiteBags.length];
        this.whiteBags = whiteBags;
        
        this.turn = 0;
        this.gameOver = false;
        this.playerDraw = new HashMap();
        this.setUpHashMap(this.players);
    }
    
    /**
    * Constructor for PebbleGame class with 2 arguments
    *
    * @param numberOfPlayers the number of players that will participate in the game
    * @param blackBagValues the different weights that the pebbles will have
    */
    public PebbleGame(int numberOfPlayers, int[][] blackBagValues) {
        this.players = new Player[numberOfPlayers];
        this.blackBags = initBlackBags(blackBagValues);
        this.whiteBags = initWhiteBags();
        partnerBags();
        this.gameListeners = new ArrayList();
        for (int i = 0; i < numberOfPlayers; i++) {
            int number = i + 1;
            this.players[i] = new Player("Player " + number);
            this.players[i].registerPlayerListener(this);
            registerGameListener(this.players[i]);
        } 
        this.turn = 0;
        this.gameOver = false;
        this.playerDraw = new HashMap();
        this.setUpHashMap(this.players);  
    }
    
    //Service method that sets each bag its partner bag. So, 'A' to 'X' etc
    private void partnerBags() {
        for (int i = 0; i < this.blackBags.length; i++) {
            BlackBag black = this.blackBags[i];
            WhiteBag white = this.whiteBags[i];
            black.setPartnerBag(white);
            white.setPartnerBag(black);    
            }
        }
    
    //Service method that initialises the WhiteBag objects and returns the array of them
    private WhiteBag[] initWhiteBags() {
        WhiteBag[] bags = new WhiteBag[3];
        Pebble[] pebbleArray = new Pebble[0];
        String[] bagNames = {"A", "B", "C"};
        for (int i = 0; i < 3; i++){
            bags[i] = new WhiteBag(pebbleArray, bagNames[i]);
            }
        return bags;
        }
    
    //Service method that initialises the BlackBag objects and returns the array of them
    private BlackBag[] initBlackBags(int[][] pebbles) {
        BlackBag[] bags = new BlackBag[3];
        String[] bagNames = {"X", "Y", "Z"};
        Pebble[][] pebbleArray = intToPebbleArray(pebbles);
        for (int i = 0; i < 3; i++){
            bags[i] = new BlackBag(pebbleArray[i], bagNames[i]);
            }
        return bags;
        }
    
    //Service method that converts the int values into Pebble objects and returns
    //Pebble[][] since there will be 3 different files we will be accessing for the pebble 
    //weight information
    private Pebble[][] intToPebbleArray(int[][] pebbles) {
        
        Pebble[][] pebbleArray = new Pebble[3][];
        for (int i = 0; i < pebbleArray.length; i++){
            pebbleArray[i] = new Pebble[pebbles[i].length];
            
        }
        
        
        
        for (int i = 0; i < pebbles.length; i++) {
            for (int j = 0; j < pebbles[i].length; j++) {
                try {
                    
                    int length = pebbles[i].length;
                    
                    Pebble pebble = new Pebble(pebbles[i][j]);
                    pebbleArray[i][j] = pebble;
                } catch (IllegalPebbleValueException e) {
                    e.printStackTrace();
                     }
                }
            }
        return pebbleArray;
        }
    
    
    
    /**
    * Method starts the game by instantiating the threads and making them continually
    * draw pebbles until a winner has been declared
    */
    public void startGame() {
        for (Playing player : this.players) {
            player = (Player) player;
            Thread thread = new Thread(player);
            thread.start(); 
        }
        while(!this.gameOver) {
            
            if (allPlayersHaveDrawn()) {
                this.playerDraw = new HashMap();
                setUpHashMap(this.players);
                fireNewTurnEvent();
                }
            
            }         
        System.out.println("This games ran for " + this.turn + " turns");
     }
    
    //Service methhod to verify that all Player objects have drawn
    private boolean allPlayersHaveDrawn() {
        Collection<Player> toCheck;
        toCheck = this.playerDraw.values();
        Object[] toCheck2 = toCheck.toArray();
        boolean toReduce = true;
        for (Object obj: toCheck2){
            boolean changeIt = (Boolean) obj;
            toReduce = toReduce && changeIt;
        }
        return toReduce;
    }
    
    //When the Player objects have been created, they have not drawn yet
    private void setUpHashMap(Playing[] players) {
        for (Playing player : players) {
            this.playerDraw.put(player, false);
        }
    }
    
    
    private synchronized void registerGameListeners(GameListener[] listeners) {
        for (GameListener listener : listeners) {
            this.gameListeners.add(listener);
        }
    }
    
    private synchronized void registerGameListener(GameListener listener) {
        this.gameListeners.add(listener);
    }
    
    /**
    * Method increments the turn count held in the PebbleGame object
    */
    private void incrementTurn() {
        this.turn++;
    }
    
    @Override
    public synchronized void onPlayerAnnouncedWinEvent(PlayerEvent e) {
        this.gameOver = true;
        this.winner = (Playing) e.getSource();
        System.out.println(this.winner.getName() + " has won");
    }

    @Override
    public synchronized void onPlayerFoundEmptyBagEvent(PlayerEvent e) {
        //refill the players partner bag
        Pebble[] pebbles = e.getPartnerBag().getAllPebbles();
        e.getPartnerBag().removeAllPebbles();
        BlackBag blackBag = (BlackBag) e.getCurrentBag();
        blackBag.addPebble(pebbles);
        blackBag.getPebblesAsString();  
    }

    @Override
    public synchronized void onPlayerHasDrawnEvent(PlayerEvent e) {
        //System.out.println(e.getName() + " has drawn");
        Player player = (Player) e.getSource();
        this.playerDraw.put(player, true);
    }
    
    /**
    * Method fires an event that there will be a new turn and increase
    * the turn counter by one
    */
    private void fireNewTurnEvent() {
        for (GameListener listener : this.gameListeners) {
            listener.onTurnChangeEvent(new GameEvent(this));
        }
        this.incrementTurn();
    }
    
    
    public static void main(String[] args) {
        PreGameSetup setup = new PreGameSetup();
        
        setup.askInput();
    }
    
    /**
    * Class holds methods that deals with the functionality of the PLayer Object
    * 
    * @author 660050748, 660049985
    * @version 19/11/2017
    */
    public class Player implements Runnable, GameListener, Playing {

        private BlackBag currentBag;
        private ArrayList pebbles;
        private Random random;
        private String name;
        private PlayerListener playerListener;
        private boolean newTurn = false;
        private int totalWeight;
        private WriteToFile file;
       
        /**
         * Constructor for the Player Class with 3 arguments
         * 
         * @param name String that holds the name of the Player object
         * @param bagChosenByGame BlackBag that is randomly chosen
         */
        public Player(String name, BlackBag bagChosenByGame) {
            this.name = name;
            this.currentBag = bagChosenByGame;
            this.pebbles = new ArrayList(1);
            this.random = new Random();
        }
        
        /**
         * Constructor for the PLayer Class with one argument
         * 
         * @param name String that holds the name of the player
         */
        public Player(String name) {
            this.name = name;
            this.pebbles = new ArrayList(1);
            this.random = new Random();
            this.setNewBag();
            this.setFile("");
        }
        

        //better to have interaction only between two types of Objects
        //in a real game, the bag will be a passive object
        @Override
        public void run() {
            //draw ten pebbles
            for (int i = 0; i < 10; i++) {
                this.drawPebble(this.currentBag);
            }
            while (!PebbleGame.this.gameOver) {
                if (newTurn){
                    if (this.hasWinningHand()) {
                        this.fireAnnounceWinEvent();
                    }
                    else {
                        this.discardPebble();
                        this.setNewBag();
                        this.drawPebble(currentBag);
                    }
                } 
            }
            System.out.println(this.name + " has stopped playing");
        }
        
        /**
         * Method to get the name of the Player object
         * 
         * @return String the name of the player object
         */
        public String getName() {
            return this.name;
        }
       
        public synchronized void registerPlayerListener(PlayerListener listener) {
            this.playerListener = listener;
        }
        
        public synchronized void deregisterPlayerListener() {
            this.playerListener = null;
        }

        /**
         * Method to set a new bag for the player to draw from. If the Bag
         * is empty, an event will be fired off telling the PebbleGame to refill
         * the Bag with Pebbles again and the Player selects a new Bag.
         */
        public void setNewBag() {
            int high = PebbleGame.this.blackBags.length;
            int rand = this.random.nextInt(high);
            BlackBag bag = PebbleGame.this.blackBags[rand];
            
            if (bag.isEmpty()) {
                
                fireFoundEmptyBagEvent();
                setNewBag();
            }
            else {
                this.currentBag = PebbleGame.this.blackBags[rand];
            }
           
            
        }

        /**
         * Method that adds a pebble from a BlackBag object at random
         * to the player's list of pebbles held
         * 
         * @param bag BlackBag object that holds pebbles
         */
        public void drawPebble(BlackBag bag){
            if (bag.isEmpty() == false) {
                Pebble rand = bag.getRandomPebble(); //returns null
                this.pebbles.add(rand);
                
                this.drawnMessage(bag, rand.getValue());
                fireHasDrawnEvent();

            }   else {//tell the pebblegame the bag is empty
                    
                    
                    this.fireFoundEmptyBagEvent();
                    setNewBag();
                    Pebble rand = bag.getRandomPebble(); //returns null
                    this.pebbles.add(rand);

                    this.drawnMessage(bag, rand.getValue());
                    handMessage();
                    
                }    
        }

        
        //Service method to calculate the current weight of all pebbles
        //held in the player's hand.
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
         * 
         * @return int the total weight the player has in its hand
         */
        public int getTotalWeight(){
            this.calculateTotalWeight();
            return this.totalWeight;
        }
        
        /**
         * Method to return the current Bag that the player has drawn from
         * 
         * @return Bag that has been previously been used to draw a Pebble 
         */
        public BlackBag getCurrentBag() {
            return this.currentBag;
        }

        //Service method that checks if the player has a total weight of 100
        //in its hand
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
            handMessage();
            this.setNewBag();
        }

        /**
         * Method to set the file path of the file to which output of
         * this player is written
         * 
         * @param filePath that contains the path for the required file
         */
        public void setFile(String filePath) {
            filePath += this.name + "_output.txt";
            try {
                this.file = new WriteToFile(filePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        /**
         * Method to write a message about which value was discarded into which bag
         * 
         * @param bag The Bag that the Pebble has been discarded to
         * @param value The weight of the Pebble that has been discarded
         */
        public void discardedMessage(Bag bag, int value) {
            String output = this.name + " has discarded a " + value + " into bag " + bag.getName();
            this.writeToFile(output);
        }

        /**
         * Method to write a message about which value was drawn from which bag
         * 
         * @param bag The Bag that the Pebble has been drawn from
         * @param value The weight of the Pebble that has been drawn
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

        /**
         * Method to write to a file
         * 
         * @param output The String that will be written to the file 
         */
        public void writeToFile(String output) {
            try {
                this.file.writeLineIntoFile(output);
            } catch (IOException e) {   
            }
        }
        
        //Service method that gets the hand of the player as an array of ints
        //representing the weights of the pebbles
        private int[] getHandAsInt() {
            Pebble[] pebbles = this.getHand();
            int[] integers = new int[pebbles.length];
            for (int i = 0; i < pebbles.length; i++) {
                integers[i] = pebbles[i].getValue();
            }
            return integers;
        }
        
        //Service method that represents how the player choses to discard a
        //pebble from its hand
        private Pebble choosePebble(){
            int totalPoints = this.getTotalWeight();
            if (totalPoints > 100)
                return this.choosePebbleOver();
            else
                return this.choosePebbleUnder();
                }

        //Service method that discards the largest weight if total hand 
        //is over 100
        private Pebble choosePebbleOver(){
           Pebble[] temp = this.getHand();
           Pebble toDiscard = temp[0];
           for(Pebble pebble: temp){
               if (pebble.getValue() > toDiscard.getValue())
                   toDiscard = pebble;
           }
           return toDiscard;
        }

        //Service method that discards the smallest weight if the total
        //hand is under 100
        private Pebble choosePebbleUnder(){
           Pebble[] temp = this.getHand();
           Pebble toDiscard = temp[0];
           for(Pebble pebble: temp){
               if (pebble.getValue() < toDiscard.getValue())
                   toDiscard = pebble;
            }
           return toDiscard;
        }

        //Service method that gets the hand of the player as an array
        //of Pebble objects
        private Pebble[] getHand(){
            removeNulls();
            Object[] tempArray = this.pebbles.toArray();
            Pebble[] toUse = new Pebble[tempArray.length];
            for(int i = 0; i < tempArray.length;i++){
                toUse[i] = (Pebble) tempArray[i];
            }
            return toUse;
        }
        
        //Service method to remove the nulls in a collection
        private void removeNulls() {
            this.pebbles.removeAll(Collections.singleton(null));
        }
        

        @Override
        public void onTurnChangeEvent(GameEvent e) {
           this.newTurn = true;
        }

        /**
         * Method that fires an event once a Player object has won
         * to tell the PebbleGame
         */
        public void fireAnnounceWinEvent() {
            System.out.println(this.name + " has won");
            PlayerEvent event = new PlayerEvent(this);
            this.playerListener.onPlayerAnnouncedWinEvent(event);
        }

        /**
         * Method that fires an event for the PebbleGame to tell it
         * that a BlackBag is empty and needs refilling
         */
        public void fireFoundEmptyBagEvent() {
            
            
            PlayerEvent event = new PlayerEvent(this);
            this.playerListener.onPlayerFoundEmptyBagEvent(event);  
        }

        /**
         * Method that fires an event once the Player has drawn to tell
         * the PebbleGame that its turn has been used.
         */
        public void fireHasDrawnEvent() {
            PlayerEvent event = new PlayerEvent(this);
            this.playerListener.onPlayerHasDrawnEvent(event);
        }
    }
}