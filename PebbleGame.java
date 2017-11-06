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
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;



/**
 *
 * @author 
 */
public class PebbleGame implements PlayerListener, Runnable {
    
    private Player[] players;
    private BlackBag[] blackBags;
    private WhiteBag[] whiteBags;
    private int turn;
    private boolean gameOver;
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
    
    public void registerAllPlayerListeners(GameListener[] listeners) {
        for (GameListener listener : listeners) {
            registerGameListener(listener);
        }
    }
    
    private void registerGameListener(GameListener listener) {
        this.gameListeners.add(listener);
    }
    
    public void incrementTurn() {
        this.turn++;
    }
    
  
    

    @Override
    public synchronized void onPlayerAnnouncedWinEvent(PlayerEvent e) {
        this.gameOver = true;
    }

    @Override
    public synchronized void onPlayerFoundEmptyBagEvent(PlayerEvent e) {
        //refill the players partner bag
        Pebble[] pebbles = e.getPartnerBag().getAllPebbles();
        e.getPartnerBag().removeAllPebbles();
        BlackBag blackBag = (BlackBag) e.getSource();
        blackBag.addAllPebbles(pebbles);
        
    }

    @Override
    public synchronized void onPlayerHasDrawnEvent(PlayerEvent e) {
        Player player = (Player) e.getSource();
        this.playerDraw.put(player, true);
    }
    
    public void fireNewTurnEvent() {
        for (GameListener listener : this.gameListeners) {
            listener.onTurnChangeEvent(new GameEvent(this));
        }
        this.incrementTurn();
    }

    

   
    
    
    public class Player implements Runnable, GameListener {

        private BlackBag currentBag;
        private ArrayList pebbles;
        private Random random;
        private String name;
        private PlayerListener playerListener;
        private boolean continuePlaying = false;
        private int totalWeight;
        private WriteToFile file;
        private boolean newTurn = false;



        public Player(String name, BlackBag bagChosenByGame) {
            this.name = name;
            this.currentBag = bagChosenByGame;
            this.pebbles = new ArrayList();
            this.random = new Random();
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
        }
        
        public void registerPlayerListener(PlayerListener listener) {
            this.playerListener = listener;
        }
        
        public void deregisterPlayerListener() {
            this.playerListener = null;
        }

        public void setNewBag() {
            this.currentBag = PebbleGame.this.blackBags[random.nextInt(2)];
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
            this.setNewBag();

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
        public void onTurnChangeEvent(GameEvent e) {
           this.newTurn = true;
        }

        public void fireAnnounceWinEvent() {

        }

        public void fireFoundEmptyBagEvent() {

        }

        public void fireHasDrawnEvent() {

        }


    }


    
}
