/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ECM2414_CA.listenerEvent;
import ECM2414_CA.Bag;
import java.util.EventObject;

/**
 *
 * @author Jonas
 */
public class PlayerEvent extends EventObject {
    
    private Bag bag;
    
    public PlayerEvent(Object source, Bag bag) {
        super(source);
        this.bag = bag;
    }
    
    public Bag getPartnerBag() {
        return this.bag.getPartnerBag();
        
    }
    
    
}
