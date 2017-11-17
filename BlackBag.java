/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ECM2414_CA;



/**
 *
 * @author Jonas
 */
public class BlackBag extends Bag{
    
    //private boolean isEmpty;
    
    public BlackBag(Pebble[] pebbles, WhiteBag partnerBag, String name) {
        super(pebbles, partnerBag, name);
        //this.isEmpty = false;
    }
    
    public BlackBag(Pebble[] pebbles, String name) {
        super(pebbles, name);
        //this.isEmpty = false;
    }
    
    public synchronized void addAllPebbles(Pebble[] pebbles) {
        for (Pebble p : pebbles) {
            super.addPebble(p);
        }
    }
    
    public boolean isEmpty() {
        if (this.getAllPebbles().length == 0){
            //this.isEmpty = true;
            return true;
        }
        //this.isEmpty = false;
        return false;
    }
    
    
    }
