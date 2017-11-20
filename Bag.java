/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecm1414_ca;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;



/**
 * Class holds methods to manipulate the contents like taking away pebbles, adding pebbles to the bag
 * and getting information from the bag such as the name and partnerBag.
 * @author 660050748, 660049985
 */
public abstract class Bag {
    
    protected Bag partnerBag;
    private ArrayList pebbles;
    private Random random;
    private String name;
    
     /**
     * Constructor for bags when other bag objects have not already been instantiated 
     *
     * @param partnerBag, the bag that is directly linked to a particular Bag object
     * @param name of the bag
     */
    public Bag(Pebble[] pebbles, String name) {
        this.pebbles = new ArrayList(1);
        for (Pebble pebble : pebbles) {
            this.pebbles.add(pebble);
        }
        this.name = name;
        this.random = new Random();
    }
    
     /**
     * Method to get the name of the Bag object
     *
     * @return the name of the Bag which will be of type String
     */
    public String getName() {
        return this.name;
    }
    
    
    
    /**
     * Method to get the partner bag of the current Bag object
     *
     * @return the Bag object that is associated with the current Bag object
     */
    public Bag getPartnerBag() {
        return this.partnerBag;
    }
    
    /**
     * Method to convert the ArrayList containing Pebbles into a Pebble[] and returning
     * that new array which contains the pebbles currently in the Bag object
     * 
     * @return Pebble[] the array of pebbles currently in the Bag object
     */
    public synchronized Pebble[] getAllPebbles() {
        //Due to the nature of ArrayList, if there are less than 10 objects 
        //instantiated with it. The rest will be made up of Null references
        removeNulls();
        Object[] object = this.pebbles.toArray();
        //pebble array to hold pebbles from ArrayList
        Pebble[] pebbles = new Pebble[object.length]; 
        
        for (int i = 0; i < object.length; i++) {
            pebbles[i] = (Pebble) object[i];
        }
        return pebbles;
    }
    
    //Service method to remove any possible null values in the field pebbles, type ArrayList
    private void removeNulls() {
        this.pebbles.removeAll(Collections.singleton(null));
    }
    
    
    /**
     * Method to add a certain pebble to this bag
     * @param pebble to be added to bag because we are discarding to it
     */
    public synchronized void addPebble(Pebble pebble) {
        this.pebbles.add(pebble);
        this.pebbles.trimToSize();
    }
    
    public synchronized void addPebble(Pebble[] pebbles){
        this.pebbles.addAll(Arrays.asList(pebbles));
    }
    
    //Service method to remove a particular pebble from this current bag
    private synchronized void removePebble(Pebble pebble) { 
        this.pebbles.remove(pebble);
        this.pebbles.trimToSize();
    }
    
    /**
     * Method to remove all pebbles from this current bag
     */
    public synchronized void removeAllPebbles() {
        this.pebbles.clear();
    }
    
    /**
     * Returns the value of all pebbles in the bag as a string. Will
     * be used to display the current pebbles in the bag
     * @return String type that have the values of of the pebbles currently in the bag
     */
    public synchronized String getPebblesAsString() {
        return Arrays.toString(getIntPebbleArray());
    }
    
    /**
     * Method selects random pebble in the Pebble Array field and then
     * removes pebble from that array list
     * 
     * @return Pebble the random pebble selected
     */
    public synchronized Pebble getRandomPebble(){
        removeNulls();
        Object[] temp = this.pebbles.toArray();
        int randNum = this.random.nextInt(temp.length);
        Pebble pebbleToBeChosen = (Pebble) temp[randNum];
        
        this.removePebble(pebbleToBeChosen);
        return pebbleToBeChosen;   
    }
  
    // Service method to return value of all pebbles in the bag in an int array 
    private int[] getIntPebbleArray() {
        removeNulls();
        Object[] object = this.pebbles.toArray();
        int[] ints = new int[object.length]; //int array to hold value of each pebble
        
        for (int i = 0; i < object.length; i++) {
            Pebble pebble = (Pebble) object[i];
            ints[i] = pebble.getValue();
        }
        
        return ints;
    }
}
