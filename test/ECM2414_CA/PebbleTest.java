/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecm1414_ca;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author 660050748, 660049985
 */
public class PebbleTest {
    
    
    public PebbleTest() {
    }
    
    @BeforeClass
    public static void beforeClass() {
        System.out.println("Testing class Pebble");
    }
    
    /**
     * Test of getValue method, of class Pebble.
     * Asssert that the positive value passed into the pebble
     * is equal to the one returned by getValue method
     */
    @Test
    public void testGetValuePositive() {
        int value = 25;
        
        
        try {
            assertEquals(value, new Pebble(25).getValue());
        } catch (IllegalPebbleValueException ex) {
            fail("Should not have thrown an exception");
        }
        
    }
    
    /**
     * Test of getValue method, of class Pebble.
     * Passes in an illegal value and expects to catch
     * IllegalPebbleValueException.
     */
    @Test
    public void testGetValueZero() {
        int value = 0;
        
        
        try {
            assertEquals(value, new Pebble(value).getValue());
            fail("Should have thrown an exception");
        } catch (IllegalPebbleValueException ex) {
            assertTrue(true);
        }
    }
    
    /**
     * Test of getValue method, of class Pebble.
     * Passes in a negative value and expects to catch
     * IllegalPebbleValueException.
     */
    @Test
    public void testGetValueNegative(){
        int value = -25;
       
        
        try {
            assertEquals(value, new Pebble(value).getValue());
            fail("Should have thrown an exception");
        } catch (IllegalPebbleValueException ex) {
            assertTrue(true);
        } 
    }
    
    /**
     * Test of getValue method, of class Pebble.
     * Passes in the maximum allowed value for an integer.
     */
    @Test
    public void testGetValueBoundaryPositive(){
        int value = Integer.MAX_VALUE;
        
        
        try {
            assertEquals(value, new Pebble(value).getValue());
        } catch (IllegalPebbleValueException ex) {
            fail("Should not have thrown an exception");
        } 
    }
    
    /**
     * Test of getValue method, of class Pebble.
     * Passes in the minimum allowed value for an integer.
     * 
     */
    @Test
    public void testGetValueBoundaryNegative(){
        int value = Integer.MIN_VALUE;
        
        
        
        try {
            assertEquals(value, new Pebble(value).getValue());
            fail("Should have thrown an exception");
        } catch (IllegalPebbleValueException ex) {
            assertTrue(true);
        } 
    }
    
    
}
