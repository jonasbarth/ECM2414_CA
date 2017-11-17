/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ECM2414_CA.listenerEvent;
import ECM2414_CA.Bag;
import ECM2414_CA.PebbleGame.Player;
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
        Player player = (Player) this.source;
        return player.getCurrentBag().getPartnerBag();
        
    }
    
    public Bag getCurrentBag() {
        Player player = (Player) this.source;
        return player.getCurrentBag();
    }
    
    public String getName() {
        Player player = (Player) this.source;
        return player.getName();
    }
    
    
}
