/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ECM2414_CA;

import java.util.Arrays;



/**
 *
 * @author Jonas
 */
public abstract class Bag {
    
    private Pebble[] pebbles;
    private Bag partnerBag;
    
    
    /**
     * 
     * @param pebbles
     * @param partnerBag 
     */
    public Bag(Pebble[] pebbles, Bag partnerBag) {
        this.pebbles = pebbles;
        setPartnerBag(partnerBag);
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
        return this.pebbles;
    }
    
    /**
     * Method to add a certain pebble to bag
     * @param pebble 
     */
    public void addPebble(Pebble pebble) {
        for (int i = 0; i < this.pebbles.length; i++) {
            if (this.pebbles[i] == null) {
                this.pebbles[i] = pebble;
                return;
            }
        }
    }
    
    
    /**
     * Method to remove a pebble from bag
     * @param pebble 
     */
    public void removePebble(Pebble pebble) {
        
        for (int i = 0; i < this.pebbles.length; i++) {
            if (this.pebbles[i] == pebble) {
                this.pebbles[i] = null;
                return;
            }
        }
    }
    
    /**
     * Returns
     * @return String
     */
    public String getPebblesAsString() {
        return Arrays.toString(this.pebbles);
    }
    
    /*
    private int[] getIntPebbleArray() {
        
    } */
}
