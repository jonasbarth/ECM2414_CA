/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ECM2414_CA;



/**
 * Class that adds additional functionality to the super class Bag such as adding an Array of pebbles to its
 * super field 'pebbles' and a way to determine whether the BlackBag object has no more pebble objects.
 *
 * @author 660050748, 660049985
 */
public class BlackBag extends Bag{
    
    public BlackBag(Pebble[] pebbles, WhiteBag partnerBag, String name) {
        super(pebbles, partnerBag, name);
    }
    
    public BlackBag(Pebble[] pebbles, String name) {
        super(pebbles, name);
    }
    
    /**
    * Methods adds all of the pebbles in the array to its super field. This method will
    * be used when the bag is empty and it will be refilled from the pebbles in its partner WhiteBag 
    *
    * @param pebbles An array of Pebble objects
    */
    public synchronized void addAllPebbles(Pebble[] pebbles) {
        for (Pebble p : pebbles) {
            super.addPebble(p);
        }
    }
    
    /**
    * Method to determine if the BlackBag object has no more Pebble objects in its super field 'pebbles'
    *
    * @param boolean True if it is empty, False if it is not empty
    **/
    public boolean isEmpty() {
        if (this.getAllPebbles().length == 0){
            return true;
        }
        return false;
    }
    
    
    }
