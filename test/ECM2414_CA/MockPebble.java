/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ECM2414_CA;

/**
 *
 * @author jb901
 */
public class MockPebble implements PebbleManager {
    
    private int value;
    
    
    @Override
    public int getValue() {
        return this.value;
    }
    
    public void setValue(int value) {
        this.value = value;
    }
    
    public void resetValue(){
        this.value = 0;
    }
}
