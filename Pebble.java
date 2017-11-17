/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ECM2414_CA;



/**
 *
 * @author 
 */
public class Pebble {
    private final int value;
    
    public Pebble(int value) throws IllegalPebbleValueException {
        if (value > 0) {
            this.value = value;
        }
        else {
            throw new IllegalPebbleValueException("Pebble must have a positive value");
        }
        
    }
    
    /*
    Method to return value of pebble
    
    @return int value of pebble
    */
    public int getValue() {
        return this.value;
        
    }
}
