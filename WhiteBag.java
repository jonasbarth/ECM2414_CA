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
public class WhiteBag extends Bag{
    
    public WhiteBag(Pebble[] pebbles, BlackBag partnerBag, String name) {
        super(pebbles, partnerBag, name);
    }
    
    public WhiteBag(Pebble[] pebbles, String name) {
        super(pebbles, name);
    }
    /*
    public WhiteBag(String name) {
        Pebble[] pebbles = new Pebble[0];
        super(pebbles, name);
    }*/
    
   
}
