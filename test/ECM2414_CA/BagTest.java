/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ECM2414_CA;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jb901
 */
public class BagTest {
    private Pebble pebble;
    private Pebble[] pebbles;
    private int[] pebbleValues;
    private int size;
    private String name;
    
    public BagTest() {
    }
    
    
    
    
    public void setUp() {
        this.name = "X";
        this.size = 5;
        this.pebbles = new Pebble[size];
        this.pebbleValues = new int[size];
        
        for (int i = 0; i < this.size; i++){
            try {
                pebbles[i] = new Pebble(i + 1);
                this.pebbleValues[i] = i + 1;
            } catch (IllegalPebbleValueException e) {
                
            }
            
        }
        
        try {
            this.pebble = new Pebble(10);
        } catch (IllegalPebbleValueException ex) {
            
        }
    }
    
    
    public void tearDown() {
        this.size = 0;
        this.pebbles = null;
        this.pebble = null;
        this.name = null;
        this.pebbleValues = null;
    }
    /**
     * Test of getName method, of class Bag.
     */
    @Test
    public void testGetName() {
        System.out.println("Test getName");
        
        setUp();
        BlackBag bag = new BlackBag(this.pebbles, this.name);
        assertEquals(this.name, bag.getName());
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
        tearDown();
    }

    

    /**
     * Test of getPartnerBag method, of class Bag.
     */
    @Test
    public void testSetGetPartnerBag() {
        System.out.println("setGetPartnerBag");
        
        String partnerValue = "A";
        setUp();
        BlackBag bag = new BlackBag(this.pebbles, this.name);
        WhiteBag partner = new WhiteBag(this.pebbles, partnerValue);
        bag.setPartnerBag(partner);
        assertEquals(partner, bag.getPartnerBag());
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
        tearDown();
    }

    /**
     * Test of getAllPebbles method, of class Bag.
     */
    @Test
    public void testGetAllPebbles() {
        System.out.println("getAllPebbles");
        
        setUp();
        Bag bag = new BlackBag(this.pebbles, name);
        Pebble[] expResult = this.pebbles;
        Pebble[] result = bag.getAllPebbles();
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
        tearDown();
    }
   
    /**
     * Test of addPebble method, of class Bag.
     */
     @Test
    public void testAddPebble_Pebble() {
        setUp();
        System.out.println("addPebble");
        Bag bag = new BlackBag(this.pebbles, this.name);
        bag.addPebble(this.pebble);
        
        Pebble[] pebbles = bag.getAllPebbles();
        
        for (Pebble p : pebbles) {
            if (p == this.pebble) {
                assertEquals(p, this.pebble);
            }
        }
        tearDown();
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of addPebble method, of class Bag.
     */
    @Test
    public void testAddPebble_PebbleArr() {
        setUp();
        System.out.println("addPebble");
        Pebble[] pebblesEmpty = new Pebble[2];
        Bag bag = new BlackBag(pebblesEmpty, this.name);
        bag.addPebble(this.pebbles);
        Pebble[] result = bag.getAllPebbles();
        Assert.assertArrayEquals(result, this.pebbles);
        
        tearDown();
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
    

    
    /**
     * Test of removeAllPebbles method, of class Bag.
     */
    @Test
    public void testRemoveAllPebbles() {
        setUp();
        System.out.println("removeAllPebbles");
        
        Bag bag = new BlackBag(this.pebbles, this.name);
        bag.removeAllPebbles();
        assertEquals(0, bag.getAllPebbles().length);
        
        tearDown();
    }

    /**
     * Test of getPebblesAsString method, of class Bag.
     */
    @Test
    public void testGetPebblesAsString() {
        setUp();
        System.out.println("getPebblesAsString");
        String expResult = Arrays.toString(this.pebbleValues);
        Bag bag = new BlackBag(this.pebbles, this.name);
        
        
        assertEquals(expResult, bag.getPebblesAsString());
        tearDown();
    }

    /**
     * Test of getRandomPebble method, of class Bag.
     */
    @Test
    public void testGetRandomPebble() {
        setUp();
        System.out.println("getRandomPebble");
        
        
        Pebble[] pebbleArr = new Pebble[2];
        HashMap map = new HashMap();
        
        for (int i = 0; i < pebbleArr.length; i++){
            try {
                pebbleArr[i] = new Pebble(i + 1);
                map.put(pebbleArr[i], 0.0);
                
                
            } catch (IllegalPebbleValueException ex) {
                fail("No negative values allowed");
            }
        }
        Bag bag = new BlackBag(pebbleArr, this.name);
        System.out.println(bag.getPebblesAsString());
        
        Pebble pebble;
        int runs = 1000000;
        for (int i = 0; i < runs; i++){
            
            pebble = bag.getRandomPebble();
            double val = (double) map.get(pebble);
            map.put(pebble, val + 1.0);
            if (i%2 == 0) {
                bag.addPebble(pebbleArr);
            }
        }
        double res1 = (double) map.get(pebbleArr[0]);
        double res2 = (double) map.get(pebbleArr[1]);
        double tolerance = 5.0;
        assertEquals(res1, res2, tolerance);
        
        
    }
 
    public class BagImpl extends Bag {

        public BagImpl() {
            super("");
        }
    }
    
    
    
}
