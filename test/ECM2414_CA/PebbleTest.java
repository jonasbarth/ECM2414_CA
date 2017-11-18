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
public class PebbleTest {
    private MockPebble pebble;
    
    public PebbleTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    public void setUp() {
        this.pebble = new MockPebble();
    }
    
    public void tearDown() {
        this.pebble = null;
    }
    /**
     * Test of getValue method, of class Pebble.
     */
    @Test
    public void testGetValuePositive() {
        int value = 25;
        System.out.println("Testing getValue with value " + value);
        
        try {
            assertEquals(value, new Pebble(25).getValue());
        } catch (IllegalPebbleValueException ex) {
            fail("Should not have thrown an exception");
        }
        
    }
    
    @Test
    public void testGetValueZero() {
        int value = 0;
        System.out.println("Testing getValue with value " + value);
        
        try {
            assertEquals(value, new Pebble(value).getValue());
            fail("Should have thrown an exception");
        } catch (IllegalPebbleValueException ex) {
            assertTrue(true);
        }
    }
    
    @Test
    public void testGetValueNegative(){
        int value = -25;
       System.out.println("Testing getValue with value " + value);
        
        try {
            assertEquals(value, new Pebble(value).getValue());
            fail("Should have thrown an exception");
        } catch (IllegalPebbleValueException ex) {
            assertTrue(true);
        } 
    }
    
    @Test
    public void testGetValueBoundaryPositive(){
        int value = Integer.MAX_VALUE;
        System.out.println("Testing getValue with value " + value);
        
        try {
            assertEquals(value, new Pebble(value).getValue());
        } catch (IllegalPebbleValueException ex) {
            fail("Should not have thrown an exception");
        } 
    }
    
    
}
