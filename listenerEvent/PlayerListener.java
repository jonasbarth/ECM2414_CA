/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ECM2414_CA.listenerEvent;

import java.util.EventListener;


/**
 *
 * @author Jonas
 */
public interface PlayerListener extends EventListener {
    
    public void onPlayerAnnouncedWinEvent(PlayerEvent e);
    
    public void onPlayerFoundEmptyBagEvent(PlayerEvent e);
    
    public void onPlayerHasDrawnEvent(PlayerEvent e);
    
    public void onPlayerNeedsNewBagEvent(PlayerEvent e);
    
    
}
