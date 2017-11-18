/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ECM2414_CA;

import ECM2414_CA.listenerEvent.PlayerListener;

/**
 *
 * @author 660050748, 660049985
 */
public interface Playing {
    
    public String getName();
    
    public void registerPlayerListener(PlayerListener listener);
    
    public void deregisterPlayerListener();
    
    public void setNewBag();
    
    public void drawPebble(BlackBag bag);
    
    public int getTotalWeight();
    
    public Bag getCurrentBag();
    
    public void discardPebble();
    
    public void setFile(String filePath);
    
    public void discardedMessage(Bag bag, int value);
    
    public void drawnMessage(Bag bag, int value);
    
    public void handMessage();
    
    public void writeToFile(String output);
    
    
    
}
