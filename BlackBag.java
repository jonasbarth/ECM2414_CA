/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecm1414_ca;



/**
 * Class that adds additional functionality to the super class Bag such as adding an Array of pebbles to its
 * super field 'pebbles' and a way to determine whether the BlackBag object has no more pebble objects.
 *
 * @author 660050748, 660049985
 */
public class BlackBag extends Bag{
    
    public BlackBag(Pebble[] pebbles, String name) {
        super(pebbles, name);
    }
    
    /**
    * Method to determine if the BlackBag object has no more Pebble objects in its super field 'pebbles'
    *
    * @return boolean True if it is empty, False if it is not empty
    **/
    public synchronized boolean isEmpty() {
        if (this.getAllPebbles().length == 0){
            return true;
        }
        return false;
    }
    
    /**
     * Method to set the partner bag of the BlackBag object.
     * @param bag the WhiteBag object be the partner of this bag.
     */
    public void setPartnerBag(WhiteBag bag){
        this.partnerBag = bag;
    }
    
    
    }
