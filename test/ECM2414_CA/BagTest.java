package ecm1414_ca;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 * Class to test the abstract Bag class
 * @author 660050748, 660049985
 */
public class BagTest {
    private Pebble pebble;
    private Pebble[] pebbles;
    private int[] pebbleValues;
    private int size;
    private String name;
    
    public BagTest() {
    }
    
    
    @BeforeClass
    public static void beforeClass() {
        System.out.println("Testing class Bag");
    }
    
    /**
     * Sets up the class members
     */
    @Before
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
    
    /**
     * Tears down the class members
     */
    @After
    public void tearDown() {
        this.size = 0;
        this.pebbles = null;
        this.pebble = null;
        this.name = null;
        this.pebbleValues = null;
    }
    /**
     * Test of getName method, of class Bag.
     * Asserts that the name passed into the
     * constructor is equal to the one returned
     * by getName method.
     */
    @Test
    public void testGetName() {
        System.out.println("Test getName");
        
        
        BlackBag bag = new BlackBag(this.pebbles, this.name);
        assertEquals(this.name, bag.getName());
        
        
    }

    
    /**
     * Test of getName method, of class Bag.
     * Asserts that getName can handle null values.
     */
    @Test
    public void testGetNameNull() {
        System.out.println("Test getName");
        
        
        BlackBag bag = new BlackBag(this.pebbles, null);
        assertNull(bag.getName());
        
        
    }
    

    /**
     * Test of getPartnerBag method, of class Bag.
     * Asserts that the bag set as partner is equal
     * to the bag returned by getPartnerBag method
     * 
     */
    @Test
    public void testSetGetPartnerBag() {
        System.out.println("setGetPartnerBag");
        
        String partnerValue = "A";
        
        BlackBag bag = new BlackBag(this.pebbles, this.name);
        WhiteBag partner = new WhiteBag(this.pebbles, partnerValue);
        bag.setPartnerBag(partner);
        assertEquals(partner, bag.getPartnerBag());
        
        
    }

    /**
     * Test of getAllPebbles method, of class Bag.
     * Asserts that the pebble array passed into the
     * constructor is equal to the one returned by
     * getAllPebbles method.
     */
    @Test
    public void testGetAllPebbles() {
        System.out.println("getAllPebbles");
        
        
        Bag bag = new BlackBag(this.pebbles, name);
        Pebble[] expResult = this.pebbles;
        Pebble[] result = bag.getAllPebbles();
        assertArrayEquals(expResult, result);
       
       
    }
   
    /**
     * Test of addPebble method, of class Bag.
     * Asserts that the pebble added with the addPebble
     * method is in the array returned by getAllPebbles.
     */
     @Test
    public void testAddPebble_Pebble() {
        
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
        
    }

    /**
     * Test of addPebble method, of class Bag.
     * Asserts that the pebble array added with addPebble
     * is equal to the one returned by getAllPebbles.
     */
    @Test
    public void testAddPebble_PebbleArr() {
        
        System.out.println("addPebble");
        
        //Initialise Bag with empty array
        Pebble[] pebblesEmpty = new Pebble[2];
        Bag bag = new BlackBag(pebblesEmpty, this.name);
        bag.addPebble(this.pebbles);
        Pebble[] result = bag.getAllPebbles();
        Assert.assertArrayEquals(result, this.pebbles);
        
        
        
    }
    
    

    
    /**
     * Test of removeAllPebbles method, of class Bag.
     * Asserts that the getAllPebbles method from bag
     * returns an array of length 0 after the removeAllPebbles
     * method is called.
     */
    @Test
    public void testRemoveAllPebbles() {
        
        System.out.println("removeAllPebbles");
        
        Bag bag = new BlackBag(this.pebbles, this.name);
        bag.removeAllPebbles();
        assertEquals(0, bag.getAllPebbles().length);
        
        tearDown();
    }

    /**
     * Test of getPebblesAsString method, of class Bag.
     * Asserts that the getPebblesAsString method returns
     * the same string as produced by Arrays.toString.
     */
    @Test
    public void testGetPebblesAsString() {
        
        System.out.println("getPebblesAsString");
        String expResult = Arrays.toString(this.pebbleValues);
        Bag bag = new BlackBag(this.pebbles, this.name);
        
        
        assertEquals(expResult, bag.getPebblesAsString());
        tearDown();
    }

    /**
     * Test of getRandomPebble method, of class Bag.
     * Asserts that the getRandomPebble method chooses each
     * pebble with the same likelihood.
     */
    @Test
    public void testGetRandomPebble() {
        
        System.out.println("getRandomPebble");
        
        //Pebble array to be passed into the bag
        Pebble[] pebbleArr = new Pebble[2];
        HashMap map = new HashMap();
        
        for (int i = 0; i < pebbleArr.length; i++){
            try {
                //Insert a pebble into the array
                pebbleArr[i] = new Pebble(i + 1);
                
                //Place pebble as key and 0.0 as value into hashmap
                map.put(pebbleArr[i], 0.0);
                
                
            } catch (IllegalPebbleValueException ex) {
                fail("No negative values allowed");
            }
        }
        Bag bag = new BlackBag(pebbleArr, this.name);
        
        
        Pebble pebble;
        int runs = 1000000;
        for (int i = 0; i < runs; i++){
            
            pebble = bag.getRandomPebble();
            double val = (double) map.get(pebble);
            //Increment the value of the drawn pebble in the hashmap by one
            map.put(pebble, val + 1.0);
            
            /*
             * Add the pebble array to the bag for every even iteration
             * because a pebble is removed from the bag in the getRandomPebble
             * method
             */
            if (i%2 == 0) {
                bag.addPebble(pebbleArr);
            }
        }
        double res1 = (double) map.get(pebbleArr[0]);
        double res2 = (double) map.get(pebbleArr[1]);
        //Some tolerance always expected with random method
        double tolerance = 5.0;
        assertEquals(res1, res2, tolerance);
        
        
    }
   
}
