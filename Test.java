/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ECM2414_CA;

import java.util.Random;

/**
 *
 * @author Jonas
 */
public class Test {
    
    public static void main(String[] args) {
        Random r = new Random();
        int size = 15;
        Pebble[] pebble = new Pebble[size];
        
        try {
            for (int i = 0; i < size; i++) {
                pebble[i] = new Pebble(r.nextInt(100) + 1);
            }
            Pebble p = new Pebble(99);
            
            BlackBag x = new BlackBag(pebble);
            x.addPebble(p);
            
            System.out.println(x.getPebblesAsString());
            x.removePebble(p);
            
            System.out.println(x.getPebblesAsString());
        }
        catch (IllegalPebbleValueException e) {
            System.out.println(e);
        }
        
    }
}
