/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ECM2414_CA;

import ECM2414_CA.listenerEvent.GameListener;

/**
 * Interface to be implemented by the PebbleGame class
 * @author 660050748, 660049985
 */
public interface GameManager {
    
    /**
     * Method to register an array of GameListeners
     * 
     */
    public void registerGameListeners(GameListener[] listeners);
    
    /**
     * Method to register a single GameListener
     * 
     */
    public void registerGameListener(GameListener listener);
    
    
    
    
    
    
    
}
