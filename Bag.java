/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ECM2414_CA;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;



/**
 *
 * @author 
 */
public abstract class Bag {
    
    //private Pebble[] pebbles;
    private Bag partnerBag;
    private ArrayList pebbles;
    private Random random;
    
    
    /**
     * 
     * @param pebbles
     * @param partnerBag 
     */
    public Bag(Pebble[] pebbles, Bag partnerBag) {
        this.pebbles = new ArrayList();
        for (Pebble pebble : pebbles) {
            this.pebbles.add(pebble);
        }
        setPartnerBag(partnerBag);
        Random random = new Random();
        this.random = random;
    }
    
    public Bag(Pebble[] pebbles) {
        this.pebbles = new ArrayList();
        for (Pebble pebble : pebbles) {
            this.pebbles.add(pebble);
        }
    }
    
    /**
     * Method to set partnerbag
     * @param partnerBag 
     */
    public void setPartnerBag(Bag partnerBag) {
        this.partnerBag = partnerBag;
    }
    
    /**
     * Method to get partner bag
     * @return Bag 
     */
    public Bag getPartnerBag() {
        return this.partnerBag;
    }
    
    /**
     * Method returning array holding pebbles
     * @return Pebble[]
     */
    public Pebble[] getAllPebbles() {
        Object[] object = this.pebbles.toArray();
        Pebble[] pebbles = new Pebble[object.length]; //pebble array to hold pebbles from ArrayList
        
        for (int i = 0; i < object.length; i++) {
            pebbles[i] = (Pebble) object[i];
        }
        return pebbles;
    }
    
    /**
     * Method to add a certain pebble to bag
     * @param pebble 
     */
    public void addPebble(Pebble pebble) {
        this.pebbles.add(pebble);
    }
    
    
    /**
     * Method to remove a pebble from bag
     * @param pebble 
     */
    public void removePebble(Pebble pebble) {
        
        this.pebbles.remove(pebble);
    }
    
    public void removeAllPebbles() {
        this.pebbles.clear();
    }
    
    /**
     * Returns the value of all pebbles in the bag as a string
     * @return String
     */
    public String getPebblesAsString() {
        return Arrays.toString(getIntPebbleArray());
    }
    
    /**
     * Method selects random pebble in the Pebble Array field and then
     * removes pebble from that array list
     * 
     * @return Pebble the random pebble selected
     */
    public Pebble getRandomPebble(){
        Object[] temp = this.pebbles.toArray();
        int randNum = this.random.nextInt(temp.length) + 1;
        Pebble temp2 = (Pebble) temp[randNum];
        this.removePebble(temp2);
        return temp2;   
    }
    
    
    /**
     * Service method to return value of all pebbles in the bag in an int array
     * @return 
     */
    private int[] getIntPebbleArray() {
        Object[] object = this.pebbles.toArray();
        int[] ints = new int[object.length]; //int array to hold value of each pebble
        
        for (int i = 0; i < object.length; i++) {
            Pebble pebble = (Pebble) object[i];
            ints[i] = pebble.getValue();
        }
        
        return ints;
    }
}
