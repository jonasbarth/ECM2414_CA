/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ECM2414_CA;

import ECM2414_CA.listenerEvent.GameListener;

/**
 *
 * @author jb901
 */
public interface GameManager {
    
    public void registerGameListeners(GameListener[] listeners);
    
    public void registerGameListener(GameListener listener);
    
    
    
    
    
    
    
}
