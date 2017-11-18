/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ECM2414_CA;

import ECM2414_CA.listenerEvent.GameEvent;
import ECM2414_CA.listenerEvent.GameListener;
import ECM2414_CA.listenerEvent.PlayerEvent;
import ECM2414_CA.listenerEvent.PlayerListener;
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
 *
 * @author 
 */
public class PebbleGame implements PlayerListener, Runnable, GameManager {
    
    private Player[] players;
    private BlackBag[] blackBags;
    private WhiteBag[] whiteBags;
    private int turn;
    private volatile boolean gameOver;
    private Player winner;
    private ArrayList<GameListener> gameListeners;
    private HashMap playerDraw;
    
    
    public PebbleGame(Player[] players, BlackBag[] blackBags, WhiteBag[] whiteBags) {
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
    
   
    
  
    private void partnerBags() {
        for (int i = 0; i < this.blackBags.length; i++) {
            BlackBag black = this.blackBags[i];
            WhiteBag white = this.whiteBags[i];
            black.setPartnerBag(white);
            white.setPartnerBag(black);
            
        }
    }

    private WhiteBag[] initWhiteBags() {
        WhiteBag[] bags = new WhiteBag[3];
        Pebble[] pebbleArray = new Pebble[0];
        String[] bagNames = {"A", "B", "C"};
        for (int i = 0; i < 3; i++){
            bags[i] = new WhiteBag(pebbleArray, bagNames[i]);
        }
        return bags;
    }
    
    private BlackBag[] initBlackBags(int[][] pebbles) {
        
        BlackBag[] bags = new BlackBag[3];
        String[] bagNames = {"X", "Y", "Z"};
        Pebble[][] pebbleArray = intToPebbleArray(pebbles);
        
        for (int i = 0; i < 3; i++){
            bags[i] = new BlackBag(pebbleArray[i], bagNames[i]);
        }
        return bags;
    }
    
    private Pebble[][] intToPebbleArray(int[][] pebbles) {
        Pebble[][] pebbleArray = new Pebble[3][this.players.length * 15];
       
        for (int i = 0; i < pebbles.length; i++) {
            
            for (int j = 0; j < pebbles[i].length; j++) {
                try {
                    int length = pebbles[i].length;
                    
                    Pebble pebble = new Pebble(pebbles[i][j]);
                    //System.out.println(pebble.getValue());
                    
                    pebbleArray[i][j] = pebble;
                } catch (IllegalPebbleValueException e) {
                    e.printStackTrace();
                }
                
            }
            
        }
        return pebbleArray;
    }
    @Override
    public void run() {
        
        for (Player player : this.players) {
            Thread thread = new Thread(player);
            thread.start();
            
        }
        while(!this.gameOver) {
            
            if (allPlayersHaveDrawn()) {
                fireNewTurnEvent();
            }
        }
        
        
        
        
        
        
    }
    
    public void startGame() {
        for (Player player : this.players) {
            Thread thread = new Thread(player);
            thread.start();
            
        }
        while(!this.gameOver) {
            
            if (allPlayersHaveDrawn()) {
                this.playerDraw = new HashMap();
                setUpHashMap(this.players);
                //System.out.println("All players have drawn");
                fireNewTurnEvent();
            }
            
        }
                  
        System.out.println("This games was " + this.turn);

    }
    
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
    
    private void setUpHashMap(Player[] players) {
        for (Player player : players) {
            this.playerDraw.put(player, false);
        }
    }
    
    public synchronized void registerGameListeners(GameListener[] listeners) {
        for (GameListener listener : listeners) {
            this.gameListeners.add(listener);
        }
    }
    
    public synchronized void registerGameListener(GameListener listener) {
        this.gameListeners.add(listener);
    }
    
    public void incrementTurn() {
        //System.out.println("Current turn: " + this.turn);
        this.turn++;
    }
    
  
    

    @Override
    public synchronized void onPlayerAnnouncedWinEvent(PlayerEvent e) {
        this.gameOver = true;
        System.out.println(e.getSource());
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
    
    public void fireNewTurnEvent() {
        for (GameListener listener : this.gameListeners) {
            listener.onTurnChangeEvent(new GameEvent(this));
        }
        this.incrementTurn();
    }

    

   
    
    
    public class Player implements Runnable, GameListener, Playing {

        private BlackBag currentBag;
        private ArrayList pebbles;
        private Random random;
        private String name;
        private PlayerListener playerListener;
        
        private volatile boolean newTurn = false;
        private int totalWeight;
        private WriteToFile file;
       
        



        public Player(String name, BlackBag bagChosenByGame) {
            this.name = name;
            this.currentBag = bagChosenByGame;
            this.pebbles = new ArrayList(1);
            this.random = new Random();
        }
        
        public Player(String name) {
            this.name = name;
            
            this.pebbles = new ArrayList(1);
            this.random = new Random();
            this.setNewBag();
            this.setFile("");
        }
        

        //31/10/2017 better to have interaction only between two types of Objects
        //in a real game, the bag will be a passive object
        @Override
        public void run() {
            //draw ten pebbles
            for (int i = 0; i < 10; i++) {
                //System.out.println(this.currentBag);
                this.drawPebble(this.currentBag);
            }
            //fire an event that we have drawn 10 pebbles to start the game
            while (!PebbleGame.this.gameOver) {
                //here the player listens if some other player has won
                if (newTurn){
                    //here the player should be listening if there is a new turn 

                    if (this.hasWinningHand()) {
                        this.fireAnnounceWinEvent();

                    // fire event to tell pebblegame
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
        
        public String getName() {
            return this.name;
        }
        
        public synchronized void registerPlayerListener(PlayerListener listener) {
            this.playerListener = listener;
        }
        
        public synchronized void deregisterPlayerListener() {
            this.playerListener = null;
        }

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
                    Pebble rand = bag.getRandomPebble(); //returns null
                    this.pebbles.add(rand);

                    this.drawnMessage(bag, rand.getValue());
                    handMessage();
                    
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
        
        public Bag getCurrentBag() {
            return this.currentBag;
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
            handMessage();
            this.setNewBag();

        }

        /**
         * Method to set the file path of the file to which output of
         * this player is written
         * @param filePath 
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
            //String output = PebbleGame.this.turn + " " + this.name + " has drawn a " + value + " from bag " + bag.getName();
            String output = this.name + " has drawn a " + value + " from bag " + bag.getName();
            //System.out.println(output);
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
            //System.out.println(output);
            try {
                this.file.writeLineIntoFile(output);
            } catch (IOException e) {
                
            }

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
           //System.out.println(Arrays.toString(temp));
           Pebble toDiscard = temp[0];
           for(Pebble pebble: temp){
               if (pebble.getValue() < toDiscard.getValue())
                   toDiscard = pebble;
            }
           return toDiscard;
        }

        private Pebble[] getHand(){
            removeNulls();
            Object[] tempArray = this.pebbles.toArray();
            Pebble[] toUse = new Pebble[tempArray.length];
            for(int i = 0; i < tempArray.length;i++){
                toUse[i] = (Pebble) tempArray[i];
            }
            return toUse;
        }
        
        private void removeNulls() {
            this.pebbles.removeAll(Collections.singleton(null));
        }
        

        @Override
        public void onTurnChangeEvent(GameEvent e) {
           this.newTurn = true;
        }

        public void fireAnnounceWinEvent() {
            System.out.println(this.name + " has won");
            PlayerEvent event = new PlayerEvent(this);
            this.playerListener.onPlayerAnnouncedWinEvent(event);
        }

        public void fireFoundEmptyBagEvent() {
            //System.out.println("Bag is empty");
            //PebbleGame.this.gameOver = true;
            PlayerEvent event = new PlayerEvent(this);
            this.playerListener.onPlayerFoundEmptyBagEvent(event);
            
        }

        public void fireHasDrawnEvent() {
            
            //System.out.println(this.name + " has drawn : " + PebbleGame.this.turn);
            PlayerEvent event = new PlayerEvent(this);
            this.playerListener.onPlayerHasDrawnEvent(event);
        }


    }


    
}
