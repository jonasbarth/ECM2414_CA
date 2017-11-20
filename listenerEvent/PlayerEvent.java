/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecm1414_ca.listenerEvent;
import ecm1414_ca.Bag;
import ecm1414_ca.PebbleGame.Player;
import ecm1414_ca.Playing;
import java.util.EventObject;

/**
 *
 * @author Jonas
 */
public class PlayerEvent extends EventObject {
 
    
    public PlayerEvent(Object source) {
        super(source);
    }
    
    public Bag getPartnerBag() {
        Playing player = (Playing) this.source;
        return player.getCurrentBag().getPartnerBag();
        
    }
    
    public Bag getCurrentBag() {
        Playing player = (Playing) this.source;
        return player.getCurrentBag();
    }
    
    public String getName() {
        Playing player = (Playing) this.source;
        return player.getName();
    }
    
    
}
