/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ECM2414_CA;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jb901
 */
public class BlackBagTest {
    private String name;
    
    public BlackBagTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    public void setUp(){
        this.name = "X";
    }
    
    public void tearDown() {
        
    }

    

    /**
     * Test of isEmpty method, of class BlackBag.
     */
    @Test
    public void testIsEmptyTrue() {
        Pebble[] pebbles = new Pebble[2];
        System.out.println("isEmpty");
        BlackBag bag = new BlackBag(pebbles, this.name);
        
        assertTrue(bag.isEmpty());
    }
    
    @Test
    public void testIsEmptyFalse() {
        Pebble pebble;
        try {
            pebble = new Pebble(2);
            Pebble[] pebbles = {pebble};
            System.out.println("isEmpty");
            BlackBag bag = new BlackBag(pebbles, this.name);
        
            assertFalse(bag.isEmpty());
        } catch (IllegalPebbleValueException ex) {
            fail("Illegal pebble value");
        }
        
    }
}
