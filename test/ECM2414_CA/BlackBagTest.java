/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecm1414_ca;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 * Test class which tests the BlackBag class
 * @author 660050748, 660049985
 */
public class BlackBagTest {
    private String name;
    private Pebble[] pebbles;
    
    public BlackBagTest() {
    }
    
    @BeforeClass
    public static void beforeClass() {
        System.out.println("Testing class BlackBag");
    }
    
    @Before
    public void setUp(){
        this.name = "X";
        this.pebbles = new Pebble[2];
    }
    
    @After
    public void tearDown() {
        this.name = null;
        this.pebbles = null;
    }

    

    /**
     * Test of isEmpty method, of class BlackBag.
     * Asserts that isEmpty returns true when
     * an empty array is passed in.
     */
    @Test
    public void testIsEmptyTrue() {
        
        
        //Array to be passed into the bag
        Pebble[] pebbles = new Pebble[2];
        System.out.println("isEmpty");
        BlackBag bag = new BlackBag(pebbles, this.name);
        
        assertTrue(bag.isEmpty());
        
    }
    
    /**
     * Test of isEmpty method, of class BlackBag.
     * Asserts that isEmpty returns false when
     * a populated array is passed in.
     */
    @Test
    public void testIsEmptyFalse() {
        
        Pebble pebble;
        try {
            pebble = new Pebble(2);
            
            //Array to be passed into the bag
            Pebble[] pebbles = {pebble};
            System.out.println("isEmpty");
            BlackBag bag = new BlackBag(pebbles, this.name);
        
            assertFalse(bag.isEmpty());
        } catch (IllegalPebbleValueException ex) {
            //If the pebble is assigned an illegal value
            fail("Illegal pebble value");
        }
        
    }
    
    /**
     * Test of setPartnerBag method, of class BlackBag.
     * Asserts that the WhiteBag object passed into the 
     * setPartnerBag method is returned by getPartnerBag
     * method.
     */
    @Test
    public void testSetPartnerBag() {
        
        
        WhiteBag whiteBag = new WhiteBag(this.pebbles, "A");
        BlackBag blackBag = new BlackBag(this.pebbles, this.name);
        blackBag.setPartnerBag(whiteBag);
        assertEquals(whiteBag, blackBag.getPartnerBag());
        
    }
}
