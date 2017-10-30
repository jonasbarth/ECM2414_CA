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
    
    private boolean isEmpty;
    
    public BlackBag(Pebble[] pebbles, WhiteBag partnerBag) {
        super(pebbles, partnerBag);
        this.isEmpty = false;
    }
    
    public void addAllPebbles(Pebble[] pebbles) {
        for (Pebble p : pebbles) {
            super.addPebble(p);
        }
    }
    
    
}
