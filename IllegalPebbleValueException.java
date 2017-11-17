/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ECM2414_CA;

/**
 * Class for the Exceptions, specifically for when an illegal value of the weight of the pebble has been found.
 *
 * @author 660050748, 660049985
 */
public class IllegalPebbleValueException extends Exception {
    
    public IllegalPebbleValueException (String message) {
        super(message);
    }
}
