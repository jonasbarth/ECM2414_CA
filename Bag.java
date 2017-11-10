/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ECM2414_CA;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
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
    private String name;
    
    
    /**
     * 
     * @param pebbles
     * @param partnerBag 
     */
    public Bag(Pebble[] pebbles, Bag partnerBag, String name) {
        this.pebbles = new ArrayList(1);
        for (Pebble pebble : pebbles) {
            this.pebbles.add(pebble);
        }
        setPartnerBag(partnerBag);
        Random random = new Random();
        this.random = random;
        
        this.name = name;
    }
    
    public Bag(Pebble[] pebbles, String name) {
        this.pebbles = new ArrayList(1);
        for (Pebble pebble : pebbles) {
            this.pebbles.add(pebble);
        }
        //System.out.println("Pebble constructor " + this.pebbles.toString());
        //this.pebbles.trimToSize();
        
        this.name = name;
        this.random = new Random();
    }
    
    public Bag(String name) {
        this.pebbles = new ArrayList(1);
        this.name = name;
        this.random = new Random();
    }
    
    public String getName() {
        return this.name;
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
        //this.pebbles.trimToSize();
        removeNulls();
        Object[] object = this.pebbles.toArray();
        object = takeNullAway(object);
        Pebble[] pebbles = new Pebble[object.length]; //pebble array to hold pebbles from ArrayList
        
        for (int i = 0; i < object.length; i++) {
            pebbles[i] = (Pebble) object[i];
        }
        return pebbles;
    }

    private Object[] takeNullAway(Object[] object) {
        int count = object.length;
        for (Object o : object) {
            if (o == null) {
                count--;
            }
        }
        Object[] obj = new Object[count];
        for (int i = 0; i < count; i++) {
            obj[i] = object[i];
        }
        return obj;
        
    }
    
    private void removeNulls() {
        this.pebbles.removeAll(Collections.singleton(null));
    }
    
    
    /**
     * Method to add a certain pebble to bag
     * @param pebble 
     */
    public synchronized void addPebble(Pebble pebble) {
        this.pebbles.add(pebble);
        this.pebbles.trimToSize();
    }
    
    public synchronized void addPebble(Pebble[] pebbles){
        this.pebbles.addAll(Arrays.asList(pebbles));
        this.pebbles.trimToSize();
    }
    
    /**
     * Method to remove a pebble from bag
     * @param pebble 
     */
    public synchronized void removePebble(Pebble pebble) {
        
        this.pebbles.remove(pebble);
        this.pebbles.trimToSize();
    }
    
    public synchronized void removeAllPebbles() {
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
    public synchronized Pebble getRandomPebble(){
        removeNulls();
        Object[] temp = this.pebbles.toArray();
        
        //System.out.println(this.random);
        int randNum = this.random.nextInt(temp.length); //+ 1;
        
        Pebble temp2 = (Pebble) temp[randNum];
        
        this.removePebble(temp2);
        return temp2;   
    }
    
    
    /**
     * Service method to return value of all pebbles in the bag in an int array
     * @return 
     */
    private int[] getIntPebbleArray() {
        removeNulls();
        Object[] object = this.pebbles.toArray();
        Object[] obj = takeNullAway(object);
        int[] ints = new int[obj.length]; //int array to hold value of each pebble
        
        for (int i = 0; i < obj.length; i++) {
            //System.out.println(obj[i]);
            Pebble pebble = (Pebble) obj[i];
            ints[i] = pebble.getValue();
        }
        
        return ints;
    }
}
