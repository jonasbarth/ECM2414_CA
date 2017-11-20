/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecm1414_ca;

import ecm1414_ca.listenerEvent.GameEvent;
import ecm1414_ca.listenerEvent.PlayerEvent;
import ecm1414_ca.listenerEvent.PlayerListener;

/**
 *
 * @author jb901
 */
public class MockPlayer implements Playing{
    
    private PlayerListener listener;
    
    @Override
    public String getName() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void registerPlayerListener(PlayerListener listener) {
        this.listener = listener;
    }

    @Override
    public void deregisterPlayerListener() {
        this.listener = null;
    }

    @Override
    public void setNewBag() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void drawPebble(BlackBag bag) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getTotalWeight() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Bag getCurrentBag() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void discardPebble() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setFile(String filePath) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void discardedMessage(Bag bag, int value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void drawnMessage(Bag bag, int value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void handMessage() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void writeToFile(String output) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void onTurnChangeEvent(GameEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
        
    }

    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void fireAnnounceWinEvent() {
        this.listener.onPlayerAnnouncedWinEvent(new PlayerEvent(this));
    }

    @Override
    public void fireFoundEmptyBagEvent() {
        this.listener.onPlayerFoundEmptyBagEvent(new PlayerEvent(this));
    }

    @Override
    public void fireHasDrawnEvent() {
        this.listener.onPlayerHasDrawnEvent(new PlayerEvent(this));
    }
    
}
