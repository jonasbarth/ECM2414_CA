/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecm1414_ca;



/**
 * This class consists of a method that operate to retrieve the weight of the Pebble object.
 *
 * @author 660050748, 660049985
 * @version 17/11/2017
 */
public class Pebble implements PebbleManager{
    //Final as we do not want the weight of the pebble to change during the game
    private final int value;
    
    public Pebble(int value) throws IllegalPebbleValueException {
        //to verify that only pebbles with weight above 0 are used.
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
