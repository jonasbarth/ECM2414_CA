/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecm1414_ca;

import ecm1414_ca.listenerEvent.PlayerEvent;
import ecm1414_ca.listenerEvent.PlayerListener;

/**
 *
 * @author jb901
 */
public class MockPlayerListener implements PlayerListener{
    private int counter;
    
    public MockPlayerListener() {
        counter = 0;
    }

    @Override
    public void onPlayerAnnouncedWinEvent(PlayerEvent e) {
        counter += 2;
    }

    @Override
    public void onPlayerFoundEmptyBagEvent(PlayerEvent e) {
        counter += 3;
    }

    @Override
    public void onPlayerHasDrawnEvent(PlayerEvent e) {
         counter++;
    }
    
    public int getCounter() {
        return this.counter;
    }
    
    public void resetCounter() {
        this.counter = 0;
    }
    
}
